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
import com.mss.tuess.entity.SectionClass;
import com.mss.tuess.util.State;
import java.util.*;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class running main method
 */
/**
 * Class for main frame which appears when a program first runs
 */
public class TScheduler {

    public String timeTable;
   
    ArrayList alData = new ArrayList();
    DataControl dcData = new DataControl();
    boolean fDouble = false;
    String[] timeList;
    String[] strDay = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    boolean[][] body1;
    boolean[][] body2;
  
    String[] data = new String[12];


    /**
     * Initialize components
     */
 

    /**
     * Initailize a JCombobox which contains time range
     */
    public String[] makeTime() {
        String strTime[] = new String[34];

        for (int i = 0; i < 17; i++) {
            strTime[i * 2] = new String();
            //strTime[i*2] = (i+7) + ":00-" + (i+7) +":30";
            if (i < 3) {
                strTime[i * 2] = "0" + (i + 7) + ":00";
            } else {
                strTime[i * 2] = (i + 7) + ":00";
            }
        }
        for (int i = 0; i < 17; i++) {
            strTime[i * 2 + 1] = new String();
            //strTime[i*2+1] = (i+7) + ":30-" + (i+8) +":00";
            if (i < 3) {
                strTime[i * 2 + 1] = "0" + (i + 7) + ":30";
            } else {
                strTime[i * 2 + 1] = (i + 7) + ":30";
            }
        }
        return strTime;
    }


    /**
     * Update Schedule which the user inputs
     */
    public String update() {

        {
            try {
                SectionClass.fetchregisteredSectionClassList();
                ArrayList<SectionClass> registeredSectionClassList = SectionClass.getAllregisteredSectionClassList();
                int total = registeredSectionClassList.size();
                int num = 0;
                System.out.println("\nTotal=" + total);
                if (total==0)
                    return "\nYou do not have any courses this term\n";
                while (num < total) {
                    DataControl dc = new DataControl();
                    dc.setTitle((registeredSectionClassList.get(num).getCourseDept() + registeredSectionClassList.get(num).getCourseNum()).trim());
                    dc.setRoom(registeredSectionClassList.get(num).getLocation().trim());
                    dc.setDay(registeredSectionClassList.get(num).getDay().trim());
                    dc.setStart(registeredSectionClassList.get(num).getDisplayStartTime().trim());
                    dc.setEnd(registeredSectionClassList.get(num).getDisplayEndTime().trim());
                    dc.setSemester(("Full").trim());
                    System.out.println(dc.getStart() + "_~_" + dc.getEnd() + "_" + registeredSectionClassList.get(num).getCourseDept() + "_"
                            + registeredSectionClassList.get(num).getDay() + "_"
                            + registeredSectionClassList.get(num).getCourseNum() + "_" + registeredSectionClassList.get(num).getType()
                            + "_" + registeredSectionClassList.get(num).getClassID());
                    alData.add(dc.getData());

                    num++;
                }
            } catch (SQLException ex) {
            }

            ShowFrame sf = new ShowFrame(alData);
            return sf.tableContent;
        }
    }

    /**
     * check whether current schedule conflicts with existing schedule return
     * true means that there is no confliction return false means that there is
     * a confliction
     */
    private boolean checkConflict(DataControl dc) {

        return true;
    }

    /**
     * return the index number of parameter d2 in parameter d1
     */
    private int getDayNum(String[] d1, String d2) {

        for (int j = 0; j < d1.length; j++) {
            if (d2.trim().equals(d1[j].trim())) {
                return j;
            }
        }
        return 0;
    }

    /**
     * return the difference between starting time of schedule and ending time
     * of schedule return value is hour * 2 getTimeDifference("09:00", "10:00")
     * will return 2 getTimeDifference("09:00", "11:30") will return 5
     */
    private int getTimeDifference(String start, String end) {
        //all full semester
        int difference = (Integer.parseInt(end.substring(0, 2))
                - Integer.parseInt(start.substring(0, 2))) * 2;
        int minute = Integer.parseInt(end.substring(3, 5))
                - Integer.parseInt(start.substring(3, 5));

        if (minute > 0) {
            difference++;
        } else if (minute < 0) {
            difference--;
        }

        return difference;
    }

}
