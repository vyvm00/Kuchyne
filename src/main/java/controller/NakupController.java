package controller;

import entity.SurovinaNaSklade;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.PersistenceManager;
import util.NumberTextField;
import util.Utils;


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
    public void koupit() {

        // pokus o precteni hodnoty z textboxu
        int nakoupitMnozstvi = Utils.tryToParseIntFromNumberTextField(tfMnozstvi);

        // aktualizace a ulozeni sns
        surovinaNaSklade.setMnozstvi(surovinaNaSklade.getMnozstvi() + nakoupitMnozstvi);
        PersistenceManager.saveSurovinaNaSklade(surovinaNaSklade);

        redirectToSklad();
    }


    public void setSurovinaNaSklade(SurovinaNaSklade surovinaNaSklade) {
        this.surovinaNaSklade = surovinaNaSklade;
        tfSurovina.setText(surovinaNaSklade.getSurovina().getNazev());
        // do textboxu se predvyplni mnozstvi zbyvajici k naplneni minimalniho poctu
        tfMnozstvi.setText(Integer.toString(surovinaNaSklade.getNutnoDokoupit()));
    }

}