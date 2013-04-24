/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class SectionTest {
    
    public SectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetSectionID() {
        System.out.println("getSectionID");
        Section instance = new Section();
        String expResult = "1";
        instance.setSectionID(expResult);
        String result = instance.getSectionID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setSectionID method, of class Section.
     */
    @Test
    public void testSetSectionID() {
        System.out.println("setSectionID");
        String sectionID = "101";
        Section instance = new Section();
        instance.setSectionID(sectionID);
        String results = instance.getSectionID();
        assertEquals(sectionID,results);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCourseDept method, of class Section.
     */
    @Test
    public void testGetCourseDept() {
        System.out.println("getCourseDept");
        Section instance = new Section();
        String expResult = "GEOL";
        instance.setCourseDept(expResult);
        String result = instance.getCourseDept();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCourseDept method, of class Section.
     */
    @Test
    public void testSetCourseDept() {
        System.out.println("setCourseDept");
        String courseDept = "MEDI";
        Section instance = new Section();
        instance.setCourseDept(courseDept);
        String result = instance.getCourseDept();
        assertEquals(courseDept, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCourseNum method, of class Section.
     */
    @Test
    public void testGetCourseNum() {
        System.out.println("getCourseNum");
        Section instance = new Section();
        String expResult = "301";
        instance.setCourseNum(expResult);
        String result = instance.getCourseNum();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCourseNum method, of class Section.
     */
    @Test
    public void testSetCourseNum() {
        System.out.println("setCourseNum");
        String courseNum = "333";
        Section instance = new Section();
        instance.setCourseNum(courseNum);
        String result = instance.getCourseNum();
        assertEquals(courseNum, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getInstructorID method, of class Section.
     */
    @Test
    public void testGetInstructorID() {
        System.out.println("getInstructorID");
        Section instance = new Section();
        int expResult = 12345;
        instance.setInstructorID(expResult);
        int result = instance.getInstructorID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setInstructorID method, of class Section.
     */
    @Test
    public void testSetInstructorID() {
        System.out.println("setInstructorID");
        int instructorID = 001;
        Section instance = new Section();
        instance.setInstructorID(instructorID);
        assertEquals(instructorID, instance.getInstructorID());
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getTermID method, of class Section.
     */
    @Test
    public void testGetTermID() {
        System.out.println("getTermID");
        Section instance = new Section();
        String expResult = "20131"; 
        instance.setTermID(expResult);
        String result = instance.getTermID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setTermID method, of class Section.
     */
    @Test
    public void testSetTermID() {
        System.out.println("setTermID");
        String term = "20123";
        Section instance = new Section();
        instance.setTermID(term);
        String result = instance.getTermID();
        assertEquals(term, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCapacity method, of class Section.
     */
    @Test
    public void testGetCapacity() {
        System.out.println("getCapacity");
        Section instance = new Section();
        int expResult = 100;
        instance.setCapacity(expResult);
        int result = instance.getCapacity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCapacity method, of class Section.
     */
    @Test
    public void testSetCapacity() {
        System.out.println("setCapacity");
        int capacity = 10;
        Section instance = new Section();
        instance.setCapacity(capacity);
        int result = instance.getCapacity();
        assertEquals(capacity, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getRegistered method, of class Section.
     */
    @Test
    public void testGetRegistered() {
        System.out.println("getRegistered");
        Section instance = new Section();
        int expResult = 3;
        instance.setRegistered(expResult);
        int result = instance.getRegistered();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setRegistered method, of class Section.
     */
    @Test
    public void testSetRegistered() {
        System.out.println("setRegistered");
        int registered = 10;
        Section instance = new Section();
        instance.setRegistered(registered);
        int result = instance.getRegistered();
        assertEquals(registered, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class Section.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Section instance = new Section();
        String expResult = "open";
        instance.setStatus(expResult);
        String result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class Section.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "close";
        Section instance = new Section();
        instance.setStatus(status);
        String result = instance.getStatus();
        assertEquals(status, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of fetch method, of class Section.
     */
    @Test
    public void testFetch_4args() throws Exception {
        System.out.println("fetch");
        String sectionID = "101";
        String courseDept = "CHEM";
        String courseNum = "201";
        String termID = "20112";
        Section instance = new Section();
        instance.setCourseDept(courseDept);
        instance.setSectionID(sectionID);
        instance.setCourseNum(courseNum);
        instance.setTermID(termID);
        //String result = instance.fetch(sectionID, courseDept, courseNum, termID);
        
        //assertNotNull(instance.fetch(sectionID, courseDept, courseNum, termID));
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}