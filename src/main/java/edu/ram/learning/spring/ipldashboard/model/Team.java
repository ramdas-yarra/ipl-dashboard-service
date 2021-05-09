package edu.ram.learning.spring.ipldashboard.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team implements Serializable {

    @Id
    private String id;
    private String name;
    private String formerTeamName="NA";
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
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
        return "Team [formerTeamName=" + formerTeamName + ", id=" + id + ", matchesPlayed=" + matchesPlayed
                + ", matchesWon=" + matchesWon + ", name=" + name + "]";
    }
    
}