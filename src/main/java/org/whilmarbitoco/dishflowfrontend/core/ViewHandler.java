package org.whilmarbitoco.dishflowfrontend.core;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ViewHandler {
    private static final String BASE_PATH = System.getProperty("user.dir") +
            "/src/main/java/org/whilmarbitoco/dishflowfrontend/view/";
    private static final Stage stage = new Stage();

    public static void openView(String view) {
        try {
            File fxmlFile = new File(BASE_PATH + view + ".fxml");
            if (!fxmlFile.exists()) {
                throw new IOException("FXML file not found: " + fxmlFile.getAbsolutePath());
            }

            URL fxmlURL = fxmlFile.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(fxmlURL);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FXMLLoader getLoader(String view) throws IOException {

            File fxmlFile = new File(BASE_PATH + view + ".fxml");
            if (!fxmlFile.exists()) {
                throw new IOException("FXML file not found: " + fxmlFile.getAbsolutePath());
            }

            URL fxmlURL = fxmlFile.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(fxmlURL);
           return loader;

    }
}
