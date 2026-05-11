import controller.StudentController;
import service.StudentService;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        StudentController controller = new StudentController(studentService);
        controller.start();
    }
}
