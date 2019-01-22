package by.silverscreen.app.pojo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Human {

    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleIntegerProperty age = new SimpleIntegerProperty();
    private SimpleObjectProperty<Date> birthday = new SimpleObjectProperty<>();

    public Human() {
    }

    public Human(Long id, String name, int age, Date birthday) {
        this.id.set(id);
        this.name.set(name);
        this.age.set(age);
        this.birthday.set(birthday);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public Date getBirthday() {
        return birthday.get();
    }

    public SimpleObjectProperty<Date> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday.set(birthday);
    }

    public String getBirthdayString(SimpleDateFormat simpleBirthdayFormat) {
        return simpleBirthdayFormat.format(birthday.get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(id, human.id) &&
                Objects.equals(name, human.name) &&
                Objects.equals(age, human.age) &&
                Objects.equals(birthday, human.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, birthday);
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", name=" + name +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}

