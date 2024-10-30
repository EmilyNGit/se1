package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.solutions.uebung3.Member;

import java.io.Serializable;
import java.sql.SQLOutput;

public class UserStory implements Member, Serializable {

    int id;
    String titel;
    String kriterium;
    int mehrwert;
    int strafe;
    int aufwand;
    int risiko;
    String projekt;
    int priowert;

    public UserStory(int id, String titel, String kriterium, int mehrwert, int strafe, int aufwand, int risiko, String projekt) {
        this.id = id;
        this.titel = titel;
        this.kriterium = kriterium;
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.aufwand = aufwand;
        this.risiko = risiko;
        this.projekt = projekt;
        if(mehrwert <= 0 || strafe <= 0 || risiko <= 0 || aufwand <= 0 || mehrwert > 5 || strafe > 5 || risiko > 5) {
            System.out.println("ungültige Werte! Bitte erneut versuchen.");
            return;
        }
        else {
            this.priowert = (mehrwert + strafe) / (aufwand + risiko);
            System.out.println("User Storie wurde erfolgreich hinzugefügt.");
        }
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.id + " | " + this.titel + " | " + this.kriterium + " | " + this.projekt + " | " + this.priowert ;
    }
}
