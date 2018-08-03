package models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
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

    public Etd(JSONObject etd) {
        this.destination = etd.getString("destination");
        this.abbreviation = etd.getString("abbreviation");
        this.limited = etd.getInt("limited");
        this.estimates = new ArrayList<>();

        JSONArray estimates = etd.getJSONArray("estimate");
        for (int i = 0; i < estimates.length(); i++) {
            JSONObject estimate = estimates.getJSONObject(i);
            this.estimates.add(new Estimate(estimate));
        }
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
