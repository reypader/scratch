package com.reypader.scratch.backend.domain.timeline;

public interface FictionalOffsetCalendar {
    int offsetOfInstant(FictionalInstant instant);

    FictionalInstant instantForOffset(int offset);
}
