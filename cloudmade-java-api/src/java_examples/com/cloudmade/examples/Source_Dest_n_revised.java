package com.cloudmade.examples;

import java.io.*;

import com.cloudmade.api.CMClient;
import com.cloudmade.api.CMClient.MeasureUnit;
import com.cloudmade.api.CMClient.RouteType;
import com.cloudmade.api.geocoding.GeoResult;
import com.cloudmade.api.geocoding.GeoResults;
import com.cloudmade.api.geometry.Point;
import com.cloudmade.api.routing.Route;

/*
 * [51.51384,-0.14206, 51.51376,-0.14247, 51.51361,-0.14312, 51.51358,-0.1433, 51.5136,-0.14341, 
 * 51.51371,-0.14333, 51.51377,-0.14329, 51.51386,-0.14328, 51.51432,-0.14352, 51.51434,-0.14366, 
 * 51.51429,-0.144, 51.51496,-0.14427, 51.51603,-0.14477, 51.51602,-0.14486, 51.51601,-0.14496, 
 * 51.51601,-0.14533, 51.51604,-0.14545, 51.51608,-0.14553, 51.51612,-0.14558, 51.51617,-0.14561, 
 * 51.51633,-0.1457, 51.51667,-0.14588, 51.51684,-0.14596, 51.51696,-0.14525, 51.517,-0.14499, 51.51707,-0.14458,
 *  51.5171,-0.14444, 51.51735,-0.14293, 51.51737,-0.14282, 51.51694,-0.14259, 51.51696,-0.14246]




2-->
51.51737
-0.14282
51.51361
-0.14312

3-->
51.51608
-0.14553
51.51361
-0.14312

4-->
51.51377
-0.14329
51.51361
-0.14312

5-->


 */




/**REVISED
 * 
 * 
Start : 16250137  51.51487, -0.14081
End : 14579476    51.5113, -0.14262

1.
51.51487
-0.14081
51.51361
-0.14312

mm.
51.51402
-0.14032
51.51361
-0.14312

q.
51.51464
-0.14090
51.51361
-0.14312

p.
51.51449
-0.14096
51.51361
-0.14312

k.
51.51449
-0.14146
51.51361
-0.14312


l-2.
51.51467
-0.14081
51.51361
-0.14312

l-1.
51.51439
-0.14166
51.51361
-0.14312

l.
51.51397
-0.14144
51.51361
-0.14312


k-3.
51.5136
-0.14120
51.51361
-0.14312

k-2.
51.5135
-0.14090
51.51361
-0.14312

k-1.
51.5137
-0.14125
51.51361
-0.14312

k.
51.51305
-0.14068
51.51361
-0.14312


2.
51.5113
-0.14262
51.51361
-0.14312

 * **/


/**
 * 
 * 
51.51609
-0.1425
51.51361
-0.14312

51.51397
-0.14144
51.51361
-0.14312

51.51490
-0.14190
51.51361
-0.14312

51.51485
-0.14190
51.51361
-0.14312



51.51464
-0.14181
51.51361
-0.14312

51.51527
-0.14191
51.51361
-0.14312

51.5113
-0.14262
51.51361
-0.14312

51.51305
-0.14068
51.51361
-0.14312

 */

/*DESTINATION : 
 * 
 * PATH : 
 * Route : 51.51579,-0.14063| 51.51588,-0.14007| 51.51589,-0.14001| 51.51595,-0.1396| 51.51598,-0.13951| 51.51607,-0.13949| 51.51637,-0.13961| 
 * 51.5164,-0.13964| 51.51678,-0.1398| 51.51691,-0.13984| 51.51676,-0.14069| 51.51668,-0.1412| 51.51613,-0.14097| 51.51575,-0.14083| 51.51546,-0.14073|
 *  51.51541,-0.14071| 51.5154,-0.14088| 51.51534,-0.14136| 51.51534,-0.14138
 * 
 * 
 1.
51.51361
-0.14312
51.51691
-0.13984
 
2.
51.51361
-0.14312
51.51676
-0.14069 
 
3&4.

3.
51.51361
-0.14312
51.51579
-0.14063 

4.
51.51361
-0.14312
51.51589
-0.14001

5.
51.51361
-0.14312
51.51588
-0.14007
 
 
 Between 3 and 5
 
1. 51.51582,-0.14063
2. 51.51587,-0.14050
3. 51.51587,-0.14040
4. 51.51587,-0.14030
5. 51.51587,-0.14020
6. 51.51586,-0.14015

1.
51.51361
-0.14312
51.51582
-0.14063

2.
51.51361
-0.14312
51.51587
-0.14050

k-3.

51.51361
-0.14312
51.51583
-0.14060

k-2.

51.51361
-0.14312
51.51582
-0.14061


k-1.

51.51361
-0.14312
51.51583
-0.14058

k.
51.51361
-0.14312
51.51585
-0.14055

3.
51.51361
-0.14312
51.51587
-0.14040

n.
51.51361
-0.14312
51.51586
-0.14015

 
 
 * 
 */


