package com.rabindra.RoutineHelper.service;


import com.rabindra.RoutineHelper.constant.ErrorCode;
import com.rabindra.RoutineHelper.entities.Group;
import com.rabindra.RoutineHelper.entities.Routine;
import com.rabindra.RoutineHelper.exceptions.NotFoundException;
import com.rabindra.RoutineHelper.exceptions.PayLoadIsEmptyException;
import com.rabindra.RoutineHelper.repositories.GroupRepository;
import com.rabindra.RoutineHelper.repositories.RoutineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService implements GroupServiceI{

    private final GroupRepository groupRepository;

    private final RoutineRepository routineRepository;

    public GroupService(GroupRepository groupRepository,RoutineRepository routineRepository) {
        this.groupRepository = groupRepository;
        this.routineRepository = routineRepository;
    }

    @Transactional
    @Override
    public Group createGroup(Group group) {
        if(group==null){
            throw new PayLoadIsEmptyException(ErrorCode.IS_NULL.getCode(), ErrorCode.IS_NULL.name(),
                    String.format(ErrorCode.IS_NULL.getMessage(), "Request payload,Teacher"));
        }
        return groupRepository.save(group);
    }

    @Transactional
    @Override
    public float calculateTotalWorkHoursUsingGroupId(Long groupId) {
        if(groupId==null){
            throw new NotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.name(),
                    String.format(ErrorCode.NOT_FOUND.getMessage(), "Group id absent:", groupId));
        }
        Optional<List<Routine>> routineFromDb = routineRepository.findByGroupId(groupId);
//        System.out.println("LOGGING routine from db _____________ :"+routineFromDb.get());
        if(routineFromDb.get().isEmpty()){
            System.out.println("LOGGING tracked ___________________________________________++++++++++++++++ :");
            throw new NotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.name(),
                    String.format(ErrorCode.NOT_FOUND.getMessage(), "Group id :", groupId));
        }
        System.out.println("LOGGING skipped from isempty _____________ :");
        List<Long> totalWorkHours =new ArrayList<>();
        for(Routine routine :routineFromDb.get()){
            totalWorkHours.add(routine.getEndTime().getTime()-routine.getStartTime().getTime());
        }
        Long totalWorkingHourSum = totalWorkHours.stream().mapToLong(Long::longValue).sum();
        float hours = totalWorkingHourSum.floatValue() / (1000 * 60 * 60);

        return hours;

    }
}



