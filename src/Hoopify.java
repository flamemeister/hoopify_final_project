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
                    quit();
                    break;
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
        System.out.println("5. Quit");
    }

    private static int getUserChoice() {
        System.out.print("Enter your choice (1-5): ");
        try {
            return scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            // Consume the invalid input
            scanner.nextLine();
            return -1; // Return an invalid choice
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
            System.out.println("---------------");
        }
        System.out.println("---------------");
    }

    private static void addNewTeam() {
        System.out.print("Enter the name of the new team: ");
        String teamName = scanner.next();
        dbConnection.insertTeam(teamName);
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

        // Assuming all players will be associated with a team
        System.out.print("Team name: ");
        String teamName = scanner.next();
        dbConnection.insertPlayer(playerName, age, position, points, teamName);
        System.out.println("Player added successfully!");
        System.out.println("---------------");
    }

    public static void quit() {
        System.out.println( "Before leaving the app we need to provide you with information about possibly the best team:\n" );
        System.out.println("---------------\n");
        TeamComponent baseTeam = new Team("Team of Sultaniyar Kuandyk");
        TeamComponent teamWithCoach = new TeamWithCoach(baseTeam, "Aldiyar Saken");
        TeamComponent teamWithChampionships = new TeamWithChampionships(teamWithCoach, 3);
        System.out.println(teamWithChampionships);
        System.out.println("---------------\n");
        System.out.println("We have to tell a little more about the coach. He is a former player who became the coach of this team after his career\n");
        System.out.println("---------------\n");
        Player player = new Player("Aldiyar Saken", 20, "PG", 100);
        AwardsStrategy strategy = new CoachChampionshipsStrategy(3);
        Coach coach = new PlayerToCoachAdapter(player, strategy);
        System.out.println(coach);
        coach.conductTraining();
        System.out.println("---------------\n");
        System.out.println("Good bye!");
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

