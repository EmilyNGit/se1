package org.hbrs.se1.ws24.tests.uebung2;

import org.hbrs.se1.ws24.exercises.uebung2.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    private Container container;
    private Member member1;
    private Member member2;

    @BeforeEach
    public void setUp() {
        container = new Container();
        member1 = new ConcreteMember(1);
        member2 = new ConcreteMember(2);
    }

    @Test
    public void testAddMember() throws ContainerException {
        container.addMember(member1);
        assertEquals(1, container.size());
    }

    @Test
    public void testAddDuplicateMember() {
        assertDoesNotThrow(() -> {
            container.addMember(member1);
        });
        ContainerException thrown = assertThrows(ContainerException.class, () -> {
            container.addMember(member1);
        });
        assertEquals("Das Member-Objekt mit der ID 1 ist bereits vorhanden!", thrown.getMessage());
    }


    @Test
    public void testDeleteMember() throws ContainerException {
        container.addMember(member1);
        assertEquals("Member mit ID 1 wurde gelöscht.", container.deleteMember(1));
        assertEquals(0, container.size());
    }

    @Test
    public void testDeleteNonExistentMember() {
        assertEquals("Kein Member mit der ID 1 gefunden.", container.deleteMember(1));
    }

    @Test
    public void testDumpMembers() throws ContainerException {
        container.addMember(member1);
        container.addMember(member2);
        container.dump(); // Manuelle Überprüfung der Konsolenausgabe
    }

    @Test
    public void testSize() throws ContainerException {
        assertEquals(0, container.size());
        container.addMember(member1);
        assertEquals(1, container.size());
        container.addMember(member2);
        assertEquals(2, container.size());
    }

}
