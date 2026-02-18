package org.example.agile_devops_blogging_platform.logging;

import java.time.Instant;

public class LogEntry {
    private String id;
    private Instant timestamp;
    private String level;
    private String logger;
    private String className;
    private String methodName;
    private String message;
    private String args;
    private String exception;

    public LogEntry() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }


    public void setLevel(String level) {
        this.level = level;
    }


    public void setLogger(String logger) {
        this.logger = logger;
    }


    public void setClassName(String className) {
        this.className = className;
    }


    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public void setArgs(String args) {
        this.args = args;
    }


    public void setException(String exception) {
        this.exception = exception;
    }
}
