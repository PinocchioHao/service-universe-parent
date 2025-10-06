package com.example.controller;

import com.example.entity.ApiResponse;
import com.example.entity.Booking;
import com.example.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/saveBooking")
    public ApiResponse<Booking> saveBooking(@RequestBody Booking booking) {
        return bookingService.saveBooking(booking);
    }

    @GetMapping("/{bookingId}")
    public ApiResponse<Booking> getBookingById(@PathVariable("bookingId") String bookingId) {
        Booking found = bookingService.findBookingById(bookingId);
        if (found == null) {
            return ApiResponse.fail("Booking not found: " + bookingId);
        }
        return ApiResponse.success("OK", found);
    }

}
