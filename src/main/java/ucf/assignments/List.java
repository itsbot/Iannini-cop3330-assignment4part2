/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Patrick Iannini
 */
package ucf.assignments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class List implements Serializable {
    /*
    String title;
    Task t1 = new Task(1,"Fix program", 12, false);
    static ArrayList<Task> taskList = new ArrayList<Task>();
    public List(){
        taskList.add(t1);
    }
     */
    String title;
    //private ArrayList<Task> taskList;
    final ObservableList<Task> taskList = FXCollections.observableArrayList();

    public ObservableList<Task> getTaskList() {
        return taskList;
    }

}

