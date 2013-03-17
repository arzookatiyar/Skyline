package com.cloudmade.examples;

/*
 * Example usage of CloudMade's API
 */

import com.cloudmade.api.CMClient;
import com.cloudmade.api.geocoding.GeoResult;
import com.cloudmade.api.geocoding.GeoResults;
import com.cloudmade.api.geocoding.ObjectNotFoundException;
import com.cloudmade.api.geometry.Point;

public class FindClosest {
	
    public static void main(String[] args) {
        CMClient client = new CMClient("5c48343660fe4aa983946d44003b075f");

        GeoResults results = client.find("Potsdamer Platz, Berlin, Germany", 2, 0, null, true, true, true);
        GeoResult result = results.results[0];

        System.out.println(result.properties);
        System.out.println(result.centroid);

        try {
            result = client.findClosest("cafe", new Point(51.51558, -0.141449));

            System.out.println(result.properties);
            System.out.println(result.centroid);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}
