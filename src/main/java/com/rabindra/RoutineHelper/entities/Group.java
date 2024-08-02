package com.rabindra.RoutineHelper.entities;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`group`")
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    private String name;

    private String details;

    public Group(Long groupId, String name,String details) {
        this.groupId = groupId;
        this.name = name;
        this.details = details;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
