package org.whilmarbitoco.dishflowfrontend.view.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.whilmarbitoco.dishflowfrontend.viewmodel.CreateMenuViewModel;

import java.io.File;

public class CreateMenuController {
    public TextField name;
    public TextField price;
    public TextField description;
    public ChoiceBox<String> type;
    public Button openFile;
    public Label fileLabel;

    CreateMenuViewModel viewModel = new CreateMenuViewModel();

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
        viewModel.create();
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }
}
