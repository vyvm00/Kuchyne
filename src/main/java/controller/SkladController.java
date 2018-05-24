package controller;


import entity.SurovinaNaSklade;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.PersistenceManager;

import java.io.IOException;

public class SkladController extends AbstractController {

    @FXML
    private TableView<SurovinaNaSklade> surovinyTable;
    @FXML
    private TableColumn<SurovinaNaSklade, String> nazevColumn;
    @FXML
    private TableColumn<SurovinaNaSklade, String> mernaJednotkaColumn;
    @FXML
    private TableColumn<SurovinaNaSklade, Integer> mnozstviColumn;
    @FXML
    private TableColumn<SurovinaNaSklade, Integer> minMnozstviColumn;
    @FXML
    private TableColumn<SurovinaNaSklade, Integer> dokoupitColumn;

    private SurovinaNaSklade selectedSNS;

    @FXML
    private void initialize() {

        // dotazeni zdrojovych dat do tabulky
        ObservableList<SurovinaNaSklade> surovinyData = FXCollections.observableArrayList(
                PersistenceManager.getAllSurovinyNasklade()
        );

        surovinyTable.setItems(surovinyData);

        // nastaveni sloupcu tabulky
        nazevColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getSurovina().getNazev())
        );

        mernaJednotkaColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getSurovina().getMernaJednotka())
        );

        mnozstviColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getMnozstvi()).asObject()
        );

        minMnozstviColumn.setCellValueFactory(cellData ->
                new ReadOnlyIntegerWrapper(cellData.getValue().getMinimalniMnozstvi()).asObject()
        );

        dokoupitColumn.setCellValueFactory(cellData ->
                new ReadOnlyIntegerWrapper(getDokoupitValue(cellData.getValue())).asObject()
        );

        // nastaveni poradi radku
        dokoupitColumn.setSortType(TableColumn.SortType.DESCENDING);
        surovinyTable.getSortOrder().add(dokoupitColumn);
        nazevColumn.setSortType(TableColumn.SortType.ASCENDING);
        surovinyTable.getSortOrder().add(nazevColumn);

        // podle vybraneho radku nastavime surovinu
        surovinyTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectedSNS = newValue);

        // dvojklik na radek presmeruje na nakup
        surovinyTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                selectedSNS = surovinyTable.getSelectionModel().getSelectedItem();
                try { nakup(); } catch (IOException e) { e.printStackTrace(); }
            }
        });

    }

    @FXML
    public void nakup() throws IOException {
        if (selectedSNS != null){
            NakupController controller = redirectToNakup();
            controller.setSurovinaNaSklade(selectedSNS);
        }
    }

    private int getDokoupitValue(SurovinaNaSklade sns) {
        int min = sns.getMinimalniMnozstvi();
        int val = sns.getMnozstvi();
        return Math.max(min - val, 0);
    }

}
