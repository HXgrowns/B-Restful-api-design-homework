package com.thoughtworks.capability.gtb.restfulapidesign.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {
    private Integer id;
    private String name;
    private String note;
    private List<Student> students;

    public Team(Integer id, List<Student> students) {
        this.id = id;
        this.name = "Team " + id;
        this.students = students;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", students=" + students +
                '}';
    }
}
