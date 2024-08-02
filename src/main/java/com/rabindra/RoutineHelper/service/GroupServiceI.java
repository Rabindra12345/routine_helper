package com.rabindra.RoutineHelper.service;

import com.rabindra.RoutineHelper.entities.Group;

public interface GroupServiceI {
    public Group createGroup(Group group);

    public float calculateTotalWorkHoursUsingGroupId(Long groupId);
}
