package org.whilmarbitoco.dishflowfrontend.view.waiter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.whilmarbitoco.dishflowfrontend.core.ViewHandler;
import org.whilmarbitoco.dishflowfrontend.model.Menu;
import org.whilmarbitoco.dishflowfrontend.viewmodel.WaiterViewModel;

public class WaiterViewController {


    public GridPane grid;
    public Button takeBTN;
    public Button statusBTN;
    public Button settingsBTN;
    public Button historyBTN;
    public Button one;
    public Button two;
    public Button three;
    public Button four;
    public Button five;

    private WaiterViewModel viewModel = new WaiterViewModel();

    @FXML
    public void initialize() {
        initData();

        viewModel.setActiveNav(one);
        one.getStyleClass().add("active-button");
        one.setOnAction(e -> viewModel.setActiveNav(one));
        two.setOnAction(e -> viewModel.setActiveNav(two));
        three.setOnAction(e -> viewModel.setActiveNav(three));
        four.setOnAction(e -> viewModel.setActiveNav(four));
        five.setOnAction(e -> viewModel.setActiveNav(five));


        viewModel.activeNavProperty().addListener((obs, oldBTN, newBTN) -> {
            if (oldBTN != null) oldBTN.getStyleClass().remove("active-button");
            if (newBTN != null) newBTN.getStyleClass().add("active-button");
        });

    }

    private void initData() {
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();

        try {
            int row = 0;
            int col = 0;

            for (Menu m : viewModel.getMenus()) {
                if (col == 3) {
                    col = 0;
                    row++;
                }
                FXMLLoader loader = ViewHandler.getLoader("waiter/MenuView");
                AnchorPane pane  = loader.load();


                MenuViewController controller = loader.getController();
                controller.setMenu(m);


                grid.add(pane, col++, row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filter(ActionEvent actionEvent) {

    }
}
