package com.reypader.scratch.backend.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    public void compareTo() throws Exception {
        Element a = new Element("A");
        Element b = new Element("B");
        Event underTest1 = new Event(0, a, "poked", b);
        Event underTest2 = new Event(0, a, "kicked", b);
        Event underTest3 = new Event(0, a, "poked", a);
        Event underTest4 = new Event(0, b, "poked", a);
        Event underTest5 = new Event(-1, b, "poked", a);

        assertEquals(0, underTest1.compareTo(underTest1));
        assertEquals(0, underTest1.compareTo(new Event(0, a, "poked", b)));

        assertTrue(0 < underTest1.compareTo(underTest2));
        assertTrue(0 > underTest2.compareTo(underTest1));
        assertTrue(0 < underTest1.compareTo(underTest3));
        assertTrue(0 > underTest1.compareTo(underTest4));
        assertTrue(0 > underTest5.compareTo(underTest1));
    }

    @Test
    public void compareToNullActor() throws Exception {
        Element a = new Element("A");
        Element b = new Element("B");
        Event underTest1 = new Event(0, "poked", b);
        Event underTest2 = new Event(0, a, "poked", b);

        assertTrue(0 > underTest1.compareTo(underTest2));
        assertTrue(0 < underTest2.compareTo(underTest1));
    }

    @Test
    public void compareToNullSubject() throws Exception {
        Element a = new Element("A");
        Element b = new Element("B");
        Event underTest1 = new Event(0, a, "poked");
        Event underTest2 = new Event(0, a, "poked", b);

        assertTrue(0 > underTest1.compareTo(underTest2));
        assertTrue(0 < underTest2.compareTo(underTest1));
    }

    @Test
    public void cascadingMembership() throws Exception {
        Element a = new Element("A");
        Element b = new Element("B");
        Event underTest1 = new Event(0, a, "poked", b);
        Event underTest2 = new Event(0, "poked", b);
        Event underTest3 = new Event(0, a, "poked");

        assertTrue(a.getEventsAsActor().contains(underTest1));
        assertFalse(a.getEventsAsSubject().contains(underTest1));
        assertTrue(b.getEventsAsSubject().contains(underTest1));
        assertFalse(b.getEventsAsActor().contains(underTest1));

        assertTrue(b.getEventsAsSubject().contains(underTest2));
        assertTrue(a.getEventsAsActor().contains(underTest3));

    }


}