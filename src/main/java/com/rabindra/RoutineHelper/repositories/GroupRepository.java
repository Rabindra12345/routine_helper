package com.rabindra.RoutineHelper.repositories;

import com.rabindra.RoutineHelper.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}



