package com.example.dao;

import com.example.entity.Booking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookingMapper {
    int insertBooking(@Param("b") Booking b);
    int updateStatus(@Param("bookingId") String bookingId, @Param("status") String status);
    Booking findByBookingId(@Param("bookingId") String bookingId);

}
