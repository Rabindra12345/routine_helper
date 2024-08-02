package com.rabindra.RoutineHelper.service;

import com.rabindra.RoutineHelper.constant.ErrorCode;
import com.rabindra.RoutineHelper.entities.Group;
import com.rabindra.RoutineHelper.entities.Routine;
import com.rabindra.RoutineHelper.entities.Teacher;
import com.rabindra.RoutineHelper.exceptions.NotFoundException;
import com.rabindra.RoutineHelper.exceptions.PayLoadIsEmptyException;
import com.rabindra.RoutineHelper.repositories.GroupRepository;
import com.rabindra.RoutineHelper.repositories.RoutineRepository;
import com.rabindra.RoutineHelper.repositories.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RoutineService implements RoutineServiceI{

    private final RoutineRepository routineRepository;

    private final GroupRepository groupRepository;

    private final TeacherRepository teacherRepository;

    public RoutineService(RoutineRepository routineRepository, TeacherRepository teacherRepository, GroupRepository groupRepository){
        this.routineRepository = routineRepository;
        this.teacherRepository = teacherRepository;
        this.groupRepository = groupRepository;
    }
    @Transactional
    @Override
    public Routine createRoutine(Routine routine) {
        if(routine==null){
            throw new PayLoadIsEmptyException(ErrorCode.IS_NULL.getCode(), ErrorCode.IS_NULL.name(),
                    String.format(ErrorCode.IS_NULL.getMessage(), "Request payload,Teacher"));
        }
        Optional<Teacher> teacher = teacherRepository.findById(routine.getTeacher().getTeacherId());
        if(teacher.isEmpty()){
            throw new NotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.name(),
                    String.format(ErrorCode.NOT_FOUND.getMessage(), "Teacher with id", routine.getTeacher().getTeacherId()));
        }
        Optional<Group> group = groupRepository.findById(routine.getGroup().getGroupId());
        if(group.isEmpty()){
            throw new NotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.name(),
                    String.format(ErrorCode.NOT_FOUND.getMessage(), "Group with id", routine.getGroup().getGroupId()));
        }
        routine.setTeacher(teacher.get());
        routine.setGroup(group.get());
        return routineRepository.save(routine);
    }
}
