package com.rabindra.RoutineHelper.controller;

import com.rabindra.RoutineHelper.constant.ErrorCode;
import com.rabindra.RoutineHelper.constant.Urls;
import com.rabindra.RoutineHelper.dtos.ApiResponse;
import com.rabindra.RoutineHelper.entities.Teacher;
import com.rabindra.RoutineHelper.service.TeacherServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class TeacherController {

    private final TeacherServiceI teacherServiceI;

    public TeacherController(TeacherServiceI teacherServiceI) {
        this.teacherServiceI = teacherServiceI;
    }

    @PostMapping(value=Urls.TEACHERS_BASE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> createTeacher(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(ApiResponse.forSuccess(ErrorCode.OK.getCode(), teacherServiceI.createTeacher(teacher)));
    }

    @GetMapping(value= Urls.TEACHER_WORKHOURS,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTotalWorkHoursOfTeacher(@PathVariable String teacherName,
                                                        @RequestParam String startDate,
                                                        @RequestParam String endDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = dateFormat.parse(startDate);
        Date end = dateFormat.parse(endDate);
        float totalWorkHours = teacherServiceI.calculateTotalWorkHours(teacherName, start, end);
        return ResponseEntity.ok(ApiResponse.forSuccess(ErrorCode.OK.getCode(), Float.valueOf(totalWorkHours)));
    }
}
