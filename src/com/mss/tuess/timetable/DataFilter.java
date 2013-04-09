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
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * HtmlFilter class is used to show only .html and htm
 */
public class DataFilter extends FileFilter {

    /**
     * Overload accept from FileFilter class
     */
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.data)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Overload getDescription from FileFilter class
     */
    public String getDescription() {
        return ".dat";
    }
}