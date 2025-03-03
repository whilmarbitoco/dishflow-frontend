package org.whilmarbitoco.dishflowfrontend.core;

public class ServiceResult {

    public boolean status;
    public String message;


    public ServiceResult() {}

    public ServiceResult(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
