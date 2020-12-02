package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.model.Priority;
import sample.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserController {

    public ListView<User> userListView;
    public User selectedItem = null;
    public TextField nameTextField;
    public TextField strasseTextField;
    public TextField hausnrTextField;
    public TextField plzTextField;
    public TextField ortTextField;

    /**
     * Beim öffnen des Fensters werden die Daten aus der Datenbank geladen und in der
     * Listview angezeigt.
     */
    public void initialize() {
        userListView.setItems(User.getList());
    }

    private void clearTextField() {
        nameTextField.clear();
        ortTextField.clear();
        plzTextField.clear();
        hausnrTextField.clear();
        strasseTextField.clear();
    }

    /**
     * Wird ein Bearbeiter in der Liste gewaehlt, so werden die zugehoerigen
     * Daten im Textfeld angezeigt.     *
     *
     * @param mouseEvent
     */
    public void itemSelected(MouseEvent mouseEvent) {
        User u = userListView.getSelectionModel().getSelectedItem();
        if (u != null) {
            nameTextField.setText(u.getName());
            strasseTextField.setText(u.getStrasse());
            hausnrTextField.setText(String.valueOf(u.getHausnr()));
            plzTextField.setText(String.valueOf(u.getPlz()));
            ortTextField.setText(u.getOrt());
            selectedItem = u;
        }
    }

    /**
     * Wird der Speicherbutton geklickt, so werden entweder die abgeaenderten
     * Daten gespeichert, oder ein neuer Benutzer hinzugefuegt.
     *
     * @param actionEvent
     */


    public void saveClicked(ActionEvent actionEvent) {


        if (selectedItem != null) {
            //update existing item
            selectedItem.setName(nameTextField.getText());
            selectedItem.setHausnr(Integer.parseInt(hausnrTextField.getText()));
            selectedItem.setOrt(ortTextField.getText());
            selectedItem.setPlz(Integer.parseInt(plzTextField.getText()));
            selectedItem.setStrasse(strasseTextField.getText());
            User.updateList(selectedItem);
        } else {

            User u = new User(0, nameTextField.getText(), strasseTextField.getText(), Integer.parseInt(hausnrTextField.getText()), Integer.parseInt(plzTextField.getText()), ortTextField.getText());
            User.addItem(u);
        }
        userListView.setItems(User.getList());
        clearTextField();
        selectedItem = null;


    }

    /**
     * Wird der Cancel Button geklickt, so wird das Fenster geschlossen
     * und man ist wieder im Hauptfenster.
     *
     * @param actionEvent
     */
    public void cancelClicked(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }

    /**
     * Wird Neu geklickt, wo werden die Textfelder  geleert und der Fokus
     * in der ListView wird zurueckgesetzt. Ebenso wird das selectedItem auf null gesetzt.
     *
     * @param actionEvent
     */
    public void newClicked(ActionEvent actionEvent) {
        selectedItem = null;
        clearTextField();
        userListView.getSelectionModel().clearSelection();
    }

    /**
     * Wird Loeschen geklickt, so wird der gewaehlte Benutzer aus der Datenbank geloescht.
     *
     * @param actionEvent
     */

    public void deleteClicked(ActionEvent actionEvent) {
        if (selectedItem != null) {
            //delete item
            User.deleteItemFromList(selectedItem);
            userListView.setItems(User.getList());
            clearTextField();
        }
        selectedItem = null;
    }
}


