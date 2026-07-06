const fs = require('fs');
const path = require('path');

const SRC = 'e:/TLCN/Mravel_Backend/services/review-service/src/main/resources/db/mravel_catalog.hotels.json';
const OUT_DIR = 'e:/TLCN/Mravel_Backend/services/review-service/src/main/resources/db/booking';

const USERS = [5, 7, 9, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20];

// Ten/lien he gia lap co dinh theo user_id de dong nhat qua cac booking.
const CONTACT = {
  5: { name: 'Nguyễn Minh Như', phone: '0901234501' },
  7: { name: 'Lý Ngọc Anh Thư', phone: '0901234507' },
  9: { name: 'Trần Gia Bảo', phone: '0901234509' },
  10: { name: 'Phạm Thu Hà', phone: '0901234510' },
  11: { name: 'Đặng Quốc Huy', phone: '0901234511' },
  12: { name: 'Vũ Thị Mai', phone: '0901234512' },
  14: { name: 'Hoàng Anh Tuấn', phone: '0901234514' },
  15: { name: 'Bùi Thanh Trúc', phone: '0901234515' },
  16: { name: 'Đỗ Văn Kiên', phone: '0901234516' },
  17: { name: 'Ngô Thị Lan', phone: '0901234517' },
  18: { name: 'Lê Đức Minh', phone: '0901234518' },
  19: { name: 'Phan Thị Ngọc', phone: '0901234519' },
  20: { name: 'Trịnh Văn Phát', phone: '0901234520' },
};

const TODAY = new Date('2026-07-07T00:00:00Z');

// PRNG seeded deterministic (mulberry32) - de ket qua on dinh, review lai duoc.
function mulberry32(seed) {
  return function () {
    seed |= 0;
    seed = (seed + 0x6d2b79f5) | 0;
    let t = Math.imul(seed ^ (seed >>> 15), 1 | seed);
    t = (t + Math.imul(t ^ (t >>> 7), 61 | t)) ^ t;
    return ((t ^ (t >>> 14)) >>> 0) / 4294967296;
  };
}

function addDays(date, days) {
  const d = new Date(date);
  d.setUTCDate(d.getUTCDate() + days);
  return d;
}

function fmtDate(d) {
  return d.toISOString().slice(0, 10);
}

function fmtDateTime(d) {
  return d.toISOString().slice(0, 19).replace('T', ' ');
}

