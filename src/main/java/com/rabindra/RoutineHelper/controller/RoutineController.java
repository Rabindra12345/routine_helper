package com.rabindra.RoutineHelper.controller;

import com.rabindra.RoutineHelper.constant.ErrorCode;
import com.rabindra.RoutineHelper.constant.Urls;
import com.rabindra.RoutineHelper.dtos.ApiResponse;
import com.rabindra.RoutineHelper.entities.Routine;
import com.rabindra.RoutineHelper.service.RoutineService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoutineController {

    private final RoutineService routineService;

    public RoutineController(RoutineService routine){
        this.routineService = routine;
    }

    @PostMapping(value= Urls.ROUTINES_BASE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> createRoutine(@RequestBody Routine routine) {
        return ResponseEntity.ok(ApiResponse.forSuccess(ErrorCode.OK.getCode(), routineService.createRoutine(routine)));
    }
}
