package org.whilmarbitoco.dishflowfrontend.view.waiter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.whilmarbitoco.dishflowfrontend.api.HttpService;
import org.whilmarbitoco.dishflowfrontend.model.Menu;

public class MenuViewController {

    public ImageView image;
    public Label price;
    public Label name;
    public Label description;

    private Menu menu;

    @FXML
    public void initialize() {}


    public void setMenu(Menu menu) {
        this.menu = menu;
        updateUI();
    }

    private void updateUI() {
        if (menu != null) {
            name.setText(menu.getName());
            description.setText(menu.getDescription());
            price.setText(Double.toString(menu.getPrice()));

            String imageUrl = HttpService.BASE_URL + "/" + menu.getImage();
            Image img = new Image(imageUrl, true);

            image.setImage(img);
        }
    }


    public void order(ActionEvent actionEvent) {
        System.out.println("Ordered");
    }
}
