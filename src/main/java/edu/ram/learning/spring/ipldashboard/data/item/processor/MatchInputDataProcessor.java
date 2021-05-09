package edu.ram.learning.spring.ipldashboard.data.item.processor;


import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import edu.ram.learning.spring.ipldashboard.model.Match;
import edu.ram.learning.spring.ipldashboard.model.MatchInput;
import edu.ram.learning.spring.ipldashboard.util.ConvertionUtil;

public class MatchInputDataProcessor implements ItemProcessor<MatchInput, Match>{

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchInputDataProcessor.class);

    AtomicLong processedRecords = new AtomicLong(0);

    Predicate<String> isBatFirst = (decission) -> "bat".equalsIgnoreCase(decission);

    @Override
    public Match process(MatchInput inputRecord) throws Exception {
        
        Match match = new Match();

        match.setId(Long.parseLong(inputRecord.getId()));
        match.setCity(inputRecord.getCity());
        match.setPlayerOfMatch(inputRecord.getPlayer_of_match());
        match.setDate(LocalDate.parse(inputRecord.getDate()));
        match.setVenue(inputRecord.getVenue());
        if(isBatFirst.test(inputRecord.getToss_decision())) {
            match.setTeam1(inputRecord.getToss_winner());
            match.setTeam2(match.getTeam1().equals(inputRecord.getTeam1())? inputRecord.getTeam2() : inputRecord.getTeam1() );
        } else {
            match.setTeam1( inputRecord.getToss_winner().equals(inputRecord.getTeam1()) ? inputRecord.getTeam2() : inputRecord.getTeam1() );
            match.setTeam2(inputRecord.getToss_winner());
        }
        match.setTeam1(ConvertionUtil.getLatestName(match.getTeam1()));
        match.setTeam2(ConvertionUtil.getLatestName(match.getTeam2()));

        match.setTossWinner(inputRecord.getToss_winner());
        match.setTossDecision(inputRecord.getToss_decision());
        match.setMatchWinner(ConvertionUtil.getLatestName(inputRecord.getWinner()));
        match.setResultType(inputRecord.getResult());
        match.setResultMargin(inputRecord.getResult_margin());
        match.setEliminator(inputRecord.getEliminator());
        match.setResultDecisionMethod(inputRecord.getMethod());
        match.setUmpire1(inputRecord.getUmpire1());
        match.setUmpire2(inputRecord.getUmpire2());
        LOGGER.debug("Completed processing of record #{} , record {}", processedRecords.incrementAndGet(), match);
        return match;
    }
}
