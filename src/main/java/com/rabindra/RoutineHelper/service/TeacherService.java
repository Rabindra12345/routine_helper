package com.rabindra.RoutineHelper.service;

import com.rabindra.RoutineHelper.constant.ErrorCode;
import com.rabindra.RoutineHelper.entities.Routine;
import com.rabindra.RoutineHelper.entities.Teacher;
import com.rabindra.RoutineHelper.exceptions.InvalidDataException;
import com.rabindra.RoutineHelper.exceptions.NotFoundException;
import com.rabindra.RoutineHelper.exceptions.PayLoadIsEmptyException;
import com.rabindra.RoutineHelper.repositories.RoutineRepository;
import com.rabindra.RoutineHelper.repositories.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class TeacherService implements TeacherServiceI{

    private final TeacherRepository teacherRepository;

    private final RoutineRepository routineRepository;

    public TeacherService(TeacherRepository teacherRepository,RoutineRepository routineRepository) {
        this.teacherRepository = teacherRepository;
        this.routineRepository=routineRepository;
    }

    @Transactional
    @Override
    public Teacher createTeacher(Teacher teacher) {
        if(teacher==null){
            throw new PayLoadIsEmptyException(ErrorCode.IS_NULL.getCode(), ErrorCode.IS_NULL.name(),
                    String.format(ErrorCode.IS_NULL.getMessage(), "Request payload,Teacher"));
        }
        return teacherRepository.save(teacher);
    }

    @Transactional
    @Override
    public float calculateTotalWorkHours(String teacherName, Date startDate, Date endDate) {
        if (teacherName == null) {
            throw new NotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.name(),
                    String.format(ErrorCode.NOT_FOUND.getMessage(), "teacher name absent:", teacherName));
        }

        Teacher teacher = findTeacherByName(teacherName);

        List<Routine> routines = findRoutinesByTeacherAndDateRange(teacher, startDate, endDate);

        return calculateTotalHours(routines);
    }

    @Transactional
    private Teacher findTeacherByName(String teacherName) {
        Teacher teacher = teacherRepository.findByName(teacherName);
        if (teacher == null) {
            throw new InvalidDataException(ErrorCode.INVALID_DATA.getCode(), ErrorCode.INVALID_DATA.name(),
                    String.format(ErrorCode.INVALID_DATA.getMessage(), "teacher name INVALID:", teacherName));
        }
        return teacher;
    }

    @Transactional
    private List<Routine> findRoutinesByTeacherAndDateRange(Teacher teacher, Date startDate, Date endDate) {
        List<Routine> routines = routineRepository.findRoutinesByTeacherIdAndDateRange(teacher.getTeacherId(), startDate, endDate);
        if (routines == null || routines.isEmpty()) {
            throw new NotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.name(),
                    String.format(ErrorCode.NOT_FOUND.getMessage(), "routines not found for teacher:", teacher.getTeacherId()));
        }
        return routines;
    }

    private float calculateTotalHours(List<Routine> routines) {
        long totalHours = 0;
        for (Routine routine : routines) {
            long duration = routine.getEndTime().getTime() - routine.getStartTime().getTime();
            totalHours += duration;
        }
        return totalHours / (1000f * 60 * 60);
    }

}
