package com.example.service;

import com.example.dao.ReportingRecordMapper;
import com.example.entity.ApiResponse;
import com.example.entity.ReportingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class ReportingService {

    @Autowired
    private ReportingRecordMapper mapper;

    public ApiResponse<ReportingRecord> createReport(ReportingRecord record) {
        // Generate unique report ID
        record.setReportId(UUID.randomUUID().toString());
        record.setTimeReported(LocalDateTime.now());
        record.setStatus("RECEIVED");

        try {
            mapper.insert(record);
            return ApiResponse.success("Outage report created successfully", record);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to create outage report: " + e.getMessage());
        }
    }

    public ApiResponse<ReportingRecord> getReport(String reportId) {
        try {
            ReportingRecord record = mapper.findByReportId(reportId);
            if (record != null) {
                return ApiResponse.success("Outage report retrieved successfully", record);
            } else {
                return ApiResponse.fail("No outage report found for reportId: " + reportId);
            }
        } catch (Exception e) {
            return ApiResponse.fail("Error retrieving outage report: " + e.getMessage());
        }
    }

    public ApiResponse<ReportingRecord> updateStatus(String reportId, String status) {
        try {
            mapper.updateStatus(reportId, status);
            return ApiResponse.success("Outage report status updated successfully", null);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to update outage report status: " + e.getMessage());
        }
    }

}