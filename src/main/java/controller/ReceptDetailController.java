package controller;


import entity.Recept;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ReceptDetailController extends AbstractController {

    private Recept recept;


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


    public Recept getRecept() {
        return recept;
    }

    public void setRecept(Recept recept) {
        this.recept = recept;
    }
}
