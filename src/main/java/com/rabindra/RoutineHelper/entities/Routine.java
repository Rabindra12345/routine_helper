package com.rabindra.RoutineHelper.entities;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.sql.Time;
import java.util.Date;
@Entity
@Table(name = "routine")
@NoArgsConstructor
@ToString
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routineId;

    private Time startTime;

    private Time endTime;

    private Date routineDate;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;

    public Routine(Long routineId, Time startTime, Time endTime, Date routineDate, Teacher teacher, Group group) {
        this.routineId = routineId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.routineDate = routineDate;
        this.teacher = teacher;
        this.group = group;
    }

    public Long getRoutineId() {
        return routineId;
    }

    public void setRoutineId(Long routineId) {
        this.routineId = routineId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getRoutineDate() {
        return routineDate;
    }

    public void setRoutineDate(Date routineDate) {
        this.routineDate = routineDate;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
