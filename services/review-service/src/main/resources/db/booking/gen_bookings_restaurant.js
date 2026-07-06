const fs = require('fs');
const path = require('path');

const SRC = 'e:/TLCN/Mravel_Backend/services/review-service/src/main/resources/db/mravel_catalog.restaurants.json';
const OUT_DIR = 'e:/TLCN/Mravel_Backend/services/review-service/src/main/resources/db/booking';

const USERS = [5, 7, 9, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20];

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

function fmtTime(h, m) {
  return `${String(h).padStart(2, '0')}:${String(m).padStart(2, '0')}:00`;
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

function bookingCountFor(idx) {
  return 5 + (idx % 4); // 5,6,7,8,5,6,...
}

const DINING_SLOTS = [
  [11, 0], [11, 30], [12, 0], [12, 30], [18, 0], [18, 30], [19, 0], [19, 30], [20, 0],
];

const data = JSON.parse(fs.readFileSync(SRC, 'utf8'));

const groups = {};
data.forEach((r) => {
  const key = r.destinationSlug;
  if (!groups[key]) groups[key] = [];
  groups[key].push(r);
});

const usedCodes = new Set();
function genCode(rnd) {
  let code;
  do {
    code = `RB-${randHex(10, rnd)}`;
  } while (usedCodes.has(code));
  usedCodes.add(code);
  return code;
}

let globalIdx = 0;

Object.entries(groups).forEach(([destSlug, restaurants]) => {
  const lines = [];
  lines.push(`-- Seed demo bookings RESTAURANT cho khu vuc: ${destSlug}`);
  lines.push('-- 5-8 booking/restaurant, ngay dat ban rai trong 1-6 thang qua, thanh toan (dat coc) qua MOMO_WALLET');
  lines.push('-- payOption luon DEPOSIT (dung flow that: totalAmount == depositAmount == amountPayable)');
  lines.push('-- Status: da so CONFIRMED+SUCCESS, mot so CANCELLED_BY_GUEST+REFUNDED/FAILED');
  lines.push('USE mravel_booking;');
  lines.push('SET NAMES utf8mb4;');
  lines.push('');

  restaurants.forEach((restaurant) => {
    const rnd = mulberry32(globalIdx * 1000 + 13);
    const name = restaurant.name.vi;
    const slug = restaurant.slug;
    const restaurantId = restaurant._id.$oid;
    const tableTypes = (restaurant.tableTypes || []).filter((t) => t.depositPrice);
    const count = bookingCountFor(globalIdx);

    lines.push(`-- ==== ${name} (${restaurantId}) | ${count} bookings ====`);

    if (tableTypes.length === 0) {
      lines.push(`-- (bo qua: restaurant khong co tableTypes hop le)`);
      lines.push('');
      globalIdx += 1;
      return;
    }

    const bookers = [];
    for (let i = 0; i < count; i++) {
      bookers.push(USERS[(globalIdx + i) % USERS.length]);
    }

    bookers.forEach((userId, bIdx) => {
      const contact = CONTACT[userId];
      const tableType = tableTypes[(globalIdx + bIdx) % tableTypes.length];
      const depositPrice = Number(tableType.depositPrice);
      const quantity = rnd() < 0.85 ? 1 : 2;
      const people = tableType.minPeople + Math.floor(rnd() * Math.max(1, tableType.maxPeople - tableType.minPeople + 1));
      const durations = tableType.allowedDurationsMinutes && tableType.allowedDurationsMinutes.length
        ? tableType.allowedDurationsMinutes
        : [tableType.defaultDurationMinutes || 90];
      const durationMinutes = durations[(globalIdx + bIdx) % durations.length];
      const totalDeposit = depositPrice * quantity;

      const slot = DINING_SLOTS[(globalIdx + bIdx) % DINING_SLOTS.length];
      const resOffsetDays = 30 + Math.floor(rnd() * 150); // 30-180 ngay truoc "hom nay"
      const reservationDate = addDays(TODAY, -resOffsetDays);
      const bookedBeforeDays = 1 + Math.floor(rnd() * 13); // dat truoc 1-14 ngay (nha hang dat gan ngay hon hotel)
      const createdAt = addDays(reservationDate, -bookedBeforeDays);

      const isCancelled = rnd() < 0.15; // ~15% huy
      const code = genCode(rnd);

      let status, paymentStatus, paidAt, cancelledAt, cancelReason, amountPaid, payStatusPayment, refundedAmount, refundedAt, providerTxnId;

      if (!isCancelled) {
        status = 'CONFIRMED';
        paymentStatus = 'SUCCESS';
        paidAt = new Date(createdAt);
        paidAt.setUTCHours(paidAt.getUTCHours() + 1 + Math.floor(rnd() * 4));
        cancelledAt = null;
        cancelReason = null;
        amountPaid = totalDeposit;
        payStatusPayment = 'SUCCESS';
        refundedAmount = null;
        refundedAt = null;
        providerTxnId = `MOMO${randHex(10, rnd)}`;
      } else {
        const refunded = rnd() < 0.5;
        status = 'CANCELLED_BY_GUEST';
        cancelledAt = new Date(createdAt);
        cancelledAt.setUTCHours(cancelledAt.getUTCHours() + 1 + Math.floor(rnd() * 20));
        cancelReason = refunded
          ? 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.'
          : 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.';
        if (refunded) {
          paymentStatus = 'REFUNDED';
          paidAt = new Date(createdAt);
          paidAt.setUTCHours(paidAt.getUTCHours() + 1 + Math.floor(rnd() * 4));
          amountPaid = totalDeposit;
          payStatusPayment = 'REFUNDED';
          refundedAmount = totalDeposit;
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
      const reservationTime = fmtTime(slot[0], slot[1]);

      lines.push(`INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)`);
      lines.push(`VALUES ('${code}', ${userId}, '${sqlEscape(contact.name)}', '${contact.phone}', NULL, 'DEPOSIT', ${totalDeposit}, ${totalDeposit}, ${totalDeposit}, ${amountPaid ?? 'NULL'}, 'VND', '${status}', '${paymentStatus}', ${paidAt ? `'${fmtDateTime(paidAt)}'` : 'NULL'}, ${cancelledAt ? `'${fmtDateTime(cancelledAt)}'` : 'NULL'}, ${cancelReason ? `'${sqlEscape(cancelReason)}'` : 'NULL'}, 1, 'MOMO_WALLET', '${fmtDateTime(createdAt)}', '${fmtDateTime(updatedAt)}');`);
      lines.push('SET @bid := LAST_INSERT_ID();');
      lines.push(`INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)`);
      lines.push(`VALUES (@bid, '${restaurantId}', '${slug}', '${sqlEscape(name)}', '${fmtDate(reservationDate)}', '${reservationTime}', ${people}, ${durationMinutes}, ${quantity});`);
      lines.push(`INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)`);
      lines.push(`VALUES (@bid, '${tableType._id}', '${sqlEscape(tableType.name.vi)}', ${quantity}, ${tableType.seats}, ${depositPrice}, ${totalDeposit}, '${fmtDateTime(createdAt)}', '${fmtDateTime(updatedAt)}');`);
      lines.push(`INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)`);
      lines.push(`VALUES (@bid, 'MOMO_WALLET', '${payStatusPayment}', ${totalDeposit}, 'VND', 'MOMO', '${code}-${randHex(6, rnd)}', ${providerTxnId ? `'${providerTxnId}'` : 'NULL'}, ${paidAt ? `'${fmtDateTime(paidAt)}'` : 'NULL'}, ${refundedAmount ?? 'NULL'}, ${refundedAt ? `'${fmtDateTime(refundedAt)}'` : 'NULL'}, '${fmtDateTime(createdAt)}', '${fmtDateTime(updatedAt)}');`);
      lines.push('');
    });

    globalIdx += 1;
  });

  const outPath = path.join(OUT_DIR, `seed_bookings_restaurant_${destSlug}.sql`);
  fs.writeFileSync(outPath, lines.join('\n'), 'utf8');
  console.log('wrote', outPath, restaurants.length, 'restaurants');
});
