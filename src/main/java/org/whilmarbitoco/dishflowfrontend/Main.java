package org.whilmarbitoco.dishflowfrontend;

import javafx.application.Application;
import javafx.stage.Stage;
import org.whilmarbitoco.dishflowfrontend.core.ViewHandler;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        ViewHandler.openView("manager/ManagerView");
        ViewHandler.openView("auth/LoginView");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
