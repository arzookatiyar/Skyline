package Code_woFileIO;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

class Aggregated_Skyline {
	static String folder = "Sample10statswoIO_10/";;
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
	//String join_file1 = SimpleJoin.computeSimpleJoin(file_name, folder+"relation_node_"+number+".txt", 3);//will result in a file called "Sample_5/join_relation_node_5.txt"
	ArrayList join_list1 = SimpleJoin.computeSimpleJoinlist(file_name, folder+"relation_node_"+number+".txt", 3);
	//We need to process the file to store the current paths seen till now.
	//FileInputStream stream_id = new FileInputStream(join_file1);
	//DataInputStream in_id = new DataInputStream(stream_id);
	//BufferedReader br_id = new BufferedReader(new InputStreamReader(in_id));
	String strLine_id;
	TreeSet free_index = new TreeSet();
	
	ArrayList<ArrayList<Integer>> path_list = new ArrayList<ArrayList<Integer>>();
	
	//"Sample_"+nodes+"/src_dest"+nodes+".txt"
	//FileInputStream stream_sd = new FileInputStream("Sample_"+number+"/src_dest"+number+".txt");
	//DataInputStream in_sd = new DataInputStream(stream_sd);
	//BufferedReader br_sd = new BufferedReader(new InputStreamReader(in_sd));

	
	//int idsd = Integer.parseInt(br_sd.readLine();
    //ArrayList<Integer> new_pathlist = new ArrayList<Integer>();
    
	
	//while((strLine_id=br_id.readLine())!=null) {
	for (int i=0; i<join_list1.size(); i++) {
	    //String[] line = strLine_id.split("\t");
	 //   System.out.println("Finding the size "+line.length);
		Tuple t = (Tuple)join_list1.get(i);
	    //int id = Integer.parseInt(line[3]);
	    ArrayList<Integer> new_pathlist = new ArrayList<Integer>();
	    new_pathlist.add(t.node_id1);
	    path_list.add(t.path_id, new_pathlist);
	}
	
	//br_id.close(); //Closing file
	
//	printPath_list(path_list);
	
	for (int i=0; i<join_order.size(); i++) {
	    file_name = folder+"relation_type"+join_order.get(i)+".txt";
	    //System.out.println(file_name);
	    //JOIN (node and edge)
	    //String join_file2;
	    ArrayList<Tuple> full_1;
	    ArrayList<Tuple> full_2;
	    ArrayList join_list2;
	    if (i == join_order.size()-1) {
		/*At the end there is a join_file1, we have to end it with the destination side relation */
		//join_file2 = SimpleJoin.computeSimpleJoin(folder+"relationchng_d"+number+".txt", folder+"relation_node_"+number+".txt", 4);
	    join_list2 = SimpleJoin.computeSimpleJoinlist(folder+"relationchng_d"+number+".txt", folder+"relation_node_"+number+".txt", 4);
		//System.out.println(" compute full skyline true "+join_file1);
		//System.out.println(" compute full skyline false "+join_file2);	
	    full_1 = SFS_FullSkyline.computeFullSkyline(join_list1, true, false);
	    full_2 = SFS_FullSkyline.computeFullSkyline(join_list2, false, true);
	    }
	    else {
	    //System.out.println("Last stage "+file_name);
		//join_file2 = SimpleJoin.computeSimpleJoin(file_name, folder+"relation_node_"+number+".txt", 1); //will result in a file called "Sample_5/join_relationtypeN.txt"
	    int end_order = (Integer)join_order.get(i+1);
	    ArrayList list1 = SimpleJoin.computeWithendlist(file_name, end_order, folder+"type"+join_order.get(i+1)+"_"+number+".txt");
			//System.out.println("Current order "+end_order+"  "+list1.size()+"  "+file_name);
//			for (int i1=0; i1<list1.size(); i1++)
//				System.out.println(((Tuple)list1.get(i1)).node_id2);

	   // System.out.println(list1.size()+"  list1");
	  //  computeWithendlist(String file, int end_order, String file2);	
		join_list2 = SimpleJoin.computeSimpleJoinlist(list1, folder+"relation_node_"+number+".txt", 1); //will result in a file called "Sample_5/join_relationtypeN.txt"
	//    	join_list2 = SimpleJoin.computeSimpleJoinlist(file_name, folder+"relation_node_"+number+".txt", 1); //will result in a file called "Sample_5/join_relationtypeN.txt" 	
		//System.out.println(join_list2.size()+"  list2");
		//for (int i1=0; i1<join_list2.size(); i1++)
		//	System.out.println(((Tuple)join_list2.get(i1)).node_id1+"  "+((Tuple)join_list2.get(i1)).node_id2+"  "+((Tuple)join_list2.get(i1)).distance+"  "+((Tuple)join_list2.get(i1)).probability);

		
		//System.out.println(" compute full skyline true "+join_file1);
		//System.out.println(" compute full skyline false "+join_file2);	
		full_1 = SFS_FullSkyline.computeFullSkyline(join_list1, true, false);
		full_2 = SFS_FullSkyline.computeFullSkyline(join_list2, false, false);
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
	    
	 /*   for (int k=0; k<dashAdashB.size(); k++) {
		if (SFS_Algorithm.is_skyline((Tuple)dashAdashB.get(k), AB, true)) {
		    AB.add((Tuple)dashAdashB.get(k));
		}
	    }*/
	    
	    AB.addAll(SFS_FullSkyline.computeFullSkyline(dashAdashB, true, false));

	    
	    return_sizes.add(AB.size());
	    
	    join_list1.clear();
	    join_list1.addAll(AB);
	    //Write this into a file
	    /* Can improve efficiency by not writing the answers everytime to the file!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
	    
	    /*join_file1 = folder+"skyline_"+number+""+i+".txt";
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
	    out.close();*/	    	    
	}
			
	//join_file1
  //  FileWriter fstream = new FileWriter(folder+"result_paths"+number+".txt");
  //  BufferedWriter out = new BufferedWriter(fstream);	
 //   ArrayList<Tuple> finalpaths = SFS_FullSkyline.computeFullSkyline(join_list1, true, false);
    ArrayList<Tuple> finalpaths = SFS_FullSkyline.computeFullSkyline_final(join_list1, true, false);

    
    
	/*for (int k=0; k<finalpaths.size(); k++) {
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
    out.close();*/
	//System.out.println("Printing the final path");
	//printPath_list(path_list);
    	return_sizes.add(finalpaths.size());
    	return return_sizes;
    }
    
    public static void main(String args[]) throws IOException {
    	Relations s = new Relations();
    	s.find_Relations();
    	
    	for (int i=0; i<10; i++){
    		s.update_sd();
    		find_aggregatedskyline();
    	}

    }
}