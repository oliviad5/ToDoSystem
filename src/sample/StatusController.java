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
        try {
            if(selectedItem != null) {
                //update existing item
                selectedItem.setName(nameTextField.getText());
                PreparedStatement statement = Status.getConn().getConnection().prepareStatement("UPDATE gr4_status SET description = '"+selectedItem.getName()+"' WHERE status_id = "+ selectedItem.getId());
                statement.executeUpdate();
                statusListView.setItems(Status.getList());

            }else{
                //insert new
                PreparedStatement statement = Status.getConn().getConnection().prepareStatement("INSERT INTO gr4_status (description) VALUES ('"+nameTextField.getText()+"')");
                statement.executeUpdate();
                statusListView.setItems(Status.getList());
            }
            nameTextField.clear();
            selectedItem = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        if(selectedItem != null) {
            //delete item
            PreparedStatement statement = null;
            try {
                statement = Status.getConn().getConnection().prepareStatement("DELETE FROM gr4_status WHERE status_id = "+ selectedItem.getId());
                statement.executeUpdate();
                statusListView.setItems(Status.getList());
                nameTextField.clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        selectedItem = null;
    }
}
