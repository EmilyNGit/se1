package org.hbrs.se1.ws24.exercises.uebung4;
import org.hbrs.se1.ws24.solutions.uebung3.*;
import org.hbrs.se1.ws24.solutions.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.solutions.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.solutions.uebung3.persistence.PersistenceStrategyStream;

import java.util.Scanner;

public class VUS {
    static Container container = Container.getInstance();

    public static void main(String[] args) {
        container.setPersistenceStrategie(new PersistenceStrategyStream());
        Scanner sc = new Scanner(System.in);
        System.out.println("Bitte geben Sie einen Befehl ein. Für Hilfe tippen Sie 'help'.");
        String befehl = sc.nextLine().trim();
        while(!(befehl.equals("exit"))) {
            switch (befehl) {
                case "help":
                    help();
                    break;
                case "enter":
                    System.out.println("Bitte geben Sie eine eindeutige ID an:");
                    int id = sc.nextInt();
                    System.out.println("Bitte geben Sie einen kurzen Titel an:");
                    sc.nextLine();
                    String titel = sc.nextLine();
                    System.out.println("Bitte geben Sie Akzeptanzkriterium an:");
                    String kriterium = sc.nextLine();
                    System.out.println("Bitte geben Sie die Kennzahl für den Mehrwert (1-5) an:");
                    int mehrwert = sc.nextInt();
                    System.out.println("Bitte geben Sie die Kennzahl für die Strafe (1-5) an:");
                    int strafe = sc.nextInt();
                    System.out.println("Bitte geben Sie die Kennzahl für den Aufwand (> 0) an:");
                    int aufwand = sc.nextInt();
                    System.out.println("Bitte geben Sie die Kennzahl für das Risiko (1-5) an:");
                    int risiko = sc.nextInt();
                    System.out.println("Bitte geben Sie an, zu welchem Projekt die User Story gehört:");
                    sc.nextLine();
                    String projekt = sc.nextLine();
                    UserStory story = new UserStory(id, titel, kriterium, mehrwert, strafe, aufwand, risiko, projekt);
                    container = Container.getInstance();
                    try {
                        container.addObject(story);
                    } catch (ContainerException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "store":
                    try {
                        container.store();
                    } catch (PersistenceException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("User Stories wurden erfolgreich gespeichert.");
                    break;
                case "load":
                    try {
                        container.load();
                    } catch (PersistenceException e) {
                        throw new RuntimeException(e);
                    }
                case "dump":
                    System.out.println(container.toString());
                    break;
                default:
                    System.out.println("Bitte geben Sie einen gültigen Befehl ein.");
            }
            befehl = sc.nextLine();
        }
        System.out.println("Das Programm wurde beendet.");
        sc.close();
    }

    public static void help(){
        System.out.println("Struktur der Befehe: befehl [parameter]");
        System.out.println("mögliche Befehle:\n" +
                "enter --> Eingabe einer User Story\n" +
                "store --> Abspeichern von User Stories\n" +
                "load --> Laden von User Stories\n" +
                "dump --> Ausgabe der User Stories nach Priorisierungen" +
                "exit --> Verlassen der Anwendung");
    }

}
