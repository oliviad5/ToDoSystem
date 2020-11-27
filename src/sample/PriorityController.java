package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.model.Priority;
import sample.model.db.AbstractDatabase;
import sample.model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PriorityController {
    public ListView<Priority> priorityListView;
    public TextField nameTextField;
    public Priority selectedItem = null;

    public void initialize(){
        priorityListView.setItems(Priority.getList());
    }

    public void itemSelected(MouseEvent mouseEvent) {
       Priority p = priorityListView.getSelectionModel().getSelectedItem();
       if(p != null){
           nameTextField.setText(p.getName());
           selectedItem = p;
       }
    }

    public void saveClicked(ActionEvent actionEvent) {
        try {
        if(selectedItem != null) {
            //update existing item
            selectedItem.setName(nameTextField.getText());
                PreparedStatement statement = Priority.getConn().getConnection().prepareStatement("UPDATE gr4_priority SET description = '"+selectedItem.getName()+"' WHERE priority_id = "+ selectedItem.getId());
                statement.executeUpdate();
                priorityListView.setItems(Priority.getList());

        }else{
            //insert new
            PreparedStatement statement = Priority.getConn().getConnection().prepareStatement("INSERT INTO gr4_priority (description) VALUES ('"+nameTextField.getText()+"')");
            statement.executeUpdate();
            priorityListView.setItems(Priority.getList());
        }
        nameTextField.clear();
        selectedItem = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelClicked(ActionEvent actionEvent) {
        //close dialog
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void deleteClicked(ActionEvent actionEvent) {
        if(selectedItem != null) {
            //delete item
            PreparedStatement statement = null;
            try {
                statement = Priority.getConn().getConnection().prepareStatement("DELETE FROM gr4_priority WHERE priority_id = "+ selectedItem.getId());
                statement.executeUpdate();
                priorityListView.setItems(Priority.getList());
                nameTextField.clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            }
        selectedItem = null;

    }

    public void newClicked(ActionEvent actionEvent) {
        selectedItem = null;
        nameTextField.clear();
        priorityListView.getSelectionModel().clearSelection();
    }
}
