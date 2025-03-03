package org.whilmarbitoco.dishflowfrontend.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import org.whilmarbitoco.dishflowfrontend.api.MenuService;
import org.whilmarbitoco.dishflowfrontend.model.Menu;

import java.util.List;

public class WaiterViewModel {

    private MenuService menuService;
    private ObjectProperty<Button> activeNav;
    private ObservableList<Menu> menus;


    public WaiterViewModel() {
        this.menuService = new MenuService();
        this.activeNav = new SimpleObjectProperty<>();
    }

    public List<Menu> getMenus() throws Exception {
       return menuService.all();
    }


    public ObjectProperty<Button> activeNavProperty() {
        return activeNav;
    }

    public void setActiveNav(Button button) {
        activeNav.set(button);
    }

}
