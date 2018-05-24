package controller;

import entity.SurovinaNaSklade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.PersistenceManager;
import util.NumberTextField;

import java.io.IOException;

public class NakupController extends AbstractController {

    private SurovinaNaSklade surovinaNaSklade;

    @FXML
    private TextField tfSurovina;

    @FXML
    private NumberTextField tfMnozstvi;


    @FXML
    private void initialize() {
        tfSurovina.setDisable(true);
    }

    @FXML
    public void koupit(ActionEvent actionEvent) throws IOException {

        // pokus o precteni hodnoty z textboxu
        int nakoupitMnozstvi = 0;
        try { nakoupitMnozstvi = Integer.parseInt(tfMnozstvi.getText()); } catch (Exception e){ }

        // aktualizace a ulozeni sns
        surovinaNaSklade.setMnozstvi(surovinaNaSklade.getMnozstvi() + nakoupitMnozstvi);
        PersistenceManager.save(surovinaNaSklade);

        redirectToSklad();
    }


    public void setSurovinaNaSklade(SurovinaNaSklade surovinaNaSklade) {
        this.surovinaNaSklade = surovinaNaSklade;
        tfSurovina.setText(surovinaNaSklade.getSurovina().getNazev());
    }


}
