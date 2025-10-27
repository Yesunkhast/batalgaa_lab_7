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

    @Test
    public void testDefaultConstructor() {
        Person p = new Person();
        assertNotNull(p);
        assertEquals("", p.getName());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("Alice", person.getName());
    }

    @Test
    public void testAddMeetingNoConflict() throws Exception {
        Meeting meeting = new Meeting(10, 15, 9, 11);
        person.addMeeting(meeting);
        Meeting retrieved = person.getMeeting(10, 15, 0);
        assertNotNull(retrieved);
        assertEquals(9, retrieved.getStartTime());
        assertEquals(11, retrieved.getEndTime());
    }

    @Test
    public void testAddMeetingWithConflict() {
        try {
            Meeting m1 = new Meeting(10, 15, 9, 11);
            Meeting m2 = new Meeting(10, 15, 10, 12);

            person.addMeeting(m1);
            person.addMeeting(m2); // Should throw
            fail("Expected TimeConflictException not thrown");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("Conflict for attendee"));
        }
    }

    @Test
    public void testIsBusy() throws Exception {
        Meeting m = new Meeting(10, 20, 14, 16);
        person.addMeeting(m);
        assertTrue(person.isBusy(10, 20, 14, 15));
        assertFalse(person.isBusy(10, 21, 14, 15));
    }

    @Test
    public void testPrintAgendaForMonth() {
        String agenda = person.printAgenda(10);
        assertNotNull(agenda);
    }

    @Test
    public void testPrintAgendaForSpecificDay() {
        String agenda = person.printAgenda(10, 20);
        assertNotNull(agenda);
    }

    @Test
    public void testRemoveMeeting() throws Exception {
        Meeting m = new Meeting(11, 5, 9, 10);
        person.addMeeting(m);

        assertNotNull(person.getMeeting(11, 5, 0));
        person.removeMeeting(11, 5, 0);

        try {
            person.getMeeting(11, 5, 0);
            fail("Expected IndexOutOfBoundsException after removing meeting");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            // Expected: no meeting after removal
        }
    }
}
    