package controller;


import javafx.event.ActionEvent;

import java.io.IOException;

public class ReceptDetailController extends AbstractController {


    public void saveRecept(ActionEvent actionEvent) throws IOException {
        System.out.println("ukladam recept");
        redirectToRecepty();
    }

    public void removeRecept(ActionEvent actionEvent) throws IOException {
        System.out.println("mazu recept");
        redirectToRecepty();
    }

    public void removeSurovina(ActionEvent actionEvent) {
        System.out.println("mazu surovinu");
    }

    public void addSurovina(ActionEvent actionEvent) {
        System.out.println("pridavam surovinu");
    }
}
