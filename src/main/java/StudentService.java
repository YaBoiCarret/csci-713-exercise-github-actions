import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> students;

    public StudentService() {
        students = new ArrayList<>();
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    // Reverted bug
    public Student getTopStudent() {
        if (students.isEmpty()) {
            return null;
        }

        Student top = students.get(0);
        for (Student s : students) {
            if (s.getGpa() > top.getGpa()) {
                top = s;
            }
        }
        return top;
    }

    public double calculateAverageGpa() {
        double total = 0.0;
        for (Student s : students) {
            total += s.getGpa();
        }
        if (!students.isEmpty()) {
            return total / students.size();
        } else {
            return 0.0;
        }
    }

    // Reverted bug
    public void removeStudentByName(String name) {
        if (name == null) {
            return;
        }
        students.removeIf(s -> name.equals(s.getName()));
    }
}
