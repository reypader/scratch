package com.reypader.scratch.backend.domain.timeline;

import java.util.*;

/*
 * (non-javadoc)
 * TODO: Massive duplication with SimpleFictionalCalendar
 */
public class SimplifiedGregorianCalendar extends SimpleFictionalCalendar {


    public SimplifiedGregorianCalendar(Map<String, Integer> monthDays, List<String> months, List<String> daysOfWeek, FictionalInstant startingInstant) {
        super(monthDays, months, daysOfWeek, startingInstant);
    }

    @Override
    protected int daysOfMonth(int month, int year) {
        int d = super.daysOfMonth(month, year);
        if (month == 1 && (year % 4) == 0) {
            d = d + 1;
        }
        return d;
    }
}
