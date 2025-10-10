package com.example.service;


import com.example.dao.TestBookingMapper;
import com.example.dao.TestCenterMapper;
import com.example.entity.TestBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private TestBookingMapper testBookingMapper;

    @Autowired
    private TestCenterMapper centerMapper;

    /** book */
    @Transactional
    public void bookCenterOnly(String candidateName,
                                      LocalDate birthday,
                                      String plateNo,
                                      Long centerId) {
        int ok = centerMapper.occupyIfFree(centerId);
        if (ok != 1) {
            throw new IllegalStateException("The examination room is occupied and cannot be booked.");
        }

        TestBooking b = new TestBooking();
        b.setCenterId(centerId);
        b.setCandidateName(candidateName);
        b.setBirthday(birthday);
        b.setPlateNo(plateNo);

        b.setTestType("Driving Test");
        b.setStatus("CONFIRMED");
        b.setPaymentStatus("UNPAID");
        b.setResult("INCOMPLETE");
        b.setFeeCents(6000L);

        testBookingMapper.insertSimple(b);
    }

    /** cancel */
    @Transactional
    public boolean cancel(Long bookingId){
        TestBooking bk = testBookingMapper.findById(bookingId);
        if (bk == null) {
            return false; // not found
        }
        // If completed, cancellation is not allowed; other states allow
        if ("COMPLETED".equalsIgnoreCase(bk.getStatus())) {
            throw new IllegalStateException("Completed bookings cannot be cancelled");
        }
        // Mark as Canceled
        int n1 = testBookingMapper.cancelById(bookingId);
        // Release the exam room (safe even if released repeatedly, only affects where status=1)
        centerMapper.release(bk.getCenterId());
        return n1 == 1;
    }

    /** complete */
    @Transactional
    public boolean completePass(Long bookingId){
        TestBooking bk = testBookingMapper.findById(bookingId);
        if (bk == null) return false;

        // canceled/completed items are no longer processed (avoiding duplicate releases)
        if ("CANCELED".equalsIgnoreCase(bk.getStatus())
                || "COMPLETED".equalsIgnoreCase(bk.getStatus())) {
            throw new IllegalStateException("The booking has been terminated or completed");
        }

        int n1 = testBookingMapper.completeWithResult(bookingId, "PASS");
        centerMapper.release(bk.getCenterId()); // Release the examination room (status=1->0)
        return n1 == 1;
    }

    /** my book history */
    public List<TestBooking> myAll(String candidateName, LocalDate birthday){
        return testBookingMapper.findAllByPerson(candidateName, birthday);
    }

    @Transactional
    public int paySuccess(Long bookingId) {
        TestBooking bk = testBookingMapper.findById(bookingId);
        if (bk == null) return -1; // not found

        if ("CANCELED".equalsIgnoreCase(bk.getStatus())) {
            throw new IllegalStateException("Cancelled bookings cannot be paid for");
        }
        if (!"COMPLETED".equalsIgnoreCase(bk.getStatus())) {
            throw new IllegalStateException("Please complete the road test before paying");
        }
        if ("PAID".equalsIgnoreCase(bk.getPaymentStatus())) {
            return 0;
        }
        return testBookingMapper.markPaid(bookingId); // 1=success
    }


}
