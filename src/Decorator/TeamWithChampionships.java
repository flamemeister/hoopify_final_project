package Decorator;

import Models.TeamComponent;

public class TeamWithChampionships extends TeamDecorator {
    private final int championships;

    public TeamWithChampionships(TeamComponent wrappedTeam, int championships) {
        super(wrappedTeam);
        this.championships = championships;
    }

    @Override
    public String toString() {
        return super.toString() + "Championships: " + championships + '\n';
    }
}