function sqlEscape(s) {
  return String(s).replace(/'/g, "''");
}

function randHex(len, rnd) {
  const chars = '0123456789ABCDEF';
  let s = '';
  for (let i = 0; i < len; i++) s += chars[Math.floor(rnd() * chars.length)];
  return s;
}

function reviewCountFor(idx) {
  return 5 + (idx % 4); // 5,6,7,8,5,6,...
}

const data = JSON.parse(fs.readFileSync(SRC, 'utf8'));

const groups = {};
data.forEach((h) => {
  const key = h.destinationSlug;
  if (!groups[key]) groups[key] = [];
  groups[key].push(h);
});

const usedCodes = new Set();
function genCode(rnd) {
  let code;
  do {
    code = `BK-${randHex(8, rnd)}`;
  } while (usedCodes.has(code));
  usedCodes.add(code);
  return code;
}

let globalHotelIdx = 0;

Object.entries(groups).forEach(([destSlug, hotels]) => {
  const lines = [];
  lines.push(`-- Seed demo bookings HOTEL cho khu vuc: ${destSlug}`);
  lines.push('-- 5-8 booking/hotel, ngay check-in rai trong 1-6 thang qua, thanh toan FULL qua MOMO_WALLET');
  lines.push('-- Status: da so CONFIRMED+SUCCESS, mot so CANCELLED_BY_GUEST+REFUNDED/FAILED');
  lines.push('USE mravel_booking;');
  lines.push('SET NAMES utf8mb4;');
  lines.push('');

  hotels.forEach((hotel) => {
    const rnd = mulberry32(globalHotelIdx * 1000 + 7);
    const name = hotel.name.vi;
    const slug = hotel.slug;
    const hotelId = hotel._id.$oid;
    const roomTypes = (hotel.roomTypes || []).filter((rt) => rt.ratePlans && rt.ratePlans.length);
    const count = reviewCountFor(globalHotelIdx);

    lines.push(`-- ==== ${name} (${hotelId}) | ${count} bookings ====`);

    if (roomTypes.length === 0) {
      lines.push(`-- (bo qua: hotel khong co roomTypes/ratePlans hop le)`);
      lines.push('');
      globalHotelIdx += 1;
      return;
    }

    const reviewers = [];
    for (let i = 0; i < count; i++) {
      reviewers.push(USERS[(globalHotelIdx + i) % USERS.length]);
    }

    reviewers.forEach((userId, bIdx) => {
      const contact = CONTACT[userId];
      const roomType = roomTypes[(globalHotelIdx + bIdx) % roomTypes.length];
      const ratePlan = roomType.ratePlans[(globalHotelIdx + bIdx * 2) % roomType.ratePlans.length];
      const pricePerNight = Number(ratePlan.pricePerNight);
      const nights = 1 + Math.floor(rnd() * 4); // 1-4
      const quantity = rnd() < 0.8 ? 1 : 2;
      const totalAmount = pricePerNight * nights * quantity;

      const checkInOffsetDays = 30 + Math.floor(rnd() * 150); // 30-180 ngay truoc "hom nay"
      const checkIn = addDays(TODAY, -checkInOffsetDays);
      const checkOut = addDays(checkIn, nights);
      const bookedBeforeDays = 3 + Math.floor(rnd() * 27); // dat truoc 3-30 ngay
      const createdAt = addDays(checkIn, -bookedBeforeDays);

      const isCancelled = rnd() < 0.18; // ~18% huy
      const code = genCode(rnd);

      let status, paymentStatus, paidAt, cancelledAt, cancelReason, amountPaid, payStatusPayment, refundedAmount, refundedAt, providerTxnId;

      if (!isCancelled) {
        status = 'CONFIRMED';
        paymentStatus = 'SUCCESS';
        paidAt = addDays(createdAt, 0);
        paidAt.setUTCHours(paidAt.getUTCHours() + 1 + Math.floor(rnd() * 5));
        cancelledAt = null;
        cancelReason = null;
        amountPaid = totalAmount;
        payStatusPayment = 'SUCCESS';
        refundedAmount = null;
        refundedAt = null;
        providerTxnId = `MOMO${randHex(10, rnd)}`;
      } else {
        const refunded = rnd() < 0.5;
        status = 'CANCELLED_BY_GUEST';
        cancelledAt = new Date(createdAt);
        cancelledAt.setUTCHours(cancelledAt.getUTCHours() + 1 + Math.floor(rnd() * 24));
        cancelReason = refunded
          ? 'Khách đổi lịch trình, hủy trước ngày nhận phòng.'
          : 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.';
        if (refunded) {
          paymentStatus = 'REFUNDED';
          paidAt = new Date(createdAt);
          paidAt.setUTCHours(paidAt.getUTCHours() + 1 + Math.floor(rnd() * 5));
          amountPaid = totalAmount;
          payStatusPayment = 'REFUNDED';
          refundedAmount = totalAmount;
          refundedAt = cancelledAt;
          providerTxnId = `MOMO${randHex(10, rnd)}`;
        } else {
          paymentStatus = 'FAILED';
          paidAt = null;
          amountPaid = null;
          payStatusPayment = 'FAILED';
          refundedAmount = null;
          refundedAt = null;
          providerTxnId = null;
        }
      }

      const updatedAt = cancelledAt || paidAt || createdAt;

      lines.push(`INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)`);
      lines.push(`VALUES ('${code}', ${userId}, '${sqlEscape(contact.name)}', '${contact.phone}', NULL, 'FULL', ${totalAmount}, NULL, ${totalAmount}, ${amountPaid ?? 'NULL'}, 'VND', '${status}', '${paymentStatus}', ${paidAt ? `'${fmtDateTime(paidAt)}'` : 'NULL'}, ${cancelledAt ? `'${fmtDateTime(cancelledAt)}'` : 'NULL'}, ${cancelReason ? `'${sqlEscape(cancelReason)}'` : 'NULL'}, 1, 'MOMO_WALLET', '${fmtDateTime(createdAt)}', '${fmtDateTime(updatedAt)}');`);
      lines.push('SET @bid := LAST_INSERT_ID();');
      lines.push(`INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)`);
      lines.push(`VALUES (@bid, '${hotelId}', '${slug}', '${sqlEscape(name)}', '${fmtDate(checkIn)}', '${fmtDate(checkOut)}', ${nights}, ${quantity});`);
      lines.push(`INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)`);
      lines.push(`VALUES (@bid, '${roomType._id}', '${sqlEscape(roomType.name.vi)}', '${ratePlan._id}', '${sqlEscape(ratePlan.name.vi)}', ${quantity}, ${nights}, ${pricePerNight}, ${totalAmount}, '${fmtDateTime(createdAt)}', '${fmtDateTime(updatedAt)}');`);
      lines.push(`INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)`);
      lines.push(`VALUES (@bid, 'MOMO_WALLET', '${payStatusPayment}', ${totalAmount}, 'VND', 'MOMO', '${code}-${randHex(6, rnd)}', ${providerTxnId ? `'${providerTxnId}'` : 'NULL'}, ${paidAt ? `'${fmtDateTime(paidAt)}'` : 'NULL'}, ${refundedAmount ?? 'NULL'}, ${refundedAt ? `'${fmtDateTime(refundedAt)}'` : 'NULL'}, '${fmtDateTime(createdAt)}', '${fmtDateTime(updatedAt)}');`);
      lines.push('');
    });

    globalHotelIdx += 1;
  });

  const outPath = path.join(OUT_DIR, `seed_bookings_hotel_${destSlug}.sql`);
  fs.writeFileSync(outPath, lines.join('\n'), 'utf8');
  console.log('wrote', outPath, hotels.length, 'hotels');
});
