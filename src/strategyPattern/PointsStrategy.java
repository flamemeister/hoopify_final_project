package strategyPattern;

import java.util.Map;

public class PointsStrategy implements StatisticsStrategy {
    @Override
    public int calculate(Map<String, Integer> playerStats) {
        // Assuming that the map contains a key "points" to represent the player's points
        if (playerStats.containsKey("points")) {
            return playerStats.get("points");
        } else {
            // Return 0 if "points" key is not found in the map
            return 0;
        }
    }
}
