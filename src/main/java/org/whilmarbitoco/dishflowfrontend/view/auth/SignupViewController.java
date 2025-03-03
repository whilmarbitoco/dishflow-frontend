package org.whilmarbitoco.dishflowfrontend.view.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.whilmarbitoco.dishflowfrontend.core.ViewHandler;
import org.whilmarbitoco.dishflowfrontend.core.auth.Session;
import org.whilmarbitoco.dishflowfrontend.viewmodel.SignupViewModel;

public class SignupViewController {

    public TextField email;
    public PasswordField password;
    public TextField plainPassword;
    public ChoiceBox<String> role;
    public CheckBox ShowPassword;
    public TextField firstname;
    public TextField lastname;
    public Button Btn;

    private SignupViewModel viewModel = new SignupViewModel();

    @FXML
    public void initialize() {
        role.getItems().addAll("Chef", "Waiter");
        role.setValue("Waiter");

        email.textProperty().bindBidirectional(viewModel.emailProperty());
        password.textProperty().bindBidirectional(viewModel.passwordProperty());
        plainPassword.textProperty().bindBidirectional(viewModel.passwordProperty());
        firstname.textProperty().bindBidirectional(viewModel.firstnameProperty());
        lastname.textProperty().bindBidirectional(viewModel.lastnameProperty());
        role.valueProperty().bindBidirectional(viewModel.roleProperty());


        ShowPassword.selectedProperty().addListener((obs, oldVal, newVal) -> {
            plainPassword.setVisible(newVal);
            password.setVisible(!newVal);
        });
        plainPassword.setVisible(false);

        viewModel.errorProperty().addListener((obs, prev, now) -> {
            if (now) {
                showAlert(viewModel.errMsgProperty().get());
                viewModel.errorProperty().setValue(false);
                viewModel.errMsgProperty().setValue("");
            }
        });
    }

    public void signup(ActionEvent actionEvent) {
        try {
            Btn.setDisable(true);
            viewModel.signup();
            Btn.setDisable(false);
        } catch (Exception e) {
            Btn.setDisable(false);
            showAlert(e.getMessage());
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Signup Failed.");
        alert.setHeaderText(msg);
        alert.showAndWait();
    }

    public void gotoLogin(MouseEvent mouseEvent) {
        ViewHandler.openView("auth/LoginView");
    }
}
