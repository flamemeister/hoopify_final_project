class CoachTeamDecorator extends TeamDecorator {
    private String coachName;

    public CoachTeamDecorator(Team decoratedTeam, String coachName) {
        super(decoratedTeam);
        this.coachName = coachName;
    }

    public String getCoachName() {
        return coachName;
    }

    @Override
    public String toString() {
        return "Team with Coach{" +
                "name='" + getName() + '\'' +
                ", coachName='" + coachName + '\'' +
                ", players=" + getPlayers() +
                '}';
    }
}