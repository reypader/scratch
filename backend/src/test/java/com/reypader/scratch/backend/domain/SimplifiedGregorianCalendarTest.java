package com.reypader.scratch.backend.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SimplifiedGregorianCalendarTest {
    private SimplifiedGregorianCalendar underTest;

    @BeforeEach
    public void setup() {
        Map<String, Integer> months = new HashMap<>();
        months.put("January", 31);
        months.put("February", 28);
        months.put("March", 31);
        months.put("April", 30);
        months.put("May", 31);
        months.put("June", 30);
        months.put("July", 31);
        months.put("August", 31);
        months.put("September", 30);
        months.put("October", 31);
        months.put("November", 30);
        months.put("December", 31);


        underTest = new SimplifiedGregorianCalendar(
                months,
                Arrays.asList("January",
                        "February",
                        "March",
                        "April",
                        "May",
                        "June",
                        "July",
                        "August",
                        "September",
                        "October",
                        "November",
                        "December"),
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2024, "November", 2, "Saturday")
        );
    }

    @Test
    public void testPositiveOffsetCalculation_sameMonthAndYear() throws Exception {
        assertEquals(new FictionalInstant(2024, "November", 30, "Saturday"), underTest.instantForOffset(28));
    }

    @Test
    public void testPositiveOffsetCalculation_nextMonthSameYear() throws Exception {
        assertEquals(new FictionalInstant(2024, "December", 1, "Sunday"), underTest.instantForOffset(29));
    }

    @Test
    public void testPositiveOffsetCalculation_nextYear() throws Exception {
        assertEquals(new FictionalInstant(2025, "January", 2, "Thursday"), underTest.instantForOffset(61));
    }

    @Test
    public void testNegativeOffsetCalculation_sameMonthAndYear() throws Exception {
        assertEquals(new FictionalInstant(2024, "November", 1, "Friday"), underTest.instantForOffset(-1));
    }

    @Test
    public void testNegativeOffsetCalculation_pastMonthSameYear() throws Exception {
        assertEquals(new FictionalInstant(2024, "October", 31, "Thursday"), underTest.instantForOffset(-2));
    }

    @Test
    public void testNegativeOffsetCalculation_pastYear() throws Exception {
        //Due to leap year
        assertEquals(new FictionalInstant(2023, "November", 3, "Friday"), underTest.instantForOffset(-365));
    }
}