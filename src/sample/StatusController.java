package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.model.Priority;
import sample.model.Status;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatusController {
    public ListView<Status> statusListView;
    public TextField nameTextField;
    public Status selectedItem = null;

    public void initialize() {
        statusListView.setItems(Status.getList());
    }

    public void itemSelected(MouseEvent mouseEvent) {
        Status s = statusListView.getSelectionModel().getSelectedItem();
        if (s != null) {
            nameTextField.setText(s.getName());
            selectedItem = s;
        }
    }

    public void saveClicked(ActionEvent actionEvent) {

        if (selectedItem != null) {
            //update existing item
            selectedItem.setName(nameTextField.getText());
            selectedItem.update();
            statusListView.setItems(Status.getList());

        } else {
            //insert new
            Status s = new Status(0, nameTextField.getText());
            s.insert();
            statusListView.setItems(Status.getList());
        }
        nameTextField.clear();
        selectedItem = null;

    }

    public void cancelClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void newClicked(ActionEvent actionEvent) {
        selectedItem = null;
        nameTextField.clear();
        statusListView.getSelectionModel().clearSelection();
    }

    public void deleteClicked(ActionEvent actionEvent) {
        if (selectedItem != null) {
            //delete item
            selectedItem.delete();
            statusListView.setItems(Status.getList());
            nameTextField.clear();
        }
        selectedItem = null;
    }
}
