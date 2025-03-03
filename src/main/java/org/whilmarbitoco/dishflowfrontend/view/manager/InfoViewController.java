package org.whilmarbitoco.dishflowfrontend.view.manager;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.whilmarbitoco.dishflowfrontend.api.HttpService;
import org.whilmarbitoco.dishflowfrontend.model.Menu;

public class InfoViewController {
    public Label menuName;
    public Label menuPrice;
    public Label description;
    public ImageView image;
    public Label available;


    public void setMenu(Menu menu) {
        menuName.setText(menu.getName());
        menuPrice.setText(Double.toString(menu.getPrice()));
        description.setText(menu.getDescription());
        available.setText(Boolean.toString(menu.isAvailable()));

        String imageUrl = HttpService.BASE_URL + "/" + menu.getImage();
        Image img = new Image(imageUrl, true);

        image.setImage(img);
    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

}
