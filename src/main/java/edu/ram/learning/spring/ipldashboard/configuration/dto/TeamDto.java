package edu.ram.learning.spring.ipldashboard.configuration.dto;

import java.io.Serializable;

public class TeamDto implements Serializable {

    private String id;
    private String name;
    private String formerTeamName;
    private long matchesPlayed;
    private long matchesWon;
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the formerTeamName
     */
    public String getFormerTeamName() {
        return formerTeamName;
    }
    /**
     * @param formerTeamName the formerTeamName to set
     */
    public void setFormerTeamName(String formerTeamName) {
        this.formerTeamName = formerTeamName;
    }
    /**
     * @return the matchesPlayed
     */
    public long getMatchesPlayed() {
        return matchesPlayed;
    }
    /**
     * @param matchesPlayed the matchesPlayed to set
     */
    public void setMatchesPlayed(long matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }
    /**
     * @return the matchesWon
     */
    public long getMatchesWon() {
        return matchesWon;
    }
    /**
     * @param matchesWon the matchesWon to set
     */
    public void setMatchesWon(long matchesWon) {
        this.matchesWon = matchesWon;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
        return "TeamDto [formerTeamName=" + formerTeamName + ", id=" + id + ", matchesPlayed=" + matchesPlayed
                + ", matchesWon=" + matchesWon + ", name=" + name + "]";
    }

        
    
}
