package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MeetingTest {

    private Person alice;
    private Person bob;
    private Room room101;

    @Before
    public void setUp() {
        alice = new Person("Alice");
        bob = new Person("Bob");
        room101 = new Room("101");
    }

    @Test
    public void testDefaultConstructor() {
        Meeting m = new Meeting();
        assertNotNull(m);
    }

    @Test
    public void testDayBlockingConstructor() {
        Meeting m = new Meeting(5, 12);
        assertEquals(5, m.getMonth());
        assertEquals(12, m.getDay());
        assertEquals(0, m.getStartTime());
        assertEquals(23, m.getEndTime());
    }

    @Test
    public void testDayBlockingWithDescriptionConstructor() {
        Meeting m = new Meeting(6, 15, "Vacation");
        assertEquals("Vacation", m.getDescription());
        assertEquals(0, m.getStartTime());
        assertEquals(23, m.getEndTime());
    }

    @Test
    public void testDetailedConstructor() {
        Meeting m = new Meeting(7, 20, 9, 12);
        assertEquals(7, m.getMonth());
        assertEquals(20, m.getDay());
        assertEquals(9, m.getStartTime());
        assertEquals(12, m.getEndTime());
    }

    @Test
    public void testFullConstructor() {
        ArrayList<Person> attendees = new ArrayList<>();
        attendees.add(alice);
        Meeting m = new Meeting(8, 10, 10, 11, attendees, room101, "Team Meeting");

        assertEquals(8, m.getMonth());
        assertEquals(10, m.getDay());
        assertEquals(10, m.getStartTime());
        assertEquals(11, m.getEndTime());
        assertEquals(room101, m.getRoom());
        assertEquals("Team Meeting", m.getDescription());
        assertTrue(m.getAttendees().contains(alice));
    }

    @Test
    public void testAddAttendee() {
        Meeting m = new Meeting();
        m.addAttendee(alice);
        assertTrue(m.getAttendees().contains(alice));
    }

    @Test
    public void testRemoveAttendee() {
        Meeting m = new Meeting();
        m.addAttendee(alice);
        m.addAttendee(bob);
        m.removeAttendee(alice);
        assertFalse(m.getAttendees().contains(alice));
        assertTrue(m.getAttendees().contains(bob));
    }

    @Test
    public void testToString() {
        ArrayList<Person> attendees = new ArrayList<>();
        attendees.add(alice);
        Meeting m = new Meeting(9, 5, 10, 12, attendees, room101, "Planning");

        String str = m.toString();
        assertTrue(str.contains("9/5"));
        assertTrue(str.contains("10 - 12"));
        assertTrue(str.contains("101"));
        assertTrue(str.contains("Planning"));
        assertTrue(str.contains("Alice"));
    }

    @Test
    public void testSettersAndGetters() {
        Meeting m = new Meeting();
        m.setMonth(3);
        m.setDay(14);
        m.setStartTime(9);
        m.setEndTime(17);
        m.setRoom(room101);
        m.setDescription("Update Meeting");

        assertEquals(3, m.getMonth());
        assertEquals(14, m.getDay());
        assertEquals(9, m.getStartTime());
        assertEquals(17, m.getEndTime());
        assertEquals(room101, m.getRoom());
        assertEquals("Update Meeting", m.getDescription());
    }
}
