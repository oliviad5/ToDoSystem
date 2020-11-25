package sample;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.model.Priority;

public class PriorityController {
    public ListView<Priority> priorityListView;
    public TextField nameTextField;

    public void initialize(){
        priorityListView.setItems(Priority.getList());
    }
    public void itemSelected(MouseEvent mouseEvent) {
       Priority p = priorityListView.getSelectionModel().getSelectedItem();
       if(p != null){
           nameTextField.setText(p.getName());
       }
    }
}
