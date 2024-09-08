package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class contains unit tests for the Course class.
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeEach // should this be before each instead of before all?
  public static void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }

  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }
  /// USE DATA PROVIDED IN THE COURSE OBJECT TO TEST THE METHODS

  @Test
  public void enrollStudentTest() {
    testCourse.setEnrolledStudentCount(0);
    
    // Enroll students up to the capacity? It doesn't seem like the capacity is being checked in the method 
    for (int i = 0; i < 250; i++) {
      assertTrue(testCourse.enrollStudent());
    }
  }

  @Test
  public void reassignInstructorTest() {
    testCourse.reassignInstructor("Christine P Hendon");
    assertEquals("Christine P Hendon", testCourse.getInstructorName());
  }

  @Test
  public void reassignLocationTest() {
    testCourse.reassignLocation("309 HAV");
    assertEquals("309 HAV", testCourse.getCourseLocation());
  }

  @Test
  public void reassignTimeTest() {
    testCourse.reassignTime("10:10-11:25");
    assertEquals("10:10-11:25", testCourse.getCourseTimeSlot());
  }

  @Test
  public void isFullTest() {
    testCourse.setEnrolledStudentCount(250);
    assertTrue(testCourse.isCourseFull());
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}

// this is the incomplete test suite
