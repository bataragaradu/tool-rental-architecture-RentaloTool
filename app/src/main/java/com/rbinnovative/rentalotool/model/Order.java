package com.rbinnovative.rentalotool.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;


public class Order {
    @SerializedName("id")
    private Integer id;
    @SerializedName("status")
    private String status;
    @SerializedName("userId")
    private String userId;
    @SerializedName("toolId")
    private Integer toolId;
    @SerializedName("startDate")
    private LocalDate startDate;
    @SerializedName("endDate")
    private LocalDate endDate;


    public Order( String status, String userId, Integer toolId, LocalDate startDate, LocalDate endDate) {
        this.status = status;
        this.userId = userId;
        this.toolId = toolId;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public Order( Integer id,String status, String userId, Integer toolId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.status = status;
        this.userId = userId;
        this.toolId = toolId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
