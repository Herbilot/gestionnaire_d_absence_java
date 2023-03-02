package dev.herbidev.gestionsabsence.controllers;

import dev.herbidev.gestionsabsence.controllers.database.DatabaseConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginControlleur {
    public Label type_compte_label;
    public ChoiceBox type_compte;
    public Label matricule_label;
    public TextField matricule_entry;
    public Label mdp_label;
    public PasswordField mdp_entry;
    public Label error_label;
    public Button login_btn;

    public void loginBtnAction(ActionEvent e){
        error_label.setText("no error to show");

        if (matricule_entry.getText().isBlank() == false && mdp_entry.getAccessibleHelp().isBlank() == false){
            error_label.setText("no error to show");
            validateLogin();
        }else{
            error_label.setText(" error to show");
        }
    }

    public void validateLogin(){
        DatabaseConnect db = new DatabaseConnect();
        Connection connectDB = db.getConnection();

        String sql = "SELECT count(1) from utilisateur Where matricule = '" + matricule_entry.getText() + "' AND mot_de_passe = '" + mdp_entry.getText() + "' ";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(sql);

            while(queryResult.next()){
                if(queryResult.getInt(1) ==1){
                    professeur();
                }else{
                    error_label.setText("Nom d'utilisateur ou mot de passe incorret !");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void professeur() throws Exception {

        Stage stage0 = (Stage) login_btn.getScene().getWindow();
        stage0.close();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/professeur/Professeur.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Gestionnaire - d'absences");
        stage.setScene(new Scene(root));
        stage.show();



    }
}
