package com.rbinnovative.rentalotool.model;

import java.time.LocalDate;


public class Order {
    private String status;
    private String userId;
    private Integer toolId;
    private LocalDate startDate;
    private LocalDate endDate;


    public Order( String status, String userId, Integer toolId, LocalDate startDate, LocalDate endDate) {
        this.status = status;
        this.userId = userId;
        this.toolId = toolId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
