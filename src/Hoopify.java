import strategyPattern.StatisticsCalculator;
import strategyPattern.StatisticsStrategy;
import strategyPattern.PointsStrategy;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hoopify {
    public static void main(String[] args) {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            PlayerFactory playerFactory = new PlayerFactory();
            TeamFactory teamFactory = new TeamFactory();

//            Player player1 = playerFactory.createPlayer("Jayson Tatum", 27, "Guard");
//            Player player2 = playerFactory.createPlayer("Jalen Brown", 23, "Center");
//            dbConnection.addPlayerToDatabase(player1);

            List<Team> allTeams = dbConnection.getAllTeamsFromDatabase();

            for (Team team : allTeams) {
                System.out.println("Team Name: " + team.getName());

                // Получите список игроков для текущей команды
                List<Player> playersInTeam = dbConnection.getPlayersByTeamFromDatabase(team.getName());

                for (Player player : playersInTeam) {
                    System.out.println("Player Name: " + player.getName());
                    System.out.println("Age: " + player.getAge());
                    System.out.println("Position: " + player.getPosition());
                    System.out.println("Points: " + player.getPoints());
                    System.out.println("---------------");
                }

                System.out.println("---------------");
            }

            StatisticsStrategy strategy = new PointsStrategy();
            StatisticsCalculator calculator = new StatisticsCalculator(strategy);

            List<Map<String, Integer>> playerStatsList = new ArrayList<>();

            Map<String, Integer> playerStats1 = new HashMap<>();
            playerStats1.put("points", 50);

            Map<String, Integer> playerStats2 = new HashMap<>();
            playerStats2.put("points", 40);

            playerStatsList.add(playerStats1);
            playerStatsList.add(playerStats2);

            for (Map<String, Integer> playerStats : playerStatsList) {
                System.out.println("Player Stats: " + playerStats);
                int playerStatsResult = calculator.calculateStatistic(playerStats);
                System.out.println("Stats: " + playerStatsResult);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
