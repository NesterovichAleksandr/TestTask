package by.silverscreen.app.utils;

import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static void showWarning(String text) {
        Alert warn = new Alert(Alert.AlertType.ERROR);
        warn.setTitle("Предупреждение!");
        warn.setHeaderText(text);
        warn.showAndWait();
    }

    public static boolean controlAge(String string) {
        Pattern pattern = Pattern.compile("[0-9]{1,3}");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
