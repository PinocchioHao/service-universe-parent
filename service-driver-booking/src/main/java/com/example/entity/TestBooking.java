package com.example.entity;

import com.example.entity.enums.BookingStatus;
import com.example.entity.enums.PaymentStatus;
import com.example.entity.enums.TestResult;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestBooking {
    private Long id;                 // booking ID
    private Long centerId;           // center ID

    private String centerName;

    // 考生信息
    private String     candidateName;
    private LocalDate birthday;      // YYYY-MM-DD
    private String     plateNo;

    // 业务字段
    private String testType;       //  DRIVE_TEST etc.
    private String status;         // PENDING/CONFIRMED/CANCELED/COMPLETED
    private String paymentStatus;  // UNPAID/PAID/REFUNDED
    private String result;         // INCOMPLETE/PASS/FAIL
    private Long   feeCents;       // cents

    // 审计
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCenterId() { return centerId; }
    public void setCenterId(Long centerId) { this.centerId = centerId; }

    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public String getPlateNo() { return plateNo; }
    public void setPlateNo(String plateNo) { this.plateNo = plateNo; }

    public String getTestType() { return testType; }
    public void setTestType(String testType) { this.testType = testType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public Long getFeeCents() { return feeCents; }
    public void setFeeCents(Long feeCents) { this.feeCents = feeCents; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }
}
