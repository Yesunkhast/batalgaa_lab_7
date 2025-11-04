package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {

    private Person person;

    @Before
    public void setUp() {
        person = new Person("Alice");
    }

    // Test default constructor
    @Test
    public void testDefaultConstructor() {
        Person p = new Person();
        assertNotNull(p);
        assertEquals("", p.getName());
    }

    // Test parameterized constructor
    @Test
    public void testParameterizedConstructor() {
        assertEquals("Alice", person.getName());
    }

    // Test adding meetings with no conflict
    @Test
    public void testAddMeetingNoConflict() throws Exception {
        Meeting m1 = new Meeting(10, 15, 9, 11);
        Meeting m2 = new Meeting(10, 15, 11, 12); // no overlap
        person.addMeeting(m1);
        person.addMeeting(m2);
        assertTrue(person.printAgenda(10, 15).contains("9 - 11"));
        assertTrue(person.printAgenda(10, 15).contains("11 - 12"));
    }

    // Test adding meetings with conflict
    @Test
    public void testAddMeetingWithConflict() throws Exception {
        Meeting m1 = new Meeting(10, 15, 9, 11);
        Meeting m2 = new Meeting(10, 15, 10, 12); // overlaps
        person.addMeeting(m1);

        try {
            person.addMeeting(m2); // should throw
            fail("Expected TimeConflictException not thrown");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("Conflict for attendee Alice"));
        }
    }

    // Test isBusy with normal times
    @Test
    public void testIsBusyNormal() throws Exception {
        Meeting m = new Meeting(10, 20, 14, 16);
        person.addMeeting(m);
        assertTrue(person.isBusy(10, 20, 14, 15));
        assertFalse(person.isBusy(10, 21, 14, 15));
    }

    // Test isBusy with edge times
    @Test
    public void testIsBusyEdgeTimes() throws Exception {
        Meeting m = new Meeting(11, 10, 9, 11);
        person.addMeeting(m);
        assertTrue(person.isBusy(11, 10, 9, 10));  // start
        assertTrue(person.isBusy(11, 10, 10, 11)); // end
        assertFalse(person.isBusy(11, 10, 7, 9));  // before
        assertFalse(person.isBusy(11, 10, 11, 12)); // after
    }

    // Test removing a meeting from a populated day
    @Test
    public void testRemoveMeeting() throws Exception {
        Meeting m = new Meeting(11, 5, 9, 10);
        person.addMeeting(m);
        person.removeMeeting(11, 5, 0); // remove normally
        person.removeMeeting(11, 5, 0); // remove again (empty list)
        assertFalse(person.printAgenda(11, 5).contains("9 - 10"));
    }

    // Test removing a meeting from an empty day
    @Test
    public void testRemoveMeetingEmptyCalendar() {
        person.removeMeeting(12, 31, 0); // should not throw
    }

    // Test printing agenda for a month
    @Test
    public void testPrintAgendaForMonth() {
        String agenda = person.printAgenda(10);
        assertNotNull(agenda);
    }

    // Test printing agenda for a specific day
    @Test
    public void testPrintAgendaForSpecificDay() {
        String agenda = person.printAgenda(10, 20);
        assertNotNull(agenda);
    }

    // Test printing empty agenda
    @Test
    public void testPrintEmptyAgenda() {
        String agendaMonth = person.printAgenda(1);
        String agendaDay = person.printAgenda(1, 1);
        assertNotNull(agendaMonth);
        assertNotNull(agendaDay);
    }
}
