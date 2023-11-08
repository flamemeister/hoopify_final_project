
import java.util.List;

import java.util.Scanner;

public class Hoopify {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DatabaseConnection dbConnection = DatabaseConnection.getInstance();

    public static void main(String[] args) {
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
                    System.out.println("Goodbye!");
                    System.exit(0);
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
            System.out.println("Team Name: " + team.getName());
        }
        System.out.println("---------------");
    }

    private static void showAllPlayers() {
        System.out.println("All Players:");
        List<Player> allPlayers = dbConnection.getAllPlayersFromDatabase();
        for (Player player : allPlayers) {
            System.out.println("Player Name: " + player.getName());
            System.out.println("Age: " + player.getAge());
            System.out.println("Position: " + player.getPosition());
            System.out.println("Points: " + player.getPoints());
            System.out.println("Points: " + player.getPoints());
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
        System.out.print("Team Name: ");
        String teamName = scanner.next();
        dbConnection.insertPlayer(playerName, age, position, points, teamName);
        System.out.println("Player added successfully!");
        System.out.println("---------------");
    }
}

