//package com.rbinnovative.scrollingapp.service;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import androidx.annotation.NonNull;
//
//import com.google.android.material.datepicker.CalendarConstraints;
//import com.google.android.material.datepicker.DateValidatorPointBackward;
//
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.List;
//
//public class RangeValidator implements CalendarConstraints.DateValidator {
//
//    private final List<LocalDate> availableDates;
//
//    public RangeValidator(List<LocalDate> availableDates) {
//        this.availableDates = availableDates;
//    }
//
//    @Override
//    public boolean isValid(long date) {
//        LocalDate dateToBeVerified =
//                Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate();
//
//        for (LocalDate availableDate:
//             availableDates) {
//            if(availableDate.getMonth().equals(dateToBeVerified.getMonth())){
//                return true;
//            }
//        }
//        return false;
//    }
//
//
////    @Override
////    public int describeContents() {
////        return 0;
////    }
////
////    @Override
////    public void writeToParcel(@NonNull Parcel dest, int flags) {
////        dest.writeLong(point);
////    }
//}
