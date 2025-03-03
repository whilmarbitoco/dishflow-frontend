package org.whilmarbitoco.dishflowfrontend.view.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.whilmarbitoco.dishflowfrontend.core.ViewHandler;
import org.whilmarbitoco.dishflowfrontend.model.Menu;
import org.whilmarbitoco.dishflowfrontend.viewmodel.ManagerViewModel;

import java.io.IOException;
import java.util.Optional;

public class ManagerViewController {

    @FXML
    private TableColumn<Menu, Integer> tableID;
    @FXML
    private TableColumn<Menu, String> tableName;
    @FXML
    private TableColumn<Menu, String> tableDesc;
    @FXML
    private TableColumn<Menu, Double> tablePrice;
    @FXML
    private TableColumn<Menu, Boolean> tableAvail;
    @FXML
    private TableColumn<Menu, String> tableType;
    @FXML
    private TableColumn<Menu, String> tableCreated;
    @FXML
    private TableView<Menu> table;

    private final ManagerViewModel viewModel = new ManagerViewModel();

    @FXML
    public void initialize() {
        tableID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableAvail.setCellValueFactory(new PropertyValueFactory<>("available"));
        tableType.setCellValueFactory(new PropertyValueFactory<>("type"));

        table.setItems(viewModel.getMenus());

        viewModel.fetchMenu();
        System.out.println(viewModel.getMenus().size());
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

        table.getFocusModel().focusedCellProperty().addListener((obs, prev, now) -> {
            if (now != null) {
                int rowIndex = now.getRow();
                if (rowIndex >= 0) {
                    Menu s = table.getItems().get(rowIndex);
                    viewModel.selectedProperty().setValue(s);
                }
            }
        });

    }

    public void delete(ActionEvent actionEvent) {
        if (viewModel.selectedProperty().get() == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Action");
        alert.setHeaderText("Are you sure you want to delete?");
        alert.setContentText(viewModel.selectedProperty().get().getName());

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {

            viewModel.delete();

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
                viewModel.fetchMenu();
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(ActionEvent actionEvent) {
        System.out.println("Edit Clicked");
    }

    public void info(ActionEvent actionEvent) {
        try {

            Stage stage = new Stage();
            FXMLLoader loader = ViewHandler.getLoader("manager/InfoView");
            Parent parent = loader.load();

            Scene scene = new Scene(parent);
            InfoViewController controller = loader.getController();
            controller.setMenu(viewModel.selectedProperty().get());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
