package observer;

public interface HoopifySubject {
    void addObserver(HoopifyObserver observer);
    void removeObserver(HoopifyObserver observer);
}

