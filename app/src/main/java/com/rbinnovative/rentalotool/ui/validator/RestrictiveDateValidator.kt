package com.rbinnovative.rentalotool.ui.validator

import android.os.Parcel
import android.os.Parcelable
import com.google.android.material.datepicker.CalendarConstraints
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class RestrictiveDateValidator : CalendarConstraints.DateValidator {

//    private val availableDates  = Collections.singleton(LocalDate.now().plusDays(1))
    private val availableDates  = Arrays.asList(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), LocalDate.now().minusDays(2))

    override fun isValid(longValidatedDate: Long): Boolean {
        val dateToBeValidated = LocalDateTime.ofInstant(Instant.ofEpochMilli(longValidatedDate), ZoneId.systemDefault())
        for (availableDate in availableDates){
            if(dateToBeValidated.dayOfMonth.equals(availableDate.dayOfMonth)){
                return true;
            }
        }
        return false;
    }

    val CREATOR: Parcelable.Creator<RestrictiveDateValidator?> =
            object : Parcelable.Creator<RestrictiveDateValidator?> {
                override fun createFromParcel(source: Parcel): RestrictiveDateValidator {
                    return RestrictiveDateValidator()
                }
                override fun newArray(size: Int): Array<RestrictiveDateValidator?> {
                    return arrayOfNulls(size)
                }
            }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun hashCode(): Int {
        val hashedFields = arrayOf<Any>()
        return hashedFields.hashCode()
    }
}