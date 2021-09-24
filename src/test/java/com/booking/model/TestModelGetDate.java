package com.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class TestModelGetDate {

       @JsonProperty("checkin")
    private LocalDate checkin;
    @JsonProperty("checkout")
    private LocalDate checkout;

    public TestModelGetDate(LocalDate checkin, LocalDate checkout){
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @JsonProperty("checkin")
    public LocalDate getCheckin() {
        return checkin;
    }

    @JsonProperty("checkin")
    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    @JsonProperty("checkout")
    public LocalDate getCheckout() {
        return checkout;
    }

    @JsonProperty("checkout")
    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }
}
