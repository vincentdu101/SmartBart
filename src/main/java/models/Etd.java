package models;

import java.util.List;

public class Etd {

    private String destination;
    private String abbreviation;
    private int limited;
    private List<Estimate> estimates;

    public Etd() {}

    public Etd clone(Etd etd) {
        this.destination = etd.getDestination();
        this.abbreviation = etd.getAbbreviation();
        this.estimates = etd.getEstimates();
        this.limited = etd.getLimited();
        return this;
    }


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public int getLimited() {
        return limited;
    }

    public void setLimited(int limited) {
        this.limited = limited;
    }

    public List<Estimate> getEstimates() {
        return estimates;
    }

    public void setEstimates(List<Estimate> estimates) {
        this.estimates = estimates;
    }
}
