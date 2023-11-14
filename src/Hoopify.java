import Adapter.Coach;
import Adapter.PlayerToCoachAdapter;
import Decorator.TeamWithChampionships;
import Decorator.TeamWithCoach;
import Models.Player;
import Models.Team;
import Models.TeamComponent;
import Observer.HoopifyObserver;
import Singleton.DatabaseConnection;
import Strategy.AwardsStrategy;
import Strategy.CoachChampionshipsStrategy;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Hoopify implements HoopifyObserver{
    private static final Scanner scanner = new Scanner(System.in);
    private static final DatabaseConnection dbConnection = DatabaseConnection.getInstance();

    public static void main(String[] args) {
        dbConnection.addObserver(new Hoopify());
        while (true) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    showAllTeams();
                    break;
                case 2:
                    showAllPlayers();
                    break;
                case 3:
                    addNewTeam();
                    break;
                case 4:
                    addNewPlayer();
                    break;
                case 5:
                    seeCoachesForTeam();
                    break;
                case 6:
                    olympiadTeam();
                    break;
                case 7:
                    quit();
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. See all the teams");
        System.out.println("2. See all the players");
        System.out.println("3. Add new team");
        System.out.println("4. Add new player");
        System.out.println("5. See team's coaches");
        System.out.println("6. See olympiad team details");
        System.out.println("7. Quit");
    }

    private static int getUserChoice() {
        System.out.print("Enter your choice (1-7): ");
        try {
            return scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }

    private static void showAllTeams() {
        System.out.println("All Teams:");
        List<Team> allTeams = dbConnection.getAllTeamsFromDatabase();
        for (Team team : allTeams) {
            System.out.println("Team name: " + team.name());
        }
        System.out.println("---------------");
    }

    private static void showAllPlayers() {
        System.out.println("All Players:");
        List<Player> allPlayers = dbConnection.getAllPlayersFromDatabase();
        for (Player player : allPlayers) {
            System.out.println("Player name: " + player.name());
            System.out.println("Age: " + player.age());
            System.out.println("Position: " + player.position());
            System.out.println("Points: " + player.points());
            System.out.println("Assists: " + player.assists());
            System.out.println("Rebounds: " + player.rebounds());
            System.out.println("Steals: " + player.steals());
            System.out.println("Blocks: " + player.blocks());
            System.out.println("---------------");
        }
        System.out.println("---------------");
    }

    private static void addNewTeam() {
        System.out.print("Enter the name of the new team: ");
        String teamName = scanner.next();
        System.out.print("Enter the name of the coach for the team: ");
        String coachName = scanner.next();
        dbConnection.insertTeam(teamName, coachName);
        System.out.println("Team added successfully!");
        System.out.println("---------------");
    }

    private static void addNewPlayer() {
        System.out.println("Enter the details of the new player:");
        System.out.print("Name: ");
        String playerName = scanner.next();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        System.out.print("Position: ");
        String position = scanner.next();
        System.out.print("Points: ");
        int points = scanner.nextInt();
        System.out.println("Assists");
        int assists = scanner.nextInt();
        System.out.println("Rebounds");
        int rebounds = scanner.nextInt();
        System.out.println("Steals");
        int steals = scanner.nextInt();
        System.out.println("Blocks");
        int blocks = scanner.nextInt();


        System.out.print("Team name: ");
        String teamName = scanner.next();
        dbConnection.insertPlayer(playerName, age, position, points, assists, rebounds, steals, blocks, teamName);
        System.out.println("Player added successfully!");
        System.out.println("---------------");
    }

    public static void seeCoachesForTeam() {
        System.out.print("Enter the name of the team to see coaches: ");
        String teamName = scanner.next();
        List<String> coaches = dbConnection.getCoachesForTeam(teamName);

        if (coaches.isEmpty()) {
            System.out.println("No coaches found for the specified team.");
        } else {
            System.out.println("Coaches for " + teamName + ":");
            for (String coach : coaches) {
                System.out.println(coach);
            }
        }
        System.out.println("---------------");
    }


    public static void olympiadTeam() {
        System.out.println( "That is the Redeem Team of the USA national olympiad team.\n" );
        System.out.println("---------------\n");
        TeamComponent baseTeam = new Team("the USA national team");
        TeamComponent teamWithCoach = new TeamWithCoach(baseTeam, "Steve Kerr");
        TeamComponent teamWithChampionships = new TeamWithChampionships(teamWithCoach, 3);
        System.out.println(teamWithChampionships);
        System.out.println("---------------\n");
        System.out.println("That is the team who are going to present the USA in the Olympics in 2024: \n");
        System.out.println("---------------\n");
        List<Player> players = new ArrayList<>();
        players.add(new Player("Lebron James", 38, "SF", 13, 7, 4, 2, 4));
        players.add(new Player("Kevin Durant", 35, "PF", 12, 3, 6, 1, 0));
        players.add(new Player("Steph Curry", 33, "PG", 20, 6, 3, 3, 0));
        players.add(new Player("Zach Lavine", 25, "SG", 8, 4, 4, 2, 1));
        players.add(new Player("Anthony Davis", 30, "C", 13, 2, 11, 1, 5));

        for (Player player : players) {
            System.out.println(player);
        }

        AwardsStrategy strategy = new CoachChampionshipsStrategy(3);
        Coach coach = new PlayerToCoachAdapter(players.get(0), strategy);

        System.out.println(coach);
        coach.conductTraining();

        System.out.println("---------------\n");

    }

    public static void quit(){
        System.out.println("Keep it G! Hoop it!");
        System.exit(0);
    }

    @Override
    public void update() {
        System.out.println("\n---------------\n");
        System.out.println("Observer Notification!");
        System.out.println("New data has been added to the Database");
        System.out.println("\n---------------\n");
    }
}