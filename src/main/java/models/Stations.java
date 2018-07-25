package models;

import java.util.ArrayList;

public enum Stations {

    MLBR("MLBR"),
    SBRN("SBRN"),
    SSAN("SSAN"),
    COLM("COLM"),
    DALY("DALY"),
    BALB("BALB"),
    GLEN("GLEN"),
    TWENTY_FOURTH("24TH"),
    SIXTEENTH("16TH"),
    CIVC("CIVC"),
    POWL("POWL"),
    MONT("MONT"),
    EMBR("EMBR"),
    WOAK("WOAK"),
    TWELVETH("12TH"),
    NINETEENTH("19TH"),
    MCAR("MCAR"),
    ASHB("ASHB"),
    DBRK("DBRK"),
    NBRK("NBRK"),
    PLZA("PLZA"),
    DELN("DELN"),
    RICH("RICH");

    private String station;

    Stations(String station) {
        this.station = station;
    }
}