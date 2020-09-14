package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi/teams")
public class TeamController {
    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<List<Team>> createGroup(@RequestParam int teamCount) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.createTeam(teamCount));
    }

    @GetMapping
    public ResponseEntity<List<Team>> getTeams() {
        return ResponseEntity.ok().body(teamService.findTeams());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Team> updateByTeamName(@PathVariable int id, @RequestParam String teamName) {
        return ResponseEntity.ok().body(teamService.updateByTeamName(id, teamName));
    }
}
