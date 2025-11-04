package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalendarTest {

    private Calendar calendar;

    @Before
    public void setUp() {
        calendar = new Calendar();
    }

    // ----------------------------
    // Tests for isBusy
    // ----------------------------
    @Test
    public void testIsBusyNormal() throws Exception {
        Meeting m = new Meeting(1, 1, 14, 16);
        calendar.addMeeting(m);
        assertTrue(calendar.isBusy(1, 1, 14, 15));
        assertFalse(calendar.isBusy(1, 1, 16, 17));
    }

    @Test
    public void testIsBusyEdgeTimes() throws Exception {
        Meeting m = new Meeting(1, 1, 9, 11);
        calendar.addMeeting(m);
        assertTrue(calendar.isBusy(1, 1, 9, 10));  // start
        assertTrue(calendar.isBusy(1, 1, 10, 11)); // end
        assertFalse(calendar.isBusy(1, 1, 7, 9));  // before
        assertFalse(calendar.isBusy(1, 1, 11, 12)); // after
    }

    // ----------------------------
    // Tests for removeMeeting
    // ----------------------------
    @Test
    public void testRemoveMeetingValid() throws Exception {
        Meeting m = new Meeting(1, 1, 9, 10);
        calendar.addMeeting(m);
        calendar.removeMeeting(1, 1, 0);
        assertFalse(calendar.printAgenda(1, 1).contains("9 - 10"));
    }

    @Test
    public void testRemoveMeetingEmptyDay() {
        calendar.removeMeeting(1, 1, 0); // should not throw
    }

    @Test
    public void testRemoveMeetingInvalidIndex() throws Exception {
        Meeting m = new Meeting(1, 1, 9, 10);
        calendar.addMeeting(m);
        calendar.removeMeeting(1, 1, 5); // index too large
        assertTrue(calendar.printAgenda(1, 1).contains("9 - 10"));
    }

    // ----------------------------
    // Tests for clearSchedule
    // ----------------------------

	@Test
	public void testClearScheduleInvalid() {
		// Should not throw exception
		calendar.clearSchedule(0, 1);  // invalid month
		calendar.clearSchedule(13, 1); // invalid month
		calendar.clearSchedule(1, 0);  // invalid day
		calendar.clearSchedule(1, 32); // invalid day
	}


    // ----------------------------
    // Tests for printAgenda
    // ----------------------------
    @Test
    public void testPrintAgendaMonth() throws Exception {
        Meeting m = new Meeting(1, 1, 9, 10);
        calendar.addMeeting(m);
        String agenda = calendar.printAgenda(1);
        assertTrue(agenda.contains("9 - 10"));
    }

    @Test
    public void testPrintAgendaMonthInvalid() {
        assertTrue(calendar.printAgenda(0).contains("Invalid month"));
        assertTrue(calendar.printAgenda(13).contains("Invalid month"));
    }

    @Test
    public void testPrintAgendaDay() throws Exception {
        Meeting m = new Meeting(1, 1, 9, 10);
        calendar.addMeeting(m);
        String agenda = calendar.printAgenda(1, 1);
        assertTrue(agenda.contains("9 - 10"));
    }

    @Test
    public void testPrintAgendaDayInvalid() {
        assertTrue(calendar.printAgenda(0, 1).contains("Invalid date"));
        assertTrue(calendar.printAgenda(1, 0).contains("Invalid date"));
        assertTrue(calendar.printAgenda(1, 32).contains("Invalid date"));
        assertTrue(calendar.printAgenda(13, 1).contains("Invalid date"));
    }

    // ----------------------------
    // Tests for getMeeting
    // ----------------------------
    @Test
    public void testGetMeeting() throws Exception {
        Meeting m = new Meeting(1, 1, 9, 10);
        calendar.addMeeting(m);
        assertEquals(m, calendar.getMeeting(1, 1, 0));
    }
}
