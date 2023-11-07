package strategyPattern;

import java.util.Map;

public interface StatisticsStrategy {
    int calculate(Map<String, Integer> playerStats);
}
