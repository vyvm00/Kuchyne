package controller;

import entity.Jidlo;
import entity.Recept;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import service.PersistenceManager;
import util.NumberTextField;
import util.Utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class JidloController extends AbstractController {

    @FXML
    private DatePicker dpDate;

    @FXML
    private ComboBox cbRecepty;

    @FXML
    private NumberTextField tfPocetPorci;

    private Recept recept;
    private Jidlo jidlo;


    @FXML
    private void initialize() {

        ObservableList<Recept> recepty = FXCollections.observableArrayList(
                PersistenceManager.getAllRecepty()
        );

        // naplneni comboboxu
        cbRecepty.setItems(recepty);

        // vybere prvni prvek z comboboxu
        if (recepty != null && recepty.size() > 0){
            recept = recepty.get(0);
            cbRecepty.getSelectionModel().selectFirst();
        }

        // prirazeni vybraneho prvku z comboboxu
        cbRecepty.getSelectionModel().selectedItemProperty().addListener(
                (ChangeListener<Recept>) (arg0, oldVal, newVal) -> recept = newVal);

    }


    @FXML
    public void saveJidlo() {

        LocalDate localDate = dpDate.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        // pokus o precteni textboxu s poctem porci
        int pocetPorci = Utils.tryToParseIntFromNumberTextField(tfPocetPorci);

        // uklada se novy zaznam
        if (jidlo == null){
            jidlo = new Jidlo(date, pocetPorci, recept);

            // vytazeni seznamu chybejicich surovin
            String missingSuroviny = PersistenceManager.isSurovinyAvilableForNumberOfRecept(recept, pocetPorci);

            if ( missingSuroviny == null){
                PersistenceManager.saveJidlo(jidlo);
            } else {
                Utils.showAlert("Nedostatek surovin", "Nedostatek surovin", missingSuroviny);
                return;
            }

        // editace stavajiciho zaznamu
        } else{

            int diffPocetPorci = pocetPorci - jidlo.getPocetPorci();

            // vytazeni seznamu chybejicich surovin
            String missingSuroviny = PersistenceManager.isSurovinyAvilableForNumberOfRecept(recept, diffPocetPorci);

            if ( missingSuroviny == null){
                jidlo.setDatum(date);
                jidlo.setPocetPorci(pocetPorci);
                jidlo.setRecept(recept);

                PersistenceManager.updateJidlo(jidlo, diffPocetPorci);
            } else {
                Utils.showAlert("Nedostatek surovin", "Nedostatek surovin", missingSuroviny);
                return;
            }

        }
        redirectToHome();
    }



    public DatePicker getDpDate() {
        return dpDate;
    }

    public void setJidlo(Jidlo jidlo) {
        cbRecepty.getSelectionModel().select(jidlo.getRecept());
        tfPocetPorci.setText(Integer.toString(jidlo.getPocetPorci()));
        dpDate.setValue(LocalDateTime.ofInstant(jidlo.getDatum().toInstant(), ZoneId.systemDefault()).toLocalDate());
        this.jidlo = jidlo;
    }
}
