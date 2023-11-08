import observer.*;

class Player implements HoopifyObserver{
    private String name;
    private int age;
    private String position;
    private int points;

    public Player(String name, int age, String position, int points) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", points=" + points +
                '}';
    }

    @Override
    public void update() {
        System.out.println("Player data updated!");
    }
}

