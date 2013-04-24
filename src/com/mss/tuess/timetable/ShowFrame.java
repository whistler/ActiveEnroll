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
import com.mss.tuess.util.State;
import java.util.*;
import java.io.*;

/**
 * html viewer frame provide save schedule to disk and copy to html source code
 * function
 */
public class ShowFrame {

    String tableContent;
    ArrayList alData = new ArrayList();
    String strMini1Sechedule = new String();
    String strMini2Sechedule = new String();
    String head;
    String body;
    String tail;
    String output;
    int row = 0;
    int col = 0;
    String startingTimeOfDay = new String();
    String endingTimeOfDay = new String();
    DataControl[] dcData;
    String[] day = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    int[][] intScheduleTable1;
    String[][] strScheduleTable1;
    int[][] intScheduleTable2;
    String[][] strScheduleTable2;
    String[] strTimeList = new String[34];
    int indexOfTimeList = 0;

    /**
     * default constructor
     */
    ShowFrame() {
    }

    /**
     * constructor
     */
    ShowFrame(ArrayList alData) {
        setTimeList();
        this.alData = alData;
        dcData = new DataControl[alData.size()];
        //dcData = new DataControl[10];
        for (int i = 0; i < alData.size(); i++) {
            dcData[i] = new DataControl((String) alData.get(i));
        }
        start();
    }

    /**
     * initializes components and data
     */
    private void start() {
        setStartingTime();
        setEndingTime();

        output = makeHead() + makeBody(checkMini()) + makeTail();

   
    }

    /**
     * default make first part of html file which will show schedule
     */
    private String makeHead() {
        String tempHead = new String("<HTML><BODY>");
        String temp;
        int mDay = 0;
        for (int i = 0; i < dcData.length; i++) {
            for (int j = 0; j < day.length; j++) {
                if (dcData[i].getDay().trim().equals(day[j].trim())) {
                    if (j > mDay) {
                        mDay = j;
                    }
                }
            }
        }
        //col = mDay;
        col = 4;
        return tempHead;
    }

    /**
     * finds the earliest starting time
     */
    private void setStartingTime() {
        int sTime = strToInt(dcData[0].getStart());
        int tTime = 0;
        for (int i = 0; i < dcData.length; i++) {
            tTime = strToInt(dcData[i].getStart());
            if (sTime > tTime) {
                sTime = tTime;
            }
        }
        startingTimeOfDay = intToTimeStr(sTime);
        for (int i = 0; i < strTimeList.length; i++) {
            if (startingTimeOfDay.trim().equals(strTimeList[i].trim())) {
                indexOfTimeList = i;
                break;
            }
        }
    }

    /**
     * finds the last ending time
     */
    private void setEndingTime() {
        int eTime = 0;
        int tTime = 0;
        for (int i = 0; i < dcData.length; i++) {
            tTime = strToInt(dcData[i].getEnd());
            if (eTime < tTime) {
                eTime = tTime;
            }
        }
        endingTimeOfDay = intToTimeStr(eTime);
    }

    /**
     * convert string format time to integer format time
     */
    private int strToInt(String time) {
        String temp = new String();
        temp = time.trim().substring(0, 2);
        temp += time.trim().substring(3, 5);
        return Integer.parseInt(temp);
    }

    /**
     * convert string format time to integer format time
     */
    private String intToTimeStr(int time) {
        String temp = "" + time;
        if (time < 1000) {
            return "0" + temp.substring(0, 1) + ":" + temp.substring(1);
        } else {
            return temp.substring(0, 2) + ":" + temp.substring(2);
        }
    }

