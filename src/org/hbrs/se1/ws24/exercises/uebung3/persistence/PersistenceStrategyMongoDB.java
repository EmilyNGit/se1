package org.hbrs.se1.ws24.exercises.uebung3.persistence;

import java.util.List;

public class PersistenceStrategyMongoDB<E> implements PersistenceStrategy<E> {

    @Override
    public void save(List<E> member) throws PersistenceException {
        throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "MongoDB noch nicht implementiert");
    }

    @Override
    public List<E> load() throws PersistenceException {
        throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "MongoDB noch nicht implementiert");
    }
}
