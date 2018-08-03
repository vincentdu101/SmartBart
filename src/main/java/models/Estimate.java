package models;

import org.json.JSONObject;

public class Estimate {

    private int minutes;
    private int platform;
    private String direction;
    private int length;
    private boolean bikeflag;
    private int delay;

    public Estimate() {}

    public Estimate(JSONObject estimate) {
        this.minutes = Integer.parseInt(estimate.getString("minutes"));
        this.platform = Integer.parseInt(estimate.getString("platform"));
        this.direction = estimate.getString("direction");
        this.length = Integer.parseInt(estimate.getString("length"));
        this.bikeflag = estimate.getString("bikeflag").equals("1");
        this.delay = Integer.parseInt(estimate.getString("delay"));
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
