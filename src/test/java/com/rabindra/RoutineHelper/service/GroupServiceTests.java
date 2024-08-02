package com.rabindra.RoutineHelper.service;


import com.rabindra.RoutineHelper.entities.Group;
import com.rabindra.RoutineHelper.entities.Routine;
import com.rabindra.RoutineHelper.exceptions.NotFoundException;
import com.rabindra.RoutineHelper.repositories.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTests {

    @InjectMocks
    private GroupService groupService;

    @Mock
    private GroupRepository groupRepository;

    private Group group;

    private List<Group> groupList ;


    @BeforeEach
    public void setUp(){
        group = new Group();
        group.setGroupId(1L);
        group.setName("Test Group");
        groupList = List.of(
                new Group(
                        2L,
                       "CS-203",
                        "Database and algorithm."
                ),
                new Group(
                        2L,
                        "CS-205",
                        "System Design and Architecture"
                ));
    }

    @Test
    @DisplayName("CREATE GROUP SUCCESS TEST CASE.")
    public void testCreateGroup_success(){

        when(groupRepository.save(Mockito.any(Group.class))).thenReturn(group);
        Group savedGroup = groupService.createGroup(group);
        assertNotNull(savedGroup);
        assertEquals(group.getGroupId(), savedGroup.getGroupId());
        assertEquals(group.getName(), savedGroup.getName());
        verify(groupRepository, times(1)).save(group);
    }

}
