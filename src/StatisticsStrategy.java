import java.util.Map;

interface StatisticsStrategy {
    int calculate(Map<String, Integer> playerStats);
}