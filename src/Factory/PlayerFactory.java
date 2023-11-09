package Factory;
import Models.Player;

public class PlayerFactory {
    public Player createPlayer(String name, int age, String position, int points, int assists, int rebounds, int steals, int blocks) {
        return new Player(name, age, position, points, assists, rebounds, steals, blocks);
    }
}
