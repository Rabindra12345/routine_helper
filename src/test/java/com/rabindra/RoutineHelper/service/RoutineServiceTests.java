package com.rabindra.RoutineHelper.service;

import com.rabindra.RoutineHelper.entities.Group;
import com.rabindra.RoutineHelper.entities.Routine;
import com.rabindra.RoutineHelper.entities.Teacher;
import com.rabindra.RoutineHelper.exceptions.NotFoundException;
import com.rabindra.RoutineHelper.repositories.RoutineRepository;
import com.rabindra.RoutineHelper.repositories.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoutineServiceTests {

    @InjectMocks
    private GroupService groupService;

    @InjectMocks
    private TeacherService teacherService;

    @Mock
    private RoutineRepository routineRepository;

    @Mock
    private TeacherRepository teacherRepository;

    private List<Routine> routineList;


    @BeforeEach
    public void init(){
        Teacher teacher1 = new Teacher();
        teacher1.setTeacherId(5L);
        Group group1 = new Group();
        group1.setGroupId(5L);
        Teacher teacher2 = new Teacher();
        teacher1.setTeacherId(6L);
        Group group2 = new Group();
        group1.setGroupId(7L);
        routineList = List.of( new Routine(
                1L,
                Time.valueOf("09:00:00"),
                Time.valueOf("10:00:00"),
                new Date(2024, 7, 1),
                teacher1,
                group1
        ),
                new Routine(
                        2L,
                        Time.valueOf("10:00:00"),
                        Time.valueOf("11:00:00"),
                        new Date(2024, 7, 2),
                        teacher2,
                        group2
                ),
                new Routine(
                        3L,
                        Time.valueOf("10:00:00"),
                        Time.valueOf("11:00:00"),
                        new Date(2024, 7, 2),
                        teacher2,
                        group2
                ));
    }

    @Test
    @DisplayName("TESTING TOTAL WORKING HOURS BASED ON GROUP SUCCESS SCENERIO")
    public void testCalculateTotalWorkingHoursBasedOnGroupId_success(){
        Teacher teacher1 = new Teacher();
        teacher1.setName("avinash");
        teacher1.setTeacherId(5L);
        Teacher teacher2 = new Teacher();
        teacher1.setTeacherId(6L);
        Group group2 = new Group();
        group2.setGroupId(7L);
        List<Routine> routineList = List.of( new Routine(
                        2L,
                        Time.valueOf("09:00:00"),
                        Time.valueOf("11:00:00"),
                        new Date(2024, 7, 2),
                        teacher2,
                        group2
                ),
                new Routine(
                        3L,
                        Time.valueOf("10:00:00"),
                        Time.valueOf("12:30:00"),
                        new Date(2024, 7, 2),
                        teacher2,
                        group2
                ));
        when(routineRepository.findByGroupId(7L)).thenReturn(Optional.of(routineList));
        float totalWorkHours = groupService.calculateTotalWorkHoursUsingGroupId(7L);
        float expectedHours = 4.5f;
        System.out.println("Total working hours__: "+totalWorkHours);
        assertEquals(expectedHours,totalWorkHours);
    }

    @Test
    @DisplayName("TESTING TOTAL WORKING HOURS BASED ON GROUP FAILURE SCENERIO")
    public void testCalculateTotalWorkingHoursBasedOnGroupId_failure(){
        when(routineRepository.findByGroupId(8L)).thenReturn(Optional.of(routineList.stream().filter(routine->routine.getGroup().getGroupId()==8L).collect(Collectors.toList())));
        float totalWorkHours = groupService.calculateTotalWorkHoursUsingGroupId(7L);
        float expectedHours = 4.5f;
        System.out.println("Total working hours__: "+totalWorkHours);
        assertEquals(expectedHours,totalWorkHours);
    }

    @Test
    @DisplayName("CALCULATING TOTAL WORKHOURS BY TEACHER NAME SUCCESS ")
    public void testCalculateTotalWorkHoursBasedOnTeachers_success(){
        Teacher teacher1 = new Teacher();
        teacher1.setTeacherId(6L);
        teacher1.setName("rabindra");
        Group group2 = new Group();
        group2.setGroupId(7L);
        Group group = new Group();
        group.setGroupId(5L);
        List<Routine> routineList = List.of( new Routine(
                        2L,
                        Time.valueOf("09:00:00"),
                        Time.valueOf("11:00:00"),
                        new Date(2024, 7, 2),
                        teacher1,
                        group2
                ),
                new Routine(
                        3L,
                        Time.valueOf("10:00:00"),
                        Time.valueOf("11:30:00"),
                        new Date(2024, 7, 2),
                        teacher1,
                        group
                ));
        when(teacherRepository.findByName("rabindra")).thenReturn(teacher1);
        when(routineRepository.findRoutinesByTeacherIdAndDateRange(6L, new Date(2024, 7, 1), new Date(2024, 7, 31))).thenReturn(routineList);
        float totalWorkHours = teacherService.calculateTotalWorkHours("rabindra", new Date(2024, 7, 1), new Date(2024, 7, 31));
        float expectedWorkHours =3.5f;
        System.out.println(totalWorkHours);
        assertEquals(expectedWorkHours,totalWorkHours);
    }
}
