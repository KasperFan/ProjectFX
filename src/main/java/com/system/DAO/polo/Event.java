package com.system.DAO.polo;

public class Event {
    private int id;
    private String title;
    private String rocketName;
    private String time;
    private String astronauts;
    private String mean;

    public Event(int id, String title, String time, String mean) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.mean = mean;
    }

    public Event(int id, String title, String rocketName, String time, String astronauts, String mean) {
        this.id = id;
        this.title = title;
        this.rocketName = rocketName;
        this.time = time;
        this.astronauts = astronauts;
        this.mean = mean;
    }

    public Event(String title, String rocketName, String time, String astronauts, String mean) {
        this.title = title;
        this.rocketName = rocketName;
        this.time = time;
        this.astronauts = astronauts;
        this.mean = mean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAstronauts() {
        return astronauts;
    }

    public void setAstronauts(String astronauts) {
        this.astronauts = astronauts;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rocketName='" + rocketName + '\'' +
                ", time='" + time + '\'' +
                ", astronauts='" + astronauts + '\'' +
                ", mean='" + mean + '\'' +
                '}';
    }
}
