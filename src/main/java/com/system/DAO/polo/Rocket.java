package com.system.DAO.polo;

import java.time.LocalDateTime;

public class Rocket {
    private int rocketID;
    private String rocketName;
    private LocalDateTime launchDate;
    private String carryPeople;
    private int in_orbitTime;

    public Rocket(int rocketID, String rocketName, LocalDateTime launchDate, String carryPeople, int in_orbitTime) {
        this.rocketID = rocketID;
        this.rocketName = rocketName;
        this.launchDate = launchDate;
        this.carryPeople = carryPeople;
        this.in_orbitTime = in_orbitTime;
    }

    public int getRocketID() {
        return rocketID;
    }

    public void setRocketID(int rocketID) {
        this.rocketID = rocketID;
    }

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    public LocalDateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
    }

    public String getCarryPeople() {
        return carryPeople;
    }

    public void setCarryPeople(String carryPeople) {
        this.carryPeople = carryPeople;
    }

    public long getInOrbitTime() {
        return in_orbitTime;
    }

    public void setInOrbitTime(int in_orbitTime) {
        this.in_orbitTime = in_orbitTime;
    }
}
