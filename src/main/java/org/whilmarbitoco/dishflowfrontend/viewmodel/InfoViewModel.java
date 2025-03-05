package org.whilmarbitoco.dishflowfrontend.viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.whilmarbitoco.dishflowfrontend.api.MenuService;
import org.whilmarbitoco.dishflowfrontend.model.Ingredient;
import org.whilmarbitoco.dishflowfrontend.model.Menu;

import java.util.List;

public class InfoViewModel {

    private final MenuService menuService;
    private final ObservableList<String> ingredients = FXCollections.observableArrayList();
    private final BooleanProperty error = new SimpleBooleanProperty();
    private final StringProperty errorMsg = new SimpleStringProperty();
    private final ObjectProperty<Menu> menu = new SimpleObjectProperty<>();

    public InfoViewModel() {
        menuService = new MenuService();
    }

    public void fetchIngredient() {
        new Thread(() -> {
            try {
                List<Ingredient> list = menuService.getIngredients(menu.get().getId());

                Platform.runLater(() -> {
                    ingredients.clear();
                    System.out.println(list.stream().map(Ingredient::getName).toList());
                    ingredients.addAll(list.stream().map(Ingredient::getName).toList());
                });
            } catch (Exception e) {
                errorMsg.setValue(e.getMessage());
                error.setValue(true);
            }
        }).start();
    }

    public ObservableList<String> getIngredients() {
        return ingredients;
    }

    public BooleanProperty errorProperty() {
        return error;
    }

    public StringProperty errorMsgProperty() {
        return errorMsg;
    }

    public ObjectProperty<Menu> menuProperty() {
        return menu;
    }
}
