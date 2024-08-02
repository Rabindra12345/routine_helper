package com.rabindra.RoutineHelper.repositories;

import com.rabindra.RoutineHelper.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByName(String name);

}
