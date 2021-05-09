package edu.ram.learning.spring.ipldashboard.configuration.dto;

import java.io.Serializable;
import java.util.List;

public class TeamDashboardDto implements Serializable {

    private TeamDto team;

    private List<MatchDto> previouseMatches;

    /**
     * @return the team
     */
    public TeamDto getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(TeamDto team) {
        this.team = team;
    }

    /**
     * @return the previouseMatches
     */
    public List<MatchDto> getPreviouseMatches() {
        return previouseMatches;
    }

    /**
     * @param previouseMatches the previouseMatches to set
     */
    public void setPreviouseMatches(List<MatchDto> previouseMatches) {
        this.previouseMatches = previouseMatches;
    }    
}
