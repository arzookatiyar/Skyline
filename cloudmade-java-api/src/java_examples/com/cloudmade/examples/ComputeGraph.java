package com.cloudmade.examples;

/*
 * Example usage of CloudMade's API
 */

import com.cloudmade.api.CMClient;
import com.cloudmade.api.CMClient.MeasureUnit;
import com.cloudmade.api.CMClient.RouteType;
import com.cloudmade.api.geometry.Point;
import com.cloudmade.api.geometry.Line;
import com.cloudmade.api.routing.Route;
import com.cloudmade.api.routing.RouteNotFoundException;
import java.io.*;
import java.util.ArrayList;

class ComputeGraph {
	
	public static void main(String args[]) throws IOException {
		
        CMClient client = new CMClient("BC9A493B41014CAABB98F0471D759707");
		String []colors = {"red", "blue", "green", "black", "grey", "orange"};
    	FileInputStream stream2 = new FileInputStream("result_lat_long10.txt");
    	DataInputStream in2 = new DataInputStream(stream2);
    	BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));

    	String source = br2.readLine();
    	String destination = br2.readLine();
    	String line;
    	String global_result = "http://staticmaps.cloudmade.com/8ee2a50541944fb9bcedded5165f09d9/staticmap?styleid=1&size=1024x600";
    	
    	String result = "a";
    	String first;
    	float prev_lat=0;
    	float prev_lon=0;
    	Point prev_point=null;
    	char label = 65;
    	int counter = 0;
    	String markers = "&marker=size:big|label:A|";
    	while ((line=br2.readLine())!=null) {
    		if (line.equals("NEW")) { 
    			System.out.println(
    			 "http://staticmaps.cloudmade.com/8ee2a50541944fb9bcedded5165f09d9/staticmap?size=1024x600&path=color:blue|weight:5|"
    			+result.substring(0, result.length()-1)+markers);
    			//+"&marker=size:big|label:Y|"
        		//		+source+"&marker=size:big|label:Z|"+destination+markers);
    			if (!result.equals("a")) {
    				global_result = global_result + "&path=color:"+colors[counter]+"|weight:5|"+result.substring(0, result.length()-1)+markers;
    				++counter;
    			}
    			result = "";
    			markers = "";
    			line = br2.readLine();
    			label = 65;
    			markers = markers+"&marker=size:mid|label:"+label+"|"+line;
    			String []split_string = line.split(", ");
    			prev_lat = Float.parseFloat(split_string[0]);
    			prev_lon = Float.parseFloat(split_string[1]);
    			prev_point = new Point(prev_lat, prev_lon);
    		}
    		else {
    			label ++;
    			markers = markers+"&marker=size:mid|label:"+label+"|"+line;
    			String []split_string = line.split(", ");
    			float lat = Float.parseFloat(split_string[0]);
    			float lon = Float.parseFloat(split_string[1]);
    			System.out.println("now : "+lat+", "+lon+"  then : "+prev_lat+" "+prev_lon);
    			Point point = new Point(lat, lon);
    			Route route = null;
    	        try {
    	            route = client.route(
    	                prev_point,
    	                point,
    	                RouteType.CAR,
    	                null,
    	                null,
    	                "en",
    	                MeasureUnit.KM
    	            );

    	        } catch (RouteNotFoundException e) {
    	            e.printStackTrace();
    	        }
    	        ArrayList<Point> list = (ArrayList)((Line)route.geometry).points;
    	        for (int i=0; i<list.size(); i++) {
    	        	result = result + ((Point)list.get(i)).lat+", "+((Point)list.get(i)).lon+"|";
    	        }
    			prev_lat = lat;
    			prev_lon = lon;
    			prev_point = point;
    		}
    	}
		System.out.println(
   			 "http://staticmaps.cloudmade.com/8ee2a50541944fb9bcedded5165f09d9/staticmap?styleid=1&size=1024x600&path=color:blue|weight:5|"
   			+result.substring(0, result.length()-1)+markers);
		global_result = global_result + "&path=color:"+colors[counter]+"|weight:5|"+result.substring(0, result.length()-1)+markers;
		
		System.out.println(global_result);
    	br2.close();
	}
}