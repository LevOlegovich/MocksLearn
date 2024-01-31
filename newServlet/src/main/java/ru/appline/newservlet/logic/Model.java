package ru.appline.newservlet.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {

    private static final Model instance = new Model();
    private final Map<Integer, User> model;

    public static Model getInstance() {

        return instance;
    }

    private Model() {
        model = new HashMap<>();
        model.put(1, new User("Ivan", "Ivanov", 55555));
        model.put(2, new User("Петр", "Петров", 66666));
        model.put(3, new User("Александра", "Александрова", 77777));
        model.put(4, new User("Николай", "Николаев", 88888));
    }

    public void add(User user, int id) {

        model.put(id, user);
    }

    public Map<Integer, User> getFromList() {

        return model;
    }
}
