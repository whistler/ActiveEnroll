package com.mss.tuess.timetable;

/**
 * Reference from http://sourceforge.net/projects/seyongj/
 *
 * @author seyongj
 * @project TScheduler v0.2
 * @last Update: 2013-03-19
 * @reference date 2013-04-08
 */
/**
 * TScheduler v0.2
 */
/**
 * Manages data using TSchedule
 */
public class DataControl {

    String title;
    String room;
    String day;
    String start;
    String end;
    String semester;
    int MAX_TITLE = 50;
    int MAX_ROOM = 10;
    int MAX_DAY = 9;
    int MAX_START = 5;
    int MAX_END = 5;
    int MAX_SEMESTER = 6;

    /**
     * Constructor gets each data and stores them
     */
    public DataControl(String title, String room, String day, String start, String end, String semester) {
        setTitle(title);
        setRoom(room);
        setDay(day);
        setStart(start);
        setEnd(end);
        setSemester(semester);
    }

    /**
     * Constructor get a line, and separately stores them
     */
    public DataControl(String wd) {
        setTitle(wd.substring(0, 50));
        setRoom(wd.substring(51, 61));
        setDay(wd.substring(62, 71));
        setStart(wd.substring(72, 77));
        setEnd(wd.substring(78, 83));
        setSemester(wd.substring(84));
    }

    /**
     * Constructor default constructor
     */
    public DataControl() {
        title = new String();
        room = new String();
        day = new String();
        start = new String();
        end = new String();
        semester = new String();
    }

    /**
     * returns a String which store all data
     */
    public String getData() {
        System.out.println("\n" + title + " " + room + " " + day + " " + start + " " + end + " " + semester);
        return title + " " + room + " " + day + " " + start + " " + end + " " + semester;
    }

    /**
     * sets Title
     */
    public void setTitle(String str) {
        int len = str.trim().length();
        if (len - 1 < MAX_TITLE) {
            this.title = str.trim() + getBlank(len, MAX_TITLE);
        } else {
            this.title = str.trim().substring(0, MAX_TITLE);
        }
    }

    /**
     * sets class room
     */
    public void setRoom(String str) {
        int len = str.trim().length();
        if (len - 1 < MAX_ROOM) {
            this.room = str.trim() + getBlank(len, MAX_ROOM);
        } else {
            this.room = str.trim().substring(0, MAX_ROOM);
        }
    }

    /**
     * sets day
     */
    public void setDay(String str) {
        int len = str.trim().length();
        if (len - 1 < MAX_DAY) {
            this.day = str.trim() + getBlank(len, MAX_DAY);
        } else {
            this.day = str.trim().substring(0, MAX_DAY);
        }
    }

    /**
     * sets starting time
     */
    public void setStart(String str) {
        int len = str.trim().length();
        if (len - 1 < MAX_START) {
            this.start = str.trim() + getBlank(len, MAX_START);
        } else {
            this.start = str.trim().substring(0, MAX_START);
        }
    }

    /**
     * sets ending time
     */
    public void setEnd(String str) {
        int len = str.trim().length();
        if (len - 1 < MAX_END) {
            this.end = str.trim() + getBlank(len, MAX_END);
        } else {
            this.end = str.trim().substring(0, MAX_END);
        }
    }

    /**
     * sets semester
     */
    public void setSemester(String str) {
        int len = str.trim().length();
        if (len - 1 < MAX_SEMESTER) {
            this.semester = str.trim() + getBlank(len, MAX_SEMESTER);
        } else {
            this.semester = str.trim().substring(0, MAX_SEMESTER);
        }
    }

    /**
     * return blanks used to arrange data
     */
    private String getBlank(int lengthOfStirng, int max) {
        int numBlank = max - lengthOfStirng;
        String strBlank = new String("");
        for (int i = 0; i < numBlank; i++) {
            strBlank += " ";
        }
        return strBlank;
    }

    /**
     * returns title
     */
    public String getTitle() {
        return title;
    }

    /**
     * returns class room
     */
    public String getRoom() {
        return room;
    }

    /**
     * returns starting time
     */
    public String getStart() {
        return start;
    }

    /**
     * returns ending time
     */
    public String getEnd() {
        return end;
    }

    /**
     * returns day
     */
    public String getDay() {
        return day;
    }

    /**
     * returns type of semester
     */
    public String getSemester() {
        return semester;
    }
}