package com.reypader.scratch.backend.domain.timeline;

import java.util.*;

public class SimpleFictionalCalendar implements FictionalOffsetCalendar {
    private final Map<Integer, Integer> monthDaysIndexed;
    private final List<String> months;
    private final List<String> daysOfWeek;
    private final int startingMonthIndex;
    private final int startingDayOfWeekIndex;
    private final FictionalInstant startingInstant;

    public SimpleFictionalCalendar(Map<String, Integer> monthDays, List<String> months, List<String> daysOfWeek, FictionalInstant startingInstant) {
        assert monthDays != null && !monthDays.isEmpty();
        assert daysOfWeek != null && !daysOfWeek.isEmpty();
        assert months != null && !months.isEmpty();
        assert monthDays.keySet().containsAll(months) && new HashSet<>(months).containsAll(monthDays.keySet());
        assert startingInstant != null;
        assert startingInstant.getDayInMonth() <= monthDays.get(startingInstant.getMonth());
        this.monthDaysIndexed = new HashMap<>();
        monthDays.forEach((key, value) -> this.monthDaysIndexed.put(months.indexOf(key), value));
        this.startingInstant = startingInstant;
        this.months = new ArrayList<>(months);
        this.daysOfWeek = new ArrayList<>(daysOfWeek);
        this.startingMonthIndex = this.months.indexOf(this.startingInstant.getMonth());
        this.startingDayOfWeekIndex = this.daysOfWeek.indexOf(this.startingInstant.getDayOfWeek());
    }


    protected int daysOfMonth(int month, int year) {
        return this.monthDaysIndexed.get(month);
    }

    protected int daysOfYear(int year) {
        return this.monthDaysIndexed.keySet().stream().map(s -> daysOfMonth(s, year)).reduce(Integer::sum).orElse(0);
    }

    @Override
    public int offsetOfInstant(FictionalInstant instant) {
        return offsetToYear(startingInstant.getYear(), instant.getYear()) + offsetToMonth(instant.getYear(), months.indexOf(instant.getMonth())) + instant.getDayInMonth() - 1 - offsetFromYearStart();
    }

    private int offsetFromYearStart() {
        return offsetToMonth(this.startingInstant.getYear(), months.indexOf(this.startingInstant.getMonth())) + this.startingInstant.getDayInMonth() - 1;
    }


    private int offsetToMonth(int inYear, int targetMonth) {
        int offset = 0;
        for (int month = 0; month != targetMonth; month++) {
            offset += daysOfMonth(month, inYear);
        }
        return offset;
    }

    private int offsetToYear(int startingYear, int targetYear) {
        int direction = Integer.signum(targetYear - startingYear);
        int offset = 0;
        for (int year = startingYear; year != targetYear; year = year + direction) {
            if (direction < 0) {
                offset -= daysOfYear(year - 1);
            } else {
                offset += daysOfYear(year);
            }
        }
        return offset;
    }

    @Override
    public FictionalInstant instantForOffset(int offset) {
        int o = offset + offsetFromYearStart();
        int year = this.startingInstant.getYear();
        while (o < 0) {
            year = year - 1;
            o += daysOfYear(year);
        }
        for (; o > daysOfYear(year); year++) {
            o -= daysOfYear(year);
        }
        int month = 0;
        for (; o >= daysOfMonth(month, year); month++) {
            o -= daysOfMonth(month, year);
        }
        int dayOfWeekIndex = (this.startingDayOfWeekIndex + (offset < 0 ? this.daysOfWeek.size() : 0) + offset) % this.daysOfWeek.size();
        if (dayOfWeekIndex < 0) dayOfWeekIndex += this.daysOfWeek.size();

        return new FictionalInstant(year, months.get(month), o + 1, this.daysOfWeek.get(dayOfWeekIndex));
    }
}
