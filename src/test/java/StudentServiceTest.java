import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentServiceTest {

    private StudentService service;

    @BeforeEach
    void setUp() {
        service = new StudentService();
    }

    @Test
    void getTopStudentReturnsNullWhenNoStudents() {
        Student top = service.getTopStudent();
        assertNull(top, "Top student should be null when there are no students");
    }

    @Test
    void getTopStudentSingleStudentReturnsThatStudent() {
        Student s = new Student("Alice", 20, 3.5);
        service.addStudent(s);

        Student top = service.getTopStudent();
        assertNotNull(top);
        assertEquals("Alice", top.getName());
        assertEquals(3.5, top.getGpa(), 0.0001);
    }

    @Test
    void getTopStudentMultipleStudentsReturnsHighestGpa() {
        Student s1 = new Student("Alice", 20, 3.5);
        Student s2 = new Student("Bob", 22, 3.9);
        Student s3 = new Student("Charlie", 19, 3.2);

        service.addStudent(s1);
        service.addStudent(s2);
        service.addStudent(s3);

        Student top = service.getTopStudent();
        assertNotNull(top);
        assertEquals("Bob", top.getName());
        assertEquals(3.9, top.getGpa(), 0.0001);
    }

    @Test
    void calculateAverageGpaNoStudentsReturnsZero() {
        double avg = service.calculateAverageGpa();
        assertEquals(0.0, avg, 0.0001);
    }

    @Test
    void calculateAverageGpaSingleStudent() {
        service.addStudent(new Student("Alice", 20, 4.0));
        double avg = service.calculateAverageGpa();
        assertEquals(4.0, avg, 0.0001);
    }

    @Test
    void calculateAverageGpaMultipleStudents() {
        service.addStudent(new Student("Alice", 20, 3.0));
        service.addStudent(new Student("Bob", 22, 4.0));
        service.addStudent(new Student("Charlie", 19, 3.5));

        double avg = service.calculateAverageGpa();
        // (3.0 + 4.0 + 3.5) / 3 = 3.5
        assertEquals(3.5, avg, 0.0001);
    }

    @Test
    void removeStudentByNameRemovesStudentWhenPresent() {
        Student s1 = new Student("Alice", 20, 3.0);
        Student s2 = new Student("Bob", 22, 4.0);

        service.addStudent(s1);
        service.addStudent(s2);

        // Before removal, top should be Bob
        assertEquals("Bob", service.getTopStudent().getName());

        service.removeStudentByName("Bob");

        // After removal, top should be Alice and average should be her GPA
        Student top = service.getTopStudent();
        assertNotNull(top);
        assertEquals("Alice", top.getName());
        assertEquals(3.0, service.calculateAverageGpa(), 0.0001);
    }

    @Test
    void removeStudentByNameDoesNothingWhenNameNotFound() {
        Student s1 = new Student("Alice", 20, 3.0);
        Student s2 = new Student("Bob", 22, 4.0);

        service.addStudent(s1);
        service.addStudent(s2);

        double beforeAvg = service.calculateAverageGpa();

        service.removeStudentByName("Charlie");

        double afterAvg = service.calculateAverageGpa();
        assertEquals(beforeAvg, afterAvg, 0.0001);
        assertEquals("Bob", service.getTopStudent().getName());
    }

    @Test
    void removeStudentByNameRemovesAllWithSameName() {
        Student s1 = new Student("Bob", 20, 3.0);
        Student s2 = new Student("Bob", 22, 4.0);
        Student s3 = new Student("Alice", 21, 3.5);

        service.addStudent(s1);
        service.addStudent(s2);
        service.addStudent(s3);

        service.removeStudentByName("Bob");

        Student top = service.getTopStudent();
        assertNotNull(top);
        assertEquals("Alice", top.getName());
        assertEquals(3.5, service.calculateAverageGpa(), 0.0001);
    }

    @Test
    void removeStudentByNameHandlesNullNameGracefully() {
        service.addStudent(new Student("Alice", 20, 3.0));
        double beforeAvg = service.calculateAverageGpa();

        service.removeStudentByName(null);

        double afterAvg = service.calculateAverageGpa();
        assertEquals(beforeAvg, afterAvg, 0.0001);
    }
}
