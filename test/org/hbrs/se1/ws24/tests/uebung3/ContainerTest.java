package org.hbrs.se1.ws24.tests.uebung3;

import org.hbrs.se1.ws24.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ContainerTest {

    private static Container container;

    @BeforeAll
    public static void init() {
        container = Container.getInstance();
    }

    @Test
    public void keineStrategie(){
        assertThrows(PersistenceException.class, () -> container.load());
    }

    @Test
    public void StrategieMongo(){
        container.setStrategy(new PersistenceStrategyMongoDB());
        assertThrows(PersistenceException.class, () -> container.load());
    }

    @Test
    public void testWrongFileLocation(@TempDir Path tmp) {
        PersistenceStrategyStream<Member> streamStrategy = new PersistenceStrategyStream<>();
        streamStrategy.setLocation(tmp.toString());
        container.setStrategy(streamStrategy);
        PersistenceException store = assertThrows(PersistenceException.class, () -> container.store());
        PersistenceException load = assertThrows(PersistenceException.class, () -> container.load());

        assertEquals(load.getExceptionTypeType(), PersistenceException.ExceptionType.LoadingFailed);
        assertEquals(store.getExceptionTypeType(), PersistenceException.ExceptionType.SavingFailed);
    }

    @Test
    public void testRoundTrip(@TempDir Path tmp) throws ContainerException, PersistenceException {
        for (int i = 0; i < 5; i++) {
             container.addMember(new ConcreteMember(i));
        }
        assertEquals(5, container.size());
        PersistenceStrategyStream<Member> streamStrategy = new PersistenceStrategyStream<>();
        container.setStrategy(streamStrategy);
        Path tmpPfad = tmp.resolve("testList.ser");
        streamStrategy.setLocation(tmpPfad.toString());

        container.store();
        assertEquals(5, container.size());
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(0)));
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(1)));
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(2)));
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(3)));
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(4)));


        container.deleteMember(4);
        container.addMember(new ConcreteMember(4));


        container.load();
        assertEquals(container.size(), 5);
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(0)));
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(1)));
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(2)));
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(3)));
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(4)));

        List<Member> members = container.getCurrentList();
        for (int i = 0; i < 5; i++) {
            assertEquals(i, members.get(i).getID());
        }
    }

}
