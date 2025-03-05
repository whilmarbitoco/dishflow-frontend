package org.whilmarbitoco.dishflowfrontend.viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.whilmarbitoco.dishflowfrontend.api.MenuService;
import org.whilmarbitoco.dishflowfrontend.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class ManagerViewModel {

    private MenuService menuService = new MenuService();

    private final BooleanProperty error = new SimpleBooleanProperty();
    private final StringProperty errorMsg = new SimpleStringProperty();
    private final ObjectProperty<Menu> selected = new SimpleObjectProperty<>();


    public void delete() {
        if (selected.get() == null) {
            errorMsg.setValue("Please select a row.");
            error.setValue(true);
            return;
        }
        new Thread(() -> {
            try {
                menuService.delete(selected.get().getId());
                Platform.runLater(() -> {
                    selected.setValue(null);
                });
            } catch (Exception e) {
                Platform.runLater(() -> {
                    errorMsg.setValue(e.getMessage());
                    error.setValue(true);
                });
            }
        }).start();
    }

    public BooleanProperty errorProperty() {
        return error;
    }

    public StringProperty errorMsgProperty() {
        return errorMsg;
    }

    public List<Menu> getMenus() throws Exception {
        return menuService.all();
    }

    public ObjectProperty<Menu> selectedProperty() {
        return selected;
    }

}
