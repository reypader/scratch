package com.reypader.scratch.backend.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FictionalCalendarTest {
    private SimpleFictionalCalendar underTest;

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


        underTest = new SimpleFictionalCalendar(
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
                new FictionalInstant(2023, "November", 2, "Thursday")
        );
    }

    @Test
    public void testPositiveOffsetCalculation_sameMonthAndYear() throws Exception {
        assertEquals(new FictionalInstant(2023, "November", 30, "Thursday"), underTest.instantForOffset(28));
    }

    @Test
    public void testPositiveOffsetCalculation_nextMonthSameYear() throws Exception {
        assertEquals(new FictionalInstant(2023, "December", 1, "Friday"), underTest.instantForOffset(29));
    }

    @Test
    public void testPositiveOffsetCalculation_nextYear() throws Exception {
        assertEquals(new FictionalInstant(2024, "January", 2, "Tuesday"), underTest.instantForOffset(61));
    }

    @Test
    public void testNegativeOffsetCalculation_sameMonthAndYear() throws Exception {
        assertEquals(new FictionalInstant(2023, "November", 1, "Wednesday"), underTest.instantForOffset(-1));
    }

    @Test
    public void testNegativeOffsetCalculation_pastMonthSameYear() throws Exception {
        assertEquals(new FictionalInstant(2023, "October", 31, "Tuesday"), underTest.instantForOffset(-2));
    }

    @Test
    public void testNegativeOffsetCalculation_pastYear() throws Exception {
        assertEquals(new FictionalInstant(2022, "November", 2, "Wednesday"), underTest.instantForOffset(-365));
    }
}
