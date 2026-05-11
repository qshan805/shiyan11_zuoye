package service;

import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    @DisplayName("测试添加学生 - 成功")
    void testAddStudent_Success() {
        Student student = new Student(1, "张三", 20);
        boolean result = studentService.addStudent(student);
        assertTrue(result);
        assertEquals(1, studentService.findAllStudents().size());
    }

    @Test
    @DisplayName("测试添加学生 - ID重复")
    void testAddStudent_DuplicateId() {
        Student student1 = new Student(1, "张三", 20);
        Student student2 = new Student(1, "李四", 21);
        
        studentService.addStudent(student1);
        boolean result = studentService.addStudent(student2);
        
        assertFalse(result);
        assertEquals(1, studentService.findAllStudents().size());
    }

    @Test
    @DisplayName("测试添加学生 - null对象")
    void testAddStudent_NullStudent() {
        boolean result = studentService.addStudent(null);
        assertFalse(result);
        assertEquals(0, studentService.findAllStudents().size());
    }

    @Test
    @DisplayName("测试删除学生 - 成功")
    void testDeleteStudent_Success() {
        Student student = new Student(1, "张三", 20);
        studentService.addStudent(student);
        
        boolean result = studentService.deleteStudent(1);
        
        assertTrue(result);
        assertEquals(0, studentService.findAllStudents().size());
    }

    @Test
    @DisplayName("测试删除学生 - 未找到")
    void testDeleteStudent_NotFound() {
        boolean result = studentService.deleteStudent(999);
        assertFalse(result);
    }

    @Test
    @DisplayName("测试更新学生 - 成功")
    void testUpdateStudent_Success() {
        Student student = new Student(1, "张三", 20);
        studentService.addStudent(student);
        
        boolean result = studentService.updateStudent(1, "李四", 21);
        
        assertTrue(result);
        Student updated = studentService.findStudentById(1);
        assertEquals("李四", updated.getName());
        assertEquals(21, updated.getAge());
    }

    @Test
    @DisplayName("测试更新学生 - 未找到")
    void testUpdateStudent_NotFound() {
        boolean result = studentService.updateStudent(999, "李四", 21);
        assertFalse(result);
    }

    @Test
    @DisplayName("测试根据ID查找学生 - 找到")
    void testFindStudentById_Found() {
        Student student = new Student(1, "张三", 20);
        studentService.addStudent(student);
        
        Student found = studentService.findStudentById(1);
        
        assertNotNull(found);
        assertEquals("张三", found.getName());
        assertEquals(20, found.getAge());
    }

    @Test
    @DisplayName("测试根据ID查找学生 - 未找到")
    void testFindStudentById_NotFound() {
        Student found = studentService.findStudentById(999);
        assertNull(found);
    }

    @Test
    @DisplayName("测试查找所有学生")
    void testFindAllStudents() {
        studentService.addStudent(new Student(1, "张三", 20));
        studentService.addStudent(new Student(2, "李四", 21));
        studentService.addStudent(new Student(3, "王五", 22));
        
        var students = studentService.findAllStudents();
        
        assertEquals(3, students.size());
    }

    @Test
    @DisplayName("测试查找所有学生 - 空列表")
    void testFindAllStudents_Empty() {
        var students = studentService.findAllStudents();
        assertTrue(students.isEmpty());
    }

    @Test
    @DisplayName("测试检查ID是否存在 - 存在")
    void testIsIdExists_True() {
        Student student = new Student(1, "张三", 20);
        studentService.addStudent(student);
        
        assertTrue(studentService.isIdExists(1));
    }

    @Test
    @DisplayName("测试检查ID是否存在 - 不存在")
    void testIsIdExists_False() {
        assertFalse(studentService.isIdExists(999));
    }

    @Test
    @DisplayName("测试完整流程 - 增删改查")
    void testCompleteWorkflow() {
        Student student1 = new Student(1, "张三", 20);
        Student student2 = new Student(2, "李四", 21);
        
        assertTrue(studentService.addStudent(student1));
        assertTrue(studentService.addStudent(student2));
        assertEquals(2, studentService.findAllStudents().size());
        
        assertTrue(studentService.updateStudent(1, "张三丰", 25));
        Student updated = studentService.findStudentById(1);
        assertEquals("张三丰", updated.getName());
        assertEquals(25, updated.getAge());
        
        assertTrue(studentService.deleteStudent(2));
        assertEquals(1, studentService.findAllStudents().size());
        
        assertNotNull(studentService.findStudentById(1));
        assertNull(studentService.findStudentById(2));
    }
}
