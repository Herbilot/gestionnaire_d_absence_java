module dev.herbidev.gestionsabsence {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires org.postgresql.jdbc;


    opens dev.herbidev.gestionsabsence to javafx.fxml;
    exports dev.herbidev.gestionsabsence;
    exports dev.herbidev.gestionsabsence.controllers;
    exports dev.herbidev.gestionsabsence.controllers.absence;
    exports dev.herbidev.gestionsabsence.controllers.professeur;
    exports dev.herbidev.gestionsabsence.controllers.etudiant;
    exports dev.herbidev.gestionsabsence.vues;
    exports dev.herbidev.gestionsabsence.models;

}