import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

class Student {
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class Main {
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        while (true) {
            logger.info("1.添加 2.删除 3.修改 4.查询 5.退出");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) add();
            else if (choice == 2) delete();
            else if (choice == 3) update();
            else if (choice == 4) find();
            else if (choice == 5) break;
            else logger.warning("错了");
        }
    }

    static void add() {
        logger.info("输id:");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        logger.info("输名字:");
        String studentName = scanner.nextLine();
        logger.info("输年龄:");
        int studentAge = scanner.nextInt();
        scanner.nextLine();
        for (int index = 0; index < studentList.size(); index++) {
            if (studentList.get(index).getId() == studentId) {
                logger.warning("id重复");
                return;
            }
        }
        studentList.add(new Student(studentId, studentName, studentAge));
        logger.info("加好了");
    }

    static void delete() {
        logger.info("输要删的id:");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;
        for (int index = 0; index < studentList.size(); index++) {
            if (studentList.get(index).getId() == studentId) {
                studentList.remove(index);
                found = true;
                break;
            }
        }
        if (found) logger.info("删了");
        else logger.warning("没找到");
    }

    static void update() {
        logger.info("输要改的id:");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        for (int index = 0; index < studentList.size(); index++) {
            if (studentList.get(index).getId() == studentId) {
                logger.info("输新名字:");
                String newName = scanner.nextLine();
                logger.info("输新年龄:");
                int newAge = scanner.nextInt();
                scanner.nextLine();
                studentList.get(index).setName(newName);
                studentList.get(index).setAge(newAge);
                logger.info("改好了");
                return;
            }
        }
        logger.warning("没找到");
    }

    static void find() {
        logger.info("1.查全部 2.按id查");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            for (int index = 0; index < studentList.size(); index++) {
                Student student = studentList.get(index);
                logger.info("id:" + student.getId() + " 名字:" + student.getName() + " 年龄:" + student.getAge());
            }
        } else if (choice == 2) {
            logger.info("输id:");
            int studentId = scanner.nextInt();
            scanner.nextLine();
            for (int index = 0; index < studentList.size(); index++) {
                if (studentList.get(index).getId() == studentId) {
                    logger.info("id:" + studentList.get(index).getId() + " 名字:" + studentList.get(index).getName() + " 年龄:" + studentList.get(index).getAge());
                    return;
                }
            }
            logger.warning("没找到");
        } else logger.warning("错了");
    }
}
