package Factory;
import Models.Player;
public class PlayerFactory {
    public Player createPlayer(String name, int age, String position) {
        return new Player(name, age, position, 0);
    }
}
