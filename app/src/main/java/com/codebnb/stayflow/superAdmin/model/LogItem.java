package com.codebnb.stayflow.superAdmin.model;

public class LogItem {
    public String title;
    public String timestamp;
    public String description;

    public LogItem(String title, String timestamp, String description) {
        this.title = title;
        this.timestamp = timestamp;
        this.description = description;
    }
}

