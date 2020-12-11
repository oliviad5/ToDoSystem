package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.db.AbstractDatabase;
import sample.model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Priority {

    private int id;
    private String name;

    public static AbstractDatabase getConn() {
        if (conn == null){
            conn = new MySQLConnector("d0345762", "5AHEL2021","rathgeb.at",3306,"d0345762");
        }
        return conn;
    }

    private static AbstractDatabase conn = null;

    public Priority(){

    }

    public Priority(int id, String name) {
        this.id = id;
        this.name = name;
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

    public void update(){
        try {
            PreparedStatement statement = Priority.getConn().getConnection().prepareStatement("UPDATE gr4_priority SET description = '"+name+"' WHERE priority_id = "+ id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insert(){

        try {
            PreparedStatement statement = Priority.getConn().getConnection().prepareStatement("INSERT INTO gr4_priority (description) VALUES ('"+name+"')");

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        PreparedStatement statement = null;
        try {
            statement = Priority.getConn().getConnection().prepareStatement("DELETE FROM gr4_priority WHERE priority_id = "+ id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public static ObservableList<Priority> getList(){
        ObservableList<Priority> list = FXCollections.observableArrayList();

       // conn = new MySQLConnector("d0345762", "5AHEL2021","rathgeb.at",3306,"d0345762");

        PreparedStatement statement = null;
        try {
            statement = getConn().getConnection().prepareStatement("SELECT * FROM gr4_priority");

            ResultSet results = statement.executeQuery();

            while (results.next()){
                Priority temp = new Priority(results.getInt("priority_id"),results.getString("description"));

                list.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return list;
    }

}
