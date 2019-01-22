package by.silverscreen.app.controller;

import by.silverscreen.app.pojo.Human;
import by.silverscreen.app.utils.ConvertorDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController extends HumanController {

    private Human human;

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

    public Human getHuman() {
        return human;
    }

    @FXML
    public void cancelAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void saveEditHuman() {
        Human human = saveHuman(name.getText(), age.getText(), ConvertorDate.convertLocalDateToDate(birthday.getValue()), this.human);
        if (human != null) {
            this.human = human;
            cancelAction();
        }
    }

    void init(MainController mainController, Human human) {
        this.mainController = mainController;
        this.human = human;
        this.name.textProperty().setValue(human.getName());
        this.age.textProperty().setValue(human.getAge() + "");
        this.birthday.valueProperty().setValue(ConvertorDate.convertDateToLocaleDate(human.getBirthday()));
    }
}
