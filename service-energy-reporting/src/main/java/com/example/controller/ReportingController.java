package com.example.controller;

import com.example.entity.ApiResponse;
import com.example.entity.ReportingRecord;
import com.example.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing outage reports.
 * Provides endpoints to create a report, retrieve a report, and update its status.
 */
@RestController
@RequestMapping("/reporting") 
public class ReportingController {

    @Autowired
    private ReportingService service; 

    /**
     * Create a new outage report.
     * Example: POST http://localhost:8082/reporting
     *
     * @param record The outage report details in JSON format (request body)
     * {
        "userId": "user789",
        "location": "Brisbane",
        "timeReported": "2025-10-03T18:10:00",
        "description": "Power outage caused by heavy storm",
        "contactDetails": "user789@example.com",
        "reportId": "rep-003",
        "status": "RESTORED"
        }
     * @return ApiResponse containing the created report details or error message
     */
    @PostMapping
    public ApiResponse<ReportingRecord> createReport(@RequestBody ReportingRecord record) {
        return service.createReport(record);
    }

    /**
     * Retrieve a specific outage report by its reportId.
     * Example: GET http://localhost:8082/reporting/rep-001
     *
     * @param reportId Unique identifier of the outage report
     * @return ApiResponse containing the found report or an error message if not found
     */
    @GetMapping("/{reportId}")
    public ApiResponse<ReportingRecord> getReport(@PathVariable String reportId) {
        return service.getReport(reportId);
    }

    /**
     * Update the status of an existing outage report.
     * Example: PUT http://localhost:8082/reporting/rep-001/status?status=IN_PROGRESS
     *
     * @param reportId Unique identifier of the outage report
     * @param status   New status value (e.g., RECEIVED, IN_PROGRESS, RESTORED)
     * @return ApiResponse indicating success or failure of the update operation
     */
    @PutMapping("/{reportId}/status")
    public ApiResponse<ReportingRecord> updateStatus(@PathVariable String reportId, @RequestParam String status) {
        return service.updateStatus(reportId, status);
    }

}
