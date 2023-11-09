package Strategy;

public record PlayerMVPStrategy(int MVP) implements AwardsStrategy{
    @Override
    public int countAwards() {
        return this.MVP;
    }
}
