package edu.sc.csce747.MeetingPlanner;

import java.util.ArrayList;

public class Calendar {
    // Indexed by Month, Day
    private ArrayList<ArrayList<ArrayList<Meeting>>> occupied;

    /**
     * Default constructor, builds a calendar and initializes each day
     * to an empty list.
     */
    public Calendar() {
        /**
         * Create an empty calendar
         * Order of access is month, day, meetingNumber
         * We want to tie 1 to January, 2 to February, etc,
         * so we will index 1–12 for months, 1–31 for days.
         * Times are indexed 0–23.
         */
        occupied = new ArrayList<>();

        // Fix: Create 13 months (0–12) but skip 0 index for simplicity
        for (int i = 0; i <= 12; i++) {
            ArrayList<ArrayList<Meeting>> days = new ArrayList<>();
            for (int j = 0; j <= 31; j++) {
                days.add(new ArrayList<>());
            }
            occupied.add(days);
        }

        /** Mark invalid dates as “Day does not exist” */
        addInvalidDays();
    }

    private void addInvalidDays() {
        // February invalid days
        addInvalidDay(2, 29);
        addInvalidDay(2, 30);
        addInvalidDay(2, 31);

        // April, June, September, November invalid days
        addInvalidDay(4, 31);
        addInvalidDay(6, 31);
        addInvalidDay(9, 31);
        addInvalidDay(11, 31);
    }

    private void addInvalidDay(int month, int day) {
        occupied.get(month).get(day).add(new Meeting(month, day, "Day does not exist"));
    }

    /**
     * Used to check whether a meeting is scheduled during a particular time frame.
     */
    public boolean isBusy(int month, int day, int start, int end) throws TimeConflictException {
        checkTimes(month, day, start, end);

        for (Meeting toCheck : occupied.get(month).get(day)) {
            if (start >= toCheck.getStartTime() && start < toCheck.getEndTime()) {
                return true;
            } else if (end > toCheck.getStartTime() && end <= toCheck.getEndTime()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates date and time inputs.
     */
    public static void checkTimes(int mMonth, int mDay, int mStart, int mEnd) throws TimeConflictException {
        if (mMonth < 1 || mMonth > 12) {
            throw new TimeConflictException("Month does not exist.");
        }

        if (mDay < 1 || mDay > 31) {
            throw new TimeConflictException("Day does not exist.");
        }

        if (mStart < 0 || mStart > 23 || mEnd < 0 || mEnd > 23) {
            throw new TimeConflictException("Illegal hour.");
        }

        if (mStart >= mEnd) {
            throw new TimeConflictException("Meeting starts before it ends.");
        }
    }

    /**
     * Adds a meeting to the calendar.
     */
    public void addMeeting(Meeting toAdd) throws TimeConflictException {
        int mMonth = toAdd.getMonth();
        int mDay = toAdd.getDay();
        int mStart = toAdd.getStartTime();
        int mEnd = toAdd.getEndTime();

        checkTimes(mMonth, mDay, mStart, mEnd);

        ArrayList<Meeting> thatDay = occupied.get(mMonth).get(mDay);
        for (Meeting toCheck : thatDay) {
            if (!"Day does not exist".equals(toCheck.getDescription())) {
                if ((mStart >= toCheck.getStartTime() && mStart < toCheck.getEndTime()) ||
                    (mEnd > toCheck.getStartTime() && mEnd <= toCheck.getEndTime())) {
                    throw new TimeConflictException("Overlap with another item - "
                            + toCheck.getDescription() + " - scheduled from "
                            + toCheck.getStartTime() + " to " + toCheck.getEndTime());
                }
            }
        }

        thatDay.add(toAdd);
    }

    /**
     * Clears all meetings for a day.
     */
    public void clearSchedule(int month, int day) {
        if (month >= 1 && month <= 12 && day >= 1 && day <= 31) {
            occupied.get(month).set(day, new ArrayList<>());
        }
    }

    /**
     * Prints the agenda for a month.
     */
    public String printAgenda(int month) {
        StringBuilder agenda = new StringBuilder("Agenda for " + month + ":\n");
        if (month < 1 || month > 12) return agenda.append("Invalid month\n").toString();

        for (int d = 1; d <= 31; d++) {
            for (Meeting meeting : occupied.get(month).get(d)) {
                agenda.append(meeting.toString()).append("\n");
            }
        }
        return agenda.toString();
    }

    /**
     * Prints the agenda for a specific day.
     */
    public String printAgenda(int month, int day) {
        StringBuilder agenda = new StringBuilder("Agenda for " + month + "/" + day + ":\n");
        if (month < 1 || month > 12 || day < 1 || day > 31)
            return agenda.append("Invalid date\n").toString();

        for (Meeting meeting : occupied.get(month).get(day)) {
            agenda.append(meeting.toString()).append("\n");
        }
        return agenda.toString();
    }

    /**
     * Retrieves a meeting.
     */
    public Meeting getMeeting(int month, int day, int index) {
        return occupied.get(month).get(day).get(index);
    }

    /**
     * Removes a meeting.
     */
    public void removeMeeting(int month, int day, int index) {
        if (month >= 1 && month <= 12 && day >= 1 && day <= 31 && index >= 0) {
            ArrayList<Meeting> meetings = occupied.get(month).get(day);
            if (index < meetings.size()) {
                meetings.remove(index);
            }
        }
    }
}
