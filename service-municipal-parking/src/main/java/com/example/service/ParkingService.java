package com.example.service;

import com.example.dao.ParkingRecordMapper;
import com.example.entity.ApiResponse;
import com.example.entity.ParkingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return ApiResponse.fail("车辆已在停车中");
        }

        ParkingRecord record = new ParkingRecord();
        record.setLicensePlate(licensePlate);
        record.setStartTime(LocalDateTime.now());
        record.setFee(0.0);
        record.setPaid(false);
        mapper.insert(record);

        return ApiResponse.success("停车开始: " + licensePlate, record);
    }

    public ApiResponse<ParkingRecord> endParking(String licensePlate) {
        ParkingRecord record = mapper.selectOngoingByLicense(licensePlate);
        if (record == null) {
            return ApiResponse.fail("车辆未在停车中");
        }

        record.setEndTime(LocalDateTime.now());
        long minutes = Duration.between(record.getStartTime(), record.getEndTime()).toMinutes();
        record.setFee(minutes * 0.1); // 每分钟0.1刀 ≈ 每小时6刀
        mapper.update(record);

        return ApiResponse.success(
                "停车结束: " + licensePlate + ", 时长: " + minutes + " 分钟, 停车费: " + record.getFee() + " 澳元",
                record
        );
    }

    public ApiResponse<ParkingRecord> payParkingFee(String licensePlate) {
        List<ParkingRecord> history = mapper.selectHistoryByLicense(licensePlate);
        if (history.isEmpty()) {
            return ApiResponse.fail("车辆未停车");
        }

        ParkingRecord record = history.get(0);
        if (record.getEndTime() == null) {
            return ApiResponse.fail("停车尚未结束，无法支付");
        }
        if (Boolean.TRUE.equals(record.getPaid())) {
            return ApiResponse.fail("停车费用已支付");
        }

        record.setPaid(true);
        mapper.update(record);

        return ApiResponse.success("支付成功: " + record.getFee() + " 元", record);
    }

    public List<ParkingRecord> queryParkingHistory(String licensePlate) {
        return mapper.selectHistoryByLicense(licensePlate);
    }
}