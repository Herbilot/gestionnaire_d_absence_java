package dev.herbidev.gestionsabsence.models;

public class Utilisateur {
    Integer utilisateurId;
    String matricule;
    String nom;
    String role;
    String prenom;
    String mot_de_pase;
    Integer nombre_absence;

    public Utilisateur(Integer utilisateurId, String matricule, String nom, String prenom, String role,
                       String mot_de_pase, Integer nombre_absence) {

        this.utilisateurId = utilisateurId;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.mot_de_pase = mot_de_pase;
        this.nombre_absence = nombre_absence;
    }

    public Integer getUtilisateurId() {
        return utilisateurId;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMot_de_pase() {
        return mot_de_pase;
    }
    public String getRole(){return  role;}
    public Integer getNombre_absence() {
        return nombre_absence;
    }

    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMot_de_pase(String mot_de_pase) {
        this.mot_de_pase = mot_de_pase;
    }

    public void setNombre_absence(Integer nombre_absence) {
        this.nombre_absence = nombre_absence;
    }
}
