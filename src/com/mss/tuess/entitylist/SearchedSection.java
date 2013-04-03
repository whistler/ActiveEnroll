/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entitylist;

import com.mss.tuess.entity.Section;

/**
 *
 * @author Admin
 */
public class SearchedSection extends Section{
    
    private String courseName;
    private int credit;
    
    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public int getCredit() {
        return this.credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }    
}
