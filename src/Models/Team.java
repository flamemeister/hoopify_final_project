package Models;

public record Team(String name) implements TeamComponent {
    @Override
    public String toString() {
        return "Team: \n" +
                "name='" + name + "'\n";
    }

    @Override
    public void update() {
        System.out.println("Models.Team data updated!");
    }
}
