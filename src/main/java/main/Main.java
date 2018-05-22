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

import java.util.Date;


public class Main extends Application {

    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        initRootLayout();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/home.fxml"));
            Parent rootLayout = loader.load();

            HomeController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            // Show the scene containing the root layout.
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

    private void doHibernateStuff(){
        SessionFactory sessionsFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionsFactory.openSession();
        session.beginTransaction();

        Sklad sklad = new Sklad("skladka", "za rohem", 814);

        Surovina mouka = new Surovina("mouka","gram");
        Surovina drozdi = new Surovina("drozdi","gram");
        Surovina voda = new Surovina("voda","litr");
        Surovina pivo = new Surovina("pivo","litr");

        Recept bramboracka = new Recept("Bramboracka","Uvařit v hrnci",50, 99);

        Jidlo obed = new Jidlo(new Date(), 99, bramboracka);

        sklad.addSurovina(mouka, 200);
        sklad.addSurovina(drozdi, 5);
        sklad.addSurovina(voda, 500);
        sklad.addSurovina(pivo, 120);

        bramboracka.addSurovina(voda, 20);
        bramboracka.addSurovina(pivo, 30);

        session.persist(sklad);
        session.persist(bramboracka);
        session.persist(obed);



        Recept recept = session.get(Recept.class, 1);
        System.out.println("Suroviny v " + recept.getNazev() + " ");
        for (Surovina s : recept.getAllSuroviny()) {
            System.out.println("- " + s.getNazev());
        }

        Sklad ss = session.get(Sklad.class,1);
        System.out.println("Suroviny v " + ss.getNazev() + " ");
        for (Surovina s : ss.getAllSuroviny()) {
            System.out.println("- " + s.getNazev());
        }


        session.getTransaction().commit();
        System.exit(0);
    }





}
