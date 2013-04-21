package com.cloudmade.examples;

import com.cloudmade.api.CMClient;
import com.cloudmade.api.CMClient.MeasureUnit;
import com.cloudmade.api.CMClient.RouteType;
import com.cloudmade.api.geocoding.GeoResult;
import com.cloudmade.api.geocoding.GeoResults;
//import com.cloudmade.api.geocoding.ObjectNotFoundException;
import com.cloudmade.api.geometry.Point;
import com.cloudmade.api.routing.Route;
import java.io.*;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

class getPOI {
	static Hashtable<Point, Integer> poi_table = new Hashtable<Point, Integer>();
	public static void main(String args[]) throws IOException{
		CMClient client = new CMClient("5c48343660fe4aa983946d44003b075f");
		//find_POI(String object_type, Point point1, Point point2, int results)
		//http://geocoding.cloudmade.com/8ee2a50541944fb9bcedded5165f09d9/geocoding/v2/find.html?bbox=40.3,-74.5,41.3,-73.5&object_type=atm
		GeoResults[] results_array = new GeoResults[10];
		
		try {
		results_array[0] = client.find_POI("cafe", new Point(40.3,-74.5), new Point(41.3,-73.5), 700); 
		results_array[1] = client.find_POI("hotel", new Point(40.3,-74.5), new Point(41.3,-73.5), 700);
		results_array[2] = client.find_POI("pub", new Point(40.3,-74.5), new Point(41.3,-73.5), 700);
		results_array[3] = client.find_POI("bank", new Point(40.3,-74.5), new Point(41.3,-73.5), 700);
		results_array[4] = client.find_POI("atm", new Point(40.3,-74.5), new Point(41.3,-73.5), 700);
		results_array[5] = client.find_POI("bus_stop", new Point(40.3,-74.5), new Point(41.3,-73.5), 700);
		results_array[6] = client.find_POI("bakery", new Point(40.3,-74.5), new Point(41.3,-73.5), 700);
		results_array[7] = client.find_POI("hospital", new Point(40.3,-74.5), new Point(41.3,-73.5), 700);
		results_array[8] = client.find_POI("museum", new Point(40.3,-74.5), new Point(41.3,-73.5), 700);
		results_array[9] = client.find_POI("theatre", new Point(40.3,-74.5), new Point(41.3,-73.5), 700);
		
		}
		catch(Exception e) {
			System.out.println(e);
		}

		
		//GeoResults results11 = client.find_POI("museum", new Point(40.3,-74.5), new Point(41.3,-73.5), 700);
		int num_POI = 10;
		
		for (int i=0; i<num_POI; i++) {
			int size1 = results_array[i].found;
			for (int j=0; j<num_POI; j++) {
				if (i!=j) {
					//compute distances					
					int size2 = results_array[j].found;
					for (int p=0; p<size1; p++) {
						for (int q=0; q<size2; q++) {
							try{
								System.out.println(i+" "+p+" -----> "+j+" "+q);
								Route route = client.route ((Point)(results_array[i].results[p].centroid),
    		    					(Point)(results_array[j].results[q].centroid),
    			                    RouteType.CAR,
    			                    null,
    			                    null,
    			                    "en",
    			                    MeasureUnit.KM
    			                	);
								System.out.println(route.summary.totalDistance);
								System.out.println(route.geometry.points);
								List<Point> points = route.geometry.points;
			    			
								for (int k=0; k<points.size(); k++) {
									//System.out.println(((Point)points.get(k)).lat+"  "+((Point)points.get(k)).lon);
									Point key = (Point)(points.get(k));
									if (poi_table.containsKey(key)) {
										int count = poi_table.get(key);
										poi_table.put(key, ++count);
									}
									else {
										poi_table.put(key, 1);
									}
								}
							}
						catch(Exception e) {
							System.out.println(e);
						}

						}
					}
				}				
			}
		}//Complete

		System.out.println("Final hashtable size "+poi_table.size());
		
		/*Write to file the c11ount*/
	    FileWriter fstream = new FileWriter("frequent_intersections.txt");
	    BufferedWriter out = new BufferedWriter(fstream);	
		
		Set table_keys = poi_table.keySet();
		Iterator it = table_keys.iterator();
		
		while(it.hasNext()) {
			Point coord = (Point)it.next();
			out.write(String.valueOf(coord.lat));
			out.write("\t");
			out.write(String.valueOf(coord.lon));
			out.write("\t");
			out.write(String.valueOf((Integer)poi_table.get(coord)));
			out.write("\n");
		}		
		out.close();
	}
}