    /**
     * make body of html file which will show schedule
     */
    private String makeBody(boolean miniFlag) {
        //boolean sFlag = false;
        row = getTimeDifference(startingTimeOfDay, endingTimeOfDay);
        String tempBody = new String();

        if (miniFlag) {

            makeTableBody(miniFlag);

            //For Mini 1
            tempBody += "<h2>Mini 1 Semester</h2>\n";
            tempBody += "<table width='100%' border='1' cellpadding='1' cellspacing='0' ";
            tempBody += "bordercolorlight='#F9ECBF' bordercolordark='#FBFEE2' bgcolor='#E4EEE6'>\n";
            tempBody += "<tr align='center' valign='middle' bordercolor='#6D5A0E'>\n";
            tempBody += "<td width='15%' bordercolor='#705C0E'>&nbsp;</td>\n";
            for (int i = 0; i < col + 1; i++) {
                tempBody += "<td width='15%' height='30'><strong>" + day[i] + "</strong></td>\n";
            }
            tempBody += "</tr>\n";
            for (int i = 0; i < row; i++) {
                tempBody += "<tr align='center' valign='middle'>\n";
                if (indexOfTimeList + i == strTimeList.length - 1) {
                    tempBody += "<td width='15%' height='30' bordercolor='#705C00'>" + strTimeList[indexOfTimeList + i]
                            + " - 24:00</td>\n";
                } else {
                    tempBody += "<td width='15%' height='30' bordercolor='#705C00'>" + strTimeList[indexOfTimeList + i]
                            + " - " + strTimeList[indexOfTimeList + i + 1] + "</td>\n";
                }
                for (int j = 0; j < col + 1; j++) {
                    if (intScheduleTable1[i][j] == 0) {
                        tempBody += "<td width='15%' height='30' bordercolor='#705C00'>&nbsp;</td>\n";
                    } else if (intScheduleTable1[i][j] == -1) {
                    } else {
                        tempBody += "<td width='15%' height='30' rowspan='" + intScheduleTable1[i][j];
                        tempBody += "' bordercolor='#FF0000' bgcolor='#728B93'>";
                        tempBody += strScheduleTable1[i][j];
                        tempBody += "</td>\n";
                    }
                }
                tempBody += "</tr>\n";
            }
            tempBody += "</table>\n";

            //For Mini 2
            tempBody += "<h2>Mini 2 Semester</h2>\n";
            tempBody += "<table width='100%' border='1' cellpadding='1' cellspacing='0' ";
            tempBody += "bordercolorlight='#F9ECBF' bordercolordark='#FBFEE2' bgcolor='#E4EEE6'>\n";
            tempBody += "<tr align='center' valign='middle' bordercolor='#6D5A0E'>\n";
            tempBody += "<td width='15%' bordercolor='#705C0E'>&nbsp;</td>\n";
            for (int i = 0; i < col + 1; i++) {
                tempBody += "<td width='15%' height='30'><strong>" + day[i] + "</strong></td>\n";
            }
            tempBody += "</tr>\n";
            for (int i = 0; i < row; i++) {
                tempBody += "<tr align='center' valign='middle'>\n";
                if (indexOfTimeList + i == strTimeList.length - 1) {
                    tempBody += "<td width='15%' height='30' bordercolor='#705C0''>" + strTimeList[indexOfTimeList + i]
                            + " - 24:00</td>\n";
                } else {
                    tempBody += "<td width='15%' height='30' bordercolor='#705C0''>" + strTimeList[indexOfTimeList + i]
                            + " - " + strTimeList[indexOfTimeList + i + 1] + "</td>\n";
                }
                for (int j = 0; j < col + 1; j++) {
                    if (intScheduleTable2[i][j] == 0) {
                        tempBody += "<td width='15%' height='30' bordercolor='#705C00'>&nbsp;</td>\n";
                    } else if (intScheduleTable2[i][j] == -1) {
                    } else {
                        tempBody += "<td width='15%' height='30' rowspan='" + intScheduleTable2[i][j];
                        tempBody += "' bordercolor='#FF0000' bgcolor='#728B93'>";
                        tempBody += strScheduleTable2[i][j];
                        tempBody += "</td>\n";
                    }
                }
                tempBody += "</tr>\n";
            }
            tempBody += "</table>\n";
        } else {
            makeTableBody(miniFlag);
            tempBody += "<CENTER><h2>Semester " + State.getCurrentTerm().getTermID() + "</h2></CENTER>\n";
            tempBody += "<table width='100%' border='1' cellpadding='1' cellspacing='0' ";
            tempBody += "bordercolorlight='#F9ECBF' bordercolordark='#FBFEE2' bgcolor='#E4EEE6'>\n";
            tempBody += "<tr align='center' valign='middle' bordercolor='#6D5A0E'>\n";
            tempBody += "<td width='15%' bordercolor='#705C0E'>&nbsp;</td>\n";
            for (int i = 0; i < col + 1; i++) {
                tempBody += "<td width='15%' height='30'><strong>" + day[i] + "</strong></td>\n";
            }
            tempBody += "</tr>\n";
            for (int i = 0; i < row; i++) {
                tempBody += "<tr align='center' valign='middle'>\n";
                if (indexOfTimeList + i == strTimeList.length - 1) {
                    tempBody += "<td width='15%' height='30' bordercolor='#705C0''>" + strTimeList[indexOfTimeList + i]
                            + " - 24:00</td>\n";
                } else {
                    tempBody += "<td width='15%' height='30' bordercolor='#705C0''>" + strTimeList[indexOfTimeList + i]
                            + " - " + strTimeList[indexOfTimeList + i + 1] + "</td>\n";
                }
                for (int j = 0; j < col + 1; j++) {
                    if (intScheduleTable1[i][j] == 0) {
                        tempBody += "<td width='15%' height='30' bordercolor='#705C00'>&nbsp;</td>\n";
                    } else if (intScheduleTable1[i][j] == -1) {
                    } else {
                        tempBody += "<td width='15%' height='30' rowspan='" + intScheduleTable1[i][j];
                        tempBody += "' bordercolor='#FF0000' bgcolor='#728B93'>";
                        tempBody += strScheduleTable1[i][j];
                        tempBody += "</td>\n";
                    }
                }
                tempBody += "</tr>\n";
            }
            tempBody += "</table>\n";
        }

        tableContent = tempBody;
        return tempBody;
    }

