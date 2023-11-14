package Factory;
import Models.Team;
public class TeamFactory {
    public Team createTeam(String teamName, int awards) {
        return new Team(teamName);
    }


}
