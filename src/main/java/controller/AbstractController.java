package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

/**
 * Abstraktní předek pro všechny controllery.
 * Nese na sobě atribut primaryStage, který si předávají všechny scény/kontrolery
 * Implementuje společnou funkcionalitu více kontrolerů
 */
public abstract class AbstractController extends BorderPane {

    protected Stage primaryStage;

    @FXML
    public void redirectToRecepty() throws IOException {
        redirectToView("/view/recepty.fxml");
    }

    @FXML
    public void redirectToHome() throws IOException {
        redirectToView("/view/home.fxml");
    }

    @FXML
    public void redirectToSklad() throws IOException {
        redirectToView("/view/sklad.fxml");
    }

    protected void redirectToView(String view) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getClass().getResource(view));
        Parent rootLayout = loader.load();

        AbstractController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
