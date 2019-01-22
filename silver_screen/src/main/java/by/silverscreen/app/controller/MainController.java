package by.silverscreen.app.controller;

import by.silverscreen.app.pojo.Human;
import by.silverscreen.app.utils.IdGenerate;
import by.silverscreen.app.utils.Utils;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainController extends Application {

    private Date today = new Date();
    private SimpleDateFormat simpleBithFormat = new SimpleDateFormat("MM-dd");
    private ObservableList<Human> humans = FXCollections.observableArrayList();
    private Human selected;

    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonDelete;
    @FXML
    private TableView<Human> tableHumans;
    @FXML
    private TableColumn<Human, String> nameColumn;
    @FXML
    private TableColumn<Human, Integer> ageColumn;
    @FXML
    private TableColumn<Human, Date> birthdayColumn;

    public ObservableList<Human> getHumans() {
        return humans;
    }

    @FXML
    private void initialize() {
        initData();

        tableHumans.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selected = (Human) newValue;
            }
        });
        buttonAdd.setOnAction(event -> {
            try {
                addNewHuman();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        buttonEdit.setOnAction(event -> {
            if (selected == null) {
                Utils.showWarning("Выберите строку для редактирования!");
            } else {
                try {
                    editHuman();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonDelete.setOnAction(event -> {
            deleteHuman();
        });
        birthdayVerification();

        nameColumn.setCellValueFactory(new PropertyValueFactory<Human, String>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Human, Integer>("age"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Human, Date>("birthday"));

        tableHumans.setItems(humans);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/fxml/main.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setTitle("JavaFX and Maven");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void addNewHuman() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Add new human");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add.fxml"));
        Parent root = loader.load();
        AddController controller = loader.getController();
        controller.setMainController(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void editHuman() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Edit human");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit.fxml"));
        Parent root = loader.load();
        EditController controller = loader.getController();
        controller.init(this, selected);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        this.selected = controller.getHuman();
    }

    public void deleteHuman() {
        if (selected == null) {
            Utils.showWarning("Выберите строку для удаления!");
        } else {
            humans.remove(selected);
        }
    }

    public void birthdayVerification() {
        tableHumans.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) &&
                    event.getClickCount() == 2 &&
                    selected.getBirthdayString(simpleBithFormat).equals(simpleBithFormat.format(today))
            ) {
                Utils.showWarning("Сегодня у " + selected.getName() + " день рождения.");
            }
        });
    }

    private void initData() {
        humans.add(new Human(IdGenerate.getNextId(), "Pavel", 27, new Date()));
    }
}
