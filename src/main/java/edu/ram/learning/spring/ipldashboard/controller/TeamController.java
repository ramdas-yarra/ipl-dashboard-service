package edu.ram.learning.spring.ipldashboard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ram.learning.spring.ipldashboard.configuration.dto.MatchDto;
import edu.ram.learning.spring.ipldashboard.service.MatchService;

@RestController
@RequestMapping(path = "v1/team")
@CrossOrigin
public class TeamController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private MatchService matchService;

    @GetMapping(path = "/{teamName}/matches", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<MatchDto>> getMatches(@PathVariable("teamName") String teamName, @RequestParam("year") int year  ) {
        HttpStatus responseStatus = HttpStatus.OK;
        if(! StringUtils.hasText(teamName)) {
            MultiValueMap<String, String> responseHeaders =  new LinkedMultiValueMap<>();
            responseHeaders.set("ERROR-MESSAGE", "Invalid Team name");
            new ResponseEntity<>(null, responseHeaders, HttpStatus.BAD_REQUEST);
        }
        List<MatchDto> matches = matchService.getMatchesForTeamByYear(teamName, year);
        if(CollectionUtils.isEmpty(matches)) {
            responseStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(matches, responseStatus);
    }
    
}
