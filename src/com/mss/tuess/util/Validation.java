package com.mss.tuess.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides methods for validation of input fields. 
 */
public class Validation {

    public static ArrayList errors;
    
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
     * To check if it is Letter + Num (Can have space) string
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
     * To check if it is a pure Num string and length is [>= max] and [<=min]
     * Space also counted e.g. "AB CD" has 5
     *
     * @param str String to check
     * @param max Max boundary
     * @param min Min boundary
     * @return true if it is; false if not
     */
    public static boolean isStr_Length_PureNum_CountSpace(String str, int max, int min) {
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
     * To check if it is a pure letter string and length is [>= max] and [<=min]
     * Space also counted e.g. "12 34" has 5
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
     * To check if it is a letter and Num string and length is [>= max] and
     * [<=min] Space also counted e.g. "AB2 CD" has 6
     *
     * @param str String to check
     * @param max Max boundary
     * @param min Min boundary
     * @return true if it is; false if not
     */
    public static boolean isStr_Length_LetterNum_CountSpace(String str, int max, int min) {
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
    
    
    public static boolean isInteger(String input)
    {
        try{
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(escapeInput("A\'B\"C"));
    }
    
    /**
     * Validates an input field to meet meet the input criteria
     * pre: the user has provided input for fields
     * post: errors contains the list of errors for this field
     * @param name name of the field for use in error messages
     * @param input input from the user
     * @param required whether the field is required or optional
     * @param min the minimum length for a string or minimum value for a number
     * @param max the maximum length for a string or maximum value for a number
     * @param type type of the input field
     * @return escaped string
     */
    public static String validate(String name, String input, boolean required, int min, int max, InputType type)
    {
        input = escapeInput(input);
        
        // Check Types
        switch(type)
        {
            case LETTERS:
                if(!isPureLetter_WithSpace(input))
                    errors.add(input + " can only contain letters and numbers");
                break;
            case LETTERS_NOSPACE:
                if(!isPureLetter_NoSpace(input))
                    errors.add(input + " can only contain letters");
                break;
            case EMAIL:
                if (!isEmailAddress(input)) 
                    errors.add(input + " must be an email address");
                break;
            case INTEGER:
                if(!isInteger(input))
                    errors.add(input + " must be a number");
                break;
            case POSITIVE_INTEGER:
                if (!isPositiveInt(input))
                    errors.add(input + " must be a positve integer");
        }
        
        // Check Null
        if (required && input.length()==0) errors.add(name + " cannot be blank");
        
        // Check Range
        if (type == InputType.INTEGER && type == InputType.POSITIVE_INTEGER)
        {
            int num = Integer.parseInt(input);
            if (num < min) errors.add(name + " cannot be less than " + min + "");
            if (num > max) errors.add(name + " cannot be more than " + max + "");            

        } else
        {
            if (input.length()< min) errors.add(name + " cannot be shorter than " + min + " characters");
            if (input.length()>max) errors.add(name + " cannot be longer than " + max + " characters");            
        }

        return input;
    }
    
    /**
     * @param input string to escape
     * @return a string without the following characters: \ ' "
     */
    public static String escapeInput(String input)
    {
        input = input.replaceAll("\\\\", " "); // match backslash
        input = input.replaceAll("'", " ");  // match single quote
        input = input.replaceAll("\"", " "); // match double quote
        return input;
    }
}
