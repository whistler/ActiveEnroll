/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entitylist;

import com.mss.tuess.entity.*;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Xin
 */
public class TranscriptList {

    private static ArrayList<Transcriptrecord> transcriptrecords = new ArrayList();
    private static int addCredit;
    private static int addCreditMultipleGrade;

    public static void fetch() throws SQLException {
        int currentID;
        int gradeNum = 0;
        setAddCredit(0);
        setAddCreditMultipleGrade(0);
        transcriptrecords.clear();
        currentID = CurrentUser.getUser().getID();
        ResultSet rs;
        Term term=new Term();
        String currentTermID=term.getTermID();
        String sql = "select es.courseDept,es.courseNum,c.courseName,es.termID,c.credit,es.grade enrollSection es natural join course c where es.studentID='" + currentID + "' and es.termID<>'"+currentTermID+"'";
        rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            Transcriptrecord Transcriptrecord = new Transcriptrecord();
            Transcriptrecord.setCourseName(rs.getString("courseDept"));
            Transcriptrecord.setCourseName(rs.getString("courseNum"));
            Transcriptrecord.setCourseName(rs.getString("courseName"));
            Transcriptrecord.setTermID(rs.getString("termID"));
            Transcriptrecord.setCredit(rs.getInt("credit"));
            Transcriptrecord.setGrade(rs.getString("grade"));
            
            System.out.println(Transcriptrecord.getCourseName()+" inloop"); //test!
            if (rs.getString("grade").equalsIgnoreCase("A")) {
                gradeNum = 4;
            } else if (rs.getString("grade").equalsIgnoreCase("B")) {
                gradeNum = 3;
            } else if (rs.getString("grade").equalsIgnoreCase("C")) {
                gradeNum = 2;
            } else if (rs.getString("grade").equalsIgnoreCase("D")) {
                gradeNum = 1;
            } else if (rs.getString("grade").equalsIgnoreCase("F")) {
                gradeNum = 1;
            }

            if(rs.getString("grade").equalsIgnoreCase("W"))
            {
                //withdrawn courses not taken into credits.
            }
            else
            {
                setAddCredit(getAddCredit() + rs.getInt("credit"));
            }
            
            TranscriptList.addCreditMultipleGrade+= (rs.getInt("credit") * gradeNum);
            transcriptrecords.add(Transcriptrecord);
        }

    }

    public static Transcriptrecord get(int index) {
        return transcriptrecords.get(index);
    }

    /**
     * Returns the course List
     *
     * @return ArrayList<Course> Course ArrayList
     */
    public static ArrayList<Transcriptrecord> getAll() {
        System.out.println(transcriptrecords.get(1).getCourseName());
        return transcriptrecords;
    }

    /**
     * @return the addCredit
     */
    public static int getAddCredit() {
        return addCredit;
    }

    /**
     * @param aAddCredit the addCredit to set
     */
    public static void setAddCredit(int aAddCredit) {
        addCredit = aAddCredit;
    }

    /**
     * @return the addCreditMultipleGrade
     */
    public static int getAddCreditMultipleGrade() {
        return addCreditMultipleGrade;
    }

    /**
     * @param aAddCreditMultipleGrade the addCreditMultipleGrade to set
     */
    public static void setAddCreditMultipleGrade(int aAddCreditMultipleGrade) {
        addCreditMultipleGrade = aAddCreditMultipleGrade;
    }
}
