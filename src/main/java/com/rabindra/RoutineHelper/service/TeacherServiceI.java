package com.rabindra.RoutineHelper.service;

import com.rabindra.RoutineHelper.entities.Teacher;

import java.util.Date;

public interface TeacherServiceI {
    public Teacher createTeacher(Teacher teacher);
    public float calculateTotalWorkHours(String teacherName, Date startDate, Date endDate);
}
