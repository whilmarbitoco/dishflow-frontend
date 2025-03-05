package org.whilmarbitoco.dishflowfrontend.api;

public interface Authentication {

    void login(String email, String password) throws Exception;

    void signup(String email, String password, String firstname, String lastname, String role) throws Exception;

    void verify(String email, String code) throws Exception;

}
