package com.example.entity;

public class SlotView {
    private Long id;
    private String centreId;
    private String slotTime;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCentreId() {
        return centreId;
    }

    public void setCentreId(String centreId) {
        this.centreId = centreId;
    }

    public String getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(String slotTime) {
        this.slotTime = slotTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
