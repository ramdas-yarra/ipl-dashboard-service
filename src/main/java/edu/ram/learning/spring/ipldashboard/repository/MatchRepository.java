package edu.ram.learning.spring.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.ram.learning.spring.ipldashboard.model.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Integer> {
    public List<Match> getByMatchWinner(String team);
    public List<Match> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    /**
     * 
     * @param team1
     * @param startDate1
     * @param endDate1
     * @param team2
     * @param startDate2
     * @param endDate2
     * @return List<Match>
     * @deprecated
     * @see use getMatchesByTeamAndYear() for this method 
     * 
     * This method needs redundent parameters as the condition is combination of or/and logic 
     */
    public List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
        String team1, LocalDate startDate1, LocalDate endDate1,
        String team2, LocalDate startDate2, LocalDate endDate2 );
    

    @Query("select match from Match match where (match.team1 = :teamName or match.team2 = :teamName) and match.date between :startDate and :endDate order by date desc")
    public List<Match> getMatchesByTeamAndYear(@Param("teamName") String teamName,
        @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate); 
}
