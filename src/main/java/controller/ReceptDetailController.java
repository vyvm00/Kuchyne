package controller;


import entity.Recept;
import entity.Surovina;
import entity.SurovinaVReceptu;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import service.PersistenceManager;
import util.NumberTextField;
import util.Utils;

import java.util.Optional;

public class ReceptDetailController extends AbstractController {

    @FXML
    private Label lbNazev;
    @FXML
    private TextField tfNazev;
    @FXML
    private NumberTextField tfCenaPorce;
    @FXML
    private NumberTextField tfCasPripravy;
    @FXML
    private NumberTextField tfMnozstvi;
    @FXML
    private TextArea taPopis;
    @FXML
    private ChoiceBox<Surovina> cbSurovina;
    @FXML
    private TableView<SurovinaVReceptu> tableSuroviny;
    @FXML
    private TableColumn<SurovinaVReceptu, String> nazevColumn;
    @FXML
    private TableColumn<SurovinaVReceptu, Integer> pocetColumn;

    private Recept recept;
    private SurovinaVReceptu svr;
    private Surovina surovina;

    @FXML
    private void initialize() {

        initSurovinyTable();
        initComboBox();

        taPopis.setWrapText(true);

        if (recept == null) recept = new Recept();
    }


    private void initSurovinyTable() {

        // při prvním zavolání je ještě null. Suroviny se dotáhnou až po nasetování receptu
        if (recept != null){
            tableSuroviny.setItems(FXCollections.observableArrayList( recept.getSuroviny()));
        }

        nazevColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getSurovina().getNazev())
        );

        pocetColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getMnozstvi()).asObject()
        );

        tableSuroviny.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> svr = newValue
        );

        // dvojklikem na surovinu bude umožněna editace suroviny v receptu
        tableSuroviny.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                tfMnozstvi.setText(Integer.toString(svr.getMnozstvi()));
                cbSurovina.getSelectionModel().select(svr.getSurovina());
            }
        });
    }

    private void initComboBox() {
        ObservableList<Surovina> suroviny = FXCollections.observableArrayList(
                PersistenceManager.getAllSuroviny()
        );
        cbSurovina.setItems(suroviny);

        // vybere prvni prvek z comboboxu
        if (suroviny != null && suroviny.size() > 0){
            surovina = suroviny.get(0);
            cbSurovina.getSelectionModel().selectFirst();
        }

        // prirazeni vybraneho prvku z comboboxu
        cbSurovina.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Surovina>)
                (arg0, oldVal, newVal) -> surovina = newVal
        );
    }


    @FXML
    public void saveRecept() {
        recept.setCenaPorce(Utils.tryToParseIntFromNumberTextField(tfCenaPorce));
        recept.setCasPripravy(Utils.tryToParseIntFromNumberTextField(tfCasPripravy));
        recept.setNazev(tfNazev.getText());
        recept.setPopis(taPopis.getText());

        PersistenceManager.saveRecept(recept);
        redirectToRecepty();
    }

    @FXML
    public void removeRecept() {

        // pokud jsou na receptu navazana jidla, zobrazi se nejprve potvrzovaci dialogove okno
        if(PersistenceManager.isExistJidloFromRecept(recept)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Smazání receptu");
            alert.setHeaderText("Z tohoto receptu jsou vytvořena jídla. Smazáním receptu dojde k odstranění těchto jídel.");
            alert.setContentText("Přesto smazat?");
            Optional<ButtonType> result = alert.showAndWait();

            // pokud uzivatel nepotvrdil, nic se mazat nebude
            if (result.get() != ButtonType.OK) return;
        }

        PersistenceManager.deleteRecept(recept);
        redirectToRecepty();

    }

    @FXML
    public void removeSurovina() {

        // na mapovani receptu musi byt zapnuto orphan-removal
        recept.removeSurovina(svr.getSurovina());

        tableSuroviny.setItems(FXCollections.observableArrayList(
                recept.getSuroviny()
        ));
    }

    @FXML
    public void addSurovina() {

        if (recept == null) recept = new Recept();

        int mnozstvi = Utils.tryToParseIntFromNumberTextField(tfMnozstvi);

        // pokud jiz surovina je na recept navazana, jen updatujeme mnozstvi
        for (SurovinaVReceptu is : recept.getSuroviny()) {

            if (is.getSurovina().equals(surovina)){
                is.setMnozstvi(mnozstvi);
                tableSuroviny.setItems(FXCollections.observableArrayList( recept.getSuroviny()));
                tableSuroviny.refresh();
                return;
            }

        }

        // jinak vytvor a pridej novou surovinu do receptu (NOVA surovina)
        recept.addSurovina(surovina, mnozstvi);

        tableSuroviny.setItems(FXCollections.observableArrayList(
                recept.getSuroviny()
        ));

    }

    public void setRecept(Recept recept) {
        this.recept = recept;
        lbNazev.setText(recept.getNazev());
        tfNazev.setText(recept.getNazev());
        taPopis.setText(recept.getPopis());
        tfCasPripravy.setText(Integer.toString(recept.getCasPripravy()));
        tfCenaPorce.setText(Integer.toString(recept.getCenaPorce()));
        initSurovinyTable();
    }


}
