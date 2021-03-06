import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

class ASJQ_revised {
	
	static String folder = "Sample_revisedtemp30/";
	static int number = 30;
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
		join_file2 = SimpleJoin.computeSimpleJoin(folder+"relationchng_d"+number+".txt", folder+"relation_node_"+number+".txt", 4);
		//System.out.println(" compute full skyline true "+join_file1);
		//System.out.println(" compute full skyline false "+join_file2);	
	    full_1 = SFS_FullSkyline.computeFullSkyline(join_file1, true, false);
	    full_2 = SFS_FullSkyline.computeFullSkyline(join_file2, false, true);
	    }
	    else {
	    //System.out.println("Last stage "+file_name);
	    	int end_order = (Integer)join_order.get(i+1);
	    	
	    	String related_file = SimpleJoin.computeWithend(file_name, end_order, folder+"type"+join_order.get(i+1)+"_"+number+".txt");
	    //System.out.println("Last stage "+file_name);
//	    	System.out.println(end_order+"  order "+related_file);
	    	join_file2 = SimpleJoin.computeSimpleJoin(related_file, folder+"relation_node_"+number+".txt", 1); //will result in a file called "Sample_5/join_relationtypeN.txt"
			//System.out.println(join_list2.size()+"  list2");
	
	    	
//		join_file2 = SimpleJoin.computeSimpleJoin(file_name, folder+"relation_node_"+number+".txt", 1); //will result in a file called "Sample_5/join_relationtypeN.txt"
		//System.out.println(" compute full skyline true "+join_file1);
		//System.out.println(" compute full skyline false "+join_file2);	
		full_1 = SFS_FullSkyline.computeFullSkyline(join_file1, true, false);
		full_2 = SFS_FullSkyline.computeFullSkyline(join_file2, false, false);
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
	    Tuple_ArrayList local_tuple_1 = SFS_LocalSkyline_revised.computeLocalSkyline(full_1, true);
	    Tuple_ArrayList local_tuple_2 = SFS_LocalSkyline_revised.computeLocalSkyline(full_2, false);
	    
	    //ArrayList<Tuple> AB = SimpleJoin.computeSimpleJoin(local_tuple_1.skyline , local_tuple_2.skyline, path_list, free_index);
	    
	    Tuple_ArrayList AB = SimpleJoin.computeSimpleJoin_revised(local_tuple_1.skyline , local_tuple_2.skyline, path_list, free_index);
	    ArrayList pathend_nodes = AB.non_skyline;
	    
	    ArrayList<Tuple> AB_final = SFS_FullSkyline.computeFullSkyline(AB.skyline, true, false);
	    
	    //Now we remove all the paths from the local_tuple_2.non_skyline!!
	    ArrayList non_skyliner2 = new ArrayList(); 
	    non_skyliner2.addAll(local_tuple_2.non_skyline);
	    
	    for (int t=0; t<non_skyliner2.size(); t++) {
	    	Tuple tt = (Tuple)non_skyliner2.get(t);
	    	if (non_skyliner2.contains(tt.node_id2)) {
	    		//we now remove this from the relation
	    		non_skyliner2.remove(t);
	    		--t;
	    	}
	    }
	    //System.out.println("AB--------"+AB.size());
	    
	    /*for (int l=0; l<AB.size(); l++) {
	    	Tuple.print_Tuple((Tuple)AB.get(l));
	    	System.out.println();
	    }*/
	    
	    ArrayList<Tuple> dashAB = SimpleJoin.computeSimpleJoin(local_tuple_1.non_skyline , local_tuple_2.skyline, path_list, free_index);
	    ArrayList<Tuple> dashAB_final = SFS_FullSkyline.computeFullSkyline(dashAB, true, false);
	    
	    for (int k=0; k<dashAB_final.size(); k++) {
			if (SFS_Algorithm.is_skyline((Tuple)dashAB_final.get(k), AB_final, true)) {
			    AB_final.add((Tuple)dashAB_final.get(k));
			}
		}

	    //System.out.println("dashAB--------"+dashAB.size());
	    ArrayList<Tuple> AdashB = SimpleJoin.computeSimpleJoin(local_tuple_1.skyline , non_skyliner2, path_list, free_index);
	    //System.out.println("AdashB--------"+AdashB.size());
	    ArrayList<Tuple> AdashB_final = SFS_FullSkyline.computeFullSkyline(AdashB, true, false);
	    for (int k=0; k<AdashB_final.size(); k++) {
			if (SFS_Algorithm.is_skyline((Tuple)AdashB_final.get(k), AB_final, true)) {
			    AB_final.add((Tuple)AdashB_final.get(k));
			}
		}

	    
	    
	    //NOT SURE WHAT TO INCLUDE HERE
	    ArrayList dashAdashB = SimpleJoin.computeSimpleJoin(local_tuple_1.non_skyline , non_skyliner2, path_list, free_index);
	    ArrayList<Tuple> dashAdashB_final = SFS_FullSkyline.computeFullSkyline(dashAdashB, true, false);
	    //System.out.println("dashAdashB--------"+dashAdashB.size());
	    for (int k=0; k<dashAdashB_final.size(); k++) {
			if (SFS_Algorithm.is_skyline((Tuple)dashAdashB_final.get(k), AB_final, true)) {
			    AB_final.add((Tuple)dashAdashB_final.get(k));
			}
		}

	    
	    
	    //According to the research paper,we need to find the join and write it in a file
	    //Create an array list containing all the skylines found till now
	    //AB.addAll(dashAB);
	    //AB.addAll(AdashB);
	    
	    /*for (int k=0; k<dashAdashB.size(); k++) {
		if (SFS_Algorithm.is_skyline((Tuple)dashAdashB.get(k), AB, true)) {
		    AB.add((Tuple)dashAdashB.get(k));
		}
	    }*/
	    return_sizes.add(AB_final.size());
	    //System.out.print(AB.size()+"  ");
	    //Write this into a file
	    /* Can improve efficiency by not writing the answers everytime to the file!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
	    
	    join_file1 = folder+"skyline_"+number+""+i+".txt";
	    FileWriter fstream = new FileWriter(join_file1);
	    BufferedWriter out = new BufferedWriter(fstream);	
	    //System.out.println(" skyline----------------  "+AB.size());
	    for (int k=0; k<AB_final.size(); k++) {
		Tuple t = (Tuple)AB_final.get(k);
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
		
	}
}