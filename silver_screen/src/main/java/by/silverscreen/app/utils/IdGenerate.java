package by.silverscreen.app.utils;

public class IdGenerate {

    private static Long nextId = 0L;

    public static Long getNextId() {
        nextId++;
        return nextId;
    }
}
