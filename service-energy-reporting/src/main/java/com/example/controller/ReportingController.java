package com.example.controller;

import com.example.entity.ApiResponse;
import com.example.entity.ReportingRecord;
import com.example.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reporting")
public class ReportingController {

    @Autowired
    private ReportingService service;

    @PostMapping
    public ApiResponse<ReportingRecord> createReport(@RequestBody ReportingRecord record) {
        return service.createReport(record);
    }

    @GetMapping("/{reportId}")
    public ApiResponse<ReportingRecord> getReport(@PathVariable String reportId) {
        return service.getReport(reportId);
    }

    @PutMapping("/{reportId}/status")
    public ApiResponse<ReportingRecord> updateStatus(@PathVariable String reportId, @RequestParam String status) {
        return service.updateStatus(reportId, status);
    }



}
