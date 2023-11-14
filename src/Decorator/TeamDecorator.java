package Decorator;

import Models.TeamComponent;

public class TeamDecorator implements TeamComponent {
    private final TeamComponent wrappedTeam;

    public TeamDecorator(TeamComponent wrappedTeam) {

        this.wrappedTeam = wrappedTeam;
    }

    @Override
    public String name() {

        return wrappedTeam.name();
    }


    @Override
    public String toString() {

        return wrappedTeam.toString();
    }

    @Override
    public void update() {

        wrappedTeam.update();
    }
}
