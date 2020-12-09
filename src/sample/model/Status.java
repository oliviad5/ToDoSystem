package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.db.AbstractDatabase;
import sample.model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Status {
    private int id;
    private String name;
    private static AbstractDatabase conn =null;

    public static AbstractDatabase getConn() {
        if (conn == null){
            conn = new MySQLConnector("d0345762", "5AHEL2021","rathgeb.at",3306,"d0345762");
        }
        return conn;
    }

public Status(){

}

    public Status(int id, String name) {
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
            PreparedStatement statement = Status.getConn().getConnection().prepareStatement("UPDATE gr4_status SET description = '"+name+"' WHERE status_id = "+ id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insert(){

        try {
            PreparedStatement statement = Status.getConn().getConnection().prepareStatement("INSERT INTO gr4_status (description) VALUES ('"+name+"')");

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        PreparedStatement statement = null;
        try {
            statement = Status.getConn().getConnection().prepareStatement("DELETE FROM gr4_status WHERE status_id = "+ id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public static ObservableList<Status> getList(){
        ObservableList<Status> list = FXCollections.observableArrayList();

       // AbstractDatabase conn = new MySQLConnector("d0345762","5AHEL2021","rathgeb.at",3306,"d0345762");
        try {
            PreparedStatement statement = getConn().getConnection().prepareStatement("SELECT * FROM gr4_status");

            ResultSet results = statement.executeQuery();

            while (results.next()){
                Status tmp = new Status(results.getInt("status_id"),results.getString("description"));

                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
