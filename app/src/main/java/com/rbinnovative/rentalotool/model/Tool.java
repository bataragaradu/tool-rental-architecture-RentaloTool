package com.rbinnovative.rentalotool.model;

import com.google.gson.annotations.SerializedName;

public class Tool {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("imageUrl")
    private String imageUrl;

    public Tool() {
    }

    public Tool(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "id='" + id + '\'' +
                ", name=" + name +
                '}';
    }
}
