package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sprint {

    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final List<Task> tasks;

    public Sprint(String name, LocalDate startDate, LocalDate endDate) {
        this(name, startDate, endDate, new ArrayList<>());
    }

    private Sprint(String name, LocalDate startDate, LocalDate endDate, List<Task> tasks) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tasks = Collections.unmodifiableList(tasks);
    }

    public Sprint withTaskAdded(Task task) {
        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.add(task);
        return new Sprint(this.name, this.startDate, this.endDate, newTasks);
    }

    public Sprint withTaskRemoved(Task task) {
        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.remove(task);
        return new Sprint(this.name, this.startDate, this.endDate, newTasks);
    }

    public List<Task> listTasks() {
        return tasks;
    }

    public String getName() {
        return name;
    }
}
