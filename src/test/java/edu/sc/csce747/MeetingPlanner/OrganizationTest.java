package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class OrganizationTest {

    private Organization org;

    @Before
    public void setUp() {
        org = new Organization();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(org.getEmployees());
        assertNotNull(org.getRooms());
        assertEquals(5, org.getEmployees().size());
        assertEquals(5, org.getRooms().size());
    }

    @Test
    public void testGetEmployees() {
        ArrayList<Person> employees = org.getEmployees();
        assertTrue(employees.stream().anyMatch(p -> p.getName().equals("Greg Gay")));
        assertTrue(employees.stream().anyMatch(p -> p.getName().equals("Manton Matthews")));
    }

    @Test
    public void testGetRooms() {
        ArrayList<Room> rooms = org.getRooms();
        assertTrue(rooms.stream().anyMatch(r -> r.getID().equals("2A01")));
        assertTrue(rooms.stream().anyMatch(r -> r.getID().equals("2A05")));
    }

    @Test
    public void testGetRoomExists() throws Exception {
        Room room = org.getRoom("2A03");
        assertNotNull(room);
        assertEquals("2A03", room.getID());
    }

    // @Test
    // public void testGetRoomNotExists() {
    //     try {
    //         org.getRoom("999");
    //         fail("Expected an Exception for non-existing room");
    //     } catch (Exception e) {
    //         assertEquals("Requested room does not exist", e.getMessage());
    //     }
    // }

    // @Test
    // public void testGetEmployeeNotExists() {
    //     try {
    //         org.getEmployee("Non Existent");
    //         fail("Expected an Exception for non-existing employee");
    //     } catch (Exception e) {
    //         assertEquals("Requested employee does not exist", e.getMessage());
    //     }
    // }
    // @Test
    // public void testGetRoomWithNull() {
    //     try {
    //         org.getRoom(null);
    //         fail("Expected an Exception for null room ID");
    //     } catch (Exception e) {
    //         assertEquals("Requested room does not exist", e.getMessage());
    //     }
    // }

    // @Test
    // public void testGetEmployeeWithNull() {
    //     try {
    //         org.getEmployee(null);
    //         fail("Expected an Exception for null employee name");
    //     } catch (Exception e) {
    //         assertEquals("Requested employee does not exist", e.getMessage());
    //     }
    // }
}
