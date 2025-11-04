package edu.sc.csce747.MeetingPlanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class MeetingTest {

    @Test
    void testDefaultConstructor() {
        Meeting m = new Meeting();
        assertNotNull(m);
    }

    @Test
    void testConstructors() {
        Meeting m1 = new Meeting(1, 10);
        assertEquals(1, m1.getMonth());
        assertEquals(10, m1.getDay());

        Meeting m2 = new Meeting(2, 15, "Vacation");
        assertEquals("Vacation", m2.getDescription());

        Meeting m3 = new Meeting(3, 20, 9, 11);
        assertEquals(9, m3.getStartTime());
        assertEquals(11, m3.getEndTime());

        ArrayList<Person> attendees = new ArrayList<>();
        attendees.add(new Person("Alice"));
        Room room = new Room("Room101");
        Meeting m4 = new Meeting(4, 25, 14, 15, attendees, room, "Meeting");
        assertEquals("Meeting", m4.getDescription());
        assertEquals("Room101", m4.getRoom().getID());
        assertEquals(1, m4.getAttendees().size());
    }

    @Test
    void testAddAttendee() {
        Meeting m = new Meeting();
        Person p = new Person("Bob");
        m.addAttendee(p);
        assertEquals(1, m.getAttendees().size());
    }

    @Test
    public void testRemoveAttendeeNullAndNotInList() {
        Meeting m = new Meeting(1, 1);
        Person p1 = new Person("Bob");
        m.addAttendee(p1);

        m.removeAttendee(null);  // nothing happens
        Person p2 = new Person("NotInList");
        m.removeAttendee(p2); // also nothing happens
        assertEquals(1, m.getAttendees().size());  // still only Bob
    }


    @Test
    void testToString() {
        Room r = new Room("RoomA");
        Meeting m = new Meeting(1, 1, 9, 10, new ArrayList<>(), r, "Project Meeting");
        assertTrue(m.toString().contains("Project Meeting"));
    }

    @Test
    void testGettersSetters() {
        Meeting m = new Meeting();
        m.setMonth(5);
        m.setDay(12);
        m.setStartTime(8);
        m.setEndTime(10);
        m.setDescription("Daily Standup");
        Room r = new Room("RoomB");
        m.setRoom(r);

        assertEquals(5, m.getMonth());
        assertEquals(12, m.getDay());
        assertEquals(8, m.getStartTime());
        assertEquals(10, m.getEndTime());
        assertEquals("Daily Standup", m.getDescription());
        assertEquals("RoomB", m.getRoom().getID());
    }

    // @Test
    // public void testAddAttendeeWithNull() {
    //     Meeting meeting = new Meeting(5, 10);
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         meeting.addAttendee(null);
    //     });
    //     assertEquals("Attendee cannot be null", exception.getMessage());
    // }

    @Test
    public void testRemoveAttendeeNullOrEmpty() {
        Meeting m = new Meeting(1, 1);
        m.removeAttendee(null); // should do nothing
        m.removeAttendee(new Person("Nobody")); // also does nothing, branch executed
    }

    @Test
    public void testToStringWithNulls() {
        Meeting m = new Meeting(1, 1);
        m.setRoom(null);
        m.setDescription(null);
        m.addAttendee(new Person("Alice")); // also test with attendees empty
        assertNotNull(m.toString());
    }

    @Test
    public void testToStringWithNullRoom() {
        Meeting m = new Meeting(1, 1);
        m.setRoom(null);  // room is null
        m.setDescription(null); // description null
        m.addAttendee(new Person("Alice"));
        String result = m.toString();
        assertTrue(result.contains("No Room"));
        assertTrue(result.contains("No Description"));
        assertTrue(result.contains("Alice"));
    }

    // @Test
    // public void testToStringWithEmptyAttendees() {
    //     Meeting m = new Meeting(1, 1);
    //     m.setRoom(new Room("101"));
    //     m.setDescription("Test meeting");
    //     m.getAttendees().clear(); // no attendees
    //     String result = m.toString();
    //     assertTrue(result.contains("None"));  // attendees empty
    // }
    @Test
    public void testToStringWithNullFields() {
        Meeting m1 = new Meeting(5, 10);
        
        // room is null, description is null, attendees is null
        String result = m1.toString();
        assertTrue(result.contains("No Room"));
        assertTrue(result.contains("No Description"));
        assertTrue(result.contains("Attending: None"));
    }


    @Test
    public void testToStringWithRoomAndDescription() {
        Meeting m4 = new Meeting(5, 10);
        m4.setRoom(new Room("102"));
        m4.setDescription("Planning");
        m4.addAttendee(new Person("Alice"));
        String result = m4.toString();
        assertTrue(result.contains("102"));
        assertTrue(result.contains("Planning"));
        assertTrue(result.contains("Alice"));
    }
}
