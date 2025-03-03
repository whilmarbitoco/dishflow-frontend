package org.whilmarbitoco.dishflowfrontend.viewmodel;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.whilmarbitoco.dishflowfrontend.api.AuthService;
import org.whilmarbitoco.dishflowfrontend.core.ViewHandler;
import org.whilmarbitoco.dishflowfrontend.core.auth.Session;

public class SignupViewModel {

    private AuthService authService = new AuthService();

    private final StringProperty email = new SimpleStringProperty("");
    private final StringProperty password = new SimpleStringProperty("");
    private final StringProperty role = new SimpleStringProperty("");
    private final StringProperty firstname = new SimpleStringProperty("");
    private final StringProperty lastname = new SimpleStringProperty("");

    private final BooleanProperty error = new SimpleBooleanProperty();
    private final StringProperty errMsg = new SimpleStringProperty();



    public void signup() throws Exception {
        if (email.get().isEmpty() || password.get().isEmpty() || role.get().isEmpty()) {
            throw new RuntimeException("Fields cannot be empty.");
        }

        new Thread(() -> {
            try {
                authService.signup(email.get(), password.get(), firstname.get(), lastname.get(), role.get());

                Platform.runLater(() -> {
                    Session.temp = email.get();
                    ViewHandler.openView("auth/VerifyEmailView");
                });
            } catch (Exception e) {
                Platform.runLater(()-> {
                    errMsg.setValue(e.getMessage());
                    error.setValue(true);
                });
            }

        }).start();
    }


    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty roleProperty() {
        return role;
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public BooleanProperty errorProperty() {
        return error;
    }

    public StringProperty errMsgProperty() {
        return errMsg;
    }
}
