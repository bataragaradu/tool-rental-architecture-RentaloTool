package com.rbinnovative.rentalotool.model;

import java.time.LocalDate;

public class RentalDatePick {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public RentalDatePick(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
