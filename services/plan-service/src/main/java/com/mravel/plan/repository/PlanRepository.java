package com.mravel.plan.repository;

import com.mravel.plan.model.Plan;
import com.mravel.plan.model.PlanStatus;
import com.mravel.plan.model.Visibility;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlanRepository extends JpaRepository<Plan, Long> {
   Page<Plan> findByVisibility(Visibility visibility, Pageable pageable);

   List<Plan> findByAuthorId(Long authorId);

   @Query("""
             select p from Plan p
             where p.visibility = 'PUBLIC'
                or p.authorId = :viewerId
                or p.id in :memberPlanIds
             order by p.createdAt desc
         """)
   Page<Plan> findVisibleForUser(@Param("viewerId") Long viewerId,
         @Param("memberPlanIds") List<Long> memberPlanIds,
         Pageable pageable);

   @Query("""
         SELECT p FROM Plan p
         WHERE p.authorId = :ownerId
           AND (
                 p.visibility = 'PUBLIC'
              OR (p.visibility = 'FRIENDS' AND :isFriend = TRUE)
              OR (p.visibility = 'PRIVATE' AND p.id IN :memberPlanIds)
           )
         ORDER BY p.createdAt DESC
         """)
   Page<Plan> findAllPlansOfUserWithVisibility(
         @Param("ownerId") Long ownerId,
         @Param("isFriend") boolean isFriend,
         @Param("memberPlanIds") List<Long> memberPlanIds,
         Pageable pageable);

   Page<Plan> findByAuthorId(Long authorId, Pageable pageable);

   @Query("""
         SELECT p FROM Plan p
         WHERE (
             p.visibility = com.mravel.plan.model.Visibility.PUBLIC
          OR p.authorId = :viewerId
          OR p.id IN :memberPlanIds
          OR (p.visibility = com.mravel.plan.model.Visibility.FRIENDS AND p.authorId IN :friendIds)
         )
         AND p.id NOT IN :hiddenPlanIds
         AND (
             p.authorId NOT IN :blockedIds
          OR p.id IN :memberPlanIds
          OR p.authorId = :viewerId
         )
         """)
   Page<Plan> findFeedForUser(
         @Param("viewerId") Long viewerId,
         @Param("memberPlanIds") List<Long> memberPlanIds,
         @Param("friendIds") List<Long> friendIds,
         @Param("blockedIds") List<Long> blockedIds,
         @Param("hiddenPlanIds") List<Long> hiddenPlanIds,
         Pageable pageable);

   @Query("""
             select p from Plan p
             where
               (
                 p.visibility = com.mravel.plan.model.Visibility.PUBLIC
                 or (p.visibility = com.mravel.plan.model.Visibility.FRIENDS and p.authorId in :friendIds)
                 or (p.authorId = :viewerId)
                 or (p.id in :memberPlanIds)
               )
               and (
                 :q is null or :q = ''
                 or lower(p.title) like lower(concat('%', :q, '%'))
                 or lower(p.description) like lower(concat('%', :q, '%'))
               )
               and p.id not in :hiddenPlanIds
               and (
                 p.authorId not in :blockedIds
                 or p.id in :memberPlanIds
                 or p.authorId = :viewerId
               )
         """)
   Page<Plan> searchFeedForUser(
         @Param("viewerId") Long viewerId,
         @Param("memberPlanIds") List<Long> memberPlanIds,
         @Param("friendIds") List<Long> friendIds,
         @Param("blockedIds") List<Long> blockedIds,
         @Param("hiddenPlanIds") List<Long> hiddenPlanIds,
         @Param("q") String q,
         Pageable pageable);

   // ===== Analytics (admin) =====

   long countByCreatedAtAfter(java.time.Instant t);

   @Query("select coalesce(sum(p.views), 0) from Plan p")
   long sumAllViews();

   @Query("select p.visibility, count(p) from Plan p group by p.visibility")
   List<Object[]> groupByVisibility();

   @Query("select p.status, count(p) from Plan p group by p.status")
   List<Object[]> groupByStatus();

   @Query(value = "SELECT DATE(created_at) AS d, COUNT(*) AS c FROM plans " +
         "WHERE created_at >= :from GROUP BY DATE(created_at) ORDER BY d", nativeQuery = true)
   List<Object[]> newPlansByDay(@Param("from") java.time.Instant from);

   List<Plan> findTop10ByVisibilityOrderByViewsDesc(Visibility visibility);

   long countByAdminLockedTrue();

   // ===== Quản lý lịch trình (admin): tìm kiếm TOÀN BỘ plan, mọi visibility =====

   /**
    * Tìm kiếm plan cho admin với các bộ lọc tùy chọn (null = bỏ qua):
    * q (tiêu đề/mô tả), visibility, status, locked (đã gỡ?).
    */
   @Query("""
         select p from Plan p
         where (:q is null or :q = ''
                or lower(p.title) like lower(concat('%', :q, '%'))
                or lower(p.description) like lower(concat('%', :q, '%')))
           and (:visibility is null or p.visibility = :visibility)
           and (:status is null or p.status = :status)
           and (:locked is null or p.adminLocked = :locked)
         """)
   Page<Plan> adminSearch(
         @Param("q") String q,
         @Param("visibility") Visibility visibility,
         @Param("status") PlanStatus status,
         @Param("locked") Boolean locked,
         Pageable pageable);

   /** Tăng lượt xem nguyên tử (atomic), an toàn với truy cập đồng thời. */
   @Modifying
   @Query("update Plan p set p.views = coalesce(p.views, 0) + 1 where p.id = :id")
   void incrementViews(@Param("id") Long id);
}
