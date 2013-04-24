/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entity;

import com.mss.tuess.entity.Instructor;
import com.mss.tuess.util.DatabaseConnector;
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
public class InstructorTest {
    
    public InstructorTest() {
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
     * Test of getID method, of class Instructor.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Instructor instance = new Instructor();
        int expResult = 39382723;
        instance.setID(expResult);
        int result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setID method, of class Instructor.
     */
    @Test
    public void testSetID() {
        System.out.println("setID");
        int instructorID = 0;
        Instructor instance = new Instructor();
        instance.setID(instructorID);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDeptID method, of class Instructor.
     */
    @Test
    public void testGetDeptID() {
        System.out.println("getDeptID");
        Instructor instance = new Instructor();
        String expResult = "68";
        instance.setDeptID(expResult);
        String result = instance.getDeptID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setDeptID method, of class Instructor.
     */
    @Test
    public void testSetDeptID() {
        System.out.println("setDeptID");
        String deptID = "23";
        Instructor instance = new Instructor();
        instance.setDeptID(deptID);
        String result = instance.getDeptID();
        assertEquals(deptID, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}