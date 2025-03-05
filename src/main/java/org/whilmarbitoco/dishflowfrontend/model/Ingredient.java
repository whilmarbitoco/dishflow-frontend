package org.whilmarbitoco.dishflowfrontend.model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Ingredient {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final StringProperty unit = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> createdAt = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> updatedAt = new SimpleObjectProperty<>();

    public Ingredient(int id, String name, int quantity, String unit, LocalDate createdAt, LocalDate updatedAt) {
        this.id.set(id);
        this.name.set(name);
        this.quantity.set(quantity);
        this.unit.set(unit);
        this.createdAt.set(createdAt);
        this.updatedAt.set(updatedAt);
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public int getQuantity() {
        return quantity.get();
    }

    public String getUnit() {
        return unit.get();
    }

    public LocalDate getCreatedAt() {
        return createdAt.get();
    }

    public LocalDate getUpdatedAt() {
        return updatedAt.get();
    }

    // Getters for properties
    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public StringProperty unitProperty() {
        return unit;
    }

    public ObjectProperty<LocalDate> createdAtProperty() {
        return createdAt;
    }

    public ObjectProperty<LocalDate> updatedAtProperty() {
        return updatedAt;
    }
}
