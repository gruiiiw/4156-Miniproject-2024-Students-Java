package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

  @BeforeEach 
  public void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }

  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
    assertNotEquals("Griffin Newbold", testCourse.toString());
  }

  @Test
  public void getCourseLocationTest() {
    assertEquals("417 IAB", testCourse.getCourseLocation());
  }

  @Test
  public void getInstructorNameTest() {
    assertEquals("Griffin Newbold", testCourse.getInstructorName());
  }

  @Test
  public void getCourseTimeSlotTest() {
    assertEquals("11:40-12:55", testCourse.getCourseTimeSlot());
  }

  @Test
  public void enrollStudentTest() {
    testCourse.setEnrolledStudentCount(249);
    assertTrue(testCourse.enrollStudent());
    assertFalse(testCourse.enrollStudent()); 
  }
  
  @Test
  public void dropStudentTest() {
    testCourse.setEnrolledStudentCount(1);
    assertTrue(testCourse.dropStudent()); // check you can drop a student if there is one
    assertFalse(testCourse.dropStudent()); // check you can't drop a student if there are none
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
    testCourse.setEnrolledStudentCount(245);
    assertFalse(testCourse.isCourseFull());
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}
