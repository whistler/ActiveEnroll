/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entity;

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
public class AdministratorTest {
    
    public AdministratorTest() {
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
     * Test of getID method, of class Administrator.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Administrator instance = new Administrator();
        int expResult = 66666666;
        instance.setID(expResult);
        int result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of setID method, of class Administrator.
     */
    @Test
    public void testSetID() {
        System.out.println("setID");
        int adminID = 66666666;
        Administrator instance = new Administrator();
        instance.setID(adminID);
        assertEquals(adminID, instance.getID());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }



}