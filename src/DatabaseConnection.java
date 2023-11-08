import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("ClassEscapesDefinedScope")
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection conn;

    private void createTablesIfNotExist() {
        try (Statement stmt = conn.createStatement()) {
            String createTeamsTableSQL =
                    "CREATE TABLE IF NOT EXISTS teams (" +
                        "id SERIAL PRIMARY KEY," +
                        "team_name VARCHAR(255) UNIQUE NOT NULL" +
                    ")";
            stmt.executeUpdate(createTeamsTableSQL);

            String createPlayersTableSQL =
                    "CREATE TABLE IF NOT EXISTS players (" +
                        "id SERIAL PRIMARY KEY," +
                        "name VARCHAR(255) NOT NULL," +
                        "age INT NOT NULL," +
                        "position VARCHAR(255) NOT NULL," +
                        "points INT NOT NULL," +
                    ")";
            stmt.executeUpdate(createPlayersTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private DatabaseConnection() {
        String url = "jdbc:postgresql://localhost:5433/postgres";
        String username = "postgres";
        String password = "03110311";

        try {
            conn = DriverManager.getConnection(url, username, password);
            createTablesIfNotExist();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    public List<Player> getAllPlayersFromDatabase() {
        List<Player> players = new ArrayList<>();

        try (Statement stmt = conn.createStatement()) {
            String selectPlayersSQL = "SELECT name, age, position, points FROM players";
            ResultSet rs = stmt.executeQuery(selectPlayersSQL);

            while (rs.next()) {
                String playerName = rs.getString("name");
                int playerAge = rs.getInt("age");
                String playerPosition = rs.getString("position");
                int playerPoints = rs.getInt("points");
                Player player = new Player(playerName, playerAge, playerPosition, playerPoints);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return players;
    }

    public List<Team> getAllTeamsFromDatabase() {
        List<Team> teams = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String selectTeamsSQL = "SELECT team_name FROM teams";
            ResultSet rs = stmt.executeQuery(selectTeamsSQL);

            while (rs.next()) {
                String teamName = rs.getString("team_name");
                Team team = new Team(teamName);
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    public void insertTeam(String teamName) {
        try (Statement stmt = conn.createStatement()) {
            String insertTeamSQL = "INSERT INTO teams (team_name) VALUES ('" + teamName + "')";
            stmt.executeUpdate(insertTeamSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPlayer(String playerName, int age, String position, int points, String teamName) {
        try (Statement stmt = conn.createStatement()) {
            String insertPlayerSQL = "INSERT INTO players (name, age, position, points, team_name) " +
                    "VALUES ('" + playerName + "', " + age + ", '" + position + "', " + points + ", '" + teamName + "')";
            stmt.executeUpdate(insertPlayerSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

