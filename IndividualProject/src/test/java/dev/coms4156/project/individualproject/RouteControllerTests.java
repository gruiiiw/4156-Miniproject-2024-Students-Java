package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * This class contains unit tests for the RouteController class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class RouteControllerTests {

  private RouteController routeController;
  private Course coms3251;

  /** Sets up the mock database and department/course mapping for route controller testing. */
  @BeforeEach
  public void setUpRouteControllerTests() {
    routeController = new RouteController();
    MyFileDatabase myFileDatabase = new MyFileDatabase(0, "./data.txt");
    Map<String, Department> departmentMapping = new HashMap<>();
    departmentMapping = myFileDatabase.deSerializeObjectFromFile();
    myFileDatabase.setMapping(departmentMapping);

    // Map<String, Course> courseMapping = new HashMap<>();
    // testDepartment = new Department("COMS", courseMapping, "Luca Carloni", 1);
    // econTestDepartment = new Department("ECON", courseMapping, "Michael Woodford", 2345);

    coms3251 = new Course("Tony Dear", "402 CHANDLER", "1:10-3:40", 125);
    /* coms3827 = new Course("Daniel Rubenstein", "207 Math",
    "10:10-11:25", 300);
    econ4710 = new Course("Matthieu Gomez", "517 HAM", "8:40-9:55", 86);  */ 
  }

  /** Tests for a not found department. */
  @Test
  public void testRetrieveDepartmentNotFound() {
    ResponseEntity<?> response = routeController.retrieveDepartment("E");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  /** Tests for a found department. */
  @Test
  public void testRetrieveDepartmentFound() {
    ResponseEntity<?> response = routeController.retrieveDepartment("COMS");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    // assertEquals(testDepartment.toString(), response.getBody());
  }

  /** Tests for a not found course. */
  @Test
  public void testRetrieveCourseNotFound() {
    ResponseEntity<?> response = routeController.retrieveCourse("COMS", 101);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  /** Tests for a not found course department. */
  @Test
  public void testRetrieveCourseDepartmentNotFound() {
    ResponseEntity<?> response = routeController.retrieveCourse("E", 101);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  /** Tests for a found course. */
  @Test
  public void testRetrieveCourseFound() {
    ResponseEntity<?> response = routeController.retrieveCourse("COMS", 3251);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(coms3251.toString(), response.getBody());
  }

  /** Tests for is Course full, course not found. */
  @Test
  public void testIsCourseFullNotFound() {
    ResponseEntity<?> response = routeController.isCourseFull("E", 3251);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  /** Tests for is Course full, Course is not full. */
  @Test
  public void testIsCourseFull() {
    ResponseEntity<?> response = routeController.isCourseFull("COMS", 3251);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(false, response.getBody());
  }

  /** getMajorCountFromDept, when the Department is not Found. */
  @Test
  public void testGetMajorCountFromDeptNotFound() {
    ResponseEntity<?> response = routeController.getMajorCtFromDept("E");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  /** getMajorCountFromDept, when the Department is Found. */
  @Test
  public void testGetMajorCountFromDept() {
    ResponseEntity<?> response = routeController.getMajorCtFromDept("COMS");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("There are: 2700 majors in the department", response.getBody());
  }

  /** Tests for identifyDeptChair, when the Department is not Found. */
  @Test
  public void testIdentifyDeptChairDeptNotFound() {
    ResponseEntity<?> response = routeController.identifyDeptChair("E");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  /** Tests for identifyDeptChair, when the Department is Found. */
  @Test
  public void testIdentifyDeptChairDeptFound() {
    ResponseEntity<?> response = routeController.identifyDeptChair("COMS");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Luca Carloni is the department chair.", response.getBody());
  }

  /** Tests for findCourseLocation, when the course is found. */
  @Test
  public void testFindCourseLocation() {
    ResponseEntity<?> response = routeController.findCourseLocation("COMS", 3251);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("402 CHANDLER is where the course is located.", response.getBody());
  }

  /** Tests for findCourseLocation, when the course is not found. */
  @Test
  public void testFindCourseLocationCourseNotFound() {
    ResponseEntity<?> response = routeController.findCourseLocation("COMS", 1);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  /** Tests for findCourseInstructor, when the course is found. */
  @Test
  public void testFindCourseInstructor() {
    ResponseEntity<?> response = routeController.findCourseInstructor("COMS", 3251);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Tony Dear is the instructor for the course.", response.getBody());
  }

  /** Tests for findCourseInstructor, when the course is not found. */
  @Test
  public void testFindCourseInstructorCourseNotFound() {
    ResponseEntity<?> response = routeController.findCourseInstructor("COMS", 1);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  /** Tests for find Course time, when the course is found. */
  @Test
  public void testFindCourseTime() {
    ResponseEntity<?> response = routeController.findCourseTime("COMS", 3251);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("The course meets at: 1:10-3:40", response.getBody());
  }

  /** Tests for find Course time, when the course is not found. */
  @Test
  public void testFindCourseTimeCourseNotFound() {
    ResponseEntity<?> response = routeController.findCourseTime("COMS", 1);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  /** Tests for addMajorToDept, when the department is found. */
  @Test
  public void testAddMajorToDept() {
    ResponseEntity<?> response = routeController.addMajorToDept("COMS");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attribute was updated successfully", response.getBody());
  }

  /** Remove Major from department, when the department is found. */
  @Test
  public void testRemoveMajorFromDept() {
    ResponseEntity<?> response = routeController.removeMajorFromDept("COMS");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attribute was updated or is at minimum", response.getBody());
  }
  
  /** Remove Major from department, when department is not found. */
  @Test
  public void testRemoveMajorFromDeptNotFound() {
    ResponseEntity<?> response = routeController.removeMajorFromDept("E");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  /** Tests dropStudentFromCourse, when course is found and student can be dropped. */
  @Test
  public void testDropStudentFromCourse() {
    ResponseEntity<?> response = routeController.dropStudent("COMS", 3251);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Student has been dropped.", response.getBody());
  }

  /** Tests drop student from course, when the course doens't exist. */
  @Test
  public void testDropStudentFromCourseCourseNotFound() {
    ResponseEntity<?> response = routeController.dropStudent("COMS", 1);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  /** Tests setting the enrollment count for a course if the course exists. */
  @Test
  public void testSetEnrollmentCountForCourse() {
    ResponseEntity<?> response = routeController.setEnrollmentCount("COMS", 3827, 100);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attributed was updated successfully.", response.getBody());
  }

  /** Tests changing the time of the course if the course is found. */
  @Test
  public void testChangeCourseTime() {
    ResponseEntity<?> response = routeController.changeCourseTime("COMS", 3827, "1:00-2:00");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attributed was updated successfully.", response.getBody());
  }

  /**  Tests changing the instructor of a course, if the course is found. */
  @Test
  public void testChangeCourseInstructor() {
    ResponseEntity<?> response = routeController.changeCourseTeacher("ECON", 4710, "Uday Menon");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attributed was updated successfully.", response.getBody());
  }

  /** Tests changing the location of a specified course, if the course is found. */
  @Test
  public void testChangeCourseLocation() {
    ResponseEntity<?> response = routeController.changeCourseLocation("ECON", 4710, "402 CHANDLER");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attributed was updated successfully.", response.getBody());
  }
}

