package edu.sc.csce747.MeetingPlanner;

import java.util.ArrayList;

public class Meeting {
	private int month;
	private int day;
	private int start;
	private int end;
	private ArrayList<Person> attendees; 
	private Room room;
	private String description;
	
	/**
	 * Default constructor
	 */
	public Meeting(){
		this.month=0;
		this.day=0;
		this.start=0;
		this.end=0;
		this.attendees = new ArrayList<Person>();
		this.room = null;
		this.description = "";
	}
	
	/**
	 * Constructor that can be used to block off a whole day -
	 * such as for a vacation
	 * @param month - The month of the meeting (1-12).
	 * @param day - The day of the meeting (1-31).
	 */
	public Meeting(int month, int day){
		this.month=month;
		this.day=day;
		this.start=0;
		this.end=23;
	}
	
	/**
	 * Constructor that can be used to block off a whole day -
	 * such as for a vacation
	 * @param month - The month of the meeting (1-12).
	 * @param day - The day of the meeting (1-31).
	 * @param description - A description of the meeting.
	 */
	public Meeting(int month, int day, String description){
		this.month=month;
		this.day=day;
		this.start=0;
		this.end=23;
		this.description= description;
	}
	
	/**
	 * More detailed constructor
	 * @param month - The month of the meeting (1-12).
	 * @param day - The day of the meeting (1-31).
	 * @param start - The time the meeting starts (0-23).
	 * @param end - The time the meeting ends (0-23).
	 */
	public Meeting(int month, int day, int start, int end){
		this.month=month;
		this.day=day;
		this.start=start;
		this.end=end;
	}
	
	/**
	 * More detailed constructor
	 * @param month - The month of the meeting (1-12).
	 * @param day - The day of the meeting (1-31).
	 * @param start - The time the meeting starts (0-23).
	 * @param end - The time the meeting ends (0-23).
	 * @param attendees - The people attending the meeting.
	 * @param room - The room that the meeting is taking place in.
	 * @param description - A description of the meeting.
	 */
	public Meeting(int month, int day, int start, int end, ArrayList<Person> attendees, Room room, String description){
		this.month=month;
		this.day=day;
		this.start=start;
		this.end=end;
		this.attendees = attendees;
		this.room = room;
		this.description = description;
	}
	public void addAttendee(Person attendee) {
		if (attendee == null) {
			throw new IllegalArgumentException("Attendee cannot be null");
		}
		if (this.attendees == null) {
			this.attendees = new ArrayList<Person>();
		}
		this.attendees.add(attendee);
	}

	public void removeAttendee(Person attendee) {
		if (this.attendees != null && attendee != null) {
			this.attendees.remove(attendee);
		}
	}

	/**
	 * Returns information about the meeting as a formatted string.
	 * @return String - Information about the meeting.
	 */
	public String toString(){
		String roomInfo = (room != null) ? room.getID() : "No Room";
		String descInfo = (description != null) ? description : "No Description";

		String info = month + "/" + day + ", " + start + " - " + end + ","
				+ roomInfo + ": " + descInfo + "\nAttending: ";

		if (attendees == null || attendees.isEmpty()) {
			info += "None";
		} else {
			for (Person attendee : attendees) {
				info += attendee.getName() + ",";
			}
			info = info.substring(0, info.length() - 1); // remove last comma
		}

		return info;
	}
	
	/**
	 * Getters and Setters
	 */
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getStartTime() {
		return start;
	}

	public void setStartTime(int start) {
		this.start = start;
	}

	public int getEndTime() {
		return end;
	}

	public void setEndTime(int end) {
		this.end = end;
	}

	public ArrayList<Person> getAttendees() {
		return attendees;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
