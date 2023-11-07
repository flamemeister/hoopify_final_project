package strategyPattern;

import java.util.Map;

public class StatisticsCalculator {
    private StatisticsStrategy strategy;

    public StatisticsCalculator(StatisticsStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculateStatistic(Map<String, Integer> playerStats) {
        return strategy.calculate(playerStats);
    }
}
