package org.hbrs.se1.ws24.exercises.uebung3.persistence;

import org.hbrs.se1.ws24.solutions.uebung2.ContainerException;
import org.hbrs.se1.ws24.solutions.uebung2.Member;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Container implements Serializable {

	private static Container instance;
	private PersistenceStrategy<Member> strategy;

	private Container(){}

	public static synchronized Container getInstance(){
		if(instance == null){
			instance = new Container();
		}
		return instance;
	}

	/*
	 * Interne ArrayList zur Abspeicherung der Objekte
	 * Alternative: HashMap oder Set. HashMap hat vor allem Probleme 
	 * bei der Bewahrung der Konsistenz vom Key und Value (siehe TestStore, letzter Test)
	 */
	private List<Member> liste = new ArrayList<Member>();
	
	
	/*
	 * Methode zum Hinzufuegen einer Member.
	 * @throws ContainerException
	 */ 
	public void addMember ( Member r ) throws ContainerException {

		if (r == null) {
			ContainerException ex = new ContainerException();
			throw ex;
		}

		if ( contains( r ) == true ) {
			ContainerException ex = new ContainerException(  r.getID().toString() );
			throw ex;
		}
		liste.add( r );
	
	} 
	
	/*
	 * Methode zur Ueberpruefung, ob ein Member-Objekt in der Liste enthalten ist
	 * 
	 */
	private boolean contains(Member r) {
		Integer ID = r.getID();
		for ( Member rec : liste) {
			// wichtig: Check auf die Values innerhalb der Integer-Objekte!
			if ( rec.getID().intValue() == ID.intValue() ) {
				return true;
			}
		}
		return false;
		
		// liste.contains(r), falls equals-Methode in Member ueberschrieben.
	}
	/*
	 * Methode zum Loeschen einer Member
	 * In Praxis durchaus verwendet: C-Programme; beim HTTP-Protokoll; SAP-Anwendung (R3); Mond-Landung ;-)
	 * 
	 */
	public String deleteMember( Integer id ) {
		Member rec = getMember( id );
		if (rec == null) return "Member nicht enthalten - ERROR"; else {
			liste.remove(rec);
			return "Member mit der ID " + id + " konnte geloescht werden";
		}
	}
	
	/*
	 * Methode zur Bestimmung der Anzahl der von Member-Objekten
	 * Aufruf der Methode size() aus List
	 * 
	 */
	public int size(){
		return liste.size();
	}

	
	/*
	 * Methode zur Ausgabe aller IDs der Member-Objekte. Es werden verschiedene Varianten vorgestellt!
	 * Fuer eine ordnungsgemaesse Ausgabe sollten die unpassenden Varianten und und Loesungen
	 * natuerlich auskommentiert werden.
	 * 
	 */

	public List<Member> getCurrentList() {
		return liste;
	}

	/**
	 * Interne Methode zur Ermittlung einer Member
	 * 
	 */
	private Member getMember(Integer id) {
		for ( Member rec : liste) {
			if (id == rec.getID().intValue() ){
				return rec;
			}
		}
		return null;
	}

	public void store() throws PersistenceException{
		if(strategy == null) throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie gesetzt");
		strategy.save(this.getCurrentList());
	}

	public void load() throws PersistenceException{
		if(strategy == null) throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie gesetzt");
		this.liste = strategy.load();
	}

	public void setStrategy(PersistenceStrategy strategy) {
		this.strategy = strategy;
	}

	public PersistenceStrategy getStrategy(){
		return strategy;
	}

}
