public class JavaUniversity {
    private final int STUDENT_LIMIT = 5;
    private final int COURSE_LIMIT = 5;
    private final double MIN_GRADUATION_GPA = 7.0d;
    private static JavaUniversity _instance;
    private Student[] students;
    private Course[] courses;
    private int courseCount = 0;
    private int studentCount = 0;

    private JavaUniversity() {
        this.students = new Student[STUDENT_LIMIT];
        this.courses = new Course[COURSE_LIMIT];
    }

    static JavaUniversity getInstance() {
        if (_instance == null) {
            _instance = new JavaUniversity();
        }
        return _instance;
    }

    public void addCourse(Course course) throws CourseLimitException {
        if (courseCount < COURSE_LIMIT) {
            this.courses[courseCount] = course;
            ++courseCount;
        } else {
            throw new CourseLimitException("University course limit reached");
        }
    }

    public void addStudent(Student student) throws StudentLimitException {
        if (studentCount < STUDENT_LIMIT) {
            this.students[studentCount] = student;
            ++studentCount;
        } else {
            throw new StudentLimitException("University student limit reached.");
        }
    }

    private int hasStudent(Student student) {
        for (int i = 0; i < students.length; ++i) {
            if (students[i].equals(student)) {
                return i;
            }
        }
        return -1;
    }

    public Student graduateStudent(Student student) throws StudentNotFoundException, IneligibleForGraduationException {
        int studentIndex = hasStudent(student);
        if (studentIndex >= 0) {
            if (student.getGpa() >= MIN_GRADUATION_GPA) {
                // remove student from students array.
                // copy all elements of students to new array except the current student.
                Student[] remainingStudents = new Student[students.length - 1];
                int j = 0;
                for (int i = 0; i < students.length; ++i) {
                    if (i != studentIndex) {
                        remainingStudents[j] = students[i];
                        ++j;
                    }
                }
                Student outStandingStudent = students[studentIndex];
                students = remainingStudents;
                studentCount = remainingStudents.length;
                return outStandingStudent;
            } else {
                throw new IneligibleForGraduationException("Student with id: "+ students[studentIndex].getId() + " is not outstanding yet." );
            }
        } else {
            throw new StudentNotFoundException("Such an exceptional student does not exist reason: "+" University only has - "+ studentCount+ " students.");
        }
    }
}
