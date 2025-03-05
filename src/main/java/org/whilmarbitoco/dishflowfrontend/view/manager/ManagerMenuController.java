package org.whilmarbitoco.dishflowfrontend.view.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.whilmarbitoco.dishflowfrontend.core.ViewHandler;
import org.whilmarbitoco.dishflowfrontend.model.Menu;
import org.whilmarbitoco.dishflowfrontend.viewmodel.ManagerViewModel;

import java.io.IOException;
import java.util.List;

public class ManagerMenuController implements TMenuAction {

    public GridPane tcontent;
    protected ManagerViewModel viewModel;

    public ManagerMenuController() {
        viewModel = new ManagerViewModel();
    }

    @FXML
    public void initialize() {
        viewModel.errorProperty().addListener((obs, prev, now) -> {
            if (now) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Something went Wrong.");
                alert.setHeaderText(viewModel.errorMsgProperty().get());
                alert.showAndWait();
                viewModel.errorProperty().setValue(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success.");
                alert.setHeaderText(viewModel.errorMsgProperty().get());
                alert.showAndWait();
            }
        });

        initData();
    }

    protected void initData() {
        tcontent.getChildren().clear();
        tcontent.getColumnConstraints().clear();
        tcontent.getRowConstraints().clear();
        try {
            List<Menu> menus = viewModel.getMenus();
            System.out.println("Size: " + menus.size());
            for (int i = 0; i < menus.size(); i++) {

                FXMLLoader loader = ViewHandler.getLoader("manager/MTableItemView");
                HBox box = loader.load();
                MTableItemController controller = loader.getController();
                controller.setBase(menus.get(i), this);

                tcontent.addRow(i, box);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void create(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent parent =  ViewHandler.getLoader("manager/CreateMenuView").load();
            Scene scene = new Scene(parent);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            stage.setOnHidden(event -> {
                initData();
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDelete(Menu menu) {
        viewModel.selectedProperty().setValue(menu);
        viewModel.delete();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        initData();

    }

    @Override
    public void onEdit(Menu menu) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = ViewHandler.getLoader("manager/UpdateMenuView");
            Parent parent =  loader.load();

            UpdateMenuController controller = loader.getController();
            controller.setMenu(menu);

            Scene scene = new Scene(parent);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            stage.setOnHidden(event -> {
                initData();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onView(Menu menu) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = ViewHandler.getLoader("manager/AddIngredientView");
            Parent parent =  loader.load();

            AddIngredientController controller = loader.getController();
            controller.setMenu(menu);

            Scene scene = new Scene(parent);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            stage.setOnHidden(event -> {
                initData();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
