package edu.sc.csce747.MeetingPlanner;

import java.util.ArrayList;
import java.util.List;

public class Organization {

    /**
     * This class is used to initialize and store a list of employees
     * and rooms available for meetings.
     * It separates initialization from the PlannerInterface.
     */
    
    private final ArrayList<Person> employees;
    private final ArrayList<Room> rooms;

    /**
     * Default constructor - sets up some rooms and employees.
     */
    public Organization() {
        employees = new ArrayList<>();
        employees.add(new Person("Greg Gay"));
        employees.add(new Person("Manton Matthews"));
        employees.add(new Person("John Rose"));
        employees.add(new Person("Ryan Austin"));
        employees.add(new Person("Csilla Farkas"));

        rooms = new ArrayList<>();
        rooms.add(new Room("2A01"));
        rooms.add(new Room("2A02"));
        rooms.add(new Room("2A03"));
        rooms.add(new Room("2A04"));
        rooms.add(new Room("2A05"));
    }

    public ArrayList<Person> getEmployees() {
        return employees;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Search for and retrieve a room by ID.
     *
     * @param id the ID of the room to retrieve
     * @return the requested Room object
     * @throws Exception if the room does not exist or id is null
     */
    public Room getRoom(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new Exception("Requested room does not exist");
        }
        return rooms.stream()
                .filter(r -> id.equals(r.getID()))
                .findFirst()
                .orElseThrow(() -> new Exception("Requested room does not exist"));
    }

    /**
     * Search for and retrieve an employee by name.
     *
     * @param name the name of the employee to retrieve
     * @return the requested Person object
     * @throws Exception if the employee does not exist or name is null
     */
    public Person getEmployee(String name) throws Exception {
        if (name == null || name.isEmpty()) {
            throw new Exception("Requested employee does not exist");
        }
        return employees.stream()
                .filter(e -> name.equals(e.getName()))
                .findFirst()
                .orElseThrow(() -> new Exception("Requested employee does not exist"));
    }
}
