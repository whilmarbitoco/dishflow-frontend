package org.whilmarbitoco.dishflowfrontend.core.dto;

public class Auth {

    public String email;
    public String password;
    public String role;

    public String lastname;
    public String firstname;

    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Auth(String email, String password, String role, String lastname, String firstname) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Auth(){}
}
