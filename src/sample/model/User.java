package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.db.AbstractDatabase;
import sample.model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String name;
    private String strasse;
    private int hausnr;
    private int plz;
    private String ort;

    private static AbstractDatabase conn = null;

    public static AbstractDatabase getConn() {
        if (conn == null) {
            conn = new MySQLConnector("d0345762", "5AHEL2021", "rathgeb.at", 3306, "d0345762");
        }
        return conn;
    }


    public User(int id, String name, String strasse, int hausnr, int plz, String ort) {
        this.id = id;
        this.name = name;
        this.strasse = strasse;
        this.hausnr = hausnr;
        this.plz = plz;
        this.ort = ort;
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

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getHausnr() {
        return hausnr;
    }

    public void setHausnr(int hausnr) {
        this.hausnr = hausnr;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Override
    public String toString() {
        return name;
    }

    public  void update() {
        try {
            System.out.println("update List");
            PreparedStatement statement = getConn().getConnection().prepareStatement("UPDATE gr4_bearbeiter SET name = '" + getName() + "' WHERE bearbeiter_id = " + getId());
            statement.executeUpdate();
            statement = getConn().getConnection().prepareStatement("UPDATE gr4_bearbeiter SET strasse = '" + getStrasse() + "' WHERE bearbeiter_id = " + getId());
            statement.executeUpdate();
            statement = getConn().getConnection().prepareStatement("UPDATE gr4_bearbeiter SET hausnummer = '" + getHausnr() + "' WHERE bearbeiter_id = " + getId());
            statement.executeUpdate();
            statement = getConn().getConnection().prepareStatement("UPDATE gr4_bearbeiter SET plz = '" + getPlz() + "' WHERE bearbeiter_id = " + getId());
            statement.executeUpdate();
            statement = getConn().getConnection().prepareStatement("UPDATE gr4_bearbeiter SET ort = '" + getOrt() + "' WHERE bearbeiter_id = " + getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert() {

        try {
             PreparedStatement statement = User.getConn().getConnection().prepareStatement("INSERT INTO gr4_bearbeiter (name,strasse,hausnummer,plz,ort) " +
                     "VALUES ('"+ getName() +"','"+ getStrasse() +"','"+ getHausnr() +"','"+ getPlz() +"','"+ getOrt()+"')");
                statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(){
        PreparedStatement statement = null;
        try {
            statement = getConn().getConnection().prepareStatement("DELETE FROM gr4_bearbeiter WHERE bearbeiter_id = "+ getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ObservableList<User> getList() {
        ObservableList<User> list = FXCollections.observableArrayList();

        //  conn = new MySQLConnector("d0345762", "5AHEL2021","rathgeb.at",3306,"d0345762");

        PreparedStatement statement = null;
        try {
            statement = getConn().getConnection().prepareStatement("SELECT * FROM gr4_bearbeiter");

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                User tmp = new User(results.getInt("bearbeiter_id"), results.getString("name"),
                        results.getString("strasse"), results.getInt("hausnummer"),
                        results.getInt("plz"), results.getString("ort"));

                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }


}
