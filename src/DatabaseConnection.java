import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection conn;

    private void createTablesIfNotExist() {
        try (Statement stmt = conn.createStatement()) {
            // Create teams table if not exists
            String createTeamsTableSQL = "CREATE TABLE IF NOT EXISTS teams (" +
                    "id SERIAL PRIMARY KEY," +
                    "team_name VARCHAR(255) UNIQUE NOT NULL" +
                    ")";
            stmt.executeUpdate(createTeamsTableSQL);

            // Create players table if not exists
            String createPlayersTableSQL = "CREATE TABLE IF NOT EXISTS players (" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "age INT NOT NULL," +
                    "position VARCHAR(255) NOT NULL," +
                    "points INT NOT NULL," +
                    "team_name VARCHAR(255)," +
                    "FOREIGN KEY (team_name) REFERENCES teams(team_name)" +
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

    public Connection getConnection() {
        return conn;
    }

    public List<Player> getPlayersFromDatabase() {
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
    public void addPlayerToDatabase(Player player) {
        try (Statement stmt = conn.createStatement()) {
            String insertPlayerSQL = "INSERT INTO players (name, age, position, points) " +
                    "VALUES ('" + player.getName() + "', " + player.getAge() + ", '" + player.getPosition() + "', " + player.getPoints() + ")";
            stmt.executeUpdate(insertPlayerSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public void addTeamToDatabase(Team team) {
        try {

            String insertTeamSQL = "INSERT INTO teams (team_name) VALUES (?)";

            PreparedStatement pstmt = conn.prepareStatement(insertTeamSQL);
            pstmt.setString(1, team.getName());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public List<Player> getPlayersByTeamFromDatabase(String teamName) {
        List<Player> players = new ArrayList<>();
        try {
            String query = "SELECT * FROM players WHERE team_name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, teamName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Player player = new Player(
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("position"),
                        resultSet.getInt("points")
                );
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
}

