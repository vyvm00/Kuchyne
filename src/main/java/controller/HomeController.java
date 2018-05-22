package controller;


import javafx.event.ActionEvent;

import java.io.IOException;

public class HomeController extends AbstractController {


    public void datePLus(ActionEvent actionEvent) {
        System.out.println("++");
    }

    public void dateMinus(ActionEvent actionEvent) {
        System.out.println("--");
    }

    public void newJidlo(ActionEvent actionEvent) throws IOException {
        redirectToJidlo();
    }

    private void redirectToJidlo() throws IOException {
        redirectToView("/view/jidlo.fxml");
    }



}
