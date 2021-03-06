package Code_woFileIO;
import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

class Normal_Skyline {
	
	static String folder = "Sample10statswoIO_10/";
	static int number = 10;
	
	public static ArrayList<Tuple> returnlist(String filename, boolean is_route, boolean is_dest) throws IOException{
		FileInputStream stream = new FileInputStream(filename);
		DataInputStream in = new DataInputStream(stream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		ArrayList<Tuple> sortedData = new ArrayList<Tuple>();

		//System.out.println("Inside full skyline "+filename);
		if (is_route) {
			//System.out.println("A route compute full skyline");
		    while ((strLine=br.readLine())!=null) {
			String []strLinearray = strLine.split("\t");
			int node1 = Integer.parseInt(strLinearray[0]);
			int distance = Integer.parseInt(strLinearray[1]);
			double probability = Double.parseDouble(strLinearray[2]);
			int route_id = Integer.parseInt(strLinearray[3]);
			Tuple t = new Tuple(node1, distance, probability, route_id);
			sortedData.add(t);
			
		    }
		}
		else {
			if (!is_dest) {
				//System.out.println("Not a route compute full skyline "+filename);
				while((strLine=br.readLine())!=null) {
					String []strLinearray = strLine.split("\t");
					int node1 = Integer.parseInt(strLinearray[0]);
					int node2 = Integer.parseInt(strLinearray[1]);
					int distance = Integer.parseInt(strLinearray[2]);
					double probability = Double.parseDouble(strLinearray[3]);
					Tuple t = new Tuple(node1, node2, distance, probability);
					sortedData.add(t);
				}	
		    }
			else {
				//System.out.println("Not a route compute full skyline dest "+filename);
				while((strLine=br.readLine())!=null) {
					String []strLinearray = strLine.split("\t");
					int node1 = Integer.parseInt(strLinearray[0]);
					//int node2 = Integer.parseInt(strLinearray[1]);
					int distance = Integer.parseInt(strLinearray[1]);
					double probability = Double.parseDouble(strLinearray[2]);
					Tuple t = new Tuple(node1, distance, probability);
					sortedData.add(t);
				}				
			}

		}
		br.close();//Closing file
		return sortedData;
	}
	
	public static ArrayList find_normalskyline() throws IOException {
		
		FileInputStream stream = new FileInputStream(folder+"order_"+number+".txt");
		DataInputStream in = new DataInputStream(stream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine_1 = br.readLine();
		ArrayList join_order = new ArrayList();
		ArrayList return_sizes = new ArrayList();

		
		while (strLine_1!=null) {
		    int type_1 = Integer.parseInt(strLine_1);
		    strLine_1 = br.readLine();
		    join_order.add(type_1);
		}
		br.close();  //Closing file
		String file_name = folder+"relationchng_s"+number+".txt";
		//JOIN (node and source edges)
		//String join_file1 = SimpleJoin.computeSimpleJoin(file_name, folder+"relation_node_"+number+".txt", 3);//will result in a file called "Sample_5/join_relation_node_5.txt"
		ArrayList join_list1 = SimpleJoin.computeSimpleJoinlist(file_name, folder+"relation_node_"+number+".txt", 3);
		//We need to process the file to store the current paths seen till now.
		/*FileInputStream stream_id = new FileInputStream(join_file1);
		DataInputStream in_id = new DataInputStream(stream_id);
		BufferedReader br_id = new BufferedReader(new InputStreamReader(in_id));
		String strLine_id;*/
		TreeSet free_index = new TreeSet();
		
		ArrayList<ArrayList<Integer>> path_list = new ArrayList<ArrayList<Integer>>();

		//"Sample_"+nodes+"/src_dest"+nodes+".txt"
		//FileInputStream stream_sd = new FileInputStream("Sample_"+number+"/src_dest"+number+".txt");
		//DataInputStream in_sd = new DataInputStream(stream_sd);
		//BufferedReader br_sd = new BufferedReader(new InputStreamReader(in_sd));

		
		//int idsd = Integer.parseInt(br_sd.readLine();
	    //ArrayList<Integer> new_pathlist = new ArrayList<Integer>();
	    
		
//		while((strLine_id=br_id.readLine())!=null) {
		for (int i=0; i<join_list1.size(); i++) {
		    //String[] line = strLine_id.split("\t");
		 //   System.out.println("Finding the size "+line.length);
			Tuple t = (Tuple)join_list1.get(i);
		    //int id = Integer.parseInt(line[3]);
		    ArrayList<Integer> new_pathlist = new ArrayList<Integer>();
		    new_pathlist.add(t.node_id1);
		    path_list.add(t.path_id, new_pathlist);
		}
//		br_id.close();
		
		//Aggregated_Skyline.printPath_list(path_list);
		
		for (int i=0; i<join_order.size(); i++) {
		    file_name = folder+"relation_type"+join_order.get(i)+".txt";
		    //System.out.println(file_name);
		    //JOIN (node and edge)
		    //String join_file2;
		    //ArrayList<Tuple> full_1;
		    //ArrayList<Tuple> full_2;
		    ArrayList join_list2;
		    
		    if (i == join_order.size()-1) {
				/*At the end there is a join_file1, we have to end it with the destination side relation */
				//join_file2 = SimpleJoin.computeSimpleJoin(folder+"relationchng_d"+number+".txt", folder+"relation_node_"+number+".txt", 4);
				join_list2 = SimpleJoin.computeSimpleJoinlist(folder+"relationchng_d"+number+".txt", folder+"relation_node_"+number+".txt", 4);

				//System.out.println(" compute full skyline true "+join_file1);
				//System.out.println(" compute full skyline false "+join_file2);	
			    //full_1 = SFS_FullSkyline.computeFullSkyline(join_file1, true, false);
				//full_1 = returnlist(join_file1, true, false);
				//full_2 = returnlist(join_file2, false, true);
			    //full_2 = SFS_FullSkyline.computeFullSkyline(join_file2, false, true);
			    }
			    else {
			    //System.out.println("Last stage "+file_name);
				//join_file2 = SimpleJoin.computeSimpleJoin(file_name, folder+"relation_node_"+number+".txt", 1); //will result in a file called "Sample_5/join_relationtypeN.txt"
			//	join_list2 = SimpleJoin.computeSimpleJoinlist(file_name, folder+"relation_node_"+number+".txt", 1); //will result in a file called "Sample_5/join_relationtypeN.txt"
				    int end_order = (Integer)join_order.get(i+1);
				    ArrayList list1 = SimpleJoin.computeWithendlist(file_name, end_order, folder+"type"+join_order.get(i+1)+"_"+number+".txt");
				  //  computeWithendlist(String file, int end_order, String file2);	
					join_list2 = SimpleJoin.computeSimpleJoinlist(list1, folder+"relation_node_"+number+".txt", 1); //will result in a file called "Sample_5/join_relationtypeN.txt"
	
				//System.out.println(" compute full skyline true "+join_file1);
				//System.out.println(" compute full skyline false "+join_file2);	
				//full_1 = SFS_FullSkyline.computeFullSkyline(join_file1, true, false);
				//full_1 = returnlist(join_file1, true, false);
				//full_2 = returnlist(join_file2, false, false);
				//full_2 = SFS_FullSkyline.computeFullSkyline(join_file2, false, false);
			    }
		    		    
		   // System.out.println("After full skyline path id remaining");
		    ArrayList path_ids = new ArrayList();
		    for (int l=0; l<join_list1.size(); l++ ){
		    	Tuple t = (Tuple)join_list1.get(l);
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

		    ArrayList<Tuple> AB = SimpleJoin.computeSimpleJoin(join_list1, join_list2, path_list, free_index);
		    //System.out.println("AB--------"+AB.size());
		    
		 /*   for (int l=0; l<AB.size(); l++) {
		    	Tuple.print_Tuple((Tuple)AB.get(l));
		    	System.out.println();
		    }*/
		    
		    return_sizes.add(AB.size());
		    
		   // ArrayList<Tuple> dashAB = SimpleJoin.computeSimpleJoin(local_tuple_1.non_skyline , local_tuple_2.skyline, path_list, free_index);
		   // System.out.println("dashAB--------"+dashAB.size());
		   // ArrayList<Tuple> AdashB = SimpleJoin.computeSimpleJoin(local_tuple_1.skyline , local_tuple_2.non_skyline, path_list, free_index);
		   // System.out.println("AdashB--------"+AdashB.size());
		    
		    //NOT SURE WHAT TO INCLUDE HERE
		   // ArrayList dashAdashB = SimpleJoin.computeSimpleJoin(local_tuple_1.non_skyline , local_tuple_2.non_skyline, path_list, free_index);
		   // System.out.println("dashAdashB--------"+dashAdashB.size());
		    
		    //join_file1 = folder+"skyline_"+number+""+i+".txt";
		    /*FileWriter fstream = new FileWriter(join_file1);
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
		    
		    join_list1.clear();
		    join_list1.addAll(AB);
		   			
		}
		
		
	    FileWriter fstream = new FileWriter(folder+"result_paths"+number+".txt");
	    BufferedWriter out = new BufferedWriter(fstream);	
	    ArrayList<Tuple> finalpaths = SFS_FullSkyline.computeFullSkyline(join_list1, true, false);
	    
	    
/*		for (int k=0; k<finalpaths.size(); k++) {
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
		//Aggregated_Skyline.printPath_list(path_list);
		return_sizes.add(finalpaths.size());
		
		return return_sizes;
	}
	
	public static void main(String args[]) throws IOException{
		find_normalskyline();
	}
}