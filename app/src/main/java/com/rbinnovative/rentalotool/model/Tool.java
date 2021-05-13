package com.rbinnovative.rentalotool.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Tool implements Parcelable {
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

    protected Tool(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Tool> CREATOR = new Creator<Tool>() {
        @Override
        public Tool createFromParcel(Parcel in) {
            return new Tool(in);
        }

        @Override
        public Tool[] newArray(int size) {
            return new Tool[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(imageUrl);
    }
}