    /**
     * make tail of html file which will show schedule
     */
    private String makeTail() {
        return "</html>";
    }

    /**
     * check whether there is mini semester true means that there is a mini
     * semester false means that there is no mini semester
     */
    private boolean checkMini() {
        for (int i = 0; i < dcData.length; i++) {
            if (dcData[i].getSemester().trim().equals("Mini 1")
                    || dcData[i].getSemester().trim().equals("Mini 2")) {
                return true;
            }
        }
        return false;
    }

    /**
     * stores information about schedule to intScheduleTable1,
     * intScheduleTable2, strScheduleTable1, strScheduleTable2
     */
    private void makeTableBody(boolean miniFlag) {

        if (miniFlag) {
            //For mini 1
            intScheduleTable1 = new int[row + 1][col + 1];
            strScheduleTable1 = new String[row + 1][col + 1];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    intScheduleTable1[i][j] = 0;
                    strScheduleTable1[i][j] = new String();
                }
            }

            for (int i = 0; i < dcData.length; i++) {
                if (dcData[i].getSemester().trim().equals("Full") || dcData[i].getSemester().trim().equals("Mini 1")) {
                    int tableCol = getDayNum(dcData[i].getDay());
                    int tableRow = getTimeDifference(startingTimeOfDay, dcData[i].getStart());
                    int classTimeDifference = getTimeDifference(dcData[i].getStart(), dcData[i].getEnd());
                    intScheduleTable1[tableRow][tableCol] = classTimeDifference;
                    for (int k = 1; k < classTimeDifference; k++) {
                        intScheduleTable1[tableRow + k][tableCol] = -1;
                    }
                    strScheduleTable1[tableRow][tableCol] = "<p><strong>" + dcData[i].getTitle().trim()
                            + "</strong></p><p><strong>" + dcData[i].getRoom().trim() + "</strong></p>";
                }
            }

            //For mini 2
            intScheduleTable2 = new int[row + 1][col + 1];
            strScheduleTable2 = new String[row + 1][col + 1];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    intScheduleTable2[i][j] = 0;
                    strScheduleTable2[i][j] = new String();
                }
            }

            for (int i = 0; i < dcData.length; i++) {
                if (dcData[i].getSemester().trim().equals("Full") || dcData[i].getSemester().trim().equals("Mini 2")) {
                    int tableCol = getDayNum(dcData[i].getDay());
                    int tableRow = getTimeDifference(startingTimeOfDay, dcData[i].getStart());
                    int classTimeDifference = getTimeDifference(dcData[i].getStart(), dcData[i].getEnd());
                    intScheduleTable2[tableRow][tableCol] = classTimeDifference;
                    for (int k = 1; k < classTimeDifference; k++) {
                        intScheduleTable2[tableRow + k][tableCol] = -1;
                    }
                    strScheduleTable2[tableRow][tableCol] = "<p><strong>" + dcData[i].getTitle().trim()
                            + "</strong></p><p><strong>" + dcData[i].getRoom().trim() + "</strong></p>";
                }
            }
        } else {
            intScheduleTable1 = new int[row + 1][col + 1];
            strScheduleTable1 = new String[row + 1][col + 1];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    intScheduleTable1[i][j] = 0;
                    strScheduleTable1[i][j] = new String();
                }
            }

            for (int i = 0; i < dcData.length; i++) {
                if (dcData[i].getSemester().trim().equals("Full")) {
                    int tableCol = getDayNum(dcData[i].getDay());
                    int tableRow = getTimeDifference(startingTimeOfDay, dcData[i].getStart());
                    int classTimeDifference = getTimeDifference(dcData[i].getStart(), dcData[i].getEnd());
                    intScheduleTable1[tableRow][tableCol] = classTimeDifference;
                    for (int k = 1; k < classTimeDifference; k++) {
                        intScheduleTable1[tableRow + k][tableCol] = -1;
                    }
                    strScheduleTable1[tableRow][tableCol] = "<p><strong>" + dcData[i].getTitle().trim()
                            + "</strong></p><p><strong>" + dcData[i].getRoom().trim() + "</strong></p>";
                }
            }
        }
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

    /**
     * return the index of day
     */
    private int getDayNum(String d) {
        for (int j = 0; j < day.length; j++) {
            if (d.trim().equals(day[j].trim())) {
                return j;
            }
        }
        return 0;
    }

    /**
     * makes list of time range
     */
    public void setTimeList() {

        for (int i = 0; i < 17; i++) {
            strTimeList[i * 2] = new String();
            if (i < 3) {
                strTimeList[i * 2] = "0" + (i + 7) + ":00";
            } else {
                strTimeList[i * 2] = (i + 7) + ":00";
            }
        }
        for (int i = 0; i < 17; i++) {
            strTimeList[i * 2 + 1] = new String();
            if (i < 3) {
                strTimeList[i * 2 + 1] = "0" + (i + 7) + ":30";
            } else {
                strTimeList[i * 2 + 1] = (i + 7) + ":30";
            }
        }
    }
    public String getClipboardContents() {
        return "";
    }
}
