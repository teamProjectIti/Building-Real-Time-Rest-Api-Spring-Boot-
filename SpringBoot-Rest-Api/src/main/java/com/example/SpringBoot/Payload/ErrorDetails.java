package com.example.SpringBoot.Payload;

import lombok.Data;

import java.util.Date;

public class ErrorDetails {
    private Date TimeStamp;
    private String Message;
    private String Details;
    public Date getTimeStamp() {
        return TimeStamp;
    }

    public ErrorDetails(Date timeStamp, String message, String details) {
        TimeStamp = timeStamp;
        Message = message;
        Details = details;
    }

    public String getMessage() {
        return Message;
    }

    public String getDetails() {
        return Details;
    }
}
