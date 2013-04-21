import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Hashtable;

class Aggregated_Skyline {
	static String folder = "Temp10/";
	static int number = 10;
	public static void printPath_list(ArrayList<ArrayList<Integer>> path_list) {
		System.out.println("Printing path list.....");
		for (int i=0; i<path_list.size(); i++) {
			System.out.print(i+" ");
			ArrayList path = (ArrayList)(path_list.get(i));
			for (int j=0; j<path.size(); j++)
				System.out.print((Integer)path.get(j)+" ");
			System.out.println();
		}		
	}
		
    public static ArrayList find_aggregatedskyline() throws IOException {
	
	/* When we are running this code we have all the files with the required 
	   relations i.e.,
	   a) source->nodes (type 1)
	   b) node->node(type 2)
	   c) node->dest(type_m)
	   d) node_id, prob
	   We also have a file which stores the sequence of the types
	*/
	ArrayList return_sizes = new ArrayList();
	FileInputStream stream = new FileInputStream(folder+"order_"+number+".txt");
	DataInputStream in = new DataInputStream(stream);
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	String strLine_1 = br.readLine();
	ArrayList join_order = new ArrayList();
	
	while (strLine_1!=null) {
	    int type_1 = Integer.parseInt(strLine_1);
	    strLine_1 = br.readLine();
	    join_order.add(type_1);
	}
	
	br.close(); //Closing file
	String file_name = folder+"relationchng_s"+number+".txt";
	//JOIN (node and source edges)
	String join_file1 = SimpleJoin.computeSimpleJoin(file_name, folder+"relation_node_"+number+".txt", 3);//will result in a file called "Sample_5/join_relation_node_5.txt"

	//We need to process the file to store the current paths seen till now.
	FileInputStream stream_id = new FileInputStream(join_file1);
	DataInputStream in_id = new DataInputStream(stream_id);
	BufferedReader br_id = new BufferedReader(new InputStreamReader(in_id));
	String strLine_id;
	TreeSet free_index = new TreeSet();
	
	ArrayList<ArrayList<Integer>> path_list = new ArrayList<ArrayList<Integer>>();
	
	//"Sample_"+nodes+"/src_dest"+nodes+".txt"
	//FileInputStream stream_sd = new FileInputStream("Sample_"+number+"/src_dest"+number+".txt");
	//DataInputStream in_sd = new DataInputStream(stream_sd);
	//BufferedReader br_sd = new BufferedReader(new InputStreamReader(in_sd));

	
	//int idsd = Integer.parseInt(br_sd.readLine();
    //ArrayList<Integer> new_pathlist = new ArrayList<Integer>();
    
	
	while((strLine_id=br_id.readLine())!=null) {
	    String[] line = strLine_id.split("\t");
	 //   System.out.println("Finding the size "+line.length);
	    int id = Integer.parseInt(line[3]);
	    ArrayList<Integer> new_pathlist = new ArrayList<Integer>();
	    new_pathlist.add(Integer.parseInt(line[0]));
	    path_list.add(id, new_pathlist);
	}
	
	br_id.close(); //Closing file
	
//	printPath_list(path_list);
	
	for (int i=0; i<join_order.size(); i++) {
	    file_name = folder+"relation_type"+join_order.get(i)+".txt";
	    //System.out.println(file_name);
	    //JOIN (node and edge)
	    String join_file2;
	    ArrayList<Tuple> full_1;
	    ArrayList<Tuple> full_2;
	    
	    if (i == join_order.size()-1) {
		/*At the end there is a join_file1, we have to end it with the destination side relation */
	    //here we need to compute only the paths of the next type
	    	
	    //
	    	join_file2 = SimpleJoin.computeSimpleJoin(folder+"relationchng_d"+number+".txt", folder+"relation_node_"+number+".txt", 4);
		//System.out.println(" compute full skyline true "+join_file1);
		//System.out.println(" compute full skyline false "+join_file2);	
	    	full_1 = SFS_FullSkyline.computeFullSkyline(join_file1, true, false);
	    	    
	    	full_2 = SFS_FullSkyline.computeFullSkyline(join_file2, false, true);
	    }
	    else {
	    	int end_order = (Integer)join_order.get(i+1);
	    	
	    	String related_file = SimpleJoin.computeWithend(file_name, end_order, folder+"type"+join_order.get(i+1)+"_"+number+".txt");
	    //System.out.println("Last stage "+file_name);
//	    	System.out.println(end_order+"  order "+related_file);
	    	join_file2 = SimpleJoin.computeSimpleJoin(related_file, folder+"relation_node_"+number+".txt", 1); //will result in a file called "Sample_5/join_relationtypeN.txt"
			//System.out.println(join_list2.size()+"  list2");
	    	
	    	//join_file2 = SimpleJoin.computeSimpleJoin(file_name, folder+"relation_node_"+number+".txt", 1); //will result in a file called "Sample_5/join_relationtypeN.txt"
		//System.out.println(" compute full skyline true "+join_file1);
		//System.out.println(" compute full skyline false "+join_file2);
	    	/*count the number of lines in the join file1*/
	    	
	    	
	    	full_1 = SFS_FullSkyline.computeFullSkyline(join_file1, true, false);
	    	//System.out.println("After Skyline computation "+full_1.size());
	    	full_2 = SFS_FullSkyline.computeFullSkyline(join_file2, false, false);
			//System.out.println(full_2.size()+"  skyline  "+full_1.size());

	    }
	    
	    /*Remove non skyline path from the path_list as well!! */
	    //Iterate through the path_list to see which path are still present
	    
	    /***************************ERRROR IN THE FULL SKYLINE *****************************/
	    
	    //System.out.println("After full skyline path id remaining");
	    ArrayList path_ids = new ArrayList();
	    for (int l=0; l<full_1.size(); l++ ){
	    	Tuple t = (Tuple)full_1.get(l);
	    	path_ids.add(t.path_id);
	    	//System.out.println(t.path_id);
	    }
	    
	    for (int l=0; l<path_list.size(); l++) {
	    	ArrayList temp = (ArrayList)path_list.get(l);
	    	if (temp.size() > 0) {
	    		//check if it is present in the array list
	    		if (path_ids.contains(l)) {
	    		}
	    		else {
	    			((ArrayList)path_list.get(l)).clear();
	    			//System.out.println("Remove from the path_list "+l);
	    			free_index.add(l);
	    		}
	    		}
	    	}
	    
	
	
	    
	    /*Compute the full skylines for both the relations*/
	    //	    ArrayList<Tuple> full_1 = computeFullSkyline(join_file1, false);//?why false : join_file1 is a route
	    // ArrayList<Tuple> full_2 = computeFullSkyline(join_file2, true);//why true?
	    
	    
	    /* for the currrent setup all the edges will finally be in the local skyline set! */
	    Tuple_ArrayList local_tuple_1 = SFS_LocalSkyline.computeLocalSkyline(full_1, true);
	    Tuple_ArrayList local_tuple_2 = SFS_LocalSkyline.computeLocalSkyline(full_2, false);
	    //System.out.println("local--------"+local_tuple_1.skyline.size()+" "+local_tuple_1.non_skyline.size()+" "+local_tuple_2.skyline.size()+" "+local_tuple_2.non_skyline.size());
	    
	    
	    ArrayList<Tuple> AB = SimpleJoin.computeSimpleJoin(local_tuple_1.skyline , local_tuple_2.skyline, path_list, free_index);
	    //System.out.println("AB--------"+AB.size());
	    
	    /*for (int l=0; l<AB.size(); l++) {
	    	Tuple.print_Tuple((Tuple)AB.get(l));
	    	System.out.println();
	    }*/
	    
	    ArrayList<Tuple> dashAB = SimpleJoin.computeSimpleJoin(local_tuple_1.non_skyline , local_tuple_2.skyline, path_list, free_index);
	    //System.out.println("dashAB--------"+dashAB.size());
	    ArrayList<Tuple> AdashB = SimpleJoin.computeSimpleJoin(local_tuple_1.skyline , local_tuple_2.non_skyline, path_list, free_index);
	    //System.out.println("AdashB--------"+AdashB.size());
	    
	    //NOT SURE WHAT TO INCLUDE HERE
	    ArrayList dashAdashB = SimpleJoin.computeSimpleJoin(local_tuple_1.non_skyline , local_tuple_2.non_skyline, path_list, free_index);
	    //System.out.println("dashAdashB--------"+dashAdashB.size());
	    
	    //According to the research paper,we need to find the join and write it in a file
	    //Create an array list containing all the skylines found till now
	    AB.addAll(dashAB);
	    AB.addAll(AdashB);
	    
	    /*for (int k=0; k<dashAdashB.size(); k++) {
		if (SFS_Algorithm.is_skyline((Tuple)dashAdashB.get(k), AB, true)) {
		    AB.add((Tuple)dashAdashB.get(k));
		}
	    }*/
	    AB.addAll(SFS_FullSkyline.computeFullSkyline(dashAdashB, true, false));
	        
	    
	    return_sizes.add(AB.size());
	    //System.out.println(AB.size()+" final ");
	    //Write this into a file
	    /* Can improve efficiency by not writing the answers everytime to the file!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
	    
	    join_file1 = folder+"skyline_"+number+""+i+".txt";
	    FileWriter fstream = new FileWriter(join_file1);
	    BufferedWriter out = new BufferedWriter(fstream);	
	    //System.out.println(" skyline----------------  "+AB.size());
	    for (int k=0; k<AB.size(); k++) {
		Tuple t = (Tuple)AB.get(k);
		//System.out.println(" tuple id "+t.node_id1+" "+t.distance + "  "+t.probability+" "+t.path_id);
		out.write(String.valueOf(t.node_id1));
		out.write("\t");
		out.write(String.valueOf(t.distance));
		out.write("\t");
		out.write(Double.toString(t.probability));
		out.write("\t");
		out.write(String.valueOf(t.path_id));
		out.write("\n");
	    }
	    out.close();	    	    
	}
			
	//join_file1
    FileWriter fstream = new FileWriter(folder+"result_paths"+number+".txt");
    BufferedWriter out = new BufferedWriter(fstream);	
    ArrayList<Tuple> finalpaths = SFS_FullSkyline.computeFullSkyline_final(join_file1, true, false);
    
    
	for (int k=0; k<finalpaths.size(); k++) {
		Tuple t = finalpaths.get(k);
		//Get the whole path, distance and probability
		ArrayList path = path_list.get(t.path_id);
		for (int j=0; j<path.size()-1; j++) {
			out.write(String.valueOf(path.get(j)));
			out.write("\t");
		}
		out.write(String.valueOf(t.distance));
		out.write("\t");
		out.write(Double.toString(t.probability));
		out.write("\n");
	}
    out.close();
	//System.out.println("Printing the final path---------------------___Aggregated Skyline");
	//printPath_list(path_list);
    	//System.out.print(finalpaths.size());
    	//System.out.println();
    	return_sizes.add(finalpaths.size());
    	return return_sizes;
    }
    
    public static void main(String args[]) throws IOException {
    /*	Relations s = new Relations();
    	s.find_Relations();
    	
    	for (int i=0; i<10; i++){
    		s.update_sd();*/
    		find_aggregatedskyline();
    	//}
    		
    	FileWriter fstream_1  = new FileWriter(folder+"/result_lat_long"+number+".txt");
    	BufferedWriter out_1 = new BufferedWriter(fstream_1);
	
    		
    	//Here we know that the resultant paths are in the 	(folder+"result_paths"+number+".txt") file.
    		
    	Hashtable table_ll = new Hashtable();
    	FileInputStream stream = new FileInputStream(folder+"lat_long"+number+".txt");
    	DataInputStream in = new DataInputStream(stream);
    	BufferedReader br = new BufferedReader(new InputStreamReader(in));
    	
    	String s;
    	while((s=br.readLine())!=null) {
    		String []line = s.split("\t");
    		table_ll.put(Integer.parseInt(line[0]), line[1]);
    	}
    	

    	FileInputStream stream1 = new FileInputStream(folder+"lat_long_s"+number+".txt");
    	DataInputStream in1 = new DataInputStream(stream1);
    	BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
    	
    	String s1 = br1.readLine();
    	String start_result = "";
    	if (s1!=null) {
    		start_result = start_result+s1;
    	}
    	else {
    		//ID
    		//change this to get the latitude and longitude for the id case!
    	}

    	
    	FileInputStream stream3 = new FileInputStream(folder+"lat_long_d"+number+".txt");
    	DataInputStream in3 = new DataInputStream(stream3);
    	BufferedReader br3 = new BufferedReader(new InputStreamReader(in3));
    	
    	String s2 = br3.readLine();
    	String end_result = "";
    	if (s2!=null) {
    		end_result = end_result+s2;
    	}
    	else {
    		//ID
    		//change this to get the latitude and longitude for the id case!
    	}

    	

    	FileInputStream stream2 = new FileInputStream(folder+"result_paths"+number+".txt");
    	DataInputStream in2 = new DataInputStream(stream2);
    	BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));

