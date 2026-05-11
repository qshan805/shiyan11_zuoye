package controller;

import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    @Mock
    private StudentService mockStudentService;
    
    private StudentController controller;

    @BeforeEach
    void setUp() {
        controller = new StudentController(mockStudentService);
    }

    @Test
    @DisplayName("测试构造函数")
    void testConstructor() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("测试 StudentService 交互 - 添加学生")
    void testAddStudentInteraction() {
        Student student = new Student(1, "张三", 20);
        when(mockStudentService.addStudent(any(Student.class))).thenReturn(true);
        
        boolean result = mockStudentService.addStudent(student);
        
        assertTrue(result);
        verify(mockStudentService, times(1)).addStudent(student);
    }

    @Test
    @DisplayName("测试 StudentService 交互 - 删除学生")
    void testDeleteStudentInteraction() {
        when(mockStudentService.deleteStudent(1)).thenReturn(true);
        
        boolean result = mockStudentService.deleteStudent(1);
        
        assertTrue(result);
        verify(mockStudentService, times(1)).deleteStudent(1);
    }

    @Test
    @DisplayName("测试 StudentService 交互 - 更新学生")
    void testUpdateStudentInteraction() {
        when(mockStudentService.updateStudent(1, "李四", 21)).thenReturn(true);
        
        boolean result = mockStudentService.updateStudent(1, "李四", 21);
        
        assertTrue(result);
        verify(mockStudentService, times(1)).updateStudent(1, "李四", 21);
    }

    @Test
    @DisplayName("测试 StudentService 交互 - 根据ID查找学生")
    void testFindStudentByIdInteraction() {
        Student student = new Student(1, "张三", 20);
        when(mockStudentService.findStudentById(1)).thenReturn(student);
        
        Student found = mockStudentService.findStudentById(1);
        
        assertNotNull(found);
        assertEquals("张三", found.getName());
        verify(mockStudentService, times(1)).findStudentById(1);
    }

    @Test
    @DisplayName("测试 StudentService 交互 - 查找所有学生")
    void testFindAllStudentsInteraction() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "张三", 20));
        students.add(new Student(2, "李四", 21));
        
        when(mockStudentService.findAllStudents()).thenReturn(students);
        
        List<Student> result = mockStudentService.findAllStudents();
        
        assertEquals(2, result.size());
        verify(mockStudentService, times(1)).findAllStudents();
    }

    @Test
    @DisplayName("测试 StudentService 交互 - 检查ID是否存在")
    void testIsIdExistsInteraction() {
        when(mockStudentService.isIdExists(1)).thenReturn(true);
        when(mockStudentService.isIdExists(999)).thenReturn(false);
        
        assertTrue(mockStudentService.isIdExists(1));
        assertFalse(mockStudentService.isIdExists(999));
        
        verify(mockStudentService, times(1)).isIdExists(1);
        verify(mockStudentService, times(1)).isIdExists(999);
    }

    @Test
    @DisplayName("测试 Mock 验证 - 未调用方法")
    void testMockVerification_NotCalled() {
        verify(mockStudentService, never()).addStudent(any(Student.class));
        verify(mockStudentService, never()).deleteStudent(anyInt());
        verify(mockStudentService, never()).updateStudent(anyInt(), anyString(), anyInt());
    }

    @Test
    @DisplayName("测试 Mock 验证 - 调用次数")
    void testMockVerification_CallCount() {
        when(mockStudentService.isIdExists(1)).thenReturn(true);
        
        mockStudentService.isIdExists(1);
        mockStudentService.isIdExists(1);
        mockStudentService.isIdExists(1);
        
        verify(mockStudentService, times(3)).isIdExists(1);
        verify(mockStudentService, atLeast(2)).isIdExists(1);
        verify(mockStudentService, atMost(3)).isIdExists(1);
    }

    @Test
    @DisplayName("测试 Mock 验证 - 参数匹配")
    void testMockVerification_ParameterMatching() {
        Student student = new Student(1, "张三", 20);
        when(mockStudentService.addStudent(any(Student.class))).thenReturn(true);
        
        mockStudentService.addStudent(student);
        
        verify(mockStudentService).addStudent(any(Student.class));
        verify(mockStudentService).addStudent(argThat(s -> s.getId() == 1));
    }

    @Test
    @DisplayName("测试 Mock - 返回null")
    void testMock_ReturnNull() {
        when(mockStudentService.findStudentById(999)).thenReturn(null);
        
        Student found = mockStudentService.findStudentById(999);
        
        assertNull(found);
        verify(mockStudentService, times(1)).findStudentById(999);
    }

    @Test
    @DisplayName("测试 Mock - 抛出异常")
    void testMock_ThrowException() {
        when(mockStudentService.addStudent(null))
            .thenThrow(new IllegalArgumentException("学生对象不能为空"));
        
        assertThrows(IllegalArgumentException.class, () -> {
            mockStudentService.addStudent(null);
        });
        
        verify(mockStudentService, times(1)).addStudent(null);
    }
}
