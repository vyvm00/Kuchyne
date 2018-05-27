package controller;


import entity.Jidlo;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.PersistenceManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class HomeController extends AbstractController {

    @FXML
    private Label lblDate;

    @FXML
    private TableView<Jidlo> jidlaTable;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<Jidlo, String> receptColumn;

    @FXML
    private TableColumn<Jidlo, Integer> porceColumn;

    @FXML
    private TableColumn<Jidlo, Integer> cenaColumn;

    private Jidlo jidlo;
    private Calendar calendar;
    private SimpleDateFormat dateFmt = new SimpleDateFormat("dd.MM.yyyy");
    private ObservableList<Jidlo> jidlaData;


    @FXML
    private void initialize() {
        calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String datum = dateFmt.format(calendar.getTime());
        lblDate.setText(datum);

        receptColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getRecept().getNazev())
        );

        porceColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getPocetPorci()).asObject()
        );

        cenaColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getRecept().getCenaPorce()).asObject()
        );

        jidlaTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> jidlo = newValue
        );

        // dvojklikem redirect na edit jidla
        jidlaTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                jidlo = jidlaTable.getSelectionModel().getSelectedItem();
                editJidlo();
            }
        });

        initJidloTable(calendar.getTime());
    }

    @FXML
    public void datePLus() {
        calendar.add(Calendar.DATE, +1);
        String datum = dateFmt.format(calendar.getTime());
        lblDate.setText(datum);
        initJidloTable(calendar.getTime());
    }

    @FXML
    public void dateMinus() {
        calendar.add(Calendar.DATE, -1);
        String datum = dateFmt.format(calendar.getTime());
        lblDate.setText(datum);
        initJidloTable(calendar.getTime());
    }

    @FXML
    public void newJidlo() {
        JidloController controller = redirectToJidlo();

        LocalDateTime ldtm = LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
        controller.getDpDate().setValue(ldtm.toLocalDate());
    }

    @FXML
    public void editJidlo() {
        if (jidlo != null){
            JidloController controller = redirectToJidlo();
            controller.setJidlo(jidlo);
        }
    }

    @FXML
    public void deleteJidlo() {
        if (jidlo != null){
            PersistenceManager.deleteJidlo(jidlo);
            jidlaData.remove(jidlo);
        }
    }

    @FXML
    public void dateSelected() {

        // nastaveni textu na labelu pro zobrazeni data
        LocalDate localDate = datePicker.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        lblDate.setText(dateFmt.format(date));

        // nastaveni dne z datepickeru do hlavni promenne hlidajici den
        calendar.setTime(date);

        // refresh tabulky s jidly
        initJidloTable(date);
    }

    private void initJidloTable(Date date){

        jidlaData = FXCollections.observableArrayList(
                PersistenceManager.getAllJidloForDate(date)
        );

        jidlaTable.setItems(jidlaData);
    }

}
