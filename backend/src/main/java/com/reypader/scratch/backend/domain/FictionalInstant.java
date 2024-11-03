package com.reypader.scratch.backend.domain;

import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class FictionalInstant {
    private final int year;
    private final String month;
    private final int dayInMonth;
    private final String dayOfWeek;

    public FictionalInstant(int year, String month, int dayInMonth, String dayOfWeek) {
        assert year >= 0;
        assert dayInMonth > 0;
        assert Strings.isNotBlank(month);
        assert Strings.isNotBlank(dayOfWeek);
        this.year = year;
        this.month = month;
        this.dayInMonth = dayInMonth;
        this.dayOfWeek = dayOfWeek;
    }

    public int getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public int getDayInMonth() {
        return dayInMonth;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FictionalInstant that)) return false;
        return year == that.year && dayInMonth == that.dayInMonth && Objects.equals(month, that.month) && Objects.equals(dayOfWeek, that.dayOfWeek);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, dayInMonth, dayOfWeek);
    }

    @Override
    public String toString() {
        return "FictionalInstant{" +
                "year=" + year +
                ", month='" + month + '\'' +
                ", dayInMonth=" + dayInMonth +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                '}';
    }
}
