package dev.herbidev.gestionsabsence.controllers.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnect {

    // datebase connexion
    private Connection cnx;
    //prepared query
    private PreparedStatement pstm;
    //select query
    private ResultSet rs;
    //update queries (INSERT INTO, UPDATE, DELETE
    private int ok;

    //Method for opening connexion
    public Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/gestion_absence";
        String user = "herbilot";
        String password = "123db";
        try {
            //chargement du pilot
            Class.forName("org.postgresql.Driver");
            //Ouverture de la connection
            cnx =DriverManager.getConnection(url, user, password);
            System.out.println("connexion établi avec succès !");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cnx;

    }

    public void initPrepar(String sql) {
        try {
            getConnection();
            pstm = cnx.prepareStatement(sql);

        }catch
        (Exception e) {
            e.printStackTrace();
        }
    }


    public ResultSet executeSelect() {

        rs = null;
        try {
            rs = pstm.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public int executeMaj() {
        try {
            ok = pstm.executeUpdate();

        }catch(Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    public void closeConnection() {
        try {
            if(cnx != null) {
                cnx.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getPstm() {
        return pstm;
    }
}
