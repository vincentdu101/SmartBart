package models;

public class Estimate {

    private int minutes;
    private int platform;
    private String direction;
    private int length;
    private boolean bikeflag;
    private int delay;

    public Estimate() {}

    public Estimate clone(Estimate estimate) {
        this.minutes = estimate.getMinutes();
        this.platform = estimate.getPlatform();
        this.direction = estimate.getDirection();
        this.length = estimate.getLength();
        this.bikeflag = estimate.isBikeflag();
        this.delay = estimate.getDelay();
        return this;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isBikeflag() {
        return bikeflag;
    }

    public void setBikeflag(boolean bikeflag) {
        this.bikeflag = bikeflag;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
