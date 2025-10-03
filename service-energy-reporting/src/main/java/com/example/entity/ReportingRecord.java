package com.example.entity;

import java.time.LocalDateTime;

public class ReportingRecord {
    private Long id;                  // Primary key ID
    private String userId;            // User ID (optional for guests)
    private String location;          // Outage location
    private LocalDateTime timeReported; // Time when the outage was reported
    private String description;       // Outage description
    private String contactDetails;    // Contact information (phone/email)
    private String reportId;          // Unique outage report ID
    private String status;            // Current status (RECEIVED, IN_PROGRESS, RESTORED)

    // Getter 和 Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDateTime getTimeReported() { return timeReported; }
    public void setTimeReported(LocalDateTime timeReported) { this.timeReported = timeReported; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getContactDetails() { return contactDetails; }
    public void setContactDetails(String contactDetails) { this.contactDetails = contactDetails; }

    public String getReportId() { return reportId; }
    public void setReportId(String reportId) { this.reportId = reportId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
