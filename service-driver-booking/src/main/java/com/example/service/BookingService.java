package com.example.service;


import com.example.dao.BookingMapper;
import com.example.dao.SlotMapper;
import com.example.entity.ApiResponse;
import com.example.entity.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.UUID;

@Service
public class BookingService {
    private static final Logger log = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private SlotMapper slotMapper;

    public BookingService(SlotMapper slotMapper, BookingMapper bookingMapper) {
        this.slotMapper = slotMapper; this.bookingMapper = bookingMapper;
    }

    @Transactional
    public ApiResponse<Booking> saveBooking(Booking b) {
        try {
            //初始化 写入pending
            b.setBookingId(UUID.randomUUID().toString());
            b.setStatus("PENDING");
            bookingMapper.insertBooking(b);

            //占位
            int held = slotMapper.holdSlot(b.getCentreId(), b.getSlotTime());
            if (held == 0) {
                bookingMapper.updateStatus(b.getBookingId(), "FAILED");
                b.setStatus("FAILED");
                return ApiResponse.fail("No slot available");
            }

            //支付
            boolean paymentOk = true; // TODO: 这里支付逻辑暂时不写
            if (!paymentOk) {
                bookingMapper.updateStatus(b.getBookingId(), "FAILED");
                b.setStatus("FAILED");
                return ApiResponse.fail("Payment declined");
            }

            //确认
            int booked = slotMapper.bookSlot(b.getCentreId(), b.getSlotTime());
            if (booked == 0) {
                bookingMapper.updateStatus(b.getBookingId(), "FAILED");
                b.setStatus("FAILED");
                return ApiResponse.fail("Slot booking failed");
            }

            //成功
            bookingMapper.updateStatus(b.getBookingId(), "CONFIRMED");
            b.setStatus("CONFIRMED");
            return ApiResponse.success("Booking confirmed", b);
        } catch (Exception e) {
            //异常回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // 打日志，返回统一错误
            log.error("saveBooking error", e);
            return ApiResponse.fail("Server error: " + e.getMessage());
        }
    }

    /** 按 bookingId 查询一条预约 */
    public Booking findBookingById(String bookingId) {
        return bookingMapper.findByBookingId(bookingId);
    }
}