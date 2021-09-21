package com.booking;

public class RequestPut {
    private String firstname;
    private String additionalneeds;
    private BookingDates bookingdates;
    private int totalprice;
    private boolean depositpaid;
    private String lastname;

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setAdditionalneeds(String additionalneeds){
        this.additionalneeds = additionalneeds;
    }

    public String getAdditionalneeds(){
        return additionalneeds;
    }

    public void setBookingDates(BookingDates bookingdates){
        this.bookingdates = bookingdates;
    }

    public BookingDates getBookingDates(){
        return bookingdates;
    }

    public void setTotalprice(int totalprice){
        this.totalprice = totalprice;
    }

    public int getTotalprice(){
        return totalprice;
    }

    public void setDepositpaid(boolean depositpaid){
        this.depositpaid = depositpaid;
    }

    public boolean isDepositpaid(){
        return depositpaid;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getLastname(){
        return lastname;
    }
}
