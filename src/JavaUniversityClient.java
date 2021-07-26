import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class JavaUniversityClient {

    private static List<Student> createStudents() {
        Student[] students = {
                new Student(1, "Raj"), new Student(2, "Rahul"),
                new Student(3, "Kumar"),
                new Student(4, "Patel"),
                new Student(5, "Dinesh")
        };
        List<Student> studentList = Arrays.asList(students);
        try {
            studentList.stream().forEach(JavaUniversity.getInstance()::addStudent);
        } catch (StudentLimitException studentLimitException) {
            System.out.println(studentLimitException.getMessage());
        }
        return studentList;
    }

    private static List<Course> createCourses() {
        Course[] courses = {
                new Course(1, "Computer Science 101", 101),
                new Course(2, "Computer Science 201", 201),
                new Course(3, "Computer Science 301", 301),
        };

        List<Course> courseList = Arrays.asList(courses);
        courseList.stream().forEach(JavaUniversity.getInstance()::addCourse);
        return courseList;
    }

    private static void registerStudentsForCourses(List<Student> students, List<Course> courses) {
        try {
            students.stream().forEach(student -> {
                courses.stream().forEach(course -> {
                    JavaUniversity.getInstance().registerStudentForCourse(student, course);
                });
            });
        } catch (InvalidEntityException invalidEntityException) {
            System.out.println(invalidEntityException.getMessage());
        } catch (CourseLimitException courseLimitException) {
            System.out.println(courseLimitException.getMessage());
        }
    }

    public static void main(String[] args) {
        JavaUniversity javaUniversity = JavaUniversity.getInstance();
        List<Student> studentList = createStudents();
        List<Course> courseList = createCourses();
        registerStudentsForCourses(studentList, courseList);

        Student exceptionalStudent = new Student(6, "Exceptional Student");
        Course exceptionalCourse = new Course(4, "Exceptional Course", 404);

        // Test exceptions here.
        try {
            // student limit has already been reached.
            javaUniversity.addStudent(exceptionalStudent);
        } catch (StudentLimitException studentLimitException) {
            System.out.println(studentLimitException.getMessage());
//            studentLimitException.printStackTrace();
        }
        try {
            // register invalid student for valid course
            javaUniversity.registerStudentForCourse(exceptionalStudent, courseList.get(0));
        } catch (InvalidEntityException invalidEntityException) {
            System.out.println(invalidEntityException.getMessage());
//            invalidEntityException.printStackTrace();
        }

        try {
            // register valid student for invalid course
            javaUniversity.registerStudentForCourse(studentList.get(0), exceptionalCourse);
        } catch (InvalidEntityException invalidEntityException) {
            System.out.println(invalidEntityException.getMessage());
//            invalidEntityException.printStackTrace();
        }

        try {
            // register student for new course after course limit reached
            javaUniversity.addCourse(exceptionalCourse);
            javaUniversity.registerStudentForCourse(studentList.get(0), exceptionalCourse);
        } catch (CourseLimitException courseLimitException) {
            System.out.println(courseLimitException.getMessage());
//            courseLimitException.printStackTrace();
        }

    }
}
