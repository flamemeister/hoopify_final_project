import java.util.Map;
class TurnoversStrategy implements StatisticsStrategy {
    @Override
    public int calculate(Map<String, Integer> playerStats) {
        return playerStats.get("turnovers");
    }
}