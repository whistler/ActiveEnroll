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
 * Utils class manage file extension for HtmlFilter
 */
public class Utils {

    public final static String html = "html";
    public final static String htm = "htm";
    public final static String data = "dat";

    /**
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String extension = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            extension = s.substring(i + 1).toLowerCase();
        }
        return extension;
    }
}