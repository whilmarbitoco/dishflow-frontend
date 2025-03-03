package org.whilmarbitoco.dishflowfrontend.model;

import javafx.beans.property.*;

public class Menu {

    private IntegerProperty id;
    private StringProperty name;
    private DoubleProperty price;
    private StringProperty description;
    private StringProperty image;
    private BooleanProperty available;
    private StringProperty created;
    private StringProperty type;


    public Menu(int id, String name, double price, String description, String image, boolean available, String created, String type) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.description = new SimpleStringProperty(description);
        this.image = new SimpleStringProperty(image);
        this.available = new SimpleBooleanProperty(available);
        this.created = new SimpleStringProperty(created);
        this.type = new SimpleStringProperty(type);

    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getImage() {
        return image.get();
    }

    public StringProperty imageProperty() {
        return image;
    }

    public boolean isAvailable() {
        return available.get();
    }

    public BooleanProperty availableProperty() {
        return available;
    }

    public String getCreated() {
        return created.get();
    }

    public StringProperty createdProperty() {
        return created;
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }


}
