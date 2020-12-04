package sample;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.model.Priority;
import sample.model.Status;

public class ToDoController {
    public TextField nameTextField;
    public TextArea descriptionTextArea;
    public ComboBox<Status> statusComboBox;
    public ComboBox<Priority> priorityComboBox;
    private ToDo selected = null;

    public void setToDo(ToDo item) {
        selected = item;
        displayItem();
    }
    private void displayItem(){
        /**
         * Hier sollen die Daten vom "selected" angezeigt werden.
         */
        nameTextField.setText(selected.getName());
        descriptionTextArea.setText(selected.getDescription());

        statusComboBox.setItems(new Status().getList() );
        priorityComboBox.setItems(new Priority().getList());

        int i=0;
        for(i=0; i<statusComboBox.getItems().size();++i){
            if(statusComboBox.getItems().get(i).getId()==selected.getStatus_id()){
                break;
            }
        }
        statusComboBox.getSelectionModel().select(i);
        i=0;
        for(i=0; i<priorityComboBox.getItems().size();++i){
            if(priorityComboBox.getItems().get(i).getId()==selected.getPriority_id()){
                break;
            }
        }
        priorityComboBox.getSelectionModel().select(i);



    }


}
