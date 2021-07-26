import java.util.ArrayList;
import java.util.List;

public class JavaUniversity {
    private final int STUDENT_LIMIT = 10;
    private final int COURSES_PER_STUDENT_LIMIT = 3;
    private static JavaUniversity _instance;
    private List<Student> students;

    private JavaUniversity() {
        this.students = new ArrayList<>();
    }

    static JavaUniversity getInstance() {
        if(_instance == null) {
            _instance = new JavaUniversity();
        }
        return _instance;
    }

    public void addStudent(Student student) {
        if(this.students.size() < STUDENT_LIMIT) {
            this.students.add(student);
        }
    }

    public void registerStudentForCourse(Student student, Course course) {

    }
}
