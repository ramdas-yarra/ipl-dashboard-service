package edu.ram.learning.spring.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.ram.learning.spring.ipldashboard.model.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, String>  {
    public Team getByIdOrName(String teamId, String name);
    public Team getByName(String name);
}
