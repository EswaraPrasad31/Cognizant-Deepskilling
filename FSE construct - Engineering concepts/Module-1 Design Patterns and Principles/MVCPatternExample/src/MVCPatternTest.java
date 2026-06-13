public class MVCPatternTest {

    public static void main(String[] args) {

        Student student =
                new Student("Rahul", 101, "A");

        StudentView view = new StudentView();

        StudentController controller =
                new StudentController(student, view);

        System.out.println("Initial Student Details:");
        controller.updateView();

        controller.setStudentName("Kiran");
        controller.setStudentGrade("A+");

        System.out.println("\nUpdated Student Details:");
        controller.updateView();
    }
}