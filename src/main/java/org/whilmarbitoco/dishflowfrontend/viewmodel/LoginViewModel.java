package org.whilmarbitoco.dishflowfrontend.viewmodel;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.whilmarbitoco.dishflowfrontend.api.AuthService;
import org.whilmarbitoco.dishflowfrontend.core.EmailValidator;
import org.whilmarbitoco.dishflowfrontend.core.ViewHandler;
import org.whilmarbitoco.dishflowfrontend.core.auth.Session;

public class LoginViewModel {

    private AuthService authService = new AuthService();

    private final StringProperty email = new SimpleStringProperty("");
    private final StringProperty password = new SimpleStringProperty("");
    private final BooleanProperty isFailed = new SimpleBooleanProperty();
    private final StringProperty errorMsg = new SimpleStringProperty();



    public void login() throws Exception {
        if (email.get().isEmpty() || password.get().isEmpty()) {
            throw new RuntimeException("Fields cannot be empty.");
        }
        if (!EmailValidator.isValidEmail(email.get())) {
            throw new RuntimeException("Invalid Email Format.");
        }

        new Thread(() -> {
            try {
                authService.login(email.get(), password.get());

                Platform.runLater(this::switchScene);
            } catch (Exception e) {
                Platform.runLater(() -> {
                    errorMsg.setValue(e.getMessage());
                    isFailed.setValue(true);
                });
            }
        }).start();

    }

    public void switchScene() {
        String role = Session.loginEmployee.getRole();

        switch (role) {
            case "Manager":
                ViewHandler.openView("manager/ManagerView");
                break;

            case "Waiter":
                ViewHandler.openView("waiter/WaiterView");
                break;

            case "Chef":
                ViewHandler.openView("chef/ChefView");
                break;

            default:
                throw new RuntimeException("Can not find appropriate view for " + role);
        }
    }

    public StringProperty errorMsgProperty() {
        return errorMsg;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public BooleanProperty isFailedProperty() {
        return isFailed;
    }

}
