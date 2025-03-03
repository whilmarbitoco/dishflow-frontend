package org.whilmarbitoco.dishflowfrontend.core.dto;

public class VerifyEmailDTO {

    public String email;
    public String code;

    public VerifyEmailDTO(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
