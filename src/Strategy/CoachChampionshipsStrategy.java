package Strategy;

public record CoachChampionshipsStrategy(int championships) implements AwardsStrategy{
    @Override
    public int countAwards() {
        return this.championships;
    }
}
