package dev.herbidev.gestionsabsence.controllers;

import dev.herbidev.gestionsabsence.controllers.database.DatabaseConnect;
import dev.herbidev.gestionsabsence.models.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilisateurControlleur implements Initializable {
    @FXML
    public   TableView<Utilisateur> etudiantTableView;

    @FXML
    public   TableColumn<Utilisateur, Integer> matriculeColumn;

    @FXML
    public   TableColumn<Utilisateur, String> nomColumn;

    @FXML
    public   TableColumn<Utilisateur, String> prenomColumn;
    @FXML
    public   TableColumn<Utilisateur, Integer> nbrAbsenceColumn;
    public TextField txtMatricule;
    public TextField txtNom;
    public TextField txtPrenom;
    public TextField txtRole;
    public TextField txtMdp;
    public Label lblMatricule;
    public Label lblNom;
    public Label lblPrenom;
    public Label lblRole;
    public Label lblMdp;
    public Button btnModifier;
    public Button btnSupprimer;
    public Button btnHistorique;
    public Button btnAjouter;

    ObservableList<Utilisateur> utilisateurObservableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle ressource){

        affiche();

    }
    public void getUtilisateur(){
        DatabaseConnect db = new DatabaseConnect();
        Connection connectDB = db.getConnection();

        String sql = "SELECT * FROM utilisateur";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while (res.next()){
                Integer utilisateurId = res.getInt("id");
                String utilisateurMatricule = res.getString("matricule");
                String utilisateurNom = res.getString("nom");
                String utilisateurPrenom = res.getString("prenom");
                String utilisateurRole = res.getString("role");
                String utilisateurMotDePasse = res.getString("mot_de_passe");
                Integer utilisateurNbrAbsence = res.getInt("nombre_absence");

                //remplir la liste
                utilisateurObservableList.add(new Utilisateur(utilisateurId, utilisateurMatricule,
                        utilisateurNom, utilisateurPrenom, utilisateurRole, utilisateurMotDePasse, utilisateurNbrAbsence));

            }

        }catch (SQLException e){
            Logger.getLogger(UtilisateurControlleur.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }
    public  void affiche(){
        getUtilisateur();
        matriculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nbrAbsenceColumn.setCellValueFactory(new PropertyValueFactory<>("nombre_absence"));

        etudiantTableView.setItems(utilisateurObservableList);
    }
    public void selectionner(MouseEvent event){
        //Récupère les infos de la ligne selectionnée
        Utilisateur user = etudiantTableView.getSelectionModel().getSelectedItem();
        txtMatricule.setText(String.valueOf(user.getMatricule()));
        txtNom.setText(String.valueOf(user.getNom()));
        txtPrenom.setText(String.valueOf(user.getPrenom()));
        txtRole.setText(String.valueOf(user.getRole()));
        txtMdp.setText(String.valueOf(user.getMot_de_pase()));
        btnAjouter.setDisable(true);
        txtMatricule.setDisable(true);
        txtRole.setDisable(true);


    }
    public void ajouter(){
        DatabaseConnect db = new DatabaseConnect();
        Connection connectDB = db.getConnection();
        String sql = "insert into utilisateur (matricule,nom, prenom, role, mot_de_passe) values(?, ?, ?, ?, ? )";

        try{
            PreparedStatement statement = connectDB.prepareStatement(sql);
            statement.setString(1, txtMatricule.getText());
            statement.setString(2, txtNom.getText());
            statement.setString(3, txtPrenom.getText());
            statement.setString(4, txtRole.getText());
            statement.setString(5, txtMdp.getText());
            statement.executeUpdate();
            etudiantTableView.getItems().clear();
            affiche();
        }catch (SQLException e){
            Logger.getLogger(UtilisateurControlleur.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    public void ajouterEvent(ActionEvent event){
        ajouter();
    }

    private void modifier() {
        DatabaseConnect db = new DatabaseConnect();
        Connection connectDB = db.getConnection();
        String sql = "update utilisateur set nom=?, prenom=? where matricule=?";

        try{
            PreparedStatement statement = connectDB.prepareStatement(sql);
            statement.setString(1, txtNom.getText());
            statement.setString(2, txtPrenom.getText());
            statement.setString(3, txtMatricule.getText());
            statement.executeUpdate();
            etudiantTableView.getItems().clear();
            affiche();

        }catch (SQLException e){
            Logger.getLogger(UtilisateurControlleur.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }
    public void modifierEvent(ActionEvent event){
        modifier();
    }

    public void supprimer(){
        DatabaseConnect db = new DatabaseConnect();
        Connection connectDB = db.getConnection();
        String sql = "delete from utilisateur where matricule=?";

        try{
            PreparedStatement statement = connectDB.prepareStatement(sql);
            statement.setString(1,txtMatricule.getText());
            statement.executeUpdate();
            etudiantTableView.getItems().clear();
            affiche();

        }catch (SQLException e){
            Logger.getLogger(UtilisateurControlleur.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void supprimerEvent(ActionEvent event){
        supprimer();
    }

    void effacer() {
        txtMatricule.setDisable(false);
        txtRole.setDisable(false);
        txtMdp.setDisable(false);
        txtMatricule.setText(null);
        txtNom.setText(null);
        txtPrenom.setText(null);
        txtRole.setText(null);
        txtMdp.setText(null);
        btnAjouter.setDisable(false);
    }
    public void effacerEvent(ActionEvent event){
        effacer();
    }

}
