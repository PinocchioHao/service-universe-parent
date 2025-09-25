package com.example.entity;

import java.time.LocalDateTime;

public class ParkingRecord {
    private Long id;
    private String licensePlate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double fee;
    private Boolean paid;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public Double getFee() { return fee; }
    public void setFee(Double fee) { this.fee = fee; }

    public Boolean getPaid() { return paid; }
    public void setPaid(Boolean paid) { this.paid = paid; }
}
