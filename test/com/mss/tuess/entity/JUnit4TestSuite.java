/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Administrator
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.mss.tuess.entity.SectionTest.class, com.mss.tuess.entity.InstructorTest.class, com.mss.tuess.entity.UserTest.class, com.mss.tuess.entity.AdministratorTest.class, com.mss.tuess.entity.StudentTest.class, com.mss.tuess.entity.CourseTest.class})
public class JUnit4TestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}