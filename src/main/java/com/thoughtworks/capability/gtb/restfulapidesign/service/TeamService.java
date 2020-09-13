package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.BussinessException;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.ExceptionEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TeamService {
    private static final List<Team> TEAMS = new ArrayList<>();

    public List<Team> getTeams(int teamCount) {
        List<Student> students = new ArrayList<>(StudentService.STUDENTS);

        int teamSize = students.size() / teamCount;
        int studentRestCount = students.size() % teamCount;

        Collections.shuffle(students);

        int start = 0;
        int end = 0;
        for (int i = 0; i < teamCount; i++) {
            if (studentRestCount > 0) {
                end = start + teamSize + 1;
                studentRestCount--;
            } else {
                end = start + teamSize;
            }
            List<Student> subStudents = students.subList(start, end);
            TEAMS.add(new Team(i + 1, subStudents));
            start = end;
        }
        return TEAMS;
    }

    public Team updateByTeamName(int id, String teamName) {
        Team updateTeam = TEAMS.stream()
                .filter(team -> team.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BussinessException(ExceptionEnum.NOT_FIND_TEAM));
        updateTeam.setName(teamName);
        return updateTeam;
    }
}
