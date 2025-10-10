package com.example.controller;

import com.example.entity.ApiResponse;
import com.example.entity.TestBooking;
import com.example.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;


@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    //book
    @PostMapping("/book-simple")
    public ApiResponse<String> bookSimple(@RequestParam Long centerId,
                                               @RequestParam String candidateName,
                                               @RequestParam String birthday,
                                               @RequestParam String plateNo
                                               ) {
        try {
            LocalDate dob = LocalDate.parse(birthday); // format YYYY-MM-DD
            bookingService.bookCenterOnly(candidateName, dob, plateNo, centerId);
            return ApiResponse.success("Booking successful");
        } catch (DateTimeParseException e) {
            return ApiResponse.fail("The birthday format is incorrect, it needs to be YYYY-MM-DD");
        } catch (IllegalStateException e) {
            return ApiResponse.fail(e.getMessage());
        } catch (Exception e) {
            return ApiResponse.fail("Server error, please try again later");
        }
    }

    // cancel
    @PostMapping("/cancel")
    public ApiResponse<String> cancel(@RequestParam Long bookingId) {
        try {
            boolean ok = bookingService.cancel(bookingId);
            return ok ? ApiResponse.success("Cancelled successfully")
                    : ApiResponse.fail("The booking was not found");
        } catch (IllegalStateException e) {
            return ApiResponse.fail(e.getMessage()); // Completed bookings cannot be cancelled
        } catch (Exception e) {
            return ApiResponse.fail("Server error, please try again later");
        }
    }


    // complete
    @PostMapping("/complete-pass")
    public ApiResponse<String> completePass(@RequestParam Long bookingId) {
        try {
            boolean ok = bookingService.completePass(bookingId);
            return ok ? ApiResponse.success("Booking completed: Road test passed")
                    : ApiResponse.fail("The booking was not found");
        } catch (IllegalStateException e) {
            return ApiResponse.fail(e.getMessage());
        } catch (Exception e) {
            return ApiResponse.fail("Server error, please try again later");
        }
    }

    // my book history
    @GetMapping("/my")
    public ApiResponse<List<TestBooking>> myAll(@RequestParam String candidateName,
                                                @RequestParam String birthday) {
        try {
            var dob = java.time.LocalDate.parse(birthday); // YYYY-MM-DD
            return ApiResponse.success("ok", bookingService.myAll(candidateName, dob));
        } catch (Exception e) {
            return ApiResponse.fail("Query failed, please check the name and birthday format");
        }
    }

    // pay-success
    @PostMapping("/pay-success")
    public ApiResponse<String> paySuccess(@RequestParam Long bookingId) {
        try {
            int n = bookingService.paySuccess(bookingId);
            if (n == 1) return ApiResponse.success("Payment successful");
            if (n == 0) return ApiResponse.success("Already paid, no need to repeat");
            return ApiResponse.fail("The booking was not found");
        } catch (IllegalStateException e) {
            return ApiResponse.fail(e.getMessage());   // "Please complete the road test before paying" etc.
        } catch (Exception e) {
            return ApiResponse.fail("Server error, please try again later");
        }
    }

}
