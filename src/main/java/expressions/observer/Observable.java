package expressions.observer;

public interface Observable {

    void add(Observer observer);
    void remove(Observer observer);
    void notifyObservers();
    void start();
    void stop();

}
