package com.rabindra.RoutineHelper.controller;

import com.rabindra.RoutineHelper.constant.ErrorCode;
import com.rabindra.RoutineHelper.constant.Urls;
import com.rabindra.RoutineHelper.dtos.ApiResponse;
import com.rabindra.RoutineHelper.entities.Group;
import com.rabindra.RoutineHelper.service.GroupService;
import com.rabindra.RoutineHelper.service.GroupServiceI;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class GroupController {

    private final GroupServiceI groupServiceI;

    public GroupController(GroupService groupServiceI){
        this.groupServiceI = groupServiceI;
    }

    @PostMapping(value=Urls.GROUPS_BASE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Group createGroup(@RequestBody Group group) {
        return groupServiceI.createGroup(group);
    }

    @GetMapping(value= Urls.GROUP_WORKHOURS,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTotalWorkHoursOfEachGroup(@PathVariable(value = "groupId") Long groupId) {
        float totalWorkHours = groupServiceI.calculateTotalWorkHoursUsingGroupId(groupId);
        return ResponseEntity.ok(ApiResponse.forSuccess(ErrorCode.OK.getCode(), Float.valueOf(totalWorkHours)));
    }
}
