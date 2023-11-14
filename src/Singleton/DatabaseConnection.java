package Singleton;

import Factory.PlayerFactory;
import Factory.TeamFactory;
import Models.Player;
import Models.Team;
import Observer.HoopifyObserver;
import Observer.HoopifySubject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection implements HoopifySubject {
    private final List<HoopifyObserver> observers = new ArrayList<>();
    @Override
    public void addObserver(HoopifyObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (HoopifyObserver observer : observers) {
            observer.update();
        }
    }
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
                            "assists INT NOT NULL," +
                            "rebounds INT NOT NULL," +
                            "steals INT NOT NULL," +
                            "blocks INT NOT NULL," +
                            "team_name VARCHAR(255) NOT NULL" +
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
            String selectPlayersSQL = "SELECT * FROM players";
            ResultSet rs = stmt.executeQuery(selectPlayersSQL);

            while (rs.next()) {
                String playerName = rs.getString("name");
                int playerAge = rs.getInt("age");
                String playerPosition = rs.getString("position");
                int playerPoints = rs.getInt("points");
                int playerAssists = rs.getInt("assists");
                int playerRebounds = rs.getInt("rebounds");
                int playerSteals = rs.getInt("steals");
                int playerBlocks = rs.getInt("blocks");
                Player player = new Player(playerName, playerAge, playerPosition, playerPoints, playerAssists, playerRebounds, playerSteals, playerBlocks);
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
            TeamFactory teamFactory = new TeamFactory();
            Team team  = teamFactory.createTeam( teamName );
            String insertTeamSQL = "INSERT INTO teams (team_name) VALUES ('" + team.name() + "')";
            stmt.executeUpdate(insertTeamSQL);

            notifyObservers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPlayer(String playerName, int age, String position, int points, int assists, int rebounds, int steals, int blocks, String teamName) {
        try (Statement stmt = conn.createStatement()) {
            PlayerFactory playerFactory = new PlayerFactory();
            Player player = playerFactory.createPlayer(playerName, age, position, points, 0, 0, 0, 0);

            String insertPlayerSQL = "INSERT INTO players (name, age, position, points, assists, rebounds, steals, blocks, team_name) " +
                    "VALUES ('" + player.getName() + "', " + player.getAge() + ", '" + player.getPosition() + "', "
                    + player.getPoints() + ", 0, 0, 0, 0, '" + teamName + "')"; // Include default values for the missing columns
            stmt.executeUpdate(insertPlayerSQL);

            notifyObservers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

