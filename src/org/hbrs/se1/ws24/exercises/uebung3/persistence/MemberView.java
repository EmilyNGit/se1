package org.hbrs.se1.ws24.exercises.uebung3.persistence;

import org.hbrs.se1.ws24.solutions.uebung2.Member;

import java.util.List;

public class MemberView {
    public static void dump(List<Member> liste){
        System.out.println("Ausgabe aller Member-Objekte: ");

        // Loesung Nr. 1 mit For each Schleife: Sequentielle Bearbeitung der Schleife
        for ( Member p : liste ) {
            System.out.println( p.toString()  );
        }
    }
}
