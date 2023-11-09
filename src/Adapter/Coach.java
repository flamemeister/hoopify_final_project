package Adapter;
import Strategy.AwardsStrategy;

public interface Coach {
    String getName();
    int getAge();
    String getRole();
    void conductTraining();
    void setAwardsStrategy(AwardsStrategy strategy); // New method for setting strategy
    int countAwards();
}
