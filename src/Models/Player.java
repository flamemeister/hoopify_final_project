package Models;

import Observer.HoopifyObserver;

public class Player implements HoopifyObserver {
    private String name;
    private int age;
    private String position;
    private int points;
    private int assists;
    private int rebounds;
    private int steals;
    private int blocks;

    public Player(String name, int age, String position, int points, int assists, int rebounds, int steals, int blocks) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.points = points;
        this.assists = assists;
        this.rebounds = rebounds;
        this.steals = steals;
        this.blocks = blocks;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public int getPoints() {
        return points;
    }

    public int getAssists() {
        return assists;
    }

    public int getRebounds() {
        return rebounds;
    }

    public int getSteals() {
        return steals;
    }

    public int getBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", points=" + points + '\n' +
                ", assists=" + assists + '\n' +
                ", rebounds=" + rebounds + '\n' +
                ", steals=" + steals + '\n' +
                ", blocks=" + blocks + '\n' +
                '}';
    }

    @Override
    public void update() {
        System.out.println("Player data updated!");
    }
}
