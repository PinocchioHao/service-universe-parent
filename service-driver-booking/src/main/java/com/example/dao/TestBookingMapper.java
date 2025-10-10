package com.example.dao;

import com.example.entity.TestBooking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestBookingMapper {

    int insertSimple(TestBooking booking);

    TestBooking findById(@Param("bookingId") Long bookingId);

    int cancelById(@Param("bookingId") Long bookingId);

    int completeWithResult(@Param("bookingId") Long bookingId,
                           @Param("result") String result); // PASS / FAIL

    List<TestBooking> findAllByPerson(@Param("candidateName") String candidateName,
                                      @Param("birthday") java.time.LocalDate birthday);


    // Mark as paid (only if status=COMPLETED and not currently PAID)
    int markPaid(@Param("bookingId") Long bookingId);

}
