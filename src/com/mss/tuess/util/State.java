/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.util;

import com.mss.tuess.entity.Course;

/**
 *
 * @author Karthik
 */
public class State {
    
    public static Course selectedCourse = new Course();
    
    public void setCurrentCourse(Course course){
        State.selectedCourse = course;
    }
    
    public Course getCurrentCourse(){
        return State.selectedCourse;
    }
    
}
