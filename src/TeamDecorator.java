import java.util.List;
class TeamDecorator {
    protected Team decoratedTeam;

    public TeamDecorator(Team decoratedTeam) {
        this.decoratedTeam = decoratedTeam;
    }

    public String getName() {
        return decoratedTeam.getName();
    }

    public List<Player> getPlayers() {
        return decoratedTeam.getPlayers();
    }

}