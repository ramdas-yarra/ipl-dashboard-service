package edu.ram.learning.spring.ipldashboard.configuration.dto;

import java.time.LocalDate;

public class MatchDto {

    private long id;
    private String city;
    private LocalDate date;
    private String playerOfMatch;
    private String venue;
    private String team1;
    private String team2;
    private String tossWinner;
    private String tossDecision;
    private String matchWinner;
    private String resultType;
    private String resultMargin;
    private String eliminator;
    private String resultDecisionMethod;
    private String umpire1;
    private String umpire2;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    /**
     * @return the playerOfMatch
     */
    public String getPlayerOfMatch() {
        return playerOfMatch;
    }
    /**
     * @param playerOfMatch the playerOfMatch to set
     */
    public void setPlayerOfMatch(String playerOfMatch) {
        this.playerOfMatch = playerOfMatch;
    }
    /**
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }
    /**
     * @param venue the venue to set
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }
    /**
     * @return the team1
     */
    public String getTeam1() {
        return team1;
    }
    /**
     * @param team1 the team1 to set
     */
    public void setTeam1(String team1) {
        this.team1 = team1;
    }
    /**
     * @return the team2
     */
    public String getTeam2() {
        return team2;
    }
    /**
     * @param team2 the team2 to set
     */
    public void setTeam2(String team2) {
        this.team2 = team2;
    }
    /**
     * @return the tossWinner
     */
    public String getTossWinner() {
        return tossWinner;
    }
    /**
     * @param tossWinner the tossWinner to set
     */
    public void setTossWinner(String tossWinner) {
        this.tossWinner = tossWinner;
    }
    /**
     * @return the tossDecision
     */
    public String getTossDecision() {
        return tossDecision;
    }
    /**
     * @param tossDecision the tossDecision to set
     */
    public void setTossDecision(String tossDecision) {
        this.tossDecision = tossDecision;
    }
    /**
     * @return the matchWinner
     */
    public String getMatchWinner() {
        return matchWinner;
    }
    /**
     * @param matchWinner the matchWinner to set
     */
    public void setMatchWinner(String matchWinner) {
        this.matchWinner = matchWinner;
    }
    /**
     * @return the resultType
     */
    public String getResultType() {
        return resultType;
    }
    /**
     * @param resultType the resultType to set
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
    /**
     * @return the resultMargin
     */
    public String getResultMargin() {
        return resultMargin;
    }
    /**
     * @param resultMargin the resultMargin to set
     */
    public void setResultMargin(String resultMargin) {
        this.resultMargin = resultMargin;
    }
    /**
     * @return the eliminator
     */
    public String getEliminator() {
        return eliminator;
    }
    /**
     * @param eliminator the eliminator to set
     */
    public void setEliminator(String eliminator) {
        this.eliminator = eliminator;
    }
    /**
     * @return the resultDecisionMethod
     */
    public String getResultDecisionMethod() {
        return resultDecisionMethod;
    }
    /**
     * @param resultDecisionMethod the resultDecisionMethod to set
     */
    public void setResultDecisionMethod(String resultDecisionMethod) {
        this.resultDecisionMethod = resultDecisionMethod;
    }
    /**
     * @return the umpire1
     */
    public String getUmpire1() {
        return umpire1;
    }
    /**
     * @param umpire1 the umpire1 to set
     */
    public void setUmpire1(String umpire1) {
        this.umpire1 = umpire1;
    }
    /**
     * @return the umpire2
     */
    public String getUmpire2() {
        return umpire2;
    }
    /**
     * @param umpire2 the umpire2 to set
     */
    public void setUmpire2(String umpire2) {
        this.umpire2 = umpire2;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
        return "MatchDto [city=" + city + ", date=" + date + ", eliminator=" + eliminator + ", id=" + id
                + ", matchWinner=" + matchWinner + ", playerOfMatch=" + playerOfMatch + ", resultDecisionMethod="
                + resultDecisionMethod + ", resultMargin=" + resultMargin + ", resultType=" + resultType + ", team1="
                + team1 + ", team2=" + team2 + ", tossDecision=" + tossDecision + ", tossWinner=" + tossWinner
                + ", umpire1=" + umpire1 + ", umpire2=" + umpire2 + ", venue=" + venue + "]";
    }

        
    
}
