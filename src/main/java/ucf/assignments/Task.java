/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Patrick Iannini
 */
package ucf.assignments;
import javafx.scene.control.CheckBox;

public class Task {
    String task;
    int dueDate;
    boolean completed;

    public Task(String task, int dueDate, boolean completed) {
        this.task = task;
        this.dueDate = dueDate;
        this.completed = completed;
    }
    public Task(String task, int dueDate) {
        this.task = task;
        this.dueDate = dueDate;
        //this.completed = new CheckBox();
    }
    public Task() {}

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
