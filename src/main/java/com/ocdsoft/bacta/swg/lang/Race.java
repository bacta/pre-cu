package com.ocdsoft.bacta.swg.lang;

/**
 * Created by kburkhardt on 3/28/14.
 */
public enum Race {
    AQUALISH,
    BITH,
    BOTHAN,
    HUMAN,
    MONCAL,
    RODIAN,
    TRANDOSHAN,
    TWILEK,
    WOOKIE,
    ZABRAK;

    public static Race parseRace(String templateString) {
        String race = templateString.substring(templateString.lastIndexOf("/") + 1, templateString.lastIndexOf("_"));
        return Race.valueOf(race.toUpperCase());
    }
}
