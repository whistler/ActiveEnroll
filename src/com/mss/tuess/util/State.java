/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.util;

import com.mss.tuess.entity.Course;
import com.mss.tuess.entity.Section;

/**
 *
 * @author Karthik
 */
public class State {
    
    private static Course currentCourse = new Course();
    private static Section currentSection = new Section();
    
    public static void setCurrentCourse(Course course){
        State.currentCourse = course;
    }
    
    public static Course getCurrentCourse(){
        return State.currentCourse;
    }
    
    public static void setCurrentSection(Section section)
    {
        currentSection = section;
    }
    
    public static Section getCurrentSection()
    {
        return currentSection;
    }
    
}
