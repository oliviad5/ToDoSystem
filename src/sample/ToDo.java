package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Priority;
import sample.model.Status;
import sample.model.db.AbstractDatabase;
import sample.model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToDo {
    int id;
    String name;
    String description;
    int status_id;
    int priority_id;

    public static AbstractDatabase getConn() {
        if (conn == null){
            conn = new MySQLConnector("d0345762", "5AHEL2021","rathgeb.at",3306,"d0345762");
        }
        return conn;
    }

    private static AbstractDatabase conn = null;

    public ToDo(int id, String name, String description, int status_id, int priority_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status_id = status_id;
        this.priority_id = priority_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getStatus_id() { return status_id; }

    public void setStatus_id(int status_id) { this.status_id = status_id; }

    public int getPriority_id() { return priority_id; }

    public void setPriority_id(int priority_id) { this.priority_id = priority_id; }

    public void delete(){
        PreparedStatement statement = null;
        try {
            statement = ToDo.getConn().getConnection().prepareStatement("DELETE FROM gr4_ToDo WHERE toDo_id = "+ id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(){
        try {
            PreparedStatement statement = Status.getConn().getConnection().prepareStatement("UPDATE gr4_ToDo SET name = '"+name+"' WHERE toDo_id = "+ id);
            statement.executeUpdate();
             statement = Status.getConn().getConnection().prepareStatement("UPDATE gr4_ToDo SET description = '"+description+"' WHERE toDo_id = "+ id);
            statement.executeUpdate();
             statement = Status.getConn().getConnection().prepareStatement("UPDATE gr4_ToDo SET status_id = '"+status_id+"' WHERE toDo_id = "+ id);
            statement.executeUpdate();
            statement = Status.getConn().getConnection().prepareStatement("UPDATE gr4_ToDo SET priority_id = '"+priority_id+"' WHERE toDo_id = "+ id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public String toString() {
        return name;
    }

    public static ObservableList<ToDo> getList(){
        ObservableList<ToDo> list = FXCollections.observableArrayList();

        // conn = new MySQLConnector("d0345762", "5AHEL2021","rathgeb.at",3306,"d0345762");

        PreparedStatement statement = null;
        try {
            statement = getConn().getConnection().prepareStatement("SELECT * FROM gr4_ToDo");

            ResultSet results = statement.executeQuery();

            while (results.next()){
                ToDo temp = new ToDo(results.getInt("toDo_id"),results.getString("name"),results.getString("description"),results.getInt("status_id"),results.getInt("priority_id"));

                list.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
