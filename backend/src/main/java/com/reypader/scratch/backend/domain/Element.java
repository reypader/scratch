package com.reypader.scratch.backend.domain;

import java.util.Comparator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class Element implements Comparable<Element> {
    private final String uniqueName;
    private final SortedSet<Event> eventsAsActor = new TreeSet<>();
    private final SortedSet<Event> eventsAsSubject = new TreeSet<>();

    public Element(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public SortedSet<Event> getEventsAsActor() {
        return eventsAsActor;
    }

    public SortedSet<Event> getEventsAsSubject() {
        return eventsAsSubject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element element)) return false;
        return Objects.equals(uniqueName, element.uniqueName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uniqueName);
    }

    @Override
    public int compareTo(Element o) {
        return Comparator.comparing(Element::getUniqueName)
                .compare(this, o);
    }

    void asSubjectOn(Event event) {
        this.eventsAsSubject.add(event);
    }

    void asActorOn(Event event) {
        this.eventsAsActor.add(event);
    }
}
