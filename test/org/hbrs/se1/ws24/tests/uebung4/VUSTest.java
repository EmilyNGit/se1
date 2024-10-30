package org.hbrs.se1.ws24.tests.uebung4;


import org.hbrs.se1.ws24.solutions.uebung3.*;
import org.hbrs.se1.ws24.exercises.uebung4.UserStory;
import org.hbrs.se1.ws24.solutions.uebung3.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VUSTest {
    private Container container;
    private UserStory story1;
    private UserStory story2;

    @BeforeEach
    public void setUp() {
        container = Container.getInstance();
        story1 = new UserStory(1, "UserStory1", "kriterium1", 2, 2, 3, 2, "Test");
        story2 = new UserStory(2, "UserStory2", "kriterium2", 3, 2, 3, 1, "Test");
    }

    @Test
    public void testAddMember() throws ContainerException {
        try {
            container.deleteAllObjects();
        } catch (PersistenceException e) {
        }
        container.addObject(story1);
        assertEquals(1, container.size());
    }

    @Test
    public void testAddDuplicateMember() {
        assertDoesNotThrow(() -> {
            container.addObject(story1);
        });
        ContainerException thrown = assertThrows(ContainerException.class, () -> {
            container.addObject(story1);
        });
        assertEquals(null, thrown.getMessage());
    }


    @Test
    public void testDeleteMember() throws ContainerException {
        try {
            container.deleteAllObjects();
        } catch (PersistenceException e) {
        }
        container.addObject(story1);
        assertEquals("Member mit der ID 1 konnte geloescht werden", container.deleteObject(1));
        assertEquals(0, container.size());
    }

    @Test
    public void testDeleteNonExistentMember() {
        assertEquals("Member nicht enthalten - ERROR", container.deleteObject(1));
    }

    @Test
    public void testSize() throws ContainerException {
        try {
            container.deleteAllObjects();
        } catch (PersistenceException e) {
        }
        assertEquals(0, container.size());
        container.addObject(story1);
        assertEquals(1, container.size());
        container.addObject(story2);
        assertEquals(2, container.size());
    }

}