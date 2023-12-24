package com.system.DAO.polo;

public class Rocket {
    private int rocketID;
    private String rocketName;
    private String launchDate;
    private int inOrbitTime;

    public Rocket(int rocketID, String rocketName, String launchDate, int inOrbitTime) {
        this.rocketID = rocketID;
        this.rocketName = rocketName;
        this.launchDate = launchDate;
        this.inOrbitTime = inOrbitTime;
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

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public long getInOrbitTime() {
        return inOrbitTime;
    }

    public void setInOrbitTime(int in_orbitTime) {
        this.inOrbitTime = in_orbitTime;
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "rocketID=" + rocketID +
                ", rocketName='" + rocketName + '\'' +
                ", launchDate='" + launchDate + '\'' +
                ", in_orbitTime=" + inOrbitTime +
                '}';
    }
}
