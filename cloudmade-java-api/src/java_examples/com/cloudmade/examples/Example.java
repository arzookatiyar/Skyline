package com.cloudmade.examples;

import com.cloudmade.api.CMClient;
import com.cloudmade.api.CMClient.MeasureUnit;
import com.cloudmade.api.CMClient.RouteType;
import com.cloudmade.api.geocoding.GeoResult;
import com.cloudmade.api.geocoding.GeoResults;
import com.cloudmade.api.geocoding.ObjectNotFoundException;
import com.cloudmade.api.geometry.Point;
import com.cloudmade.api.routing.Route;
import com.cloudmade.api.routing.RouteNotFoundException;
import java.io.*;
import java.util.List;


public class Example {
	
	public static int among_POI(int []array_id, int []check_id) {
		int count = 0;
		for (int i=0; i<check_id.length; i++) {
			for (int j=0; j<array_id.length; j++) {
				if (check_id[i] == array_id[j])
					++count;
			}
		}
		return count;
	}
	
	public static void sort(int []array_dist, int []array_id, int counter) {
		int iMin = 0;
		for (int j=0; j<counter-1; j++) {
			iMin = j;
			for (int i=j+1; i<counter; i++) {
				if (array_dist[i] < array_dist[iMin])
					iMin = i;
			}
			
			if (iMin!=j) {
				int temp1 = array_dist[j];
				int temp2 = array_id[j];
				array_dist[j] = array_dist[iMin];
				array_id[j] = array_id[iMin];
				array_dist[iMin] = temp1;
				array_id[iMin] = temp2;
			}
		}
	}
	
	
    public static void main(String args[]) throws IOException{
	CMClient client = new CMClient("5c48343660fe4aa983946d44003b075f");
	GeoResults results = client.find("Potsdamer Platz, Berlin, Germany", 2, 0, null, true, true, true);
	GeoResult result = results.results[0];
	System.out.println(result.properties);
	System.out.println(result.centroid);	
	System.out.println(result.geometry);	
	try {
	    results = client.find_range("cafe", new Point(51.51558,-0.141449), 50, 5000);
	    result = results.results[1];
	   // System.out.println(result.properties);
	    //System.out.println("id "+result.id);
	   // System.out.println((Point)result.centroid);
	    FileWriter fstream  = new FileWriter("data.txt");
	    BufferedWriter out = new BufferedWriter(fstream);
	    int []result_id = new int[results.found];
	   // for (int i=0; i<results.found; i++) {
	    for (int i=0; i<30; i++) {
	    	result = results.results[i];
	    	result_id[i] =  result.id;
	    	System.out.println(result.id+" "+result.properties);
	    	System.out.println(result.id+" "+result.centroid);
	    }
	    
	    //for (int i=0; i<results.found; i++) {
	    int counter1=0;
	    for (int i=0; i<30; i++) {
	    	out.write("NEW\n");	
	    	out.write(Integer.toString((results.results[i]).id));
	    	out.write("\n");
	    	int []array_dist = new int[results.found];
	    	int []array_id = new int[results.found];
	    	int counter = 0;
	    	//for (int j=0; j<results.found; j++) {
	    	for (int j=0; j<30; j++) {
	    		 try {
	    			if (i != j) {
	    			//System.out.println(i+"  "+j);
	    			Route route = client.route ((Point)((results.results[i]).centroid),
	    					(Point)((results.results[j]).centroid),
		                    RouteType.CAR,
		                    null,
		                    null,
		                    "en",
		                    MeasureUnit.KM
		                );
	    			System.out.println(route.summary.totalDistance);
	    			System.out.println(route.geometry.points);
	    			
	    			System.out.println((results.results[i]).centroid+"  "+(results.results[j]).centroid);
	    			GeoResults results1 = client.present_along_route("cafe", route.geometry.points, 20, 30);
	    			
	    			int []found_id = new int[results1.found];
	    			for (int p = 0; p<results1.found; p++) {
	    				found_id[p] = results1.results[p].id;
	    			}
	    			//result_id
	    			int common_ids = among_POI(result_id, found_id);
	    		//	System.out.println("In the route");
	    			/*for (int k=0; k<results1.found; k++) {
	    				GeoResult result1 = results1.results[k];
	    			//System.out.println(result1.properties);
	    				System.out.println(result1.centroid);	
	    			}*/
	    		//	System.out.println("route ends");
	    			//System.out.println(result1.geometry);
	    			if (common_ids<=2) { //Ignore only when the cafe belongs to the list of the cafes!
	    				System.out.println("Distance added "+(++counter1)+" for node "+i);
	    				array_dist[counter] = (int)route.summary.totalDistance;
	    				array_id[counter] = (results.results[j]).id;
	    				++counter;
	    			}
	    			}
	    		 } catch (Exception e) {
	    	           // e.printStackTrace();
	    				System.out.println("Road not found");
	    	        }
	    	}
	    	
	    	//Here we sort the array and write it in a file
	    	sort(array_dist, array_id, counter);
	    	out.write(Integer.toString(counter));
	    	out.write("\n");
	    	for (int k=0; k<counter; k++) {
	    		out.write(Integer.toString(array_id[k]));
	    		out.write("\n");
	    		out.write(Integer.toString(array_dist[k]));
	    		out.write("\n");
	    	}
	    }
		
	    out.close();
	    //System.out.println(result.geometry);
	    //System.out.println(((Point)(result.geometry).lat+"  "+((Point)result.geometry).lon);
	    //result = client.findClosest("school", new Point(29.0167, 77.3833));
	    //System.out.println(result.properties);
	    //System.out.println(result.centroid);	    
	    //String object_type, Point point, int results, int distance
	    //http://geocoding.cloudmade.com/8ee2a50541944fb9bcedded5165f09d9/geocoding/v2/find.js
	    //?object_type=cafe&around=51.51558,-0.141449&results=5&distance=500
	} catch(ObjectNotFoundException e) {
	    e.printStackTrace();
	}
    }
}