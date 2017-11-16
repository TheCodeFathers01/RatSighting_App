package edu.gatech.tcf.ratsighting_app;

/**
 * Created by taint on 11/15/2017.
 *
 * Will test the RatSighting class's argument constructor
 */

import org.junit.Test;

import edu.gatech.tcf.ratsighting_app.Model.Borough;
import edu.gatech.tcf.ratsighting_app.Model.LocationType;
import edu.gatech.tcf.ratsighting_app.Model.RatSighting;

import static org.junit.Assert.*;

public class RatSightingConstructorTest {
    @Test
    public void constructorTest() throws Exception {
        RatSighting ratSightingValid = new RatSighting(10, "date", "coordinates", LocationType.FamilyApt, "zip", "address", "city", Borough.BRONX);
        RatSighting notValid = new RatSighting(10, null, null, null, null, null, null, null);
        assertEquals(ratSightingValid.getDate(), "date");
        assertEquals(ratSightingValid.getCoordinates(), "coordinates");
        assertEquals(ratSightingValid.getLocationType(), LocationType.FamilyApt);
        assertEquals(ratSightingValid.getZipCode(), "zip");
        assertEquals(ratSightingValid.getAddress(), "address");
        assertEquals(ratSightingValid.getCity(), "city");
        assertEquals(ratSightingValid.getBorough(), Borough.BRONX);
        assertEquals(notValid.getDate(), "No valid date");
        assertEquals(notValid.getCoordinates(), "No valid coordinates");
        assertEquals(notValid.getLocationType(), LocationType.Other);
        assertEquals(notValid.getZipCode(), "No valid zipcode");
        assertEquals(notValid.getAddress(), "No address found");
        assertEquals(notValid.getCity(), "No city found");
        assertEquals(notValid.getBorough(), Borough.OTHER);
    }
}
