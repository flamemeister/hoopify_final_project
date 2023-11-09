package Models;

import Observer.HoopifyObserver;

public record Player(String name, int age, String position, int points) implements HoopifyObserver {
    @Override
    public String toString() {
        return "Models.Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", points=" + points +
                '}';
    }

    @Override
    public void update() {
        System.out.println("Models.Player data updated!");
    }
}

