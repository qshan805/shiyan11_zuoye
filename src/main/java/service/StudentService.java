package service;

import model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StudentService {
    private static final Logger logger = Logger.getLogger(StudentService.class.getName());
    private final List<Student> studentList;

    public StudentService() {
        this.studentList = new ArrayList<>();
    }

    public boolean addStudent(Student student) {
        if (student == null) {
            logger.warning("学生对象不能为空");
            return false;
        }
        for (Student s : studentList) {
            if (s.getId() == student.getId()) {
                logger.warning("id重复: " + student.getId());
                return false;
            }
        }
        studentList.add(student);
        logger.info("添加学生成功: " + student);
        return true;
    }

    public boolean deleteStudent(int studentId) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == studentId) {
                Student removed = studentList.remove(i);
                logger.info("删除学生成功: " + removed);
                return true;
            }
        }
        logger.warning("未找到学生，id: " + studentId);
        return false;
    }

    public boolean updateStudent(int studentId, String newName, int newAge) {
        for (Student student : studentList) {
            if (student.getId() == studentId) {
                student.setName(newName);
                student.setAge(newAge);
                logger.info("更新学生成功: " + student);
                return true;
            }
        }
        logger.warning("未找到学生，id: " + studentId);
        return false;
    }

    public Student findStudentById(int studentId) {
        for (Student student : studentList) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public List<Student> findAllStudents() {
        return new ArrayList<>(studentList);
    }

    public boolean isIdExists(int studentId) {
        return findStudentById(studentId) != null;
    }
}
