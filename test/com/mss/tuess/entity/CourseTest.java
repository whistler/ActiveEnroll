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
public class CourseTest {
    
    public CourseTest() {
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


    /**
     * Test of get method, of class Course.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int index = 0;
        Course expResult = null;
        Course result = Course.get(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class Course.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        ArrayList expResult = null;
        
        ArrayList result = Course.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCourseDept method, of class Course.
     */
    @Test
    public void testGetCourseDept() {
        System.out.println("getCourseDept");
        Course instance = new Course();
        String expResult = "MECH";
        instance.setCourseDept(expResult);
        String result = instance.getCourseDept();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCourseDept method, of class Course.
     */
    @Test
    public void testSetCourseDept() {
        System.out.println("setCourseDept");
        String courseDept = "CHEM";
        Course instance = new Course();
        instance.setCourseDept(courseDept);
        assertEquals(courseDept, instance.getCourseDept());
        // TODO review the generated test code and remove the default call to fail.
        //Sfail("The test case is a prototype.");
    }

    /**
     * Test of getCourseNum method, of class Course.
     */
    @Test
    public void testGetCourseNum() {
        System.out.println("getCourseNum");
        DatabaseConnector.Connect();
        Course instance = new Course();
        String expResult = "000123";
        instance.setCourseNum(expResult);
        String result = instance.getCourseNum();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCourseNum method, of class Course.
     */
    @Test
    public void testSetCourseNum() {
        System.out.println("setCourseNum");
        DatabaseConnector.Connect();
        String courseNum = "10321";
        Course instance = new Course();
        instance.setCourseNum(courseNum);
        assertEquals(courseNum, instance.getCourseNum());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCourseName method, of class Course.
     */
    @Test
    public void testGetCourseName() {
        System.out.println("getCourseName");
        DatabaseConnector.Connect();
        Course instance = new Course();
        String expResult = "sdf";
        instance.setCourseName(expResult);
        String result = instance.getCourseName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCourseName method, of class Course.
     */
    @Test
    public void testSetCourseName() {
        System.out.println("setCourseName");
        DatabaseConnector.Connect();
        String courseName = "abd";
        Course instance = new Course();
        instance.setCourseName(courseName);
        assertEquals(courseName, instance.getCourseName());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }


    /**
     * Test of getCredit method, of class Course.
     */
    @Test
    public void testGetCredit() {
        System.out.println("getCredit");
        DatabaseConnector.Connect();
        int credit = 0;
        Course instance = new Course();
        instance.setCredit(credit);
        assertEquals(credit, instance.getCredit());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCredit method, of class Course.
     */
    @Test
    public void testSetCredit() {
        DatabaseConnector.Connect();
        System.out.println("setCredit");
        int credit = 0;
        Course instance = new Course();
        instance.setCredit(credit);
        assertEquals(credit, instance.getCredit());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fetch method, of class Course.
     */
    @Test
    public void testFetch_String_String() throws Exception {
        DatabaseConnector.Connect();
        System.out.println("fetch");
        String courseDepartment = "EECE";
        String courseNumber = "101";
        Course instance = new Course();
        instance.fetch(courseDepartment, courseNumber);
        assertEquals(courseDepartment, instance.getCourseDept());
        assertEquals(courseNumber, instance.getCourseNum());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class Course.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Course instance = new Course();
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class Course.
     */
    @Test
    public void testDelete() throws Exception {
        DatabaseConnector.Connect();
        System.out.println("delete");
        Course instance = new Course();
        instance.setCredit(10);
        instance.delete();
        // assertFalse(instance);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class Course.
     */
    @Test
    public void testInsert() throws Exception {
        
        System.out.println("insert");
        Course instance = new Course();
        instance.insert();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}