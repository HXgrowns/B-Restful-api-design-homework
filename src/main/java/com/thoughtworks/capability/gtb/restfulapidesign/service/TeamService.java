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
    private static List<Team> teams = new ArrayList<>();

    public List<Team> createTeam(int teamCount) {
        teams = new ArrayList<>();
        List<Student> students = new ArrayList<>(StudentService.STUDENTS);

        int teamSize = students.size() / teamCount;
        int studentRestCount = students.size() % teamCount;

        Collections.shuffle(students);

        int start = 0;
        for (int i = 0; i < teamCount; i++) {
            List<Student> subStudents = students.subList(start, start + teamSize);
            Team creatTeam = new Team(i + 1, subStudents);
            teams.add(creatTeam);
            start += teamSize;
        }

        for (int i = 0; i < studentRestCount; i++) {
            List<Student> teamStudents = teams.get(i).getStudents();
            List<Student> resStudent = students.subList(start, start + 1);
            teams.get(i).setStudents(new ArrayList<>());
            teams.get(i).getStudents().addAll(teamStudents);
            teams.get(i).getStudents().addAll(resStudent);
            start += 1;
        }
        return teams;
    }

    public Team updateByTeamName(int id, String teamName) {
        Team updateTeam = teams.stream()
                .filter(team -> team.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BussinessException(ExceptionEnum.NOT_FIND_TEAM));
        updateTeam.setName(teamName);
        return updateTeam;
    }

    public List<Team> findTeams() {
        return teams;
    }
}
