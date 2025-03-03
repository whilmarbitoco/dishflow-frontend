package org.whilmarbitoco.dishflowfrontend.viewmodel;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.whilmarbitoco.dishflowfrontend.api.AuthService;
import org.whilmarbitoco.dishflowfrontend.core.ViewHandler;
import org.whilmarbitoco.dishflowfrontend.core.auth.Session;

public class VerifyEmailViewModel {

    private final StringProperty email = new SimpleStringProperty("");
    private final StringProperty code = new SimpleStringProperty("");
    private final BooleanProperty error = new SimpleBooleanProperty();
    private final StringProperty errorMsg = new SimpleStringProperty();


    private AuthService authService = new AuthService();


    public VerifyEmailViewModel() {
        email.setValue(Session.temp);
    }


    public void verify() {
        new Thread(() -> {
            try {
                authService.verify(email.get(), code.get());
                Platform.runLater(() -> error.setValue(false));
            } catch (Exception e) {
                Platform.runLater(() -> {
                    errorMsg.setValue(e.getMessage());
                    error.set(true);
                });
            }
        }).start();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty codeProperty() {
        return code;
    }

    public BooleanProperty errorProperty() {
        return error;
    }

    public StringProperty errorMsgProperty() {
        return errorMsg;
    }
}
