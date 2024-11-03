package com.reypader.scratch.backend.domain.timeline;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FictionalCalendarTest {
    private Map<String, Integer> monthDays;
    private List<String> months;

    @BeforeEach
    public void setup() {
        monthDays = new HashMap<>();
        monthDays.put("January", 31);
        monthDays.put("February", 28);
        monthDays.put("March", 31);
        monthDays.put("April", 30);
        monthDays.put("May", 31);
        monthDays.put("June", 30);
        monthDays.put("July", 31);
        monthDays.put("August", 31);
        monthDays.put("September", 30);
        monthDays.put("October", 31);
        monthDays.put("November", 30);
        monthDays.put("December", 31);
        months = Arrays.asList("January",
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
                "December");

    }

    @Test
    public void testPositiveOffsetCalculation_sameMonthAndYear() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2023, "November", 2, "Thursday")
        );
        assertEquals(new FictionalInstant(2023, "November", 30, "Thursday"), underTest.instantForOffset(28));
    }

    @Test
    public void testPositiveOffsetCalculation_nextMonthSameYear() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2023, "November", 2, "Thursday")
        );
        assertEquals(new FictionalInstant(2023, "December", 1, "Friday"), underTest.instantForOffset(29));
    }

    @Test
    public void testPositiveOffsetCalculation_nextYear() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2023, "November", 2, "Thursday")
        );
        assertEquals(new FictionalInstant(2024, "January", 2, "Tuesday"), underTest.instantForOffset(61));
    }

    @Test
    public void testNegativeOffsetCalculation_sameMonthAndYear() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2023, "November", 2, "Thursday")
        );
        assertEquals(new FictionalInstant(2023, "November", 1, "Wednesday"), underTest.instantForOffset(-1));
    }

    @Test
    public void testNegativeOffsetCalculation_pastMonthSameYear() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2023, "November", 2, "Thursday")
        );
        assertEquals(new FictionalInstant(2023, "October", 31, "Tuesday"), underTest.instantForOffset(-2));
    }

    @Test
    public void testNegativeOffsetCalculation_pastYear() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2023, "November", 2, "Thursday")
        );
        assertEquals(new FictionalInstant(2022, "November", 2, "Wednesday"), underTest.instantForOffset(-365));
    }

    @Test
    public void testNegativeYearOffset() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2025, "January", 1, "Wednesday")
        );
        // leap year not supported here
        assertEquals(-365, underTest.offsetOfInstant(new FictionalInstant(2024, "January", 1, "Monday")));
    }

    @Test
    public void testPositiveYearOffset() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2024, "January", 1, "Monday")
        );
        // leap year not supported here
        assertEquals(365, underTest.offsetOfInstant(new FictionalInstant(2025, "January", 1, "Wednesday")));
    }

    @Test
    public void testNegativeYearMonthOffset() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2025, "January", 1, "Wednesday")
        );
        // leap year not supported here
        assertEquals(-396, underTest.offsetOfInstant(new FictionalInstant(2023, "December", 1, "Friday")));
    }

    @Test
    public void testPositiveYearMonthOffset() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2024, "January", 1, "Monday")
        );
        // leap year not supported here
        assertEquals(396, underTest.offsetOfInstant(new FictionalInstant(2025, "February", 1, "Saturday")));
    }

    @Test
    public void testNegativeYearMonthDayOffset() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2025, "January", 1, "Wednesday")
        );
        // leap year not supported here
        assertEquals(-392, underTest.offsetOfInstant(new FictionalInstant(2023, "December", 5, "Friday")));
    }

    @Test
    public void testPositiveYearMonthDayOffset() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2024, "January", 1, "Monday")
        );
        // leap year not supported here
        assertEquals(400, underTest.offsetOfInstant(new FictionalInstant(2025, "February", 5, "Saturday")));
    }

    @Test
    public void testSkewedNegativeYearMonthDayOffset() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2025, "February", 2, "Wednesday")
        );
        // leap year not supported here
        assertEquals(-424, underTest.offsetOfInstant(new FictionalInstant(2023, "December", 5, "Friday")));
    }

    @Test
    public void testSkewedPositiveYearMonthDayOffset() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2024, "February", 2, "Monday")
        );
        // leap year not supported here
        assertEquals(368, underTest.offsetOfInstant(new FictionalInstant(2025, "February", 5, "Saturday")));
    }

    @Test
    public void testYesterdayOffset() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2025, "January", 1, "Wednesday")
        );
        // leap year not supported here
        assertEquals(-1, underTest.offsetOfInstant(new FictionalInstant(2024, "December", 31, "Tuesday")));
    }

    @Test
    public void testTomorrowOffset() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2024, "January", 1, "Monday")
        );
        // leap year not supported here
        assertEquals(1, underTest.offsetOfInstant(new FictionalInstant(2024, "January", 2, "Tuesday")));
    }

    @Test
    public void testNowOffset() throws Exception {
        FictionalOffsetCalendar underTest = new SimpleFictionalCalendar(
                monthDays,
                months,
                Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
                new FictionalInstant(2024, "January", 1, "Monday")
        );
        // leap year not supported here
        assertEquals(0, underTest.offsetOfInstant(new FictionalInstant(2024, "January", 1, "Monday")));
    }
}
