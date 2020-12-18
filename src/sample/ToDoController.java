package sample;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.model.Priority;
import sample.model.Status;

public class ToDoController {
    public TextField nameTextField;
    public TextArea descriptionTextArea;
    public ComboBox<Status> statusComboBox;
    public ComboBox<Priority> priorityComboBox;
    private ToDo selected = null;
    private ListView<ToDo> todoList;

    public void setToDo(ToDo item) {
        selected = item;
        displayItem();
    }

    private void displayItem() {
        /**
         * Hier sollen die Daten vom "selected" angezeigt werden.
         */
        nameTextField.setText(selected.getName());
        descriptionTextArea.setText(selected.getDescription());

        statusComboBox.setItems(new Status().getList());
        priorityComboBox.setItems(new Priority().getList());

        int i = 0;
        for (i = 0; i < statusComboBox.getItems().size(); ++i) {
            if (statusComboBox.getItems().get(i).getId() == selected.getStatus_id()) {
                break;
            }
        }
        statusComboBox.getSelectionModel().select(i);
        i = 0;
        for (i = 0; i < priorityComboBox.getItems().size(); ++i) {
            if (priorityComboBox.getItems().get(i).getId() == selected.getPriority_id()) {
                break;
            }
        }
        priorityComboBox.getSelectionModel().select(i);


    }

    public void setToDoList(ListView<ToDo> list) {
        this.todoList = list;
    }


    public void deleteClicked(ActionEvent actionEvent) {
        selected.delete();
        todoList.refresh();
    }

    public void saveClicked(ActionEvent actionEvent) {
        if (selected != null) {
            //update existing item
            selected.setName(nameTextField.getText());
            selected.setDescription(descriptionTextArea.getText());
            selected.setPriority_id(priorityComboBox.getSelectionModel().getSelectedItem().getId());
            selected.setStatus_id(statusComboBox.getSelectionModel().getSelectedItem().getId());
            selected.update();

        } else {
            //insert new
            ToDo t = new ToDo(0, nameTextField.getText(), descriptionTextArea.getText(),
                    statusComboBox.getSelectionModel().getSelectedItem().getId(), priorityComboBox.getSelectionModel().getSelectedItem().getId());
            t.insert();
            todoList.getItems().add(t);
        }

        todoList.refresh();
    }

    public void newClicked(ActionEvent actionEvent) {
        selected = null;
        nameTextField.clear();
        descriptionTextArea.clear();
        priorityComboBox.getSelectionModel().clearSelection();
        statusComboBox.getSelectionModel().clearSelection();
        todoList.refresh();
    }
}
