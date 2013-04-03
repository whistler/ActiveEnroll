/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.util;

import com.mss.tuess.entity.Course;
import com.mss.tuess.entity.Section;
import com.mss.tuess.entity.Term;
import java.sql.SQLException;

/**
 *
 * @author Karthik
 */
public class State {
    
    private static Term currentTerm = new Term();
    private static Section currentSection = new Section();
    
    public static void setCurrentTerm(Term course){
        currentTerm = course;
    }
    
    public static Term getCurrentTerm(){
        return currentTerm;
    }
    
    public static void setCurrentSection(Section section) throws SQLException
    {
        currentSection = section;
        currentSection.fetchAssociations();
    }
    
    public static Section getCurrentSection()
    {
        return currentSection;
    }
    
}
