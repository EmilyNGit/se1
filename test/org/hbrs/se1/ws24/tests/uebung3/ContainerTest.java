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

}
