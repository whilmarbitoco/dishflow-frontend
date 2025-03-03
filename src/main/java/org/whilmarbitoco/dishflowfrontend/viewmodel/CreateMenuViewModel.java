package org.whilmarbitoco.dishflowfrontend.viewmodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.beans.property.*;
import org.whilmarbitoco.dishflowfrontend.api.MenuService;

public class CreateMenuViewModel {

    private final StringProperty menuName = new SimpleStringProperty();
    private final StringProperty price = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty type = new SimpleStringProperty();
    private final StringProperty file = new SimpleStringProperty();

    private final BooleanProperty error = new SimpleBooleanProperty();
    private final StringProperty errorMsg = new SimpleStringProperty();
    private final StringProperty resVal = new SimpleStringProperty();

    private MenuService menuService = new MenuService();


    public void create() {
        new Thread(() -> {
            try {
                String selectedType = "";

                switch (type.get()) {
                    case "Main Course":
                        selectedType = "MainCourse"; 
                        break;
                    case "Appetizer":
                        selectedType = "Appetizer";
                        break;
                    case "Side Dish":
                        selectedType = "SideDish";
                        break;
                    case "Dessert":
                        selectedType = "Dessert";
                        break;
                    case "Beverage":
                        selectedType = "Beverage";
                        break;
                    default:
                        Platform.runLater(() -> {
                            throw new IllegalArgumentException("Invalid menu type: " + type.get());
                        });
                }
                String res = menuService.create(menuName.get(), price.get(), description.get(), selectedType,file.get());
                Platform.runLater(() -> {
                    resVal.setValue(res);
                });

            } catch (Exception e) {
                Platform.runLater(() -> {
                    errorMsg.setValue(e.getMessage());
                    error.setValue(true);
                });
            }

        }).start();
    }

    public StringProperty menuNameProperty() {
        return menuName;
    }

    public StringProperty priceProperty() {
        return price;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty fileProperty() {
        return file;
    }

    public BooleanProperty errorProperty() {
        return error;
    }

    public StringProperty resValProperty() {
        return resVal;
    }

    public StringProperty errorMsgProperty() {
        return errorMsg;
    }
}

