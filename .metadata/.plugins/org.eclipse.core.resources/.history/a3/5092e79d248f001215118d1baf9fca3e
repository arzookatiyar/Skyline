package com.cloudmade.examples;

import com.cloudmade.api.CMClient;
import com.cloudmade.api.CMClient.MeasureUnit;
import com.cloudmade.api.CMClient.RouteType;
import com.cloudmade.api.geocoding.GeoResult;
import com.cloudmade.api.geocoding.GeoResults;
import com.cloudmade.api.geometry.Point;
import com.cloudmade.api.routing.Route;
import java.io.*;
import java.util.Hashtable;


public class Examplem_n {
	static Hashtable prob_table = new Hashtable();
	static int nodes = 10;
	static String folder = "Sample10_";
	
	
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
		System.out.println(counter);
		for (int i=0; i<counter; i++) {
			System.out.print(array_dist[i]+" "+array_id[i]+" ,,  ");
		}
		System.out.println();
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
		
		System.out.println("After sorting");
		for (int i=0; i<counter; i++) {
			System.out.print(array_dist[i]+" "+array_id[i]+" ,,  ");
		}
		System.out.println();

	}
	
	
    public static void main(String args[]) throws IOException{
	CMClient client = new CMClient("5c48343660fe4aa983946d44003b075f");
	GeoResults results = client.find("Potsdamer Platz, Berlin, Germany", 2, 0, null, true, true, true);
	GeoResult result = results.results[0];
	System.out.println(result.properties);
	System.out.println(result.centroid);	
	System.out.println(result.geometry);	
	try {
		GeoResults results11 = client.find_range("cafe", new Point(51.51558,-0.141449), nodes, 5000);
	    GeoResults results12 = client.find_range("pub", new Point(51.51558,-0.141449), nodes, 5000);
	    GeoResults results13 = client.find_range("hotel", new Point(51.51558,-0.141449), nodes, 5000);
	    GeoResults results14 = client.find_range("atm", new Point(51.51558,-0.141449), nodes, 5000);
	    GeoResults results15 = client.find_range("fuel", new Point(51.51558,-0.141449), nodes, 5000);
	    //result = results.results[1];
	   // System.out.println(result.properties);
	    //System.out.println("id "+result.id);
	   // System.out.println((Point)result.centroid);
	    FileWriter fstream  = new FileWriter(folder+nodes+"/data2_"+nodes+".txt");
	    BufferedWriter out = new BufferedWriter(fstream);
	    int []result_id = new int[results11.found+results12.found+results13.found+results14.found+results15.found];
	    //int []result12_id = new int[results12.found];
	    //int []result13_id = new int[results13.found];	
	   // for (int i=0; i<results.found; i++) {
	    FileWriter fstream1  = new FileWriter(folder+nodes+"/type1_"+nodes+".txt");
	    BufferedWriter out1 = new BufferedWriter(fstream1);
	    FileWriter fstream2  = new FileWriter(folder+nodes+"/type2_"+nodes+".txt");
	    BufferedWriter out2 = new BufferedWriter(fstream2);
	    FileWriter fstream3  = new FileWriter(folder+nodes+"/type3_"+nodes+".txt");
	    BufferedWriter out3 = new BufferedWriter(fstream3);
	    FileWriter fstream4  = new FileWriter(folder+nodes+"/type4_"+nodes+".txt");
	    BufferedWriter out4 = new BufferedWriter(fstream4);
	    FileWriter fstream5  = new FileWriter(folder+nodes+"/type5_"+nodes+".txt");
	    BufferedWriter out5 = new BufferedWriter(fstream5);

	    FileWriter fstream_node  = new FileWriter(folder+nodes+"/relation_node_"+nodes+".txt");
	    BufferedWriter out_node = new BufferedWriter(fstream_node);	    

	    for (int i=0; i<(results11.found+results12.found+results13.found+results14.found+results15.found); i++) {
	    	//here i can keep a file that stores the type associated with the node id
	    	if (i < results11.found) {
		    	result = results11.results[i];
		    	out1.write(Integer.toString((results11.results[i]).id));
		    	out1.write("\n");
	    	}
	    	if (i >= results11.found && i<(results11.found + results12.found)) {
		    	result = results12.results[i-results11.found];
		    	out2.write(Integer.toString((results12.results[i-(results11.found)]).id));
		    	out2.write("\n");
	    	}
	    	if (i >= (results11.found + results12.found) && i<(results11.found + results12.found+results13.found)) {
		    	result = results13.results[i-(results11.found + results12.found)];
		    	out3.write(Integer.toString((results13.results[i-(results11.found+results12.found)]).id));
		    	out3.write("\n");
	    	}
	    	
	    	if (i >= (results11.found + results12.found+results13.found) && i<(results11.found + results12.found+results13.found+results14.found)) {
		    	result = results14.results[i-(results11.found + results12.found + results13.found)];
		    	out4.write(Integer.toString((results14.results[i-(results11.found+results12.found+results13.found)]).id));
		    	out4.write("\n");
	    	}
	    	
	    	if (i>=(results11.found + results12.found+results13.found+results14.found)) {
		    	result = results15.results[i-(results11.found + results12.found + results13.found + results14.found)];
		    	out5.write(Integer.toString((results15.results[i-(results11.found+results12.found+results13.found + results14.found)]).id));
		    	out5.write("\n");
	    	}
	    	

	    	result_id[i] =  result.id;
	    	prob_table.put(result.id, Math.random());
	    	out_node.write(Integer.toString(result.id));
	    	out_node.write("\t");
	    	out_node.write(Double.toString(Math.random()));
	    	out_node.write("\n");
	    	System.out.println(result.id+" "+result.properties);
	    	System.out.println(result.id+" "+result.centroid);
	    }

	    
	    out1.close();
	    out2.close();
	    out3.close();
	    out4.close();
	    out5.close();
	    out_node.close();
	    //for (int i=0; i<results.found; i++) {
	    int counter1=0;
	    for (int i=0; i<(results11.found+results12.found+results13.found+results14.found+results15.found); i++) {
	    	out.write("NEW\n");	
	    	if (i < results11.found) {
	    		out.write(Integer.toString((results11.results[i]).id));
	    	}
	    	if (i >= results11.found && i<(results11.found + results12.found)) {
	    		out.write(Integer.toString((results12.results[i-(results11.found)]).id));
	    	}
	    	
	    	if (i >= (results11.found + results12.found) && i<(results11.found + results12.found+results13.found)) {
	    		out.write(Integer.toString((results13.results[i-(results11.found + results12.found)]).id));	    	
	    	}
	    	
	    	if (i >= (results11.found + results12.found+results13.found) && i<(results11.found + results12.found+results13.found+results14.found)) {
	    		out.write(Integer.toString((results14.results[i-(results11.found + results12.found+results13.found)]).id));	   
	    	}
	    	
	    	if (i>=(results11.found + results12.found+results13.found+results14.found)) {
	    		out.write(Integer.toString((results15.results[i-(results11.found + results12.found+results13.found+results14.found)]).id));
	    	}

	    	
	    	
	    	out.write("\n");
	    	int []array_dist = new int[results11.found+results12.found+results13.found+results14.found+results15.found];
	    	int []array_id = new int[results11.found+results12.found+results13.found+results14.found+results15.found];
	    	int counter = -1;
	    	System.out.println("initialised");
	    	//for (int j=0; j<results.found; j++) {
	    	for (int j=0; j<(results11.found+results12.found+results13.found+results14.found+results15.found); j++) {
	    		//System.out.println(i+"  "+j+"  "+results11.found+" "+results12.found+" " +results13.found);
	    		 try {
	    			//only to initialise
	    			/*Route route = client.route ((Point)((results.results[0]).centroid),
		    					(Point)((results.results[0]).centroid),
			                    RouteType.CAR,
			                    null,
			                    null,
			                    "en",
			                    MeasureUnit.KM
			                );*/
	    			 Route route = null;
	    			 
	    			if (i != j) {
	    			////System.out.println(i+"  "+j);
	    				
	    		    	if (i < results11.found) {
	    		    		//out.write(Integer.toString((results11.results[i]).id));
	    			    	if (j < results11.found) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results11.results[i]).centroid),
	    		    					(Point)((results11.results[j]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );

	    			    	}
	    			    	if (j >= results11.found && j<(results11.found + results12.found)) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results11.results[i]).centroid),
	    		    					(Point)((results12.results[j-(results11.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	if (j >= (results11.found + results12.found) && j<(results11.found + results12.found + results13.found)) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results11.results[i]).centroid),
	    		    					(Point)((results13.results[j-(results11.found + results12.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	
	    			    	if (j >= (results11.found + results12.found+results13.found) && j<(results11.found + results12.found+results13.found+results14.found)) {
	    		    			route = client.route ((Point)((results11.results[i]).centroid),
	    		    					(Point)((results14.results[j-(results11.found + results12.found+results13.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	
	    			    	if (j>=(results11.found + results12.found+results13.found+results14.found)) {
	    		    			route = client.route ((Point)((results11.results[i]).centroid),
	    		    					(Point)((results15.results[j-(results11.found + results12.found+results13.found+results14.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}

	    			    	

	    		    	}
	    		    	if (i >= results11.found && i<(results11.found + results12.found)) {
	    		    		
	    			    	if (j < results11.found) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results12.results[i-(results11.found)]).centroid),
	    		    					(Point)((results11.results[j]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );

	    			    	}
	    			    	if (j >= results11.found && j<(results11.found + results12.found)) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results12.results[i-(results11.found)]).centroid),
	    		    					(Point)((results12.results[j-(results11.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	if (j >= (results11.found + results12.found) && j<(results11.found + results12.found + results13.found)) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results12.results[i-(results11.found)]).centroid),
	    		    					(Point)((results13.results[j-(results11.found + results12.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	
	    			    	if (j >= (results11.found + results12.found+results13.found) && j<(results11.found + results12.found+results13.found+results14.found)) {
	    		    			route = client.route ((Point)((results12.results[i-(results11.found)]).centroid),
	    		    					(Point)((results14.results[j-(results11.found + results12.found+results13.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	
	    			    	if (j>=(results11.found + results12.found+results13.found+results14.found)) {
	    		    			route = client.route ((Point)((results12.results[i-(results11.found)]).centroid),
	    		    					(Point)((results15.results[j-(results11.found + results12.found+results13.found+results14.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}

	    		    	}
	    		    	if (i >= (results11.found + results12.found) && i<(results11.found + results12.found + results13.found)) {
	    			    	if (j < results11.found) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results13.results[i-(results11.found + results12.found)]).centroid),
	    		    					(Point)((results11.results[j]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );

	    			    	}
	    			    	if (j >= results11.found && j<(results11.found + results12.found)) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results13.results[i-(results11.found + results12.found)]).centroid),
	    		    					(Point)((results12.results[j-results11.found]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	if (j >= (results11.found + results12.found) && j<(results11.found + results12.found + results13.found)) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results13.results[i-(results11.found + results12.found)]).centroid),
	    		    					(Point)((results13.results[j-(results11.found + results12.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	
	    			    	if (j >= (results11.found + results12.found+results13.found) && j<(results11.found + results12.found+results13.found+results14.found)) {
	    		    			route = client.route ((Point)((results13.results[i-(results11.found + results12.found)]).centroid),
	    		    					(Point)((results14.results[j-(results11.found + results12.found+results13.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	
	    			    	if (j>=(results11.found + results12.found+results13.found+results14.found)) {
	    		    			route = client.route ((Point)((results13.results[i-(results11.found + results12.found)]).centroid),
	    		    					(Point)((results15.results[j-(results11.found + results12.found+results13.found+results14.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}

	    		    	}
	    		    	
	    		    	if (i >= (results11.found + results12.found+results13.found) && i<(results11.found + results12.found+results13.found+results14.found)) {
	    		    		
	    			    	if (j < results11.found) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results14.results[i-(results11.found + results12.found+results13.found)]).centroid),
	    		    					(Point)((results11.results[j]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );

	    			    	}
	    			    	if (j >= results11.found && j<(results11.found + results12.found)) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results14.results[i-(results11.found + results12.found+results13.found)]).centroid),
	    		    					(Point)((results12.results[j-results11.found]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	if (j >= (results11.found + results12.found) && j<(results11.found + results12.found + results13.found)) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results14.results[i-(results11.found + results12.found+results13.found)]).centroid),
	    		    					(Point)((results13.results[j-(results11.found + results12.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	
	    			    	if (j >= (results11.found + results12.found+results13.found) && j<(results11.found + results12.found+results13.found+results14.found)) {
	    		    			route = client.route ((Point)((results14.results[i-(results11.found + results12.found+results13.found)]).centroid),
	    		    					(Point)((results14.results[j-(results11.found + results12.found+results13.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	
	    			    	if (j>=(results11.found + results12.found+results13.found+results14.found)) {
	    		    			route = client.route ((Point)((results14.results[i-(results11.found + results12.found+results13.found)]).centroid),
	    		    					(Point)((results15.results[j-(results11.found + results12.found+results13.found+results14.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}

	   
	    		    	}
	    		    	
	    		    	if (i>=(results11.found + results12.found+results13.found+results14.found)) {
	    		    		
	    			    	if (j < results11.found) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results15.results[i-(results11.found + results12.found+results13.found+results14.found)]).centroid),
	    		    					(Point)((results11.results[j]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );

	    			    	}
	    			    	if (j >= results11.found && j<(results11.found + results12.found)) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results15.results[i-(results11.found + results12.found+results13.found+results14.found)]).centroid),
	    		    					(Point)((results12.results[j-results11.found]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	if (j >= (results11.found + results12.found) && j<(results11.found + results12.found + results13.found)) {
	    			    		//System.out.println(i+"  "+j);
	    		    			route = client.route ((Point)((results15.results[i-(results11.found + results12.found+results13.found+results14.found)]).centroid),
	    		    					(Point)((results13.results[j-(results11.found + results12.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	
	    			    	if (j >= (results11.found + results12.found+results13.found) && j<(results11.found + results12.found+results13.found+results14.found)) {
	    		    			route = client.route ((Point)((results15.results[i-(results11.found + results12.found+results13.found+results14.found)]).centroid),
	    		    					(Point)((results14.results[j-(results11.found + results12.found+results13.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}
	    			    	
	    			    	if (j>=(results11.found + results12.found+results13.found+results14.found)) {
	    		    			route = client.route ((Point)((results15.results[i-(results11.found + results12.found+results13.found+results14.found)]).centroid),
	    		    					(Point)((results15.results[j-(results11.found + results12.found+results13.found+results14.found)]).centroid),
	    			                    RouteType.CAR,
	    			                    null,
	    			                    null,
	    			                    "en",
	    			                    MeasureUnit.KM
	    			                );
	    			    	}

	    		    		
	    		    	}

	    				
	    				
	    			/*Route route = client.route ((Point)((results.results[i]).centroid),
	    					(Point)((results.results[j]).centroid),
		                    RouteType.CAR,
		                    null,
		                    null,
		                    "en",
		                    MeasureUnit.KM
		                );*/
	    			System.out.println(route.summary.totalDistance);
	    			System.out.println(route.geometry.points);
	    			
	    		//	System.out.println((results.results[i]).centroid+"  "+(results.results[j]).centroid);
	    			GeoResults results1 = client.present_along_route("cafe", route.geometry.points, 20, 30);
	    			GeoResults results2 = client.present_along_route("pub", route.geometry.points, 20, 30);
	    			GeoResults results3 = client.present_along_route("hotel", route.geometry.points, 20, 30);
	    			GeoResults results4 = client.present_along_route("atm", route.geometry.points, 20, 30);
	    			GeoResults results5 = client.present_along_route("fuel", route.geometry.points, 20, 30);
	    			
	    			int []found_id = new int[results1.found+results2.found+results3.found+results4.found+results5.found];
	    			for (int p = 0; p<(results1.found+results2.found+results3.found+results4.found+results5.found); p++) {
	    		    	if (p < results1.found) {
	    		    		found_id[p] = results1.results[p].id;
	    		    	}
	    		    	if (p >= results1.found && p<(results1.found + results2.found)) {
	    		    		found_id[p] = results2.results[p-results1.found].id;
	    		    	}
	    		    	if (p >= (results1.found + results2.found) && p<(results1.found + results2.found + results3.found)) {
	    		    		found_id[p] = results3.results[p-(results1.found+results2.found)].id;
	    		    	}
	    		    	if (p >= (results1.found + results2.found + results3.found) && p<(results1.found + results2.found + results3.found + results4.found)) {
	    		    		found_id[p] = results4.results[p-(results1.found+results2.found + results3.found)].id;
	    		    	}
	    		    	if (p >= (results1.found + results2.found+results3.found + results4.found)) {
	    		    		found_id[p] = results5.results[p-(results1.found+results2.found+results3.found+results4.found)].id;
	    		    	}	    		    	
	    		    	
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
	    				System.out.println("Distance added "+(++counter1)+" for node "+i+" "+counter);
	    				++counter;
	    				System.out.println("counter now increased "+counter+"  "+array_dist.length);
	    				array_dist[counter] = (int)route.summary.totalDistance;
	    				
    			    	if (j < results11.found) {
    			    		//System.out.println(i+"  "+j);
    	    				array_id[counter] = (results11.results[j]).id;
    			    	}
    			    	if (j >= results11.found && j<(results11.found + results12.found)) {
    			    		//System.out.println(i+"  "+j);
    	    				array_id[counter] = (results12.results[j-(results11.found)]).id;
    			    	}
    			    	if (j >= (results11.found + results12.found) && j<(results11.found + results12.found+results13.found)) {
    			    		//System.out.println(i+"  "+j);
    			    		array_id[counter] = (results13.results[j-(results11.found+results12.found)]).id;
    			    	}
    			    	if (j >= (results11.found + results12.found+results13.found) && j<(results11.found + results12.found+results13.found+results14.found)) {
    			    		//System.out.println(i+"  "+j);
    			    		array_id[counter] = (results14.results[j-(results11.found+results12.found+results13.found)]).id;
    			    	}
    			    	if (j >= (results11.found + results12.found+results13.found + results14.found)) {
    			    		//System.out.println(i+"  "+j);
    			    		array_id[counter] = (results15.results[j-(results11.found+results12.found+results13.found + results14.found)]).id;
    			    	}
    			    	
    			    	
	    					    	
	    				//counter=counter+1;
	    				
	    			}
	    			}
	    		 } catch (Exception e) {
	    	           // e.printStackTrace();
	    				System.out.println(e);
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
	    		//double prob = Math.random();
	    		//out.write(Double.toString((Double)prob_table.get(array_id[k])));
	    		//out.write("\n");
	    	}
	    }
		
	    out.close();
	    	    
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("1. ID       2.Query");
	    int choice = Integer.parseInt(br.readLine());
	    int source_id = -1;
	    int dest_id = -1;
	    GeoResults source = null;
	    GeoResults dest = null;
	    GeoResult result_s = null;
	    GeoResult result_d = null;
	    Route route_s = null;
	    Route route_d = null;
	    if (choice == 2) {
	    	String query_s = br.readLine();
	    	String query_d = br.readLine();
	    
	    	source = client.find(query_s, 2, 0, null, true, true, true);
	    	result_s = source.results[0];
		
	    	dest = client.find(query_d, 2, 0, null, true, true, true);
	    	result_d = dest.results[0];
	    	
	    	source_id = result_s.id;
	    	dest_id = result_d.id;
	    }
	    else {
	    	source_id = Integer.parseInt(br.readLine());
	    	dest_id = Integer.parseInt(br.readLine());
	    }
	    
	    FileWriter fstream_o  = new FileWriter(folder+nodes+"/order_"+nodes+".txt");
	    BufferedWriter out_o = new BufferedWriter(fstream_o);
	    
	    
	    System.out.println("Enter the sequence separated by space");
	    String []seq = br.readLine().split(" ");
	    int source_type = Integer.parseInt(seq[0]);
	    int dest_type = Integer.parseInt(seq[seq.length-1]);
	    //int []order = new int[seq.length];
	    for (int i=0; i<seq.length; i++) {
	    	out_o.write(Integer.toString(Integer.parseInt(seq[i])));
	    	out_o.write("\n");
	    }
	    out_o.close();	
		
		/* Check if these ids are there in the graph or not*/
	    boolean temp_s = false;
	    boolean temp_d = false;
	    for (int i=0; i<result_id.length; i++) {
	    	if (result_id[i] == source_id) {
	    		temp_s = true;
	    	}
	    	if (result_id[i] == dest_id) {
	    		temp_d = true;
	    	}
	    }
	    
	    FileWriter fstream_s  = new FileWriter(folder+nodes+"/relation_s"+nodes+".txt");
	    BufferedWriter out_s = new BufferedWriter(fstream_s);

	    FileWriter fstream_d  = new FileWriter(folder+nodes+"/relation_d"+nodes+".txt");
	    BufferedWriter out_d = new BufferedWriter(fstream_d);	    
	    
	    FileWriter fstreamsd  = new FileWriter(folder+nodes+"/src_dest"+nodes+".txt");
	    BufferedWriter outsd = new BufferedWriter(fstreamsd);

	    
	    
	    /*TODO : for source and destination change the setting for 5 nodes!! */
	    
	    // FOR SOURCE
	    
	    if (temp_s == false) {
	    	/* Assume that it is the case only when the user enters the query. With only id we do not determine the 
	    	 * Georesults and calculate the new distances
	    	 */
	    	
	    	int []array_dist = new int[results11.found+results12.found+results13.found];
	    	int []array_id = new int[results11.found+results12.found+results13.found];
	    	int counter = -1;
	    	System.out.println("initialised");

	    	
	    	for (int j=0; j<(results11.found+results12.found+results13.found); j++) {
		    	if (j < results11.found && source_type==1) {
		    		//System.out.println(i+"  "+j);
	    			route_s = client.route ((Point)((result_s).centroid),
	    					(Point)((results11.results[j]).centroid),
		                    RouteType.CAR,
		                    null,
		                    null,
		                    "en",
		                    MeasureUnit.KM
		                );

		    	} else if (j >= results11.found && j<(results11.found + results12.found) && source_type==2) {
		    		//System.out.println(i+"  "+j);
	    			route_s = client.route ((Point)((result_s).centroid),
	    					(Point)((results12.results[j-(results11.found)]).centroid),
		                    RouteType.CAR,
		                    null,
		                    null,
		                    "en",
		                    MeasureUnit.KM
		                );
		    	}
		    	else if (j >= (results11.found + results12.found) && source_type==3) {
		    		//System.out.println(i+"  "+j);
	    			route_s = client.route ((Point)((result_s).centroid),
	    					(Point)((results13.results[j-(results11.found + results12.found)]).centroid),
		                    RouteType.CAR,
		                    null,
		                    null,
		                    "en",
		                    MeasureUnit.KM
		                );
		    	}
		    	else {
		    		continue;
		    	}
		    			    			    			    	
    			GeoResults results1 = client.present_along_route("cafe", route_s.geometry.points, 20, 30);
    			GeoResults results2 = client.present_along_route("pub", route_s.geometry.points, 20, 30);
    			GeoResults results3 = client.present_along_route("hotel", route_s.geometry.points, 20, 30);
    			
    			int []found_id = new int[results1.found+results2.found+results3.found];
    			for (int p = 0; p<(results1.found+results2.found+results3.found); p++) {
    		    	if (p < results1.found) {
    		    		found_id[p] = results1.results[p].id;
    		    	}
    		    	if (p >= results1.found && p<(results1.found + results2.found)) {
    		    		found_id[p] = results2.results[p-results1.found].id;
    		    	}
    		    	if (p >= (results1.found + results2.found)) {
    		    		found_id[p] = results3.results[p-(results1.found+results2.found)].id;
    		    	}
    			}
    			//result_id
    			int common_ids = among_POI(result_id, found_id);
    			if (common_ids<=2) { //Ignore only when the cafe belongs to the list of the cafes!
    				//System.out.println("Distance added "+(++counter1)+" for node "+i+" "+counter);
    				++counter;
    				System.out.println("counter now increased "+counter+" "+array_dist.length);
    				array_dist[counter] = (int)route_s.summary.totalDistance;
    				
			    	if (j < results11.found) {
			    		//System.out.println(i+"  "+j);
	    				array_id[counter] = (results11.results[j]).id;
			    	}
			    	if (j >= results11.found && j<(results11.found + results12.found)) {
			    		//System.out.println(i+"  "+j);
	    				array_id[counter] = (results12.results[j-(results11.found)]).id;
			    	}
			    	if (j >= (results11.found + results12.found)) {
			    		//System.out.println(i+"  "+j);
			    		array_id[counter] = (results13.results[j-(results11.found+results12.found)]).id;
			    	}

    				//counter=counter+1;    				
    			}
    			
    	    	sort(array_dist, array_id, counter);
    	    	

    		    //FileWriter fstream_s  = new FileWriter("relation_s.txt");
    		    //BufferedWriter out_s = new BufferedWriter(fstream_s);

    	    	
    	    	//out_s.write(Integer.toString(counter));
    	    	//out.write("\n");
    	    	for (int k=0; k<counter; k++) {
    	    		out_s.write(Integer.toString(array_id[k]));
    	    		out_s.write("\t");
    	    		out_s.write(Integer.toString(array_dist[k]));
    	    		out_s.write("\t");
    	    		out_s.write(Integer.toString(k));
    	    		//out_s.write("\t");
    	    		//double prob = Math.random();
    	    		//out.write(Double.toString(prob));
    	    		//out_s.write(Double.toString((Double)prob_table.get(array_id[k])));
    	    		out_s.write("\n");
    	    	}
    			
	    	}
	    		    	
	    }//if statement ends here
	    else {
	    	//meaning that the id is already present in the graph formed
    		out_s.write(Integer.toString(source_id));
    		outsd.write(Integer.toString(source_id));
    		outsd.write("\n");
    	}

	    
	    
	    //SIMILAR FOR DESTINATION
	    
	    

	    if (temp_d == false) {
	    	/* Assume that it is the case only when the user enters the query. With only id we do not determine the 
	    	 * Georesults and calculate the new distances
	    	 */
	    	
	    	int []array_dist = new int[results11.found+results12.found+results13.found];
	    	int []array_id = new int[results11.found+results12.found+results13.found];
	    	int counter = -1;
	    	System.out.println("initialised");

	    	
	    	for (int j=0; j<(results11.found+results12.found+results13.found); j++) {
		    	if (j < results11.found && dest_type == 1) {
		    		//System.out.println(i+"  "+j);
	    			route_d = client.route ((Point)((result_d).centroid),
	    					(Point)((results11.results[j]).centroid),
		                    RouteType.CAR,
		                    null,
		                    null,
		                    "en",
		                    MeasureUnit.KM
		                );

		    	}
		    	else if (j >= results11.found && j<(results11.found + results12.found) && dest_type == 2) {
		    		//System.out.println(i+"  "+j);
	    			route_d = client.route ((Point)((result_d).centroid),
	    					(Point)((results12.results[j-(results11.found)]).centroid),
		                    RouteType.CAR,
		                    null,
		                    null,
		                    "en",
		                    MeasureUnit.KM
		                );
		    	}
		    	else if (j >= (results11.found + results12.found) && dest_type == 3) {
		    		//System.out.println(i+"  "+j);
	    			route_d = client.route ((Point)((result_d).centroid),
	    					(Point)((results13.results[j-(results11.found + results12.found)]).centroid),
		                    RouteType.CAR,
		                    null,
		                    null,
		                    "en",
		                    MeasureUnit.KM
		                );
		    	}
		    	else {
		    		continue;
		    	}
		    			    			    			    	
    			GeoResults results1 = client.present_along_route("cafe", route_d.geometry.points, 20, 30);
    			GeoResults results2 = client.present_along_route("pub", route_d.geometry.points, 20, 30);
    			GeoResults results3 = client.present_along_route("hotel", route_d.geometry.points, 20, 30);
    			
    			int []found_id = new int[results1.found+results2.found+results3.found];
    			for (int p = 0; p<(results1.found+results2.found+results3.found); p++) {
    		    	if (p < results1.found) {
    		    		found_id[p] = results1.results[p].id;
    		    	}
    		    	if (p >= results1.found && p<(results1.found + results2.found)) {
    		    		found_id[p] = results2.results[p-results1.found].id;
    		    	}
    		    	if (p >= (results1.found + results2.found)) {
    		    		found_id[p] = results3.results[p-(results1.found+results2.found)].id;
    		    	}
    			}
    			//result_id
    			int common_ids = among_POI(result_id, found_id);
    			if (common_ids<=2) { //Ignore only when the cafe belongs to the list of the cafes!
    				//System.out.println("Distance added "+(++counter1)+" for node "+i+" "+counter);
    				++counter;
    				System.out.println("counter now increased "+counter);
    				array_dist[counter] = (int)route_d.summary.totalDistance;
    				
			    	if (j < results11.found) {
			    		//System.out.println(i+"  "+j);
	    				array_id[counter] = (results11.results[j]).id;
			    	}
			    	if (j >= results11.found && j<(results11.found + results12.found)) {
			    		//System.out.println(i+"  "+j);
	    				array_id[counter] = (results12.results[j-(results11.found)]).id;
			    	}
			    	if (j >= (results11.found + results12.found)) {
			    		//System.out.println(i+"  "+j);
			    		array_id[counter] = (results13.results[j-(results11.found+results12.found)]).id;
			    	}

    				//counter=counter+1;    				
    			}
    			
    	    	sort(array_dist, array_id, counter);
    	    	

    		    //FileWriter fstream_s  = new FileWriter("relation_s.txt");
    		    //BufferedWriter out_s = new BufferedWriter(fstream_s);

    	    	
    	    	//out_s.write(Integer.toString(counter));
    	    	//out.write("\n");
    	    	for (int k=0; k<counter; k++) {
    	    		out_d.write(Integer.toString(array_id[k]));
    	    		out_d.write("\t");
    	    		out_d.write(Integer.toString(array_dist[k]));
    	    		//out_d.write("\t");
    	    		//double prob = Math.random();
    	    		//out.write(Double.toString(prob));
    	    		//out_d.write(Double.toString((Double)prob_table.get(array_id[k])));
    	    		out_d.write("\n");	
    	    	}
    			
	    	}
	    		    	
	    }//if statement ends here
	    else {
	    	//meaning that the id is already present in the graph formed
    		out_d.write(Integer.toString(dest_id));	
    		outsd.write(Integer.toString(dest_id));
    	}
	    
	    out_s.close();
	    out_d.close();	    
	    outsd.close();
	    	    
	} catch(Exception e) {
	    e.printStackTrace();
	}
    }
}