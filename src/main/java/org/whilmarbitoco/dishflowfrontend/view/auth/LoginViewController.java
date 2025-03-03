package org.whilmarbitoco.dishflowfrontend.view.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.whilmarbitoco.dishflowfrontend.core.ViewHandler;
import org.whilmarbitoco.dishflowfrontend.viewmodel.LoginViewModel;

public class LoginViewController {

    public TextField plainPassword;
    public PasswordField password;
    public TextField email;
    public CheckBox ShowPassword;

    LoginViewModel viewModel = new LoginViewModel();

    @FXML
    public void initialize() {
        email.textProperty().bindBidirectional(viewModel.emailProperty());
        password.textProperty().bindBidirectional(viewModel.passwordProperty());
        plainPassword.textProperty().bindBidirectional(viewModel.passwordProperty());

        ShowPassword.selectedProperty().addListener((obs, oldVal, newVal) -> {
            plainPassword.setVisible(newVal);
            password.setVisible(!newVal);
        });
        plainPassword.setVisible(false);

        viewModel.isFailedProperty().addListener(((obs, prev, now) -> {
            if (now) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(viewModel.errorMsgProperty().get());
                alert.showAndWait();

                viewModel.isFailedProperty().setValue(false);
                viewModel.errorMsgProperty().setValue("");
            }
        }));
    }

    public void login(ActionEvent actionEvent) {
        try {
            viewModel.login();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void goToSignup(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        ViewHandler.openView("auth/SignupView");
    }
}
