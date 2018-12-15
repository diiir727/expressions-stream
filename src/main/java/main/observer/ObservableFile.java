package main.observer;

import main.observer.Observable;
import main.observer.Observer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ObservableFile implements Observable{

    private File observableFile;
    private List<Observer> observers;
    private long period;
    private Timer timer;
    private long lastModification;

    public ObservableFile(File observableFile, long period) {
        this.observableFile = observableFile;
        this.period = period;
        this.timer = new Timer();
        this.observers = new ArrayList<>();
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

    @Override
    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                task();
            }
        }, 10, period);
    }

    @Override
    public void stop() {
        this.timer.cancel();
    }

    private void task(){
        long tmpModification = this.observableFile.lastModified();
        if(tmpModification > this.lastModification){
            this.lastModification = tmpModification;
            notifyObservers();
        }
    }
}