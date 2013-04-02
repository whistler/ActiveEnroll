/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entity;

/**
 *
 * @author Karthik
 */
public class SelectedCourse {
    
    public static Course selectedCourse = new Course();
    
    public void setSelectedCourse(Course course){
        SelectedCourse.selectedCourse = course;
    }
    
    public Course getSelectedCourse(){
        return SelectedCourse.selectedCourse;
    }
    
}
