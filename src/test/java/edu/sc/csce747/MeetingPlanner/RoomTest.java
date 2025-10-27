package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RoomTest {

    private Room room;

    @Before
    public void setUp() {
        room = new Room("2A01");
    }

    @Test
    public void testDefaultConstructor() {
        Room r = new Room();
        assertNotNull(r);
        assertEquals("", r.getID());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("2A01", room.getID());
    }

    @Test
    public void testAddMeetingNoConflict() throws Exception {
        Meeting meeting = new Meeting(10, 15, 9, 11);
        room.addMeeting(meeting);

        Meeting retrieved = room.getMeeting(10, 15, 0);
        assertNotNull(retrieved);
        assertEquals(9, retrieved.getStartTime());
        assertEquals(11, retrieved.getEndTime());
    }

    @Test
    public void testAddMeetingWithConflict() {
        try {
            Meeting m1 = new Meeting(10, 20, 13, 15);
            Meeting m2 = new Meeting(10, 20, 14, 16);

            room.addMeeting(m1);
            room.addMeeting(m2); // Should throw

            fail("Expected TimeConflictException not thrown");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("Conflict for room 2A01"));
        }
    }

    @Test
    public void testIsBusy() throws Exception {
        Meeting m = new Meeting(11, 5, 8, 10);
        room.addMeeting(m);

        assertTrue(room.isBusy(11, 5, 8, 9));
        assertFalse(room.isBusy(11, 6, 8, 9));
    }

    @Test
    public void testPrintAgendaForMonth() {
        String agenda = room.printAgenda(10);
        assertNotNull(agenda);
    }

    @Test
    public void testPrintAgendaForSpecificDay() {
        String agenda = room.printAgenda(10, 15);
        assertNotNull(agenda);
    }

    @Test
    public void testRemoveMeeting() throws Exception {
        Meeting m = new Meeting(12, 1, 9, 11);
        room.addMeeting(m);

        assertNotNull(room.getMeeting(12, 1, 0));
        room.removeMeeting(12, 1, 0);

        try {
            room.getMeeting(12, 1, 0);
            fail("Expected IndexOutOfBoundsException after removing meeting");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            // Expected
        }
    }
}
