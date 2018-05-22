package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.Main;

import java.io.IOException;

public class ReceptyController extends AbstractController {


    @FXML
    private void novyRecept(ActionEvent actionEvent) throws IOException {
        redirectToReceptDetail();
    }


    private void redirectToReceptDetail() throws IOException {
        redirectToView("/view/receptDetail.fxml");
    }





}
