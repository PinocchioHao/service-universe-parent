package com.example.controller;

import com.example.entity.ApiResponse;
import com.example.entity.ParkingRecord;
import com.example.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/start")
    public ApiResponse<ParkingRecord> start(@RequestParam String licensePlate) {
        return parkingService.startParking(licensePlate);
    }

    @PostMapping("/end")
    public ApiResponse<ParkingRecord> end(@RequestParam String licensePlate) {
        return parkingService.endParking(licensePlate);
    }

    @PostMapping("/pay")
    public ApiResponse<ParkingRecord> pay(@RequestParam String licensePlate) {
        return parkingService.payParkingFee(licensePlate);
    }

    @GetMapping("/history")
    public List<ParkingRecord> history(@RequestParam String licensePlate) {
        return parkingService.queryParkingHistory(licensePlate);
    }
}
