/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
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
public class StudentTest {
    
    public StudentTest() {
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
     * Test of getAll method, of class Student.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        ArrayList expResult = null;
        ArrayList result = Student.getAll();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //  fail("The test case is a prototype.");
    }


    /**
     * Test of getID method, of class Student.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Student instance = new Student();
        int expResult = 12345678;
        instance.setID(expResult);
        int result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setID method, of class Student.
     */
    @Test
    public void testSetID() {
        System.out.println("setID");
        int studentID = 987655;
        Student instance = new Student();
        instance.setID(studentID);
        assertEquals(studentID, instance.getID());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class Student.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        DatabaseConnector.Connect();       
        Student instance = new Student();
        instance.update();
        assertNotNull(instance);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCreditsCompleted method, of class Student.
     */
    @Test
    public void testGetCreditsCompleted() {
        System.out.println("getCreditsCompleted");
        DatabaseConnector.Connect();
        Student instance = new Student();
        int expResult = 12;
        int result = instance.getCreditsCompleted();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getRequiredCourses method, of class Student.
     */
    @Test
    public void testGetRequiredCourses() {
        System.out.println("getRequiredCourses");
        DatabaseConnector.Connect();
        Student instance = new Student();
        ArrayList result = instance.getRequiredCourses();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}