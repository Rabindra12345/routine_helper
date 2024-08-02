package com.rabindra.RoutineHelper.repositories;

import com.rabindra.RoutineHelper.entities.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {

    @Query("SELECT r FROM Routine r WHERE r.teacher.teacherId = :teacherId AND r.routineDate >= :startDate AND r.routineDate <= :endDate")
    List<Routine> findRoutinesByTeacherIdAndDateRange123(@Param("teacherId") Long teacherId,
                                                      @Param("startDate") Date startDate,
                                                      @Param("endDate") Date endDate);

    @Query("SELECT r FROM Routine r WHERE r.teacher.teacherId = :teacherId AND r.routineDate >= :startDate AND r.routineDate <= :endDate")
    List<Routine> findRoutinesByTeacherIdAndDateRange(@Param("teacherId") Long teacherId,
                                                      @Param("startDate") Date startDate,
                                                      @Param("endDate") Date endDate);

    @Query("SELECT r FROM Routine r WHERE r.group.groupId=:groupId")
    Optional<List<Routine>> findByGroupId(@Param("groupId") Long groupId);
}
