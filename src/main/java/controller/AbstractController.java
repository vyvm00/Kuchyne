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
 * Implementuje společnou funkcionalitu více kontrolerů (redirecty mezi jednotlivými view)
 */
public abstract class AbstractController extends BorderPane {

    protected Stage primaryStage;

    @FXML
    public ReceptyController redirectToRecepty() throws IOException {
        return (ReceptyController) redirectToView("/view/recepty.fxml");
    }

    @FXML
    public HomeController redirectToHome() throws IOException {
        return (HomeController) redirectToView("/view/home.fxml");
    }

    @FXML
    public SkladController redirectToSklad() throws IOException {
        return  (SkladController) redirectToView("/view/sklad.fxml");
    }

    @FXML
    public ReceptDetailController redirectToReceptDetail() throws IOException {
        return (ReceptDetailController) redirectToView("/view/receptDetail.fxml");
    }

    @FXML
    public JidloController redirectToJidlo() throws IOException {
        return (JidloController) redirectToView("/view/jidlo.fxml");
    }

    @FXML
    public NakupController redirectToNakup() throws IOException {
        return (NakupController) redirectToView("/view/nakup.fxml");
    }

    /**
     * Obecna metoda pro presmerovani na zadane view. Vraci AbstractController,
     * ktery lze nasledne pretypovat na controller odpovidajici danemu view
     * @param view
     * @return
     * @throws IOException
     */
    protected AbstractController redirectToView(String view) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getClass().getResource(view));
        Parent rootLayout = loader.load();

        AbstractController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

        return controller;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
