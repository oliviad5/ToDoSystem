package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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


    public void initialize(){
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
}
