package models;

class Advisory {

    private String station;
    private String description;
    private String smsText;

    public Advisory() {}

    public Advisory clone(Advisory advisory) {
        this.station = advisory.getStation();
        this.description = advisory.getDescription();
        this.smsText = advisory.getSmsText();
        return this;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }
}