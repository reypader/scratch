package com.reypader.scratch.backend.domain;

import org.springframework.util.comparator.Comparators;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class Event implements Comparable<Event> {
    private final int eventIndex;
    private final String action;
    private final Element actor;
    private final Element subject;

    public Event(int eventIndex, Element actor, String action, Element subject) {
        this.eventIndex = eventIndex;
        this.action = action;
        this.subject = subject;
        this.actor = actor;
        subject.asSubjectOn(this);
        actor.asActorOn(this);
    }

    public Event(int eventIndex, String action, Element subject) {
        this.eventIndex = eventIndex;
        this.action = action;
        this.subject = subject;
        this.actor = null;
        subject.asSubjectOn(this);
    }

    public Event(int eventIndex, Element actor, String action) {
        this.eventIndex = eventIndex;
        this.action = action;
        this.actor = actor;
        this.subject = null;
        actor.asActorOn(this);
    }

    public String getAction() {
        return action;
    }

    public Optional<Element> getActor() {
        return Optional.ofNullable(actor);
    }

    private Element getActorNullable() {
        return actor;
    }

    public Optional<Element> getSubject() {
        return Optional.ofNullable(subject);
    }

    private Element getSubjectNullable() {
        return subject;
    }

    public int getEventIndex() {
        return eventIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;
        return eventIndex == event.eventIndex && Objects.equals(action, event.action) && Objects.equals(actor, event.actor) && Objects.equals(subject, event.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventIndex, action, actor, subject);
    }

    @Override
    public int compareTo(Event o) {
        return Comparator.comparing(Event::getEventIndex)
                .thenComparing(Event::getAction)
                .thenComparing(Event::getActorNullable, Comparator.nullsFirst(Comparator.naturalOrder()))
                .thenComparing(Event::getSubjectNullable, Comparator.nullsFirst(Comparator.naturalOrder()))
                .compare(this, o);
    }
}
