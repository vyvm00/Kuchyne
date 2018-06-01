package util;

import javafx.scene.control.Alert;

import java.util.Calendar;
import java.util.Date;

/**
 * Abstraktní třída obsahující pomocné statické metody
 */
public abstract class Utils {

    /**
     * vrací instanci třídy Date posunutou o zadaný počet dnů
     * @param date
     * @return
     */
    public static Date getDateShiftedOfDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }


    /**
     * Pokusí se vrátit čísenou hodnotu NumberTextField. Defaultně vrací nulu
     * @param tf
     * @return
     */
    public static int tryToParseIntFromNumberTextField(NumberTextField tf){
        int res = 0;

        try {
            res = Integer.parseInt(tf.getText());
        } catch (Exception e){
            //e.printStackTrace();
        }

        return res;
    }


    public static void showAlert(String title, String header, String warning){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(warning);

        alert.showAndWait();
    }


}
