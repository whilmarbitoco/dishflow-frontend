module org.whilmarbitoco.dishflowfrontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires jdk.jsobject;
    requires com.fasterxml.jackson.databind;
    requires okhttp3;

    opens org.whilmarbitoco.dishflowfrontend to javafx.graphics;
    exports org.whilmarbitoco.dishflowfrontend;

    opens org.whilmarbitoco.dishflowfrontend.model to javafx.base;

    opens org.whilmarbitoco.dishflowfrontend.view.auth to javafx.fxml;
    exports org.whilmarbitoco.dishflowfrontend.view.auth;
    opens org.whilmarbitoco.dishflowfrontend.view.waiter to javafx.fxml;
    exports org.whilmarbitoco.dishflowfrontend.view.waiter;
    opens org.whilmarbitoco.dishflowfrontend.view.manager to javafx.fxml;
    exports org.whilmarbitoco.dishflowfrontend.view.manager;


    exports org.whilmarbitoco.dishflowfrontend.core.auth;
    opens org.whilmarbitoco.dishflowfrontend.core.auth to com.fasterxml.jackson.databind;
    exports org.whilmarbitoco.dishflowfrontend.core.dto;
    opens org.whilmarbitoco.dishflowfrontend.core.dto to com.fasterxml.jackson.databind;

}



