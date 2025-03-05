package org.whilmarbitoco.dishflowfrontend.view.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.whilmarbitoco.dishflowfrontend.api.HttpService;
import org.whilmarbitoco.dishflowfrontend.core.ViewHandler;
import org.whilmarbitoco.dishflowfrontend.model.Menu;

import java.io.IOException;
import java.util.Optional;

public class MTableItemController {
    public Label name;
    public Label price;
    public Label description;
    public Label available;
    public Label type;
    public ImageView image;

    private Menu menu;
    private TMenuAction action;

    @FXML
    public void initialize() {}

    public void setBase(Menu menu, TMenuAction base) {
        this.menu = menu;
        this.action = base;

        String imgUrl = HttpService.BASE_URL + "/" + menu.getImage();

        Image img = new Image(imgUrl, true);
        name.setText(menu.getName());
        price.setText(Double.toString(menu.getPrice()));
        description.setText(menu.getDescription());
        available.setText(Boolean.toString(menu.isAvailable()));
        type.setText(menu.getType());
        image.setImage(img);

    }

    public void viewAction(ActionEvent actionEvent) {
        try {

            Stage stage = new Stage();
            FXMLLoader loader = ViewHandler.getLoader("manager/InfoView");
            Parent parent = loader.load();

            Scene scene = new Scene(parent);
            InfoViewController controller = loader.getController();
            controller.setMenu(menu);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editAction(ActionEvent actionEvent) {
        action.onEdit(menu);
    }

    public void deleteAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Action");
        alert.setHeaderText("Are you sure you want to delete?");
        alert.setContentText(menu.getName());
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) action.onDelete(menu);
    }

    public void ingredientAction(ActionEvent actionEvent) {
        action.onView(menu);
    }
}
