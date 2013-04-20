/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author wwh
 */
public class Validation {

    /**
     * To check if it is positive integer
     *
     * @param value
     * @return true if it is positive integer; false if not
     */
    public static boolean isPositiveInt(String value) {
        Pattern pattern = null;
        Matcher matcher = null;
        boolean result = false;
        pattern = pattern.compile("^-?[1-9]\\d*$");
        matcher = pattern.matcher(value);
        result = matcher.matches();
        return result;
    }

    /**
     * To check if it is pure letter (Can have space) string
     *
     * @param str
     * @return true if it is; false if not
     */
    public static boolean isPureLetter_WithSpace(String str) {
        str = str.trim();
        return isPureLetter_NoSpace(str);
    }

    /**
     * To check if it is pure letter string without space
     *
     * @param str
     * @return true if it is; false if not
     */
    public static boolean isPureLetter_NoSpace(String str) {
        Pattern pattern = null;
        Matcher matcher = null;
        boolean result = false;
        pattern = pattern.compile("[A-Za-z]*");
        matcher = pattern.matcher(str);
        result = matcher.matches();
        return result;
    }

    /**
     * To check if it is pure Num (Can have space) string
     *
     * @param str
     * @return true if it is; false if not
     */
    public static boolean isPureNum_WithSpace(String str) {
        str = str.trim();
        return isPureLetter_NoSpace(str);
    }

    /**
     * To check if it is pure Num string without space
     *
     * @param str
     * @return true if it is; false if not
     */
    public static boolean isPureNum_NoSpace(String str) {
        Pattern pattern = null;
        Matcher matcher = null;
        boolean result = false;
        pattern = pattern.compile("[0-9]*");
        matcher = pattern.matcher(str);
        result = matcher.matches();
        return result;
    }
    
    /**
     * To check if it is Letter +  Num (Can have space) string
     *
     * @param str
     * @return true if it is; false if not
     */
    public static boolean isLetterNum_WithSpace(String str) {
        str = str.trim();
        return isLetterNum_NoSpace(str);
    }

    /**
     * To check if it is Letter + Num string without space
     *
     * @param str
     * @return true if it is; false if not
     */
    public static boolean isLetterNum_NoSpace(String str) {
        Pattern pattern = null;
        Matcher matcher = null;
        boolean result = false;
        pattern = pattern.compile("^[A-Za-z0-9]+$");
        matcher = pattern.matcher(str);
        result = matcher.matches();
        return result;
    }

    /**
     * To check if the string's length is [>= max] and [<=min]
     *
     * @param str String to check
     * @param max Max boundary
     * @param min Min boundary
     * @return true if it is; false if not
     */
    public static boolean isStr_Length(String str, int max, int min) {
        if (str.length() < min || str.length() < max) {
            return false;
        }
        return true;
    }
  
    /**
     * To check if it is NULL
     *
     * @param str
     * @return true if it is NULL; false if not
     */
    public static boolean isNull(String str) {
        str = str.trim();
        if ((str.equals("null")) || (str.equals(""))) {
            return true;
        }
        return false;
    }

    /**
     * To check if it is a pure Num string and length is [>=
     * max] and [<=min] Space also counted e.g. "AB CD" has 5
     *
     * @param str String to check
     * @param max Max boundary
     * @param min Min boundary
     * @return true if it is; false if not
     */
    public static boolean isStr_Length_PureNum_NoSpace(String str, int max, int min) {
        if (isPureNum_NoSpace(str)) {
            if (isStr_Length(str, max, min)) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * To check if it is a pure letter string and length is [>=
     * max] and [<=min] Space also counted e.g. "12 34" has 5
     *
     * @param str String to check
     * @param max Max boundary
     * @param min Min boundary
     * @return true if it is; false if not
     */
    public static boolean isStr_Length_PureLetter_CountSpace(String str, int max, int min) {
        if (isPureLetter_NoSpace(str)) {
            if (isStr_Length(str, max, min)) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

     /**
     * To check if it is a letter and Num string and length is [>=
     * max] and [<=min] Space also counted e.g. "AB2 CD" has 6
     *
     * @param str String to check
     * @param max Max boundary
     * @param min Min boundary
     * @return true if it is; false if not
     */
    public static boolean isStr_Length_LetterNum_NoSpace(String str, int max, int min) {
        if (isLetterNum_WithSpace(str)) {
            if (isStr_Length(str, max, min)) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }
   

    /**
     * To check if it is a validate email address
     *
     * @param email
     * @return true if it is validate; false if not
     */
    public static boolean isEmailAddress(String email) {
        String check = "^([a-z0-9A-Z]+[-|//._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?//.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        boolean isMatched = matcher.matches();
        if (isMatched) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        boolean result = isPositiveInt("25");
        System.out.println(result);
    }
}
