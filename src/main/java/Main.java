import java.util.ArrayList;
import java.util.Scanner;

class Student {
    public int id;
    public String name;
    public int age;
    public Student(int i, String n, int a) { id = i; name = n; age = a; }
}

public class Main {
    static ArrayList<Student> studentList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1.添加 2.删除 3.修改 4.查询 5.退出");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) add();
            else if (choice == 2) delete();
            else if (choice == 3) update();
            else if (choice == 4) find();
            else if (choice == 5) break;
            else System.out.println("错了");
        }
    }

    static void add() {
        System.out.println("输id:");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("输名字:");
        String studentName = scanner.nextLine();
        System.out.println("输年龄:");
        int studentAge = scanner.nextInt();
        scanner.nextLine();
        for (int index = 0; index < studentList.size(); index++) {
            if (studentList.get(index).id == studentId) {
                System.out.println("id重复");
                return;
            }
        }
        studentList.add(new Student(studentId, studentName, studentAge));
        System.out.println("加好了");
    }

    static void delete() {
        System.out.println("输要删的id:");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;
        for (int index = 0; index < studentList.size(); index++) {
            if (studentList.get(index).id == studentId) {
                studentList.remove(index);
                found = true;
                break;
            }
        }
        if (found) System.out.println("删了");
        else System.out.println("没找到");
    }

    static void update() {
        System.out.println("输要改的id:");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        for (int index = 0; index < studentList.size(); index++) {
            if (studentList.get(index).id == studentId) {
                System.out.println("输新名字:");
                String newName = scanner.nextLine();
                System.out.println("输新年龄:");
                int newAge = scanner.nextInt();
                scanner.nextLine();
                studentList.get(index).name = newName;
                studentList.get(index).age = newAge;
                System.out.println("改好了");
                return;
            }
        }
        System.out.println("没找到");
    }

    static void find() {
        System.out.println("1.查全部 2.按id查");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            for (int index = 0; index < studentList.size(); index++) {
                Student student = studentList.get(index);
                System.out.println("id:" + student.id + " 名字:" + student.name + " 年龄:" + student.age);
            }
        } else if (choice == 2) {
            System.out.println("输id:");
            int studentId = scanner.nextInt();
            scanner.nextLine();
            for (int index = 0; index < studentList.size(); index++) {
                if (studentList.get(index).id == studentId) {
                    System.out.println("id:" + studentList.get(index).id + " 名字:" + studentList.get(index).name + " 年龄:" + studentList.get(index).age);
                    return;
                }
            }
            System.out.println("没找到");
        } else System.out.println("错了");
    }
}
