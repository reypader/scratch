package com.reypader.scratch.backend.domain;

import java.util.*;

/*
 * (non-javadoc)
 * TODO: Massive duplication with SimpleFictionalCalendar
 */
public class SimplifiedGregorianCalendar implements FictionalOffsetCalendar {
    private final Map<Integer, FictionalInstant> instantCache = new HashMap<>();
    private final Map<String, Integer> monthDays;
    private final String[] months;
    private final String[] daysOfWeek;
    private final int startingMonthIndex;
    private final int startingdayOfWeekIndex;
    private final FictionalInstant startingInstant;

    public SimplifiedGregorianCalendar(Map<String, Integer> monthDays, List<String> months, List<String> daysOfWeek, FictionalInstant startingInstant) {
        assert monthDays != null && !monthDays.isEmpty();
        assert daysOfWeek != null && !daysOfWeek.isEmpty();
        assert months != null && !months.isEmpty();
        assert monthDays.keySet().containsAll(months) && new HashSet<>(months).containsAll(monthDays.keySet());
        assert startingInstant != null;
        assert startingInstant.getDayInMonth() <= monthDays.get(startingInstant.getMonth());
        this.monthDays = monthDays;
        this.months = months.toArray(new String[0]);
        this.daysOfWeek = daysOfWeek.toArray(new String[0]);
        this.startingInstant = startingInstant;
        this.startingMonthIndex = months.indexOf(startingInstant.getMonth());
        this.startingdayOfWeekIndex = daysOfWeek.indexOf(startingInstant.getDayOfWeek());
        this.instantCache.put(0, startingInstant);
    }


    @Override
    public FictionalInstant instantForOffset(int offset) {
        int signum = Integer.signum(offset);
        int o = offset;
        int year = this.startingInstant.getYear();
        int monthIndex = this.startingMonthIndex;
        int dayInMonth = this.startingInstant.getDayInMonth();
        int dayOfWeekIndex = (this.startingdayOfWeekIndex + (offset < 0 ? this.daysOfWeek.length : 0) + offset) % this.daysOfWeek.length;
        if (dayOfWeekIndex < 0) dayOfWeekIndex += this.daysOfWeek.length;
        do { //TODO: Optimize this so it doesn't iterate per day
            dayInMonth = dayInMonth + signum;
            if (dayInMonth < 1) {
                if (monthIndex - 1 < 0) {
                    year = year - 1;
                    monthIndex = months.length - 1;
                } else {
                    monthIndex = monthIndex - 1;
                }
                dayInMonth = getMonthDays(monthIndex, year);

            } else if (dayInMonth > getMonthDays(monthIndex, year)) {
                if (monthIndex + 1 >= months.length) {
                    year = year + 1;
                    monthIndex = 0;
                } else {
                    monthIndex = monthIndex + 1;
                }
                dayInMonth = 1;
            }
        } while ((o = o + (signum * -1)) != 0);
        return new FictionalInstant(year, months[monthIndex], dayInMonth, daysOfWeek[dayOfWeekIndex]);
    }

    private int getMonthDays(int monthIndex, int forYear) {
        return (forYear % 4 == 0 && monthIndex == 1) ? monthDays.get(months[monthIndex]) + 1 : monthDays.get(months[monthIndex]);
    }
}
