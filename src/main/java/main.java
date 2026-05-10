import java.util.ArrayList;
import java.util.Scanner;

class Student {
    public int id;
    public String name;
    public int age;
    public Student(int i, String n, int a) { id = i; name = n; age = a; }
}

public class main {
    static ArrayList<Student> s = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1.添加 2.删除 3.修改 4.查询 5.退出");
            int c = sc.nextInt();
            sc.nextLine();
            if (c == 1) add();
            else if (c == 2) del();
            else if (c == 3) update();
            else if (c == 4) find();
            else if (c == 5) break;
            else System.out.println("错了");
        }
    }

    static void add() {
        System.out.println("输id:");
        int i = sc.nextInt();
        sc.nextLine();
        System.out.println("输名字:");
        String n = sc.nextLine();
        System.out.println("输年龄:");
        int a = sc.nextInt();
        sc.nextLine();
        for (int j=0; j<s.size(); j++) {
            if (s.get(j).id == i) {
                System.out.println("id重复");
                return;
            }
        }
        s.add(new Student(i,n,a));
        System.out.println("加好了");
    }

    static void del() {
        System.out.println("输要删的id:");
        int i = sc.nextInt();
        sc.nextLine();
        boolean f = false;
        for (int j=0; j<s.size(); j++) {
            if (s.get(j).id == i) {
                s.remove(j);
                f = true;
                break;
            }
        }
        if (f) System.out.println("删了");
        else System.out.println("没找到");
    }

    static void update() {
        System.out.println("输要改的id:");
        int i = sc.nextInt();
        sc.nextLine();
        for (int j=0; j<s.size(); j++) {
            if (s.get(j).id == i) {
                System.out.println("输新名字:");
                String n = sc.nextLine();
                System.out.println("输新年龄:");
                int a = sc.nextInt();
                sc.nextLine();
                s.get(j).name = n;
                s.get(j).age = a;
                System.out.println("改好了");
                return;
            }
        }
        System.out.println("没找到");
    }

    static void find() {
        System.out.println("1.查全部 2.按id查");
        int c = sc.nextInt();
        sc.nextLine();
        if (c == 1) {
            for (int j=0; j<s.size(); j++) {
                Student st = s.get(j);
                System.out.println("id:" + st.id + " 名字:" + st.name + " 年龄:" + st.age);
            }
        } else if (c == 2) {
            System.out.println("输id:");
            int i = sc.nextInt();
            sc.nextLine();
            for (int j=0; j<s.size(); j++) {
                if (s.get(j).id == i) {
                    System.out.println("id:" + s.get(j).id + " 名字:" + s.get(j).name + " 年龄:" + s.get(j).age);
                    return;
                }
            }
            System.out.println("没找到");
        } else System.out.println("错了");
    }
}
