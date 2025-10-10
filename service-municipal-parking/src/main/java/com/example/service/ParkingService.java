package com.example.service;

import com.example.dao.ParkingRecordMapper;
import com.example.entity.ApiResponse;
import com.example.entity.ParkingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {

    @Autowired
    private ParkingRecordMapper mapper;

    public ApiResponse<ParkingRecord> startParking(String licensePlate) {
        ParkingRecord ongoing = mapper.selectOngoingByLicense(licensePlate);
        if (ongoing != null) {
            return ApiResponse.fail("Vehicle is already parked");
        }

        ParkingRecord record = new ParkingRecord();
        record.setLicensePlate(licensePlate);
        record.setStartTime(LocalDateTime.now());
        record.setFee(0.0);
        record.setPaid(false);
        mapper.insert(record);

        return ApiResponse.success("Parking started: " + licensePlate, record);
    }


    public ApiResponse<ParkingRecord> endParking(String licensePlate) {
        ParkingRecord record = mapper.selectOngoingByLicense(licensePlate);
        if (record == null) {
            return ApiResponse.fail("Vehicle is not currently parked");
        }

        record.setEndTime(LocalDateTime.now());
        long minutes = Duration.between(record.getStartTime(), record.getEndTime()).toMinutes();

        // Calculate fee and round to 2 decimal places
        BigDecimal fee = BigDecimal.valueOf(minutes * 0.1).setScale(2, RoundingMode.HALF_UP); // $0.1 per minute ≈ $6 per hour
        record.setFee(fee.doubleValue());

        mapper.update(record);

        return ApiResponse.success(
                "Parking ended: " + licensePlate + ", Duration: " + minutes + " minutes, Fee: " + fee + " AUD",
                record
        );
    }


    public ApiResponse<ParkingRecord> payParkingFee(String licensePlate) {
        List<ParkingRecord> history = mapper.selectHistoryByLicense(licensePlate);
        if (history.isEmpty()) {
            return ApiResponse.fail("No parking record found for this vehicle");
        }

        ParkingRecord record = history.get(0);
        if (record.getEndTime() == null) {
            return ApiResponse.fail("Parking has not ended yet");
        }
        if (Boolean.TRUE.equals(record.getPaid())) {
            return ApiResponse.fail("Parking fee has already been paid");
        }

        record.setPaid(true);
        mapper.update(record);

        return ApiResponse.success("Payment successful: " + record.getFee() + " AUD", record);
    }

    public List<ParkingRecord> queryParkingHistory(String licensePlate) {
        return mapper.selectHistoryByLicense(licensePlate);
    }
}
