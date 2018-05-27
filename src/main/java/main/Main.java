package main;

import controller.HomeController;
import entity.*;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main extends Application {

    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        initRootLayout();
        initDummyDB();
    }

    /**
     * Zobrazí hlavní okno
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/home.fxml"));
            Parent rootLayout = loader.load();

            HomeController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initDummyDB(){

        SessionFactory sessionsFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionsFactory.openSession();
        session.beginTransaction();

        Sklad sklad = /*session.get(Sklad.class, 1);/*/new Sklad("Hlavní sklad", "Hospodská 27", 814);

        Surovina mouka = new Surovina("mouka","gram");
        Surovina drozdi = new Surovina("drozdi","gram");
        Surovina voda = new Surovina("voda","litr");
        Surovina pivo = new Surovina("pivo","pinta");
        Surovina cukr = new Surovina("cukr","lžíce");
        Surovina sul = new Surovina("sůl","gram");
        Surovina rajce = new Surovina("rajče","ks");
        Surovina mrkev = new Surovina("mrkev","ks");
        Surovina vejce = new Surovina("vejce","ks");
        Surovina nedostatkove = new Surovina("nedostatkove","ks");

        sklad.addSurovina(mouka, 1000);
        sklad.addSurovina(drozdi, 1000);
        sklad.addSurovina(voda, 1000);
        sklad.addSurovina(pivo, 1000);
        sklad.addSurovina(cukr, 1000);
        sklad.addSurovina(sul, 1000);
        sklad.addSurovina(rajce, 1000);
        sklad.addSurovina(mrkev, 1000);
        sklad.addSurovina(vejce, 1000);
        sklad.addSurovina(nedostatkove, 0);

        session.persist(sklad);

        Recept rizek = new Recept("Kuřecí řízek", "aaa", 1,2);
        rizek.addSurovina(mouka, 10);
        rizek.addSurovina(rajce, 5);
        rizek.addSurovina(sul,1);
        session.persist(rizek);

        Recept chleba = new Recept("Chleba s máslem", "bbb", 1,2);
        chleba.addSurovina(voda, 4);
        chleba.addSurovina(mouka, 40);
        chleba.addSurovina(sul,3);
        session.persist(chleba);

        Recept svickova = new Recept("Svickova", "cccc", 1,2);
        svickova.addSurovina(drozdi, 7);
        svickova.addSurovina(pivo,32);
        svickova.addSurovina(cukr,62);
        svickova.addSurovina(mrkev,3);
        session.persist(svickova);

        Recept gulas = new Recept("Gulas se sesti", "ddd", 1,2);
        gulas.addSurovina(nedostatkove, 1);
        session.persist(gulas);

        session.getTransaction().commit();
        session.close();
    }

}
