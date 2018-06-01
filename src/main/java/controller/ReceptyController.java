package controller;

import entity.Recept;
import entity.SurovinaVReceptu;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.PersistenceManager;

import java.io.IOException;
import java.util.StringJoiner;

public class ReceptyController extends AbstractController {

    @FXML
    private TableView<Recept> receptyTable;
    @FXML
    private TableColumn<Recept, String> nazevColumn;
    @FXML
    private TableColumn<Recept, String> casPripravyColumn;
    @FXML
    private TableColumn<Recept, String> surovinyColumn;

    private Recept recept;

    @FXML
    private void initialize() {

        ObservableList<Recept> receptyData = FXCollections.observableArrayList(
                PersistenceManager.getAllRecepty()
        );

        receptyTable.setItems(receptyData);

        nazevColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getNazev())
        );

        casPripravyColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getCasPripravy()))
        );

        surovinyColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(getSeznamSurovinAsString(cellData.getValue()))
        );

        receptyTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> recept = newValue);


        receptyTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                recept = receptyTable.getSelectionModel().getSelectedItem();
                editRecept();
            }
        });

    }

    @FXML
    private void novyRecept() {
        redirectToReceptDetail();
    }

    @FXML
    private void editRecept() {

        ReceptDetailController controller = redirectToReceptDetail();
        controller.setRecept(recept);
    }

    /**
     * vrati stringovy seznam surovin receptu oddelenych carkou
     * @param r
     * @return
     */
    private String getSeznamSurovinAsString(Recept r) {
        StringJoiner joiner = new StringJoiner(", ");

        for (SurovinaVReceptu s : r.getSuroviny()) {
            joiner.add(s.getSurovina().getNazev());
        }
        return joiner.toString();
    }




}
