package org.whilmarbitoco.dishflowfrontend.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.whilmarbitoco.dishflowfrontend.api.MenuService;

public class UpdateMenuViewModel {

    private final StringProperty menuName = new SimpleStringProperty();
    private final StringProperty price = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty type = new SimpleStringProperty();
    private final StringProperty file = new SimpleStringProperty();

    private final BooleanProperty error = new SimpleBooleanProperty();
    private final StringProperty errorMsg = new SimpleStringProperty();
    private final StringProperty resVal = new SimpleStringProperty();

    private MenuService menuService;

    public UpdateMenuViewModel() {
        menuService = new MenuService();
    }


    public void update() {

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

    public StringProperty errorMsgProperty() {
        return errorMsg;
    }

    public StringProperty resValProperty() {
        return resVal;
    }

}
