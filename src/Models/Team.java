package Models;

public record Team(String name) implements TeamComponent {
    @Override
    public String toString() {
        return "Team: \n" +
                "Name: " + name + "\n";
    }

    @Override
    public void update() {
        System.out.println("Team data updated!");
    }
}
