package by.silverscreen.app.controller;

import by.silverscreen.app.pojo.Human;
import by.silverscreen.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;

public class AddController {

    private MainController mainController;

    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField name;
    @FXML
    private TextField age;
    @FXML
    private DatePicker birthday;

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void cancelAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void saveNewHuman() {
        if ("".equals(name.getText())) {
            Utils.showWarning("Введите имя Human!");
        } else if ("".equals(age.getText())) {
            Utils.showWarning("Введите возраст Human!");
        } else if (!Utils.controlAge(age.getText())) {
            Utils.showWarning("Введите корректный возраст Human!");
        } else if (birthday.getValue() == null) {
            Utils.showWarning("Введите дату рождения Human!");
        } else if (Date.valueOf(birthday.getValue()).after(new java.util.Date())) {
            Utils.showWarning("Human ещё не родился!");
        } else {
            Human human = new Human(
                    name.getText(),
                    Integer.valueOf(age.getText()),
                    Date.valueOf(birthday.getValue())
            );
            addToList(human);
            cancelAction();
        }
    }

    private void addToList(Human human) {
        this.mainController.getHumans().add(human);
    }
}
