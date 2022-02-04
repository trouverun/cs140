
import java.util.*;
import java.util.stream.Collectors;

public class StudentRegister {
    private Map<String, Student> students;
    private Map<String, Course> courses;
    private Map<String, List<Attainment>> attainments;

    public StudentRegister() {
        students = new HashMap<>();
        courses = new HashMap<>();
        attainments = new HashMap<>();
    }

    public List<Student> getStudents() {
        Comparator<Student> comp = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        return students.values().stream().sorted(comp).collect(Collectors.toList());
    }

    public List<Course> getCourses() {
        Comparator<Course> comp = new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        return courses.values().stream().sorted(comp).collect(Collectors.toList());
    }

    public void addStudent(Student student) {
        students.put(student.getStudentNumber(), student);
    }

    public void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    public void addAttainment(Attainment att) {
        if (!attainments.containsKey(att.getStudentNumber())) {
            attainments.put(att.getStudentNumber(), new ArrayList<>());
        }
        List<Attainment> atts = attainments.get(att.getStudentNumber());
        atts.add(att);
    }

    public void printStudentAttainments(String studentNumber, String order) {
        if (!attainments.containsKey(studentNumber)) {
            System.out.format("Unknown student number: %s\n", studentNumber);
            return;
        }

        Comparator<Attainment> comp;
        switch(order) {
            case "by name":
                comp = (o1, o2) -> {
                    Course course1 = courses.get(o1.getCourseCode());
                    Course course2 = courses.get(o2.getCourseCode());
                    return course1.getName().compareTo(course2.getName());
                };
                break;
            case "by code":
                comp = (o1, o2) -> o1.getCourseCode().compareTo(o2.getCourseCode());
                break;
            default:
                comp = (o1, o2) -> 1;
                break;
        }

        Student stud = students.get(studentNumber);
        List<Attainment> atts = attainments.get(studentNumber).stream().sorted(comp).collect(Collectors.toList());
        System.out.format("%s (%s):\n", stud.getName(), stud.getStudentNumber());
        for (Attainment att : atts) {
            Course course = courses.get(att.getCourseCode());
            System.out.format("  %s %s: %d\n", course.getCode(), course.getName(), att.getGrade());
        }
    }

    public void printStudentAttainments(String studentNumber) {
        printStudentAttainments(studentNumber, "any");
    }
}
