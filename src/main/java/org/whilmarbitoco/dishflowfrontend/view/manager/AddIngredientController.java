package org.whilmarbitoco.dishflowfrontend.view.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.whilmarbitoco.dishflowfrontend.core.dto.AddIngredientDTO;
import org.whilmarbitoco.dishflowfrontend.model.AddIngredient;
import org.whilmarbitoco.dishflowfrontend.model.Ingredient;
import org.whilmarbitoco.dishflowfrontend.model.Menu;
import org.whilmarbitoco.dishflowfrontend.viewmodel.AddIngredientViewModel;

public class AddIngredientController {
    public TableView<Ingredient> table;
    public TableColumn<Ingredient, Integer> tid;
    public TableColumn<Ingredient, String> tname;
    public TableColumn<Ingredient, String> tunit;
    public TableView<AddIngredient> addTable;
    public TableColumn<AddIngredient, Integer> ATId;
    public TableColumn<AddIngredient, String> ATName;
    public TableColumn<AddIngredient, String> ATUnit;
    public TableColumn<AddIngredient, Integer> ATQty;

    private AddIngredientViewModel viewModel;

    public AddIngredientController() {
        viewModel = new AddIngredientViewModel();
    }

    @FXML
    public void initialize() {
        viewModel.fetchData();

        viewModel.errorProperty().addListener((obs, prev, now) -> {
            System.out.println("Error:>> " + now);
        });

        System.out.println(viewModel.getIngredients().size());
        tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tname.setCellValueFactory(new PropertyValueFactory<>("name"));
        tunit.setCellValueFactory(new PropertyValueFactory<>("unit"));

        ATId.setCellValueFactory(new PropertyValueFactory<>("ingredient"));
        ATName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ATUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        ATQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        table.setItems(viewModel.getIngredients());
        addTable.setItems(viewModel.getAddIngredients());
    }

    public void setMenu(Menu menu) {
        viewModel.menuProperty().setValue(menu);
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
    }

    public void submit(ActionEvent actionEvent) {

    }

    public void addAction(ActionEvent actionEvent) {
        Ingredient selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) return;

       viewModel.addIngredient(selected);
    }

    public void minus(ActionEvent actionEvent) {
        AddIngredient selected = addTable.getSelectionModel().getSelectedItem();

        if (selected == null) return;

        viewModel.minus(selected);
    }
}
