package edu.ram.learning.spring.ipldashboard.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ram.learning.spring.ipldashboard.configuration.dto.MatchDto;
import edu.ram.learning.spring.ipldashboard.model.Match;
import edu.ram.learning.spring.ipldashboard.repository.MatchRepository;

@Service
public class MatchService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MatchService.class);

    @Autowired
    private MatchRepository matchRepository;

    public List<MatchDto> getMatchesForTeamByYear(String teamName, int year) {
        final List<MatchDto> matches = new ArrayList<>();
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        List<Match> matchesResultSet = matchRepository.getMatchesByTeamAndYear(teamName, startDate, endDate);
        
        matchesResultSet
            .stream()
            .filter(Objects::nonNull)
            .forEach(match ->  {
                final MatchDto matchDto = new MatchDto();
                BeanUtils.copyProperties(match, matchDto);
                matches.add(matchDto);
            });
        return Collections.unmodifiableList(matches);
    }
}