import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaUniversity {
    private final int STUDENT_LIMIT = 5;
    private final int COURSES_PER_STUDENT_LIMIT = 3;
    private static JavaUniversity _instance;
    private Map<Integer, Student> students;
    private Map<Integer, Course> courses;
    private Map<Integer, List<Integer>> courseStudentMap;

    private JavaUniversity() {
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
        this.courseStudentMap = new HashMap<>();
    }

    static JavaUniversity getInstance() {
        if(_instance == null) {
            _instance = new JavaUniversity();
        }
        return _instance;
    }

    public void addCourse(Course course) {
        this.courses.put(course.getId(), course);
        this.courseStudentMap.put(course.getId(), new ArrayList<>());
    }

    public void addStudent(Student student) throws StudentLimitException {
        if(this.students.size() < STUDENT_LIMIT) {
            this.students.put(student.getId(), student);
        } else {
            throw new StudentLimitException("University student limit reached.");
        }
    }

    public void registerStudentForCourse(Student student, Course course) throws CourseLimitException, InvalidEntityException {
        if(!students.containsKey(student.getId())) {
            throw new InvalidEntityException("Student with id: " + student.getId() + " is not registered.");
        }
        if(!courses.containsKey(course.getId())) {
            throw new InvalidEntityException("Course with id: " + course.getId() + " is not available.");
        }

        if(student.getCourseList().size() < COURSES_PER_STUDENT_LIMIT) {
            student.addCourse(course);
            this.courseStudentMap.get(course.getId()).add(student.getId());
        } else {
            throw new CourseLimitException("Student course limit reached.");
        }
    }
}
