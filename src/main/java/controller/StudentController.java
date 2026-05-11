package controller;

import model.Student;
import service.StudentService;
import java.util.Scanner;
import java.util.logging.Logger;

public class StudentController {
    private static final Logger logger = Logger.getLogger(StudentController.class.getName());
    private final StudentService studentService;
    private final Scanner scanner;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    handleAdd();
                    break;
                case 2:
                    handleDelete();
                    break;
                case 3:
                    handleUpdate();
                    break;
                case 4:
                    handleFind();
                    break;
                case 5:
                    logger.info("退出系统");
                    return;
                default:
                    logger.warning("无效的选择");
            }
        }
    }

    private void showMenu() {
        logger.info("1.添加 2.删除 3.修改 4.查询 5.退出");
    }

    private void handleAdd() {
        logger.info("输id:");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        
        logger.info("输名字:");
        String studentName = scanner.nextLine();
        
        logger.info("输年龄:");
        int studentAge = scanner.nextInt();
        scanner.nextLine();
        
        Student student = new Student(studentId, studentName, studentAge);
        if (studentService.addStudent(student)) {
            logger.info("加好了");
        } else {
            logger.warning("添加失败");
        }
    }

    private void handleDelete() {
        logger.info("输要删的id:");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        
        if (studentService.deleteStudent(studentId)) {
            logger.info("删了");
        } else {
            logger.warning("没找到");
        }
    }

    private void handleUpdate() {
        logger.info("输要改的id:");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        
        if (studentService.isIdExists(studentId)) {
            logger.info("输新名字:");
            String newName = scanner.nextLine();
            
            logger.info("输新年龄:");
            int newAge = scanner.nextInt();
            scanner.nextLine();
            
            if (studentService.updateStudent(studentId, newName, newAge)) {
                logger.info("改好了");
            }
        } else {
            logger.warning("没找到");
        }
    }

    private void handleFind() {
        logger.info("1.查全部 2.按id查");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                findAllStudents();
                break;
            case 2:
                findStudentById();
                break;
            default:
                logger.warning("无效的选择");
        }
    }

    private void findAllStudents() {
        var students = studentService.findAllStudents();
        if (students.isEmpty()) {
            logger.info("没有学生数据");
        } else {
            for (Student student : students) {
                logger.info(student.toString());
            }
        }
    }

    private void findStudentById() {
        logger.info("输id:");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        
        Student student = studentService.findStudentById(studentId);
        if (student != null) {
            logger.info(student.toString());
        } else {
            logger.warning("没找到");
        }
    }
}
