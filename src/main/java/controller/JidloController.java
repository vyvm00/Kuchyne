package controller;

import entity.Jidlo;
import entity.Recept;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import service.PersistenceManager;
import util.NumberTextField;

import java.io.IOException;
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
    public void saveJidlo() throws IOException {

        LocalDate localDate = dpDate.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        // pokus o precteni textboxu s poctem porci
        int pocetPorci = 0;
        try { pocetPorci = Integer.parseInt(tfPocetPorci.getText()); } catch (Exception e){ }

        // uklada se novy zaznam
        if (jidlo == null){
            jidlo = new Jidlo(date, pocetPorci, recept);

            // vytazeni seznamu chybejicich surovin
            String missingSuroviny = PersistenceManager.isSurovinyAvilableForNumberOfRecept(recept, pocetPorci);

            if ( missingSuroviny == null){
                PersistenceManager.saveJidlo(jidlo);
            } else {
                showWarning(missingSuroviny);
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
                showWarning(missingSuroviny);
            }

        }
        redirectToHome();
    }

    /**
     * Zobrazí dialogové okno s hláškou o chybějících surovinách
     * @param warning
     */
    private void showWarning(String warning){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Nedostatek surovin!");
        alert.setHeaderText("Nedostatek surovin");
        alert.setContentText(warning);

        alert.showAndWait();
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
