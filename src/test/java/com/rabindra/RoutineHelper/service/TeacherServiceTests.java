package com.rabindra.RoutineHelper.service;

import com.rabindra.RoutineHelper.entities.Teacher;
import com.rabindra.RoutineHelper.exceptions.NotFoundException;
import com.rabindra.RoutineHelper.exceptions.PayLoadIsEmptyException;
import com.rabindra.RoutineHelper.repositories.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTests {


    @InjectMocks
    private TeacherService teacherService;

    @Mock
    private TeacherRepository teacherRepository;

    private List<Teacher> teachers;

    @BeforeEach
    public void init(){
        teachers = List.of(
                new Teacher(1L, "John Doe", "john.doe@example.com", "Computer Science",new Date(2024, 2, 1)
                        ),
                new Teacher(2L, "Jane Smith", "jane.smith@example.com", "Mathematics",new Date(2024, 2, 1))
        );
    }

    @Test
    @DisplayName("CREATE TEACHER SUCCESS TEST CASE")
    public void createTeacher_succcess(){
        Teacher teacher = new Teacher(3L, "Adam Johnson", "adam.johnson@example.com", "Physics", new Date(2024, 2, 1));
        when(teacherRepository.save(Mockito.any(Teacher.class))).thenReturn(teacher);
        Teacher teahcerFromService=teacherService.createTeacher(teacher);
        assertEquals(teacher.getTeacherId(), teahcerFromService.getTeacherId());
        assertEquals(teacher.getName(), teahcerFromService.getName());
        assertEquals(teacher.getEmail(), teahcerFromService.getEmail());
        assertEquals(teacher.getDepartment(), teahcerFromService.getDepartment());
        assertEquals(teacher.getHireDate(), teahcerFromService.getHireDate());
        verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    @DisplayName("CREATE TEACHER FAILURE TEST CASE")
    public void createTeacher_failure(){
        Teacher teacher = null;
        assertThrows(PayLoadIsEmptyException.class, () -> teacherService.createTeacher(teacher));
    }
}
