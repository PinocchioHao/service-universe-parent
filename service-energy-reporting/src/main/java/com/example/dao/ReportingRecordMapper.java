package com.example.dao;

import com.example.entity.ReportingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper interface for ReportingRecord.
 * MyBatis will generate implementations based on XML or annotations.
 */
@Mapper
public interface ReportingRecordMapper {

    /**
     * Insert a new outage report record into the database.
     * @param record ReportingRecord entity containing report details
     */
    void insert(ReportingRecord record);

    /**
     * Find a report by its unique reportId.
     * @param reportId The unique report ID string
     * @return The ReportingRecord object if found, otherwise null
     */
    ReportingRecord findByReportId(String reportId);

    /**
     * Update the status of a specific outage report.
     * @param reportId The unique report ID of the report to update
     * @param status   The new status (e.g., RECEIVED, IN_PROGRESS, RESTORED)
     */
    void updateStatus(String reportId, String status);

    /**
     * Retrieve all outage reports from the database.
     * @return List of all ReportingRecord objects
     */
    List<ReportingRecord> findAll();
}
