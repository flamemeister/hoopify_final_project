package Models;

public class Team implements TeamComponent {
    private String name;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Team: \n" +
                "name='" + name + "'\n";
    }

    @Override
    public void update() {
        System.out.println("Team data updated!");
    }

    @Override
    public String name() {
        return getName();
    }
}
