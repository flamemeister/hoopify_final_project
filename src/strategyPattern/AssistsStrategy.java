package strategyPattern;

import java.util.Map;
class AssistsStrategy implements StatisticsStrategy {
    @Override
    public int calculate(Map<String, Integer> playerStats) {
        return playerStats.get("assists");
    }
}