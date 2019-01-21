package by.silverscreen.app.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Human {

    private String name;
    private int age;
    private Date birthday;

    public Human() {
    }

    public Human(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBithString(SimpleDateFormat simpleBithFormat) {
        return simpleBithFormat.format(birthday);
    }
}

