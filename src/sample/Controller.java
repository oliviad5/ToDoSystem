package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.model.Priority;
import sample.model.Status;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    public ListView<ToDo> todoListView; //befüllen
    public ComboBox<Status> statusComboBox; //befüllen
    public ComboBox<Priority> priorityComboBox; //befüllen
    public TextField todoTextField;

    public Pane contentPane;

    Parent root = null;


    public void initialize() {
        todoListView.setItems(ToDo.getList());
        Status s = new Status(-1, "Keine Filterauswahl");
        Priority p = new Priority(-1, "Keine Filterauswahl");
        statusComboBox.setItems(Status.getList());
        statusComboBox.getItems().add(s);
        statusComboBox.getSelectionModel().select(s);
        priorityComboBox.setItems(Priority.getList());
        priorityComboBox.getItems().add(p);
        priorityComboBox.getSelectionModel().select(p);
    }

    public void onStatusClicked(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("status.fxml"));

            Stage s = new Stage();
            s.setTitle("Stati");
            s.setScene(new Scene(root));
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onPriorityClicked(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("priority.fxml"));

            Stage s = new Stage();
            s.setTitle("Prioritäten");
            s.setScene(new Scene(root));
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onUserClicked(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("user.fxml"));

            Stage s = new Stage();
            s.setTitle("User");
            s.setScene(new Scene(root));
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void onToDoClicked(MouseEvent mouseEvent) {
        ToDo selectedElement = todoListView.getSelectionModel().getSelectedItem();

        if (selectedElement != null) {
            /**
             * Stelle die Daten des gewählten ToDos auf der rechten Seite dar
             */
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("toDo.fxml"));
                Parent root = loader.load();

                ToDoController controller = (ToDoController) loader.getController();
                controller.setToDo(selectedElement);
                controller.setToDoList(todoListView.getItems());
                contentPane.getChildren().add(root);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void statusComboBoxChanged(ActionEvent actionEvent) {
        ObservableList<ToDo> filteredList;
        filteredList = ToDo.getFilteredList(statusComboBox.getSelectionModel().getSelectedItem().getId(), priorityComboBox.getSelectionModel().getSelectedItem().getId());

        todoListView.setItems(filteredList);
    }

    public void priorityComboBoxChanged(ActionEvent actionEvent) {
        ObservableList<ToDo> filteredList = ToDo.getFilteredList(statusComboBox.getSelectionModel().getSelectedItem().getId(), priorityComboBox.getSelectionModel().getSelectedItem().getId());
        todoListView.setItems(filteredList);
    }
}
