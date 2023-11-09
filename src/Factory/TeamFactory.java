package Factory;

import Models.Team;

public class TeamFactory {
    public Team createTeam(String name) {
        return new Team(name);
    }
}