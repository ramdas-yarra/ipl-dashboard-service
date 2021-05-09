package edu.ram.learning.spring.ipldashboard.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;

public class ConvertionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertionUtil.class);

    private static final Map<String, String> TEAM_NAME_MAPER = new HashMap<>();

    static {
        TEAM_NAME_MAPER.put("Delhi Daredevils", "Delhi Capitals");
        TEAM_NAME_MAPER.put("Deccan Chargers", "Sunrisers Hyderabad");
        TEAM_NAME_MAPER.put("Rising Pune Supergiants", "Pune Warriors");
        TEAM_NAME_MAPER.put("Rising Pune Supergiant", "Pune Warriors");
    }

    public static String getLatestName(String teamName) {
        if(TEAM_NAME_MAPER.containsKey(teamName)) {
            return TEAM_NAME_MAPER.get(teamName);
        } 
        return teamName;
    }

    public static String getFormerTeamName(final String teamName) {
        Optional<Entry<String, String>> teamEntry = TEAM_NAME_MAPER.entrySet().stream()
                .filter(entry -> entry.getValue().equalsIgnoreCase(teamName)).findFirst();
        if (teamEntry.isPresent()) {
            return teamEntry.get().getKey();
        }
        return "NA";
    }

    public static String getIdForTeam(final String name) {
        final StringBuffer sb = new StringBuffer();
        if(name.trim().indexOf(" ") >= 0) {
            Arrays.stream(name.split(" "))
            .filter(Objects::nonNull)
            .map(w -> w.trim())
            .forEach(word -> sb.append(word.charAt(0)));
        } else {
            sb.append(name.trim().substring(0, 2));
        }
        LOGGER.info("Short name/id for {} is {} ", name, sb.toString());
        return sb.toString();
    }
}
