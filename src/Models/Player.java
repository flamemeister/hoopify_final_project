package Models;

import Observer.HoopifyObserver;

public record Player(String name, int age, String position, int points, int assists, int rebounds, int steals, int blocks) implements HoopifyObserver {
    @Override
    public String toString() {
        return "Models.Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", points=" + points +
                ", assists=" + assists +
                ", rebounds=" + rebounds +
                ", steals=" + steals +
                ", blocks="  +blocks +
                '}';
    }

    @Override
    public void update() {
        System.out.println("Models.Player data updated!");
    }
}

