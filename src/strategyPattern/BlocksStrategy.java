package strategyPattern;

import strategyPattern.StatisticsStrategy;

import java.util.Map;
class BlocksStrategy implements StatisticsStrategy {
    @Override
    public int calculate(Map<String, Integer> playerStats) {
        return playerStats.get("blocks");
    }
}