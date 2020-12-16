package sample;

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

public class Controller {
    public ListView<ToDo> todoListView; //befüllen
    public ComboBox statusComboBox; //befüllen
    public ComboBox priorityComboBox; //befüllen
    public TextField todoTextField;

    public Pane contentPane;

    Parent root = null;


    public void initialize() {
        todoListView.setItems(ToDo.getList());
        statusComboBox.setItems(Status.getList());
        priorityComboBox.setItems(Priority.getList());
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
}
