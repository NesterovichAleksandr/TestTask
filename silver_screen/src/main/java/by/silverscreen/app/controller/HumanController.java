package by.silverscreen.app.controller;

import by.silverscreen.app.pojo.Human;
import by.silverscreen.app.utils.Utils;

import java.util.Date;

public abstract class HumanController {

    protected MainController mainController;

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public Human saveHuman(String name, String age, Date birthday, Human human) {

        if (name == null || name.isEmpty()) {
            Utils.showWarning("Введите имя Human!");
        } else if (age == null || age.isEmpty()) {
            Utils.showWarning("Введите возраст Human!");
        } else if (!Utils.controlAge(age)) {
            Utils.showWarning("Введите корректный возраст Human!");
        } else if (birthday == null) {
            Utils.showWarning("Введите дату рождения Human!");
        } else if (birthday.after(new java.util.Date())) {
            Utils.showWarning("Human ещё не родился!");
        } else {
            human.setName(name);
            human.setBirthday(birthday);
            human.setAge(Integer.valueOf(age));
            return human;
        }
        return null;
    }

    protected void addToList(Human human) {
        this.mainController.getHumans().add(human);
    }
}

