package strategyPattern;

import java.util.Map;

public class PointsStrategy implements StatisticsStrategy {
    @Override
    public int calculate(Map<String, Integer> playerStats) {
        return playerStats.get("points");
    }
}
