package com.myblog.blogapp.payload;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String messege;
    private String details;

    public ErrorDetails(Date timestamp, String messege, String details) {
        this.timestamp = timestamp;
        this.messege = messege;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessege() {
        return messege;
    }

    public String getDetails() {
        return details;
    }
}
