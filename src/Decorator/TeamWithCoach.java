package Decorator;

import Models.TeamComponent;

public class TeamWithCoach extends TeamDecorator {
    private final String coach;

    public TeamWithCoach(TeamComponent wrappedTeam, String coach) {
        super(wrappedTeam);
        this.coach = coach;
    }

    @Override
    public String toString() {
        return super.toString() + "coach='" + coach + "'\n";
    }
}
