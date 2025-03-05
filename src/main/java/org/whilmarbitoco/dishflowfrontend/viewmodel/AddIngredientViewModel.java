package org.whilmarbitoco.dishflowfrontend.viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.whilmarbitoco.dishflowfrontend.api.IngredientService;
import org.whilmarbitoco.dishflowfrontend.core.dto.AddIngredientDTO;
import org.whilmarbitoco.dishflowfrontend.core.dto.IngredientDTO;
import org.whilmarbitoco.dishflowfrontend.model.AddIngredient;
import org.whilmarbitoco.dishflowfrontend.model.Ingredient;
import org.whilmarbitoco.dishflowfrontend.model.Menu;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AddIngredientViewModel {

    private final IngredientService ingredient;

    private final ObjectProperty<Menu> menu = new SimpleObjectProperty<>();
    private final StringProperty error = new SimpleStringProperty();
    private final ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();

    private final ObservableList<AddIngredient> addIngredients = FXCollections.observableArrayList();


    public AddIngredientViewModel() {
        ingredient = new IngredientService();
    }

    public void fetchData() {
        new Thread(() -> {
            try {
                List<Ingredient> allIngredients = ingredient.all();
                List<Ingredient> current = ingredient.getMenuIngredients(menu.get().getId());

                Platform.runLater(() -> {
                    Set<Integer> currentIds = current.stream()
                            .map(Ingredient::getId)
                            .collect(Collectors.toSet());

                    List<Ingredient> filtered = allIngredients.stream()
                            .filter(i -> !currentIds.contains(i.getId()))
                            .toList();

                    ingredients.clear();
                    ingredients.addAll(filtered);
                });
            } catch (Exception e) {
                Platform.runLater(() -> error.setValue(e.getMessage()));
            }
        }).start();
    }


    public void addIngredient(Ingredient ingredient) {
        AddIngredient check = addIngredients.stream().filter(e -> e.ingredientProperty().get() == ingredient.getId()).findFirst().orElse(null);
        if (check != null) {
            check.quantityProperty().setValue(check.quantityProperty().get() + 1);
            return;
        }

        AddIngredient add = new AddIngredient(ingredient.getId(), ingredient.getName(), ingredient.getUnit(), 1);
        addIngredients.add(add);
    }

    public void minus(AddIngredient ingredient) {
        AddIngredient check = addIngredients.stream().filter(e -> e == ingredient).findFirst().orElse(null);
        if (check == null) return;

        int qty = check.quantityProperty().get() - 1;

        if (qty > 0) {
            check.quantityProperty().setValue(qty);
        }
    }

    public ObjectProperty<Menu> menuProperty() {
        return menu;
    }

    public StringProperty errorProperty() {
        return error;
    }

    public ObservableList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ObservableList<AddIngredient> getAddIngredients() {
        return addIngredients;
    }
}
