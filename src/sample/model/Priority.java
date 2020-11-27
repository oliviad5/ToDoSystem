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
