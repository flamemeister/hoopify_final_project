import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Hoopify {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5431/postgres";
        String username = "postgres";
        String password = "aldik2003";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {


            PlayerFactory playerFactory = new PlayerFactory();
            TeamFactory teamFactory = new TeamFactory();

            Player player1 = playerFactory.createPlayer("Lebron", 25, "Guard");
            Player player2 = playerFactory.createPlayer("Michael", 28, "Forward");


            String selectPlayersSQL = "SELECT name, age, position, points FROM players";
            ResultSet rs = stmt.executeQuery(selectPlayersSQL);

            List<Player> players = new ArrayList<>();
            while (rs.next()) {
                String playerName = rs.getString("name");
                int playerAge = rs.getInt("age");
                String playerPosition = rs.getString("position");
                int playerPoints = rs.getInt("points");
                Player player = new Player(playerName, playerAge, playerPosition, playerPoints);
                players.add(player);
            }

            String selectTeamsSQL = "SELECT name FROM teams";
            rs = stmt.executeQuery(selectTeamsSQL);

            List<Team> teams = new ArrayList<>();
            while (rs.next()) {
                String teamName = rs.getString("name");
                Team team = new Team(teamName);
                teams.add(team);
            }

            for (Team team : teams) {
                System.out.println(team.getName());
                for (Player player : players) {
                    System.out.println(player);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