    	String line;
    	String result = start_result;
    	
		out_1.write(start_result);
		out_1.write("\n");
		out_1.write(end_result);
		out_1.write("\n");

    	
    	while((line=br2.readLine())!=null) {
    		String split_line[] = line.split("\t");
    		out_1.write("NEW");
    		out_1.write("\n");
    		out_1.write(start_result);
    		out_1.write("\n");
    		for (int i=0; i<split_line.length-1; i++) {
    			int path_id = Integer.parseInt(split_line[i]);
    			if(table_ll.get(path_id)!=null){
    				//result =result +"|"+table_ll.get(path_id);
    	    		out_1.write((String)table_ll.get(path_id));
        			out_1.write("\n");    		
    			}
    		}
    		//result = result + "|" + end_result;
    				out_1.write(end_result);
    				out_1.write("\n");
    		//System.out.println("http://staticmaps.cloudmade.com/8ee2a50541944fb9bcedded5165f09d9/staticmap?size=1024x600&path=color:0x0000ff|weight:8|"+result+"&marker=size:big|label:Y|"
    		//		+start_result+"&marker=size:big|label:Z|"+end_result);
    		//result = start_result;
    	}
    	    	
    	br.close();
    	br1.close();
    	br2.close();
    	br3.close();
    	out_1.close();
    }
}