package org.whilmarbitoco.dishflowfrontend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.whilmarbitoco.dishflowfrontend.core.ServiceResult;
import org.whilmarbitoco.dishflowfrontend.core.dto.Auth;
import org.whilmarbitoco.dishflowfrontend.core.auth.Session;
import org.whilmarbitoco.dishflowfrontend.core.dto.Token;
import javafx.application.Platform;
import org.whilmarbitoco.dishflowfrontend.core.dto.VerifyEmailDTO;
import org.whilmarbitoco.dishflowfrontend.model.Employee;

import java.util.concurrent.CompletableFuture;

public class AuthService implements Authentication {

    private HttpService http;
    private ObjectMapper mapper;


    public AuthService() {
        http = new HttpService();
        mapper = new ObjectMapper();
    }

    @Override
    public void login(String email, String password) throws Exception {
        if (email.isEmpty() || password.isEmpty()) {
           throw new RuntimeException("Fields cannot be empty.");
        }

        Auth data = new Auth(email, password);
        String body = mapper.writeValueAsString(data);
        String res = http.post("/auth/login", body);
        Token token = mapper.readValue(res, Token.class);

        Employee employee = new Employee();
        employee.setRole(token.employee.role);
        employee.setEmail(token.employee.email);
        employee.setId(token.employee.id);
        employee.setLastname(token.employee.lastname);
        employee.setFirstname(token.employee.firstname);
        employee.setPhoto(token.employee.photo);
        employee.setVerified(token.employee.verified);

        Session.accessToken = token.accessToken;
        Session.loginEmployee = employee;
    }


    @Override
    public void signup(String email, String password, String firstname, String lastname, String role) throws Exception {
        if (email.isEmpty() || password.isEmpty() || role.isEmpty())
            throw new RuntimeException("Email or Password cannot be empty");

        Auth data = new Auth(email, password, role, lastname, firstname);

        String body = mapper.writeValueAsString(data);
        http.post("/auth/signup", body);
    }

    @Override
    public void verify(String email, String code) throws Exception {
        if (email.isEmpty() || code.isEmpty())
            throw new RuntimeException("Code cannot be empty.");

        VerifyEmailDTO dto = new VerifyEmailDTO(email, code);
        String body = mapper.writeValueAsString(dto);

        http.post("/auth/verify-email", body);
    }
}
