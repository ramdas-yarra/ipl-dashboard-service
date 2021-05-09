package edu.ram.learning.spring.ipldashboard.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.ram.learning.spring.ipldashboard.model.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Integer> {
    public List<Match> getByMatchWinner(String team);
    public List<Match> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);
}
