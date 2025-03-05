package org.whilmarbitoco.dishflowfrontend.model;

import javafx.beans.property.*;

public class AddIngredient {
    private final IntegerProperty ingredient = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty unit = new SimpleStringProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();

    // Constructor
    public AddIngredient(int ingredient, String name, String unit, int quantity) {
        this.ingredient.set(ingredient);
        this.name.set(name);
        this.unit.set(unit);
        this.quantity.set(quantity);
    }

    public IntegerProperty ingredientProperty() {
        return ingredient;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty unitProperty() {
        return unit;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

}