class Source_Dest_n_revised {
	
	static int nodes = 10;
	static String folder = "Sampletest_revised";
	
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
		
		/*Read in result_id from the relation_node file*/
		
		CMClient client = new CMClient("5c48343660fe4aa983946d44003b075f");
		
		try {
		
			
		FileWriter fstream_1  = new FileWriter(folder+nodes+"/lat_long"+nodes+".txt");
		BufferedWriter out_1 = new BufferedWriter(fstream_1);

		
		GeoResults results11 = client.find_range("cafe", new Point(51.51558,-0.141449), nodes, 5000);
	    GeoResults results12 = client.find_range("pub", new Point(51.51558,-0.141449), nodes, 5000);
	    GeoResults results13 = client.find_range("hotel", new Point(51.51558,-0.141449), nodes, 5000);

	    int []result_id = new int[results11.found+results12.found+results13.found];
		GeoResult result = null;

	    for (int i=0; i<(results11.found+results12.found+results13.found); i++) {
	    	
	    	if (i < results11.found) {
		    	result = results11.results[i];		    	
	    	}
	    	if (i >= results11.found && i<(results11.found + results12.found)) {
		    	result = results12.results[i-results11.found];
	    	}
	    	if (i >= (results11.found + results12.found)) {
		    	result = results13.results[i-(results11.found + results12.found)];
	    	}
	    	result_id[i] =  result.id;
	    	float latit = ((Point)result.centroid).lat;
	    	float longit = ((Point)result.centroid).lon;
	    	String s = latit +", "+longit;
	    	out_1.write(String.valueOf(result_id[i]));
	    	out_1.write("\t");
	    	out_1.write(s);
	    	out_1.write("\n");
	    }
		
	    out_1.close();
	    
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("1. ID       2.Query		3. Point");
	    int choice = Integer.parseInt(br.readLine());
	    int source_id = -1;
	    int dest_id = -1;
	    GeoResults source = null;
	    GeoResults dest = null;
	    GeoResult result_s = null;
	    GeoResult result_d = null;
	    Route route_s = null;
	    Route route_d = null;
	    Point point_s = null;
	    Point point_d = null;

		FileWriter fstream_s1  = new FileWriter(folder+nodes+"/lat_long_s"+nodes+".txt");
		BufferedWriter out_s1 = new BufferedWriter(fstream_s1);

		FileWriter fstream_d1  = new FileWriter(folder+nodes+"/lat_long_d"+nodes+".txt");
		BufferedWriter out_d1 = new BufferedWriter(fstream_d1);

		
	    if (choice == 3) {
	    	float lat_s = Float.parseFloat(br.readLine());
	    	float long_s = Float.parseFloat(br.readLine());
	    	float lat_d = Float.parseFloat(br.readLine());
	    	float long_d = Float.parseFloat(br.readLine());
	    	point_s = new Point(lat_s, long_s);
	    	point_d = new Point(lat_d, long_d);
	    	
	    	out_s1.write(lat_s+", "+long_s);
	    	out_d1.write(lat_d+", "+long_d);
	    }
	    else if (choice == 2) {
	    	String query_s = br.readLine();
	    	String query_d = br.readLine();
	    
	    	source = client.find(query_s, 2, 0, null, true, true, true);
	    	result_s = source.results[0];
		
	    	dest = client.find(query_d, 2, 0, null, true, true, true);
	    	result_d = dest.results[0];
	    	
	    	source_id = result_s.id;
	    	dest_id = result_d.id;
	    	
	    	out_s1.write(((Point)result_s.centroid).lat+", "+((Point)result_s.centroid).lon);
	    	out_s1.write(((Point)result_s.centroid).lat+", "+((Point)result_s.centroid).lon);
	    	
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
               
                if (choice == 2) {

                if (j < results11.found) {
                    //System.out.println(i+"  "+j);
                    route_s = client.route ((Point)((result_s).centroid),
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
                    route_s = client.route ((Point)((result_s).centroid),
                            (Point)((results12.results[j-(results11.found)]).centroid),
                            RouteType.CAR,
                            null,
                            null,
                            "en",
                            MeasureUnit.KM
                        );
                }
                if (j >= (results11.found + results12.found)) {
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
                }
               
                else if(choice == 3) {
                	
                //    System.out.println("Choice 3");
                    if (j < results11.found) {
                    	if (point_s.lat!=((Point)((results11.results[j]).centroid)).lat 
                    			&& point_s.lon!=((Point)((results11.results[j]).centroid)).lon) {

                            System.out.println(point_s.lat+"  "+point_s.lon+"  "+(results11.results[j]).centroid);
                            route_s = client.route (point_s,
                                    (Point)((results11.results[j]).centroid),
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
                    }
                    	else if (j >= results11.found && j<(results11.found + results12.found)) {
                            //System.out.println(i+"  "+j);
                        	if (point_s.lat!=((Point)((results12.results[j-(results11.found)]).centroid)).lat 
                        			&& point_s.lon!=((Point)((results12.results[j-(results11.found)]).centroid)).lon) {

                            route_s = client.route (point_s,
                                    (Point)((results12.results[j-(results11.found)]).centroid),
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
                    	}
                    
                        else if (j >= (results11.found + results12.found)) {
                            //System.out.println(i+"  "+j);
                        	if (point_s.lat!=((Point)((results13.results[j-(results11.found+results12.found)]).centroid)).lat 
                        			&& point_s.lon!=((Point)((results13.results[j-(results11.found+results12.found)]).centroid)).lon) {

                            route_s = client.route (point_s,
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
                        }
                        else {
                            continue;
                        }
                }
                                                               
                GeoResults results1 = client.present_along_route("cafe", route_s.geometry.points, 20, 30);
                GeoResults results2 = client.present_along_route("pub", route_s.geometry.points, 20, 30);
                GeoResults results3 = client.present_along_route("hotel", route_s.geometry.points, 20, 30);
               
               
                int []found_id1 = new int[results1.found];
                int []found_id2 = new int[results2.found];
                int []found_id3 = new int[results3.found];
                for (int p = 0; p<(results1.found+results2.found+results3.found); p++) {
                    if (p < results1.found) {
                        //found_id[p] = results1.results[p].id;
                        found_id1[p] = results1.results[p].id;
                    }
                    if (p >= results1.found && p<(results1.found + results2.found)) {
                        //found_id[p] = results2.results[p-results1.found].id;
                        found_id2[p-results1.found] = results2.results[p-results1.found].id;
                    }
                    if (p >= (results1.found + results2.found)) {
                        //found_id[p] = results3.results[p-(results1.found+results2.found)].id;
                        found_id3[p-((results1.found+results2.found))] = results3.results[p-(results1.found+results2.found)].id;
                    }
                }
                //result_id
                int common_ids1 = among_POI(result_id, found_id1);
                int common_ids2 = among_POI(result_id, found_id2);
                int common_ids3 = among_POI(result_id, found_id3);
               
/*                if (j < results11.found) {
                    if (common_ids1<=2) { //Ignore only when the cafe belongs to the list of the cafes!
                        //System.out.println("Distance added "+(++counter1)+" for node "+i+" "+counter);
                        ++counter;
                        System.out.println("counter now increased "+counter);
                        array_dist[counter] = (int)route_s.summary.totalDistance;
                        array_id[counter] = (results11.results[j]).id;
                }
               
                    if (j >= results11.found && j<(results11.found + results12.found)) {
                        if (common_ids2<=2) { //Ignore only when the cafe belongs to the list of the cafes!
                            //System.out.println("Distance added "+(++counter1)+" for node "+i+" "+counter);
                            ++counter;
                            System.out.println("counter now increased "+counter);
                            array_dist[counter] = (int)route_s.summary.totalDistance;
                            //array_id[counter] = (results11.results[j]).id;
                            array_id[counter] = (results12.results[j-(results11.found)]).id;
                        }
                    }
                    if (j >= (results11.found + results12.found)) {
                        if (common_ids3<=2) { //Ignore only when the cafe belongs to the list of the cafes!
                            //System.out.println("Distance added "+(++counter1)+" for node "+i+" "+counter);
                            ++counter;
                            System.out.println("counter now increased "+counter);
                            array_dist[counter] = (int)route_s.summary.totalDistance;
                            //array_id[counter] = (results11.results[j]).id;
                            array_id[counter] = (results13.results[j-(results11.found+results12.found)]).id;
                        }
                       
                    }
                   
                       
                    }
  */                 
               
                if (j < results11.found) {
                		System.out.println("Type1");
                        if (common_ids1 <= 1) {
                            ++counter;
                            
                            System.out.println("counter now increased "+counter);
                            array_dist[counter] = (int)route_s.summary.totalDistance;
                            array_id[counter] = (results11.results[j]).id;      
                           
                        }                   
                }
               
                    if (j >= results11.found && j<(results11.found + results12.found)) {
                       
                            if (common_ids2 <= 1) {
                                ++counter;
                        //        System.out.println("counter now increased "+counter);
                                array_dist[counter] = (int)route_s.summary.totalDistance;
                                array_id[counter] = (results12.results[j-(results11.found)]).id;                                                               
                            }
                        }

                    if (j >= (results11.found + results12.found)) {
                            if (common_ids3 <= 1) {
                                ++counter;
                            //    System.out.println("counter now increased "+counter);
                                array_dist[counter] = (int)route_s.summary.totalDistance;
                                array_id[counter] = (results13.results[j-(results11.found+results12.found)]).id;                                                               
                            }                                                       
                    }
                   

                               
  /*              if (common_ids<=2) { //Ignore only when the cafe belongs to the list of the cafes!
                    //System.out.println("Distance added "+(++counter1)+" for node "+i+" "+counter);
                    ++counter;
                    System.out.println("counter now increased "+counter);
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
                }*/

               
            /*    int []found_id = new int[results1.found+results2.found+results3.found];
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
                }*/
            }
                sort(array_dist, array_id, counter);
               
            //    System.out.println(" Counter source "+counter);
                //FileWriter fstream_s  = new FileWriter("relation_s.txt");
                //BufferedWriter out_s = new BufferedWriter(fstream_s);

               
                //out_s.write(Integer.toString(counter));
                //out.write("\n");
                GeoResult result_s1 = client.findClosest("cafe", point_s);
                if (((Point)result_s1.centroid).lat == point_s.lat && ((Point)result_s1.centroid).lon == point_s.lon) {
                    out_s.write(Integer.toString(result_s1.id));
                    out_s.write("\t");
                    out_s.write(Integer.toString(0));
                    out_s.write("\n");
                }
                	
                
                result_s1 = client.findClosest("pub", point_s);
                if (((Point)result_s1.centroid).lat == point_s.lat && ((Point)result_s1.centroid).lon == point_s.lon) {
                    out_s.write(Integer.toString(result_s1.id));
                    out_s.write("\t");
                    out_s.write(Integer.toString(0));
                    out_s.write("\n");
                }
                
                result_s1 = client.findClosest("hotel", point_s);
                if (((Point)result_s1.centroid).lat == point_s.lat && ((Point)result_s1.centroid).lon == point_s.lon) {
                    out_s.write(Integer.toString(result_s1.id));
                    out_s.write("\t");
                    out_s.write(Integer.toString(0));
                    out_s.write("\n");
                }
                
                for (int k=0; k<counter; k++) {
//                    System.out.println("Writing------------");
                    out_s.write(Integer.toString(array_id[k]));
                    out_s.write("\t");
                    out_s.write(Integer.toString(array_dist[k]));
                    //out_s.write("\t");
                    //double prob = Math.random();
                    //out.write(Double.toString(prob));
                    //out_s.write(Double.toString((Double)prob_table.get(array_id[k])));
                    out_s.write("\n");
                }
               
           
                       
        }//if statement ends here
        else {
            //meaning that the id is already present in the graph formed
            out_s.write(Integer.toString(source_id));   
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
                
                if (choice == 2) {
                if (j < results11.found) {
                    //System.out.println(i+"  "+j);
                    route_d = client.route (
                            (Point)((results11.results[j]).centroid),(Point)((result_d).centroid),
                            RouteType.CAR,
                            null,
                            null,
                            "en",
                            MeasureUnit.KM
                        );

                }
                if (j >= results11.found && j<(results11.found + results12.found)) {
                    //System.out.println(i+"  "+j);
                    route_d = client.route (
                            (Point)((results12.results[j-(results11.found)]).centroid),(Point)((result_d).centroid),
                            RouteType.CAR,
                            null,
                            null,
                            "en",
                            MeasureUnit.KM
                        );
                }
                if (j >= (results11.found + results12.found)) {
                    //System.out.println(i+"  "+j);
                    route_d = client.route (
                            (Point)((results13.results[j-(results11.found + results12.found)]).centroid),(Point)((result_d).centroid),
                            RouteType.CAR,
                            null,
                            null,
                            "en",
                            MeasureUnit.KM
                        );
                }
                }
                
                else if(choice == 3) {
                    if (j < results11.found) {
                    	if (point_d.lat!=((Point)((results11.results[j]).centroid)).lat 
                    			&& point_d.lon!=((Point)((results11.results[j]).centroid)).lon) {

                           // System.out.println(point_s.lat+"  "+point_s.lon+"  "+(results11.results[j]).centroid);
                            route_d = client.route (
                                    (Point)((results11.results[j]).centroid), point_d,
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
                    }
                    	else if (j >= results11.found && j<(results11.found + results12.found)) {
                            //System.out.println(i+"  "+j);
                        	if (point_d.lat!=((Point)((results12.results[j-(results11.found)]).centroid)).lat 
                        			&& point_d.lon!=((Point)((results12.results[j-(results11.found)]).centroid)).lon) {

                            route_d = client.route (
                                    (Point)((results12.results[j-(results11.found)]).centroid),point_d,
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
                    	}
                    
                        else if (j >= (results11.found + results12.found)) {
                            //System.out.println(i+"  "+j);
                        	if (point_d.lat!=((Point)((results13.results[j-(results11.found+results12.found)]).centroid)).lat 
                        			&& point_d.lon!=((Point)((results13.results[j-(results11.found+results12.found)]).centroid)).lon) {

                            route_d = client.route (
                                    (Point)((results13.results[j-(results11.found + results12.found)]).centroid), point_d,
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
                        }
                        else {
                            continue;
                        }
                	
                	
                }
                
                
                /*
                 * 
                 *                 	if (point_s.lat!=((Point)((results11.results[j]).centroid)).lat 
                			&& point_s.lon!=((Point)((results11.results[j]).centroid)).lon) {
                    
                    if (j < results11.found) {
                            //System.out.println(i+"  "+j);
                            route_d = client.route (
                                    (Point)((results11.results[j]).centroid),point_d,
                                    RouteType.CAR,
                                    null,
                                    null,
                                    "en",
                                    MeasureUnit.KM
                                );

                        } else if (j >= results11.found && j<(results11.found + results12.found)) {
                            //System.out.println(i+"  "+j);
                            route_d = client.route (
                                    (Point)((results12.results[j-(results11.found)]).centroid),point_d,
                                    RouteType.CAR,
                                    null,
                                    null,
                                    "en",
                                    MeasureUnit.KM
                                );
                        }
                        else if (j >= (results11.found + results12.found)) {
                            //System.out.println(i+"  "+j);
                            route_d = client.route (
                                    (Point)((results13.results[j-(results11.found + results12.found)]).centroid),point_d,
                                    RouteType.CAR,
                                    null,
                                    null,
                                    "en",
                                    MeasureUnit.KM
                                );
                        }
                    }
                	else {
                		continue;
                	}

                 * 
                 */

                                                                
                GeoResults results1 = client.present_along_route("cafe", route_d.geometry.points, 20, 30);
                GeoResults results2 = client.present_along_route("pub", route_d.geometry.points, 20, 30);
                GeoResults results3 = client.present_along_route("hotel", route_d.geometry.points, 20, 30);
                
                
                
                int []found_id1 = new int[results1.found];
                int []found_id2 = new int[results2.found];
                int []found_id3 = new int[results3.found];
                for (int p = 0; p<(results1.found+results2.found+results3.found); p++) {
                    if (p < results1.found) {
                        //found_id[p] = results1.results[p].id;
                        found_id1[p] = results1.results[p].id;
                    }
                    if (p >= results1.found && p<(results1.found + results2.found)) {
                        //found_id[p] = results2.results[p-results1.found].id;
                        found_id2[p-results1.found] = results2.results[p-results1.found].id;
                    }
                    if (p >= (results1.found + results2.found)) {
                        //found_id[p] = results3.results[p-(results1.found+results2.found)].id;
                        found_id3[p-((results1.found+results2.found))] = results3.results[p-(results1.found+results2.found)].id;
                    }
                }
                //result_id
                int common_ids1 = among_POI(result_id, found_id1);
                int common_ids2 = among_POI(result_id, found_id2);
                int common_ids3 = among_POI(result_id, found_id3);
                
                
                if (j < results11.found) {
                    System.out.println("Dest "+common_ids1+"  Type 1 "+j);
                   // if (common_ids1<=1) { //Ignore only when the cafe belongs to the list of the cafes!
                        //System.out.println("Distance added "+(++counter1)+" for node "+i+" "+counter);
                        ++counter;
                    //    System.out.println("counter now increased "+counter);
                        array_dist[counter] = (int)route_d.summary.totalDistance;
                        array_id[counter] = (results11.results[j]).id;
               // }
                }
                
                    if (j >= results11.found && j<(results11.found + results12.found)) {
                        System.out.println("Dest "+common_ids2+"  Type 2 "+j);
                 //       if (common_ids2<=1) { //Ignore only when the cafe belongs to the list of the cafes!
                            //System.out.println("Distance added "+(++counter1)+" for node "+i+" "+counter);
                            ++counter;
                        //    System.out.println("counter now increased "+counter);
                            array_dist[counter] = (int)route_d.summary.totalDistance;
                            //array_id[counter] = (results11.results[j]).id;
                            array_id[counter] = (results12.results[j-(results11.found)]).id;
                  //      }
                    }
                    if (j >= (results11.found + results12.found)) {
                        System.out.println("Dest "+common_ids3+"  Type 3 "+j);
                    //    if (common_ids3<=1) { //Ignore only when the cafe belongs to the list of the cafes!
                            //System.out.println("Distance added "+(++counter1)+" for node "+i+" "+counter);
                            ++counter;
                        //    System.out.println("counter now increased "+counter);
                            array_dist[counter] = (int)route_d.summary.totalDistance;
                            //array_id[counter] = (results11.results[j]).id;
                            array_id[counter] = (results13.results[j-(results11.found+results12.found)]).id;
                 //       }
                        
                    }                        
                }
                    
                                
  /*              if (common_ids<=2) { //Ignore only when the cafe belongs to the list of the cafes!
                    //System.out.println("Distance added "+(++counter1)+" for node "+i+" "+counter);
                    ++counter;
                    System.out.println("counter now increased "+counter);
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
                }*/

                
                
                
                
/*                int []found_id = new int[results1.found+results2.found+results3.found];
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
                }*/
                
                sort(array_dist, array_id, counter);
            //    System.out.println(" Counter destination "+counter);


                //FileWriter fstream_s  = new FileWriter("relation_s.txt");
                //BufferedWriter out_s = new BufferedWriter(fstream_s);

                
                //out_s.write(Integer.toString(counter));
                //out.write("\n");
                
                
                GeoResult result_d1 = client.findClosest("cafe", point_d);
                if (((Point)result_d1.centroid).lat == point_d.lat && ((Point)result_d1.centroid).lon == point_d.lon) {
                    out_d.write(Integer.toString(result_d1.id));
                    out_d.write("\t");
                    out_d.write(Integer.toString(0));
                    out_d.write("\n");
                }
                	
                
                result_d1 = client.findClosest("pub", point_d);
                if (((Point)result_d1.centroid).lat == point_d.lat && ((Point)result_d1.centroid).lon == point_d.lon) {
                    out_d.write(Integer.toString(result_d1.id));
                    out_d.write("\t");
                    out_d.write(Integer.toString(0));
                    out_d.write("\n");
                }
                
                result_d1 = client.findClosest("hotel", point_d);
                if (((Point)result_d1.centroid).lat == point_d.lat && ((Point)result_d1.centroid).lon == point_d.lon) {
                    out_d.write(Integer.toString(result_d1.id));
                    out_d.write("\t");
                    out_d.write(Integer.toString(0));
                    out_d.write("\n");
                }
                                
                
                for (int k=0; k<counter; k++) {
                    System.out.println("Writing to dest file");
                    out_d.write(Integer.toString(array_id[k]));
                    out_d.write("\t");
                    out_d.write(Integer.toString(array_dist[k]));
                    //out_d.write("\t");
                    //double prob = Math.random();
                    //out.write(Double.toString(prob));
                    //out_d.write(Double.toString((Double)prob_table.get(array_id[k])));
                    out_d.write("\n");
                }
                
            
                        
        }//if statement ends here
        else {
            //meaning that the id is already present in the graph formed
        	
        	
        	//HERE THERE IS AN ERROR AS LESS NUMBER OF PATHS ARE READ! So instead of ids
        	//let us always pass the latitude and longitude.
        	
            out_d.write(Integer.toString(dest_id));    
            System.out.println("SEQUENCE ");
        }
        
	    


	    out_s.close();
	    out_d.close();

	    out_s1.close();
	    out_d1.close();
	    
		}
		catch(Exception e) {
		    e.printStackTrace();
		}
		
	}
}






/*
 * Route instances now : Start : 51.51589, -0.14068  End : 51.51671, -0.14134 
 * [51.51579,-0.14063, 51.51588,-0.14007, 51.51589,-0.14001, 51.51595,-0.1396, 51.51598,-0.13951, 
 * 51.51607,-0.13949, 51.51637,-0.13961, 51.5164,-0.13964, 51.51678,-0.1398, 51.51691,-0.13984, 
 * 51.51676,-0.14069, 51.51668,-0.1412, 51.51666,-0.14132]
 
 51.51579,-0.14063| 51.51588,-0.14007| 51.51589,-0.14001| 51.51595,-0.1396| 51.51598,-0.13951| 
 51.51607,-0.13949| 51.51637,-0.13961| 51.5164,-0.13964| 51.51678,-0.1398| 51.51691,-0.13984| 
 51.51676,-0.14069| 51.51668,-0.1412| 51.51666,-0.14132
 
 Reverse : 51.51666,-0.14132| 51.51668,-0.1412| 51.51613,-0.14097| 51.51575,-0.14083| 51.51579,-0.14063
 
51.51589
-0.14068
51.51361
-0.14312
 
51.51671
-0.14134 
51.51361
-0.14312
 
1: 
51.51579
-0.14063
51.51361
-0.14312

2:
51.51588
-0.14007
51.51361
-0.14312

3:
51.51589
-0.14001
51.51361
-0.14312

4:
51.51598
-0.13951
51.51361
-0.14312

5.
51.51607
-0.13949
51.51361
-0.14312
 
6.
51.51637
-0.13961
51.51361
-0.14312

7.
51.5164
-0.13964
51.51361
-0.14312
 
8.
51.51678
-0.1398
51.51361
-0.14312
 
9.
51.51691
-0.13984
51.51361
-0.14312

10.
51.51676
-0.14069
51.51361
-0.14312
 
11.
51.51668
-0.1412
51.51361
-0.14312

12.
51.51666
-0.14132
51.51361
-0.14312
 
13.
51.51671
-0.14134
51.51361
-0.14312

Between the two points
14.
51.51613
-0.14097
51.51361
-0.14312

14.
51.51623
-0.14097
51.51361
-0.14312

15.
51.51633
-0.14097
51.51361
-0.14312
 
16.
51.51619
-0.14097
51.51361
-0.14312

17.
51.51621
-0.14097
51.51361
-0.14312
 
18.
51.51622
-0.14097
51.51361
-0.14312

 19.
51.516225
-0.14097
51.51361
-0.14312

20.
51.516228
-0.14097
51.51361
-0.14312
 
 */

/*
 *   Start : 51.51671, -0.14134 (16272469) End : 51.51589, -0.14068 
 * 
 *  Reverse : 51.51666,-0.14132| 51.51668,-0.1412| 51.51613,-0.14097| 51.51575,-0.14083| 51.51579,-0.14063
 *  
 1.
51.51666
-0.14132


n-3.
51.51668
-0.1412
51.51361
-0.14312


n-2.
51.51613
-0.14097
51.51361
-0.14312

n-1.
51.51575
-0.14083
51.51361
-0.14312
 
n.
51.51579
-0.14063
51.51361
-0.14312
 
 */


/*
 * FOR DIFFERENT DESTINATIONS.
 * 
 	
 Start : 51.51671, -0.14134 (16272469) End : 51.51589, -0.14068
 1.
51.51361
-0.14312
51.51666
-0.14132
 

n.
51.51361
-0.14312
51.51579
-0.14063
 
 * 
 */
