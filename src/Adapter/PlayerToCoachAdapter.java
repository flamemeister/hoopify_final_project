package Adapter;

import Models.Player;
import Strategy.AwardsStrategy;

public class PlayerToCoachAdapter implements Coach {
    private final Player player;
    private AwardsStrategy awardsStrategy;

    public PlayerToCoachAdapter(Player player, AwardsStrategy awardsStrategy) {
        this.player = player;
        this.awardsStrategy = awardsStrategy;
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public int getAge() {
        return player.getAge();
    }

    @Override
    public String getRole() {
        return "Coach";
    }

    @Override
    public void conductTraining() {
        System.out.println(player.getName() + " is conducting training.\n");
    }

    @Override
    public void setAwardsStrategy(AwardsStrategy strategy) {
        this.awardsStrategy = strategy;
    }

    @Override
    public int countAwards() {
        return awardsStrategy.countAwards();
    }

    @Override
    public String toString() {
        return "Player: \n" +
                "name='" + getName() + '\n' +
                "age=" + getAge() + '\n' +
                "role'" + getRole() + '\n' +
                "championships" + countAwards() + '\n';
    }
}
