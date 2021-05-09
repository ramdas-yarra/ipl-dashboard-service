package edu.ram.learning.spring.ipldashboard.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import edu.ram.learning.spring.ipldashboard.model.Team;
import edu.ram.learning.spring.ipldashboard.util.ConvertionUtil;

@Component
public class IplDataloadCompletionListener extends JobExecutionListenerSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(IplDataloadCompletionListener.class);

    @Autowired
    private EntityManager entityManager;

    private StopWatch stopWatch  = new StopWatch("IPL dashboard data load job");;

    @Transactional
    @Override
    public void afterJob(JobExecution jobExecution) {
        LOGGER.info("Data load job completed in {}ms with status {}", stopWatch.getTotalTimeMillis(), jobExecution.getStatus());
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            Predicate<Object[]> invalidTeamName = (record) ->  "na".equalsIgnoreCase( (String)record[0] ); 
            Function<Object[], Team> formTeam = validRecord -> { 
                final Team team = new Team();
                team.setName((String)validRecord[0]);
                team.setMatchesPlayed( (long) validRecord[1]);
                return team;
            }; 
            final Map<String, Team> teamData = new HashMap<>();
            entityManager.createQuery("select team1, count(*) from Match group by team1", Object[].class)
            .getResultList()
            .stream()
            .filter(invalidTeamName.negate())
            .map(formTeam)
            .forEach(team1 -> teamData.put(team1.getName(), team1));

            entityManager.createQuery("select team2, count(*) from Match group by team2", Object[].class)
            .getResultList()
            .stream()
            .filter(invalidTeamName.negate())
            .map(formTeam)
            .forEach(team2 ->  {  
                if(teamData.containsKey(team2.getName())) {
                    Team existingTeamDef = teamData.get(team2.getName());
                    LOGGER.debug(" {} played {} matches as team 1 and played {} matches as team2", existingTeamDef.getName(),
                    existingTeamDef.getMatchesPlayed(), team2.getMatchesPlayed()) ;
                    existingTeamDef.setMatchesPlayed(existingTeamDef.getMatchesPlayed() + team2.getMatchesPlayed() );
                    LOGGER.debug("{} played {} matches after calculation", teamData.get(team2.getName()).getName(), teamData.get(team2.getName()).getMatchesPlayed());
                } else {
                    teamData.put(team2.getName(), team2);
                }
             } );
            
            teamData.forEach((teamName, team) -> {
                team.setMatchesWon(entityManager.createQuery("select count(*) from Match where matchWinner = :team", Long.class)
                .setParameter("team", teamName)
                .getSingleResult());
                team.setFormerTeamName(ConvertionUtil.getFormerTeamName(team.getName()));
                team.setId(ConvertionUtil.getIdForTeam(team.getName()));
                LOGGER.info("Team to be persisted definition {}", team);
                entityManager.persist(team);
            });
        }
        stopWatch.stop();
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info("IPL dataload job started");
        stopWatch.start("data-load");        
    }    
}
