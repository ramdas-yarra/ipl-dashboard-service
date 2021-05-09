package edu.ram.learning.spring.ipldashboard.configuration;


import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import edu.ram.learning.spring.ipldashboard.data.item.processor.MatchInputDataProcessor;
import edu.ram.learning.spring.ipldashboard.listener.IplDataloadCompletionListener;
import edu.ram.learning.spring.ipldashboard.model.Match;
import edu.ram.learning.spring.ipldashboard.model.MatchInput;

@Configuration
@EnableBatchProcessing
public class MatchDataLoadJobConfigurations {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchDataLoadJobConfigurations.class);
    
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public FlatFileItemReader<MatchInput> reader() {
        LOGGER.info("About to configure input file reader");
        return new FlatFileItemReaderBuilder<MatchInput>()
        .name("IPL-Dashboard-data-load-reader")
        .resource(new ClassPathResource("ip_match_summary_data.csv"))
        .delimited()
        .names("id","city","date","player_of_match","venue","neutral_venue","team1","team2","toss_winner","toss_decision","winner","result","result_margin","eliminator","method","umpire1","umpire2")
        .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{
            setTargetType(MatchInput.class);
         }})
         .linesToSkip(1)
        .build();
    }

    @Bean
    public MatchInputDataProcessor processor() {
        LOGGER.info("About to configure item processor");
        return new MatchInputDataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {

        LOGGER.info("About to configure item writer");
        return new JdbcBatchItemWriterBuilder<Match>()
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .sql("insert into match(id, city, date, player_of_match, venue, team1, team2, toss_winner, toss_decision, match_winner, result_type,"+
             "result_margin, eliminator, result_decision_method, umpire1, umpire2)"+ 
             " values (:id,:city,:date,:playerOfMatch,:venue,:team1,:team2,:tossWinner,:tossDecision,:matchWinner,:resultType,:resultMargin,"+
             ":eliminator,:resultDecisionMethod,:umpire1,:umpire2)")
        .dataSource(dataSource)
        .build();
    }

    @Bean
    public Job importDataJob(IplDataloadCompletionListener listener, 
            @Qualifier("dataloadStep") Step dataloadStep) {
        LOGGER.info("About to configure data load job");
        return jobBuilderFactory.get("IPL-Dataload job")
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .flow(dataloadStep)
        .end()
        .build();
    }

    @Bean
    public Step dataloadStep(JdbcBatchItemWriter<Match> writer) {
        LOGGER.info("About to configure dataload step");
        return stepBuilderFactory.get("dataloadStep")
        .<MatchInput, Match>chunk(10)
        .reader(reader())
        .processor(processor())
        .writer(writer)
        .build();
    }
}
