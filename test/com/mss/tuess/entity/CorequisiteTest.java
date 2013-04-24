/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entity;

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
public class CorequisiteTest {
    
    public CorequisiteTest() {
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
     * Test of getCoreqGroup method, of class Corequisite.
     */
    @Test
    public void testGetCoreqGroup() {
        System.out.println("getCoreqGroup");
        Corequisite instance = new Corequisite();
        int expResult = 0;
        int result = instance.getCoreqGroup();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCoreqGroup method, of class Corequisite.
     */
    @Test
    public void testSetCoreqGroup() {
        System.out.println("setCoreqGroup");
        int coreqGroup = 0;
        Corequisite instance = new Corequisite();
        instance.setCoreqGroup(coreqGroup);
        int result = instance.getCoreqGroup();
        assertEquals(coreqGroup, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}