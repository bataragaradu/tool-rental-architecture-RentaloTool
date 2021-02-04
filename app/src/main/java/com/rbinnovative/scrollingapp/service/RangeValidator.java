package com.rbinnovative.scrollingapp.service;

import android.os.Parcel;

import com.google.android.material.datepicker.CalendarConstraints;

import java.util.List;

public class RangeValidator implements CalendarConstraints.DateValidator {

    private final List<Long> availableDates;

    public RangeValidator(List<Long> availableDates) {
        this.availableDates = availableDates;
    }

    @Override
    public boolean isValid(long date) {
        for (Long availableDate:
             availableDates) {
            if(availableDate.equals(date)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
