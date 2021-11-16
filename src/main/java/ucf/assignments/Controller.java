/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Patrick Iannini
 */
package ucf.assignments;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Controller {
    final ObservableList<Task> taskList = FXCollections.observableArrayList();


    @FXML
    void importList(ActionEvent e) {
        FileChooser fc = new FileChooser();
        configureFileChooser(fc);
        File file = fc.showOpenDialog(null);
        if (file != null) {
            openFile(file);
        }
    }
    private void openFile(File file) {
        try {
            Scanner sc = new Scanner(file);
            int i=0;
            while (sc.hasNextLine()) {
                String arr[] = sc.nextLine().split(",");
                table.getItems().add(new Task(arr[0], Integer.parseInt(arr[1])));
            }
        } catch (IOException ex) {
            System.out.println("Could not find file " + file);
        }
    }

    @FXML
    void saveFile(ActionEvent e) throws IOException {
        FileChooser fc = new FileChooser();
        configureFileChooser(fc);
        File file = fc.showSaveDialog(null);
        if (file != null) {
            try {
                FileWriter fw = new FileWriter(file);
                for (Task i : taskList) {
                    fw.write(i.getTask()+","+i.getDueDate()+","+i.getCompleted()+"\n");
                }
                fw.close();
            } catch (IOException ex) {
                System.out.println("FileWriter error");
            }
        }
    }

    private void configureFileChooser(FileChooser fc) {
        fc.setTitle("Import or Export a ToDo List");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
    }

    // TODO - Table and list properties
    @FXML
    TableView<Task> table = new TableView<>();
            // test data
            //new Task("task 1", 9),
            //new Task("task 2", 9),
            //new Task("task 3", 9)


    //@FXML private TableColumn<Task, Integer> idC;
    @FXML private TableColumn<Task, String> taskC;
    @FXML private TableColumn<Task, Integer> dueDateC;
    @FXML private TableColumn<Task, Boolean> completedC;
    @FXML
    public void initialize() {
        table.setEditable(true);

        //idC.setVisible(false);
        //idC.setCellValueFactory(new PropertyValueFactory<>("id"));

        taskC.setCellValueFactory(new PropertyValueFactory<>("task"));
        taskC.setCellFactory(TextFieldTableCell.forTableColumn());

        dueDateC.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        dueDateC.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //completedC.setCellValueFactory(new PropertyValueFactory<>("completed"));
        completedC.setCellValueFactory(t -> {
            Task task = t.getValue();
            SimpleBooleanProperty b = new SimpleBooleanProperty(task.getCompleted());
            b.addListener(((observable, oldValue, newValue) -> task.setCompleted(newValue)));
            return b;
        });
        completedC.setCellFactory(t -> {
            CheckBoxTableCell<Task, Boolean> cell = new CheckBoxTableCell<>();
            return cell;
                });

        table.setItems(taskList);
    }

    void cellEdit(ActionEvent e) {
        taskC.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setTask(t.getNewValue())
        );
        dueDateC.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setDueDate(t.getNewValue())
        );
    }

    //@FXML Button newTask; //
    @FXML TextField taskField;
    @FXML TextField dueDateField;
    @FXML
    void newTask(ActionEvent e) {
        if (taskField.getCharacters() == null || dueDateField.getText() == null) {
            taskField.setText("Task cannot be null");
            dueDateField.setText("Due Date cannot be null");
            return;
        }
        Task task = new Task(taskField.getText(), Integer.parseInt(dueDateField.getText()));
        table.getItems().add(task);
    }
    @FXML
    void removeTask(ActionEvent e) {
        ObservableList<Task> item = table.getSelectionModel().getSelectedItems();
        table.getItems().remove(item);
    }



}
