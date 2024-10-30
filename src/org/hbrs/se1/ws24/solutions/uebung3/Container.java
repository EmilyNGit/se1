package org.hbrs.se1.ws24.solutions.uebung3;

import org.hbrs.se1.ws24.solutions.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.solutions.uebung3.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;


/*
 * Klasse zum Abspeichern von Objekten in einer Liste
 *
 * c/o Sascha Alda, H-BRS, 2020-2024
 *
 */

public class Container<T extends Member> {

	// Interne ArrayList zur Abspeicherung der Objekte
	private List<T> liste = null;

	// Statische Klassen-Variable, um die Referenz
	// auf das einzige Container-Objekt abzuspeichern
	// Dynamische Belegung: nur falls Methode getInstance geladen
	// wird, dann wird nach Bedarf die Variable mit einer Referenz gefüllt
	private static Container instance = null; // = new Container();

	// Reference to the internal strategy (e.g. MongoDB or Stream)
	private PersistenceStrategy strategy = null;


	/**
	 * Statische Methode um die einzige Instanz der Klasse
	 * Container zu bekommen. Das Keyword synchronized bewirkt,
	 * dass garantiert nur ein Objekt den alleinigen Zugriff
	 * auf diese Methode hat. Anonsten koennte es passieren, dass
	 * zwei parallel zugreifende Objekte zwei unterschiedliche
	 * Objekte erhalten (vgl. auch Erlaeuterung in Uebung)
	 *
	 */
	public static synchronized Container getInstance() {
		if (instance == null) {
			instance = new Container();
		}
		return instance;
	}

	/**
	 * Ueberschreiben des Konstruktors. Durch die Sichtbarkeit private
	 * kann man von aussen die Klasse nicht mehr instanziieren,
	 * sondern nur noch kontrolliert ueber die statische Methode
	 * der Klasse Container!
	 */
	private Container(){
		this.liste = new ArrayList<T>();
	}


	/**
	 * Method for getting the internal list. e.g. from a View-object
	 * @return
	 */
	public List getCurrentList() {
		return this.liste;
	}

	/**
	 * Method for adding Member-objects
	 * @param r
	 * @throws ContainerException
	 */
	public void addObject ( T r ) throws ContainerException {
		if ( contains( r ) == true ) {
			System.out.println("Duplikat: " + r.toString() );
			ContainerException ex = new ContainerException( ContainerException.ExceptionType.DuplicateMember );
			ex.addID ( r.getID() );
			throw ex;
		}
		liste.add(r);
	}

	/**
	 * Methode zur Ueberpruefung, ob ein Member-Objekt in der Liste enthalten ist
	 *
	 */
	private boolean contains( T r) {
		Integer ID = r.getID();
		for ( T rec : liste) {
			if ( rec.getID() == ID ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method for deleting an object with a given id.
	 *
	 */
	public String deleteObject(Integer id ) {
		T rec = getObject( id );
		if (rec == null) return "Member nicht enthalten - ERROR"; else {
			liste.remove(rec);
			return "Member mit der ID " + id + " konnte geloescht werden";
		}
	}

	/**
	 * Method for getting the number of currently stored objects
	 *
	 */
	public int size(){
		return liste.size();
	}


	/**
	 * Interne Methode zur Ermittlung eines Member
	 *
	 */
	private T getObject(Integer id) {
		for ( T rec : liste) {
			if (id == rec.getID().intValue() ){
				return rec;
			}
		}
		return null;
	}


	/**
	 * Method for loading objects. Uses the internally stored strategy object
	 * @throws PersistenceException
	 */
	public void load() throws PersistenceException {
		if (this.strategy == null)
			throw new PersistenceException(
					PersistenceException.ExceptionType.NoStrategyIsSet,
					"Strategy not initialized");

		try {
			List<T> liste = this.strategy.load();
			this.liste = liste;
		} catch ( java.lang.UnsupportedOperationException e) {
			throw new PersistenceException(
					PersistenceException.ExceptionType.ImplementationNotAvailable
					, "MongoDB is not implemented!" );
		}
	}

	/**
	 * Method for setting the Persistence-Strategy from outside.
	 * @param persistenceStrategy
	 */
	public void setPersistenceStrategie(PersistenceStrategy persistenceStrategy) {

		this.strategy = persistenceStrategy;
	}


	/**
	 * Method for storing objects. Uses the internally stored strategy object
	 * @throws PersistenceException
	 */
	public void store() throws PersistenceException {
		if (this.strategy == null)
			throw new PersistenceException( PersistenceException.
					ExceptionType.NoStrategyIsSet,
					"Strategy not initialized");

		try {
			this.strategy.save( this.liste  );
		} catch ( java.lang.UnsupportedOperationException e) {
			throw new PersistenceException( PersistenceException.ExceptionType.ImplementationNotAvailable
					, "MongoDB is not implemented!" );
		}
	}

	/**
	 * Methode zum Löschen aller Member-Objekte
	 * @throws PersistenceException
	 */
	public void deleteAllObjects() throws PersistenceException {
		this.liste.clear();
	}
	public void String() {
		for (T obj : liste) {
			System.out.println(obj.toString());
		}
	}
}
