/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.util;

import com.mss.tuess.entity.Section;
import com.mss.tuess.entity.SectionClass;
import com.mss.tuess.entity.Term;
import java.sql.SQLException;

/**
 *
 * @author Karthik
 */
public class State {
    
    private static Term currentTerm = new Term();
    private static Section currentSection = new Section();
    private static SectionClass currentSectionClass = new SectionClass();
    
    
    /**
     * 
     * @param course 
     */
    public static void setCurrentTerm(Term course){
        currentTerm = course;
    }
    
    public static Term getCurrentTerm(){
        return currentTerm;
    }
    
    public static void setCurrentSection(Section section) throws SQLException {
            currentSection = section;
            currentSection.fetchAssociations();
        }
    
    public static Section getCurrentSection()
        {
            return currentSection;
        }
    
    public static void setCurrentSectionClass(SectionClass sectionClass) throws SQLException
        {
            currentSectionClass = sectionClass;
            /**
             * Ibrahim - you need to look into this to load the current section into the view section page
             */
            //currentSection.fetchAssociations();
        }
    
    public static SectionClass getCurrentSectionClass()
        {
            return currentSectionClass;
        }
    
}
