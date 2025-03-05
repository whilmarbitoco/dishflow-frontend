package org.whilmarbitoco.dishflowfrontend.view.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.whilmarbitoco.dishflowfrontend.model.Menu;
import org.whilmarbitoco.dishflowfrontend.viewmodel.UpdateMenuViewModel;

import java.io.File;

public class UpdateMenuController {
    public TextField name;
    public TextField price;
    public TextField description;
    public ChoiceBox<String> type;
    public Button openFile;
    public Label fileLabel;

    private UpdateMenuViewModel viewModel;
    private Menu menu;

    public UpdateMenuController() {
        this.viewModel = new UpdateMenuViewModel();
    }

    public void setMenu(Menu menu) {
        this.menu = menu;

        name.setText(menu.getName());
        price.setText(Double.toString(menu.getPrice()));
        description.setText(menu.getDescription());
        type.setValue(menu.getType());
        fileLabel.setText(menu.getImage());
    }

    @FXML
    public void initialize() {
        type.getItems().addAll("Main Course", "Side Dish", "Appetizer", "Dessert", "Beverage");

        name.textProperty().bindBidirectional(viewModel.menuNameProperty());
        price.textProperty().bindBidirectional(viewModel.priceProperty());
        description.textProperty().bindBidirectional(viewModel.descriptionProperty());
        type.valueProperty().bindBidirectional(viewModel.typeProperty());


        viewModel.errorProperty().addListener((obs, prev, now) -> {
            if (now) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Something went Wrong.");
                alert.setHeaderText(viewModel.errorMsgProperty().get());
                alert.showAndWait();
                viewModel.errorProperty().setValue(false);
            }
        });

        viewModel.resValProperty().addListener((obs, prev, now) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success.");
            alert.setHeaderText(viewModel.resValProperty().get());
            alert.showAndWait();

            Stage stage = (Stage) name.getScene().getWindow();
            stage.close();
        });

    }

    public void selectImg(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        Stage stage = (Stage) openFile.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            fileLabel.setText(selectedFile.getName());
            System.out.println(selectedFile.getAbsolutePath());
            viewModel.fileProperty().setValue(selectedFile.getAbsolutePath());
        } else {
            fileLabel.setText("No file selected");
        }
    }

    public void submit(ActionEvent actionEvent) {
        viewModel.update();
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }
}
