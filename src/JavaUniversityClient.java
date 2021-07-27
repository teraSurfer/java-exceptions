public class JavaUniversityClient {

    private static Student[] createStudents() {
        Student[] students = {
                new Student(1, "Raj", 7.1d), new Student(2, "Rahul", 9.0d),
                new Student(3, "Kumar", 8.5d),
                new Student(4, "Patel", 6.3d),
                new Student(5, "Dinesh", 5.1d)
        };
        try {
            for (Student s : students) {
                JavaUniversity.getInstance().addStudent(s);
            }
        } catch (StudentLimitException studentLimitException) {
            System.out.println(studentLimitException.getMessage());
        }
        return students;
    }

    private static Course[] createCourses() {
        Course[] courses = {
                new Course(1, "Computer Science 101", 101),
                new Course(2, "Computer Science 201", 201),
                new Course(3, "Computer Science 301", 301),
                new Course(4, "Computer Science 401", 401),
                new Course(5, "Computer Science 501", 501),
        };

        try {
            for (Course c : courses) {
                JavaUniversity.getInstance().addCourse(c);
            }
        } catch (CourseLimitException courseLimitException) {
            System.out.println(courseLimitException.getMessage());
        }
        return courses;
    }


    public static void main(String[] args) {
        JavaUniversity javaUniversity = JavaUniversity.getInstance();
        Student[] students = createStudents();
        Course[] courses = createCourses();

        Student exceptionalStudent = new Student(6, "Exceptional Student", 10.0d);
        Course exceptionalCourse = new Course(6, "Exceptional Course", 404);

        // Test exceptions here.
        try {
            // student limit has already been reached.
            javaUniversity.addStudent(exceptionalStudent);
        } catch (StudentLimitException studentLimitException) {
            System.out.println(studentLimitException.getMessage());
        }

        try {
            // course limit has already been reached.
            javaUniversity.addCourse(exceptionalCourse);
        } catch (CourseLimitException courseLimitException) {
            System.out.println(courseLimitException.getMessage());
        }


        try {
            // graduate a student that does not exist.
            javaUniversity.graduateStudent(exceptionalStudent);
        } catch (IneligibleForGraduationException ineligibleForCourseException) {
            System.out.println(ineligibleForCourseException.getMessage());
        } catch (StudentNotFoundException studentNotFoundException) {
            System.out.println(studentNotFoundException.getMessage());
        }

        try {
            // graduate a student with insufficient requirements.
            javaUniversity.graduateStudent(students[4]);
        } catch (IneligibleForGraduationException ineligibleForCourseException) {
            System.out.println(ineligibleForCourseException.getMessage());
        } catch (StudentNotFoundException studentNotFoundException) {
            System.out.println(studentNotFoundException.getMessage());
        }
    }
}
