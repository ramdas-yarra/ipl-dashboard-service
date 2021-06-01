package edu.ram.learning.spring.ipldashboard.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.ram.learning.spring.ipldashboard.configuration.dto.MatchDto;
import edu.ram.learning.spring.ipldashboard.configuration.dto.TeamDashboardDto;
import edu.ram.learning.spring.ipldashboard.configuration.dto.TeamDto;
import edu.ram.learning.spring.ipldashboard.model.Match;
import edu.ram.learning.spring.ipldashboard.model.Team;
import edu.ram.learning.spring.ipldashboard.repository.MatchRepository;
import edu.ram.learning.spring.ipldashboard.repository.TeamRepository;

@Service
public class DashboardService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;
    
    public TeamDashboardDto getTeamDashboard(final String teamId, final int numberOfMatches) {
        Team team = teamRepository.getByIdOrName(teamId.toUpperCase(), teamId);
        
        TeamDashboardDto teamDashboardDto = new TeamDashboardDto();
        if(team !=null) {
            TeamDto teamDto = new TeamDto();
            BeanUtils.copyProperties(team, teamDto);
            teamDashboardDto.setTeam(teamDto);
            List<MatchDto> matchesDto = new ArrayList<>(); 
            Pageable pageable = PageRequest.of(0, numberOfMatches);
            
            List<Match> matches =  matchRepository.getByTeam1OrTeam2OrderByDateDesc(teamDto.getName(), teamDto.getName(), pageable);
            
            if(matches != null && !matches.isEmpty()) {
                matches.stream()
                .filter(Objects::nonNull)
                .forEach(match -> {
                    MatchDto matchDto = new MatchDto();
                    BeanUtils.copyProperties(match, matchDto);
                    matchesDto.add(matchDto);
                });
                teamDashboardDto.setPreviouseMatches(Collections.unmodifiableList(matchesDto));
            } 
        }
        return teamDashboardDto;
    } 
}