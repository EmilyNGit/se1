package org.hbrs.se1.ws24.exercises.uebung3.persistence;

import org.hbrs.se1.ws24.solutions.uebung2.Member;

import java.util.Iterator;

public class MemberView {
   public void dump(Container liste){
       System.out.println("Ausgabe aller Member-Objekte: ");

       // Loesung Nr. 1 mit For each Schleife: Sequentielle Bearbeitung der Schleife
       Iterator<Member> i = liste.iterator();
       while (  i.hasNext() ) {
           Member p = i.next();
           System.out.println("ID: " + p.getID() );
       }
   }
}
