package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Project {

    private final String name;
    private final String description;
    private final List<Sprint> sprints = new ArrayList<>();

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addSprint(Sprint sprint) {
        sprints.add(sprint);
    }

    public void removeSprint(Sprint sprint) {
        sprints.remove(sprint);
    }

    public List<Sprint> listSprints() {
        return Collections.unmodifiableList(sprints);
    }

    public String getName() {
        return name;
    }
}
