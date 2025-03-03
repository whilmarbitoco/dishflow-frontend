package org.whilmarbitoco.dishflowfrontend.view.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.whilmarbitoco.dishflowfrontend.core.ViewHandler;
import org.whilmarbitoco.dishflowfrontend.core.auth.Session;
import org.whilmarbitoco.dishflowfrontend.viewmodel.VerifyEmailViewModel;

public class VerifyEmailController {
    public Label email;
    public TextField codeField;


    VerifyEmailViewModel viewModel = new VerifyEmailViewModel();

    @FXML
    public void initialize() {
        email.setText(viewModel.emailProperty().get());
        viewModel.codeProperty().bindBidirectional(codeField.textProperty());

        viewModel.errorProperty().addListener((obs, prev, now) -> {
            if (now) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Verification Failed");
                alert.setHeaderText(viewModel.errorMsgProperty().get());
                alert.showAndWait();

                viewModel.errorProperty().setValue(false);
                viewModel.errorMsgProperty().setValue("");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Verification Success");
                alert.setHeaderText("Account Successfully Verified.");
                alert.showAndWait();
                ViewHandler.openView("auth/LoginView");
            }
        });
    }

    public void verify(ActionEvent actionEvent) {
        viewModel.verify();
    }

    public void resend(MouseEvent mouseEvent) {
    }
}
