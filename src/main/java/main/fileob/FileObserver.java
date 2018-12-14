package main.fileob;

import main.observer.Observable;
import main.observer.Observer;

import java.io.File;
import java.util.List;

public class FileObserver implements Observable{

    private File observableFile;
    private List<Observer> observers;
    private long period;

    public FileObserver(File observableFile, long period) {
        this.observableFile = observableFile;
        this.period = period;
    }

    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::handleEvent);
    }
}
