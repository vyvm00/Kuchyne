package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.Main;

import java.io.IOException;

public class SkladController extends AbstractController {

    @FXML
    public void redirectToNakup() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getClass().getResource("/view/nakup.fxml"));
        Parent rootLayout = loader.load();

        NakupController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}
