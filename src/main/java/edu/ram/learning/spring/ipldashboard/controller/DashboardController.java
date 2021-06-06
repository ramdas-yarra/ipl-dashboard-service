package edu.ram.learning.spring.ipldashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ram.learning.spring.ipldashboard.configuration.dto.TeamDashboardDto;
import edu.ram.learning.spring.ipldashboard.service.DashboardService;

@RestController
@RequestMapping(path = "v1/dashboard")
@CrossOrigin
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping(path = "/team/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamDashboardDto> getTeamDashboard(@PathVariable("id")String teamId ) {
        HttpStatus status = HttpStatus.OK;
        TeamDashboardDto teamDashboard = dashboardService.getTeamDashboard(teamId, 4);
        if(teamDashboard.getTeam() == null) {
            status = HttpStatus.NO_CONTENT;
        } else if(teamDashboard.getPreviouseMatches() == null || teamDashboard.getPreviouseMatches().isEmpty()) {
            status = HttpStatus.PARTIAL_CONTENT;
        }
        ResponseEntity<TeamDashboardDto> teamDashboardResponse = new ResponseEntity<>(teamDashboard, status);
        return teamDashboardResponse;
    }

    @GetMapping(path = "/teams", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getTeams() {
        HttpStatus responseStatus = HttpStatus.OK;

        List<String> teams = dashboardService.getAllTeams();
        if(CollectionUtils.isEmpty(teams)) {
            responseStatus = HttpStatus.NO_CONTENT;
        }
        ResponseEntity<List<String>> teamsResponse = new ResponseEntity<List<String>>(teams, responseStatus);
        return teamsResponse;
    }
}