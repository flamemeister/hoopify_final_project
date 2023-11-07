package strategyPattern;

import strategyPattern.StatisticsStrategy;

import java.util.Map;
class ReboundsStrategy implements StatisticsStrategy {
    @Override
    public int calculate(Map<String, Integer> playerStats) {
        return playerStats.get("rebounds");
    }
}