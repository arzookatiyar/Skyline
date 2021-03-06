package Code_woFileIO;

/* TODO : maintain the path computed so far : Right now we ignore the intermediated nodes*/

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

class SimpleJoin {
    
    public static ArrayList<Tuple> computeSimpleJoin(ArrayList<Tuple> list1, ArrayList<Tuple> list2, ArrayList<ArrayList<Integer>>path_list, TreeSet free_index) throws IOException{
	ArrayList<Tuple> final_join = new ArrayList<Tuple>();
	/* Now we are joining the local skylines and non-local skylines */
	/* List1 will contain node1, distance and probability, path_id
	   and list 2 will contain node1, node2, distance and probability
	*/
	
	//System.out.println("Before the join takes place the current ");
	//Aggregated_Skyline.printPath_list(path_list);
	
	if (list1.size() == 0 || list2.size() == 0)
		return final_join;
	for (int i=0; i<list1.size(); i++) {
	    boolean is_first = true;
	    Tuple left = (Tuple)list1.get(i);
		//System.out.println("i is "+i+" path id is "+left.path_id+" "+list1.size() + " "+list2.size());
	    ArrayList tobecopied = new ArrayList();
	    for (int i1=0; i1<((ArrayList)path_list.get(left.path_id)).size(); i1++)
	    	tobecopied.add(((ArrayList)path_list.get(left.path_id)).get(i1));
	    
	    //for (int kk=0; kk<tobecopied.size(); kk++)
	    	//System.out.print(tobecopied.get(kk)+"  ");
	    //System.out.println();
	    		//(ArrayList)path_list.get(left.path_id);
	    for (int j=0; j<list2.size(); j++) {
		Tuple right = (Tuple)list2.get(j);
		//System.out.println(left.node_id1+" "+left.node_id2+" "+right.node_id1+" "+right.node_id2);
		if (left.node_id1 == right.node_id1){
		    /*Here we need to take care of the intermediate nodes in the path*/
		    
		    /* In order to take care of the intermediate path, we will take into account the
		     *  path-ids, while joining two paths we see the path-id first, then add a new path with
		     *   the new path-id.Also we maintain a TreeSet to store all the empty indices and whether
		     *    we need to extend the path Arraylist.
		     */
//			System.out.println("JOIN RESULT");
		    if (is_first) {
			is_first = false;
			//We need to add the new node in the path and also write the new path in a file
			Tuple new_tuple = new Tuple(right.node_id2, (left.distance + right.distance), (left.probability*right.probability), left.path_id);
			final_join.add(new_tuple);
			//Also add the new path in the path_list
			//((ArrayList)path_list.get(left.path_id)).add(left.node_id1);
			((ArrayList)path_list.get(left.path_id)).add(right.node_id2);
			//System.out.println("Add to the path_list1 "+left.node_id1+"  "+is_first+" "+right.node_id1
			//		+" "+left.path_id);
			
			/*System.out.println("paths now..................... ");
			Aggregated_Skyline.printPath_list(path_list);
			System.out.println("paths now......ends............... ");*/
			
		    }
		    else {
			//find a new available path_id from the tree set
			int used_id;
			if (free_index.size()!=0) {
			    used_id = (Integer)free_index.first();
			    free_index.remove(used_id);
			    path_list.remove(used_id);
			}
			else {
			    //add at the last!
			    used_id = (path_list.size())+(free_index.size());	
			    //		    used_id = (path_list.size());
			    
			}
			Tuple new_tuple = new Tuple(right.node_id2, (left.distance + right.distance), (left.probability*right.probability), used_id);
			final_join.add(new_tuple);
			//prepare a copy of the arraylist and then add a new element at the end
			ArrayList new_path = new ArrayList();
			
			for (int p=0; p<tobecopied.size(); p++) {
			    new_path.add(tobecopied.get(p));
			}
			//new_path.add(left.node_id1);
			new_path.add(right.node_id2);
			
			path_list.add(used_id, new_path);			
			//System.out.println("Add to the path_list2 "+left.node_id1+"  "+is_first+" "+right.node_id1
			//		+" "+left.path_id+" "+used_id);
			
			/*System.out.println("paths now..................... ");
			Aggregated_Skyline.printPath_list(path_list);
			System.out.println("paths now......ends............... ");*/
			
			//new_path.clear();
		    }
		}		   
	    }
	    //check if is_first is false then we know that at least 1 corresponding join was formed.
	    if (is_first == true) {
		//Need to remove the index and keep it in the free_index
		int id_removed = left.path_id;
		//System.out.println(path_list.size()+"  "+id_removed+"  "+left.node_id1+" ");
		if (path_list.size() > 0 && path_list.size()>id_removed) {
			//path_list.remove(id_removed);
			((ArrayList)path_list.get(id_removed)).clear();
			//System.out.println("Remove from the path_list "+id_removed);
			free_index.add(id_removed);
			
			//System.out.println("paths now..................... ");
			//Aggregated_Skyline.printPath_list(path_list);
			//System.out.println("paths now......ends............... ");
			
		}
	    }
	}
	//System.out.println("Printing intermediate path");
	//Aggregated_Skyline.printPath_list(path_list);

	/*System.out.println("FINAL JOIN");
	for (int k=0; k<final_join.size(); k++) {
		Tuple.print_Tuple((Tuple)final_join.get(k));
		System.out.println();
	}*/
	
	return final_join;
    }
    
    
    
    /*Assume that the join is with nodes of the edges*/
    /* Possible Types 
       1) ---> id1, id2, distance(edge) and id1, probability(node)
       2) ---> id1, distance, probability, path_id (currentpath) and id1, id2, distance and probability (joined node and edge)
       3) ---> id1, distance, path_id and id1, probability
       4) ---> id1, distance and id1, probability
    */
    public static ArrayList<Tuple> computeSimpleJoin(ArrayList<Tuple> list1, ArrayList<Tuple> list2, int join_type) {
    	ArrayList final_join = new ArrayList();
    	for (int i=0; i<list1.size(); i++) {
    		Tuple left = (Tuple)list1.get(i);
    		for (int j=0; j<list2.size(); j++) {
    			Tuple right = (Tuple)list2.get(j);
    			if (join_type == 1) {
    				if(left.node_id2 == right.node_id1) {
    					final_join.add(new Tuple(left.node_id1, right.node_id1, left.distance, right.probability));
    				}
    			}
    			else if (join_type == 2) {
    				// will not be called but rather the function above will be called
    			}
    			else if (join_type == 3) {
    				if (left.node_id1 == right.node_id1) {
    					final_join.add(new Tuple(left.node_id1, left.distance, right.probability, left.path_id));
    				}
    				
    			}
    			else if (join_type == 4) {
    				if (left.node_id1 == right.node_id1) {
    					final_join.add(new Tuple(left.node_id1, left.distance, right.probability));
    				}
    			}
    		}
    	}
    	return final_join;
    }
    
    
    
    public static ArrayList<Tuple> computeSimpleJoinlist(String filename1, String filename2, int join_type) throws IOException{
    	//System.out.println(filename1+" Simple Join "+filename2);
    	ArrayList final_join = new ArrayList();
	FileInputStream stream1 = new FileInputStream(filename1);
	DataInputStream in1 = new DataInputStream(stream1);
	BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
	
	
	//System.out.println(filename1+" Simple Join "+filename2);
	int index = filename1.lastIndexOf("/");
	String output_file = filename1.substring(0, index+1)+"join_"+filename1.substring(index+1, filename1.length());
	FileWriter fstream = new FileWriter(output_file);
	BufferedWriter out = new BufferedWriter(fstream);	
	//System.out.println(" Output Join File "+output_file);	
	
	String strLine1;
	while((strLine1=br1.readLine()) != null) {
		//System.out.println("Enters the join "+join_type);
	    /*Assume that the join is with nodes of the edges*/
	    /* Possible Types 
	       1) ---> id1, id2, distance(edge) and id1, probability(node)
	       2) ---> id1, distance, probability, path_id (currentpath) and id1, id2, distance and probability (joined node and edge)
	       3) ---> id1, distance, path_id and id1, probability
	       4) ---> id1, distance and id1, probability
	    */
		
		FileInputStream stream2 = new FileInputStream(filename2);
		DataInputStream in2 = new DataInputStream(stream2);
		BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
		
	    boolean temp = false;
	    String strLine2;
	    String []string1_array = strLine1.split("\t");		
	    if (join_type == 1) {
		int id_file1 = Integer.parseInt(string1_array[1]);
		while((strLine2 = br2.readLine())!=null) {
		    //in the second file the id occurs only once and hence we can stop iterating there. Can optimise it by storing it in the ascending order??
		    String []string2_array = strLine2.split("\t");		    
		    int id_file2 = Integer.parseInt(string2_array[0]);
		    if (id_file1 == id_file2) {
				final_join.add(new Tuple(Integer.parseInt(string1_array[0]), Integer.parseInt(string1_array[1]), Integer.parseInt(string1_array[2]), Double.parseDouble(string2_array[1])));   
						/*left.node_id1, right.node_id1, left.distance, right.probability)));
			out.write(string1_array[0]);
			out.write("\t");
			out.write(string1_array[1]);
			out.write("\t");
			out.write(string1_array[2]);
			out.write("\t");
			out.write(string2_array[1]);
			out.write("\n");
			break;*/
		    }
		}
	    }
	    else if (join_type == 2) {
		int id_file1 = Integer.parseInt(string1_array[0]);
		while ((strLine2=br2.readLine())!=null) {
		    String []string2_array = strLine2.split("\t");
		    //here we need to take care of the storing the path computed so far!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		    int id_file2 = Integer.parseInt(string2_array[0]);
		    if (id_file1 == id_file2) {
			out.write(string2_array[1]);
			out.write("\t");
			out.write(Integer.parseInt(string2_array[2])+Integer.parseInt(string1_array[1]));
			out.write("\t");
			out.write(Double.toString((Double.parseDouble(string2_array[3])*
					Double.parseDouble(string1_array[2]))));
			//		out.write("\t");
			//out.write(string2_array[1]);
			out.write("\n");
			
		    }
		}
	    }
	    else if (join_type == 3) {		
		int id_file1 = Integer.parseInt(string1_array[0]);
		while ((strLine2=br2.readLine())!=null) {
		//	System.out.println("Reading another file ");
		    String []string2_array = strLine2.split("\t");
		    int id_file2 = Integer.parseInt(string2_array[0]);
		    //System.out.println(id_file1+" aaaaaaa "+id_file2);
		    if (id_file1 == id_file2) {
		   // System.out.println(" Enters");	
				final_join.add(new Tuple(Integer.parseInt(string1_array[0]), Integer.parseInt(string1_array[1]), Double.parseDouble(string2_array[1]), Integer.parseInt(string1_array[2])));   
			/*out.write(string1_array[0]);
			out.write("\t");
			out.write(string1_array[1]);			
			out.write("\t");
			out.write(string2_array[1]);
			out.write("\t");
			out.write(string1_array[2]);
			out.write("\n");*/			
		    }
		}				
	    }
	    else if(join_type == 4) {
		int id_file1 = Integer.parseInt(string1_array[0]);
		while ((strLine2=br2.readLine())!=null) {
			//System.out.println("Join complete here");
		    String []string2_array = strLine2.split("\t");
		    int id_file2 = Integer.parseInt(string2_array[0]);
		    if (id_file1 == id_file2) {
				final_join.add(new Tuple(Integer.parseInt(string1_array[0]), Integer.parseInt(string1_array[1]), Double.parseDouble(string2_array[1])));   
			/*out.write(string1_array[0]);
			out.write("\t");
			out.write(string1_array[1]);			
			out.write("\t");
			out.write(string2_array[1]);
			//	out.write("\t"); there is no path_id
			//	out.write(string1_array[2]);
			out.write("\n");*/			
		    }
		}									
	    }
	    br2.close(); //Closing file
	    
	}
	
	br1.close();

	out.close();
	return final_join;
    }
    
    
    public static ArrayList<Tuple> computeSimpleJoinlist(ArrayList<Tuple> list1, String filename2, int join_type) throws IOException{
    	//System.out.println(filename1+" Simple Join "+filename2);
    	ArrayList final_join = new ArrayList();
//	FileInputStream stream1 = new FileInputStream(filename1);
//	DataInputStream in1 = new DataInputStream(stream1);
//	BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
	
	
	//System.out.println(filename1+" Simple Join "+filename2);
//	int index = filename1.lastIndexOf("/");
//	String output_file = filename1.substring(0, index+1)+"join_"+filename1.substring(index+1, filename1.length());
//	FileWriter fstream = new FileWriter(output_file);
//	BufferedWriter out = new BufferedWriter(fstream);	
	//System.out.println(" Output Join File "+output_file);	
	
	//String strLine1;
	//while((strLine1=br1.readLine()) != null) {
	for (int i=0; i<list1.size(); i++) {
		//System.out.println("Enters the join "+join_type);
	    /*Assume that the join is with nodes of the edges*/
	    /* Possible Types 
	       1) ---> id1, id2, distance(edge) and id1, probability(node)
	       2) ---> id1, distance, probability, path_id (currentpath) and id1, id2, distance and probability (joined node and edge)
	       3) ---> id1, distance, path_id and id1, probability
	       4) ---> id1, distance and id1, probability
	    */
		
		FileInputStream stream2 = new FileInputStream(filename2);
		DataInputStream in2 = new DataInputStream(stream2);
		BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
		
	    boolean temp = false;
	    String strLine2;
	  //  String []string1_array = strLine1.split("\t");		
	    if (join_type == 1) {
		//int id_file1 = Integer.parseInt(string1_array[1]);
	    	Tuple left = (Tuple)list1.get(i);
	    	int id_file1 = left.node_id2;
		while((strLine2 = br2.readLine())!=null) {
		    //in the second file the id occurs only once and hence we can stop iterating there. Can optimise it by storing it in the ascending order??
		    String []string2_array = strLine2.split("\t");		    
		    int id_file2 = Integer.parseInt(string2_array[0]);
		    if (id_file1 == id_file2) {
		    	//System.out.println(id_file1+"  "+id_file2);
				final_join.add(new Tuple(left.node_id1, left.node_id2, left.distance, Double.parseDouble(string2_array[1])));   
						/*left.node_id1, right.node_id1, left.distance, right.probability)));
			out.write(string1_array[0]);
			out.write("\t");
			out.write(string1_array[1]);
			out.write("\t");
			out.write(string1_array[2]);
			out.write("\t");
			out.write(string2_array[1]);
			out.write("\n");
			break;*/
		    }
		}
	    }
	    else if (join_type == 2) {
	    	Tuple left = (Tuple)list1.get(i);
	    	int id_file1 = left.node_id1;
		while ((strLine2=br2.readLine())!=null) {
		    String []string2_array = strLine2.split("\t");
		    //here we need to take care of the storing the path computed so far!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		    int id_file2 = Integer.parseInt(string2_array[0]);
		    if (id_file1 == id_file2) {
		    }
		}
	    }
	    else if (join_type == 3) {		
//		int id_file1 = Integer.parseInt(string1_array[0]);
	    	Tuple left = (Tuple)list1.get(i);
	    	int id_file1 = left.node_id1;

		while ((strLine2=br2.readLine())!=null) {
		//	System.out.println("Reading another file ");
		    String []string2_array = strLine2.split("\t");
		    int id_file2 = Integer.parseInt(string2_array[0]);
		    //System.out.println(id_file1+" aaaaaaa "+id_file2);
		    if (id_file1 == id_file2) {
		   // System.out.println(" Enters");	
				final_join.add(new Tuple(left.node_id1, left.node_id2, Double.parseDouble(string2_array[1]), left.distance));   
			/*out.write(string1_array[0]);
			out.write("\t");
			out.write(string1_array[1]);			
			out.write("\t");
			out.write(string2_array[1]);
			out.write("\t");
			out.write(string1_array[2]);
			out.write("\n");*/			
		    }
		}				
	    }
	    else if(join_type == 4) {
//		int id_file1 = Integer.parseInt(string1_array[0]);
	    	Tuple left = (Tuple)list1.get(i);
	    	int id_file1 = left.node_id1;

		while ((strLine2=br2.readLine())!=null) {
			//System.out.println("Join complete here");
		    String []string2_array = strLine2.split("\t");
		    int id_file2 = Integer.parseInt(string2_array[0]);
		    if (id_file1 == id_file2) {
				final_join.add(new Tuple(left.node_id1, left.node_id2, Double.parseDouble(string2_array[1])));   
			/*out.write(string1_array[0]);
			out.write("\t");
			out.write(string1_array[1]);			
			out.write("\t");
			out.write(string2_array[1]);
			//	out.write("\t"); there is no path_id
			//	out.write(string1_array[2]);
			out.write("\n");*/			
		    }
		}									
	    }
	    br2.close(); //Closing file
	    
	}
	
	//br1.close();

	//out.close();
	return final_join;
    }

    
    
    public static String computeSimpleJoin(String filename1, String filename2, int join_type) throws IOException{
    	//System.out.println(filename1+" Simple Join "+filename2);	
	FileInputStream stream1 = new FileInputStream(filename1);
	DataInputStream in1 = new DataInputStream(stream1);
	BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
	
	
	//System.out.println(filename1+" Simple Join "+filename2);
	int index = filename1.lastIndexOf("/");
	String output_file = filename1.substring(0, index+1)+"join_"+filename1.substring(index+1, filename1.length());
	FileWriter fstream = new FileWriter(output_file);
	BufferedWriter out = new BufferedWriter(fstream);	
	//System.out.println(" Output Join File "+output_file);	
	
	String strLine1;
	while((strLine1=br1.readLine()) != null) {
		//System.out.println("Enters the join "+join_type);
	    /*Assume that the join is with nodes of the edges*/
	    /* Possible Types 
	       1) ---> id1, id2, distance(edge) and id1, probability(node)
	       2) ---> id1, distance, probability, path_id (currentpath) and id1, id2, distance and probability (joined node and edge)
	       3) ---> id1, distance, path_id and id1, probability
	       4) ---> id1, distance and id1, probability
	    */
		
		FileInputStream stream2 = new FileInputStream(filename2);
		DataInputStream in2 = new DataInputStream(stream2);
		BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
		
	    boolean temp = false;
	    String strLine2;
	    String []string1_array = strLine1.split("\t");		
	    if (join_type == 1) {
		int id_file1 = Integer.parseInt(string1_array[1]);
		while((strLine2 = br2.readLine())!=null) {
		    //in the second file the id occurs only once and hence we can stop iterating there. Can optimise it by storing it in the ascending order??
		    String []string2_array = strLine2.split("\t");		    
		    int id_file2 = Integer.parseInt(string2_array[0]);
		    if (id_file1 == id_file2) {
			out.write(string1_array[0]);
			out.write("\t");
			out.write(string1_array[1]);
			out.write("\t");
			out.write(string1_array[2]);
			out.write("\t");
			out.write(string2_array[1]);
			out.write("\n");
			break;
		    }
		}
	    }
	    else if (join_type == 2) {
		int id_file1 = Integer.parseInt(string1_array[0]);
		while ((strLine2=br2.readLine())!=null) {
		    String []string2_array = strLine2.split("\t");
		    //here we need to take care of the storing the path computed so far!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		    int id_file2 = Integer.parseInt(string2_array[0]);
		    if (id_file1 == id_file2) {
			out.write(string2_array[1]);
			out.write("\t");
			out.write(Integer.parseInt(string2_array[2])+Integer.parseInt(string1_array[1]));
			out.write("\t");
			out.write(Double.toString((Double.parseDouble(string2_array[3])*
					Double.parseDouble(string1_array[2]))));
			//		out.write("\t");
			//out.write(string2_array[1]);
			out.write("\n");
			
		    }
		}
	    }
	    else if (join_type == 3) {		
		int id_file1 = Integer.parseInt(string1_array[0]);
		while ((strLine2=br2.readLine())!=null) {
		//	System.out.println("Reading another file ");
		    String []string2_array = strLine2.split("\t");
		    int id_file2 = Integer.parseInt(string2_array[0]);
		    //System.out.println(id_file1+" aaaaaaa "+id_file2);
		    if (id_file1 == id_file2) {
		   // System.out.println(" Enters");	
			out.write(string1_array[0]);
			out.write("\t");
			out.write(string1_array[1]);			
			out.write("\t");
			out.write(string2_array[1]);
			out.write("\t");
			out.write(string1_array[2]);
			out.write("\n");			
		    }
		}				
	    }
	    else if(join_type == 4) {
		int id_file1 = Integer.parseInt(string1_array[0]);
		while ((strLine2=br2.readLine())!=null) {
			//System.out.println("Join complete here");
		    String []string2_array = strLine2.split("\t");
		    int id_file2 = Integer.parseInt(string2_array[0]);
		    if (id_file1 == id_file2) {
			out.write(string1_array[0]);
			out.write("\t");
			out.write(string1_array[1]);			
			out.write("\t");
			out.write(string2_array[1]);
			//	out.write("\t"); there is no path_id
			//	out.write(string1_array[2]);
			out.write("\n");			
		    }
		}									
	    }
	    br2.close(); //Closing file
	    
	}
	
	br1.close();

	out.close();
	return output_file;
    }
    
    
	public static ArrayList<Tuple> computeWithendlist(String file, int end_order, String file2) throws IOException{
		FileInputStream stream1 = new FileInputStream(file);
		DataInputStream in1 = new DataInputStream(stream1);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
		String strLine;
		//System.out.println(file+"  "+end_order+"  "+file2);
		//int index = file.lastIndexOf("/");
		//String output_file = file.substring(0, index+1)+"temp_"+file.substring(index+1, file.length());
		
		//FileWriter fstream = new FileWriter(output_file);
		//BufferedWriter out = new BufferedWriter(fstream);
		
		ArrayList<Tuple> list1 = new ArrayList<Tuple>();
		
		FileInputStream stream2 = new FileInputStream(file2);
		DataInputStream in2 = new DataInputStream(stream2);
		BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));

		ArrayList nodesoftype = new ArrayList();
		String strLine2;
		
		while((strLine2=br2.readLine())!=null) {
			nodesoftype.add(Integer.parseInt(strLine2));
		}
		
	//	System.out.println("Current order "+end_order+"  "+file2);
	//	for (int i=0; i<nodesoftype.size(); i++)
	//		System.out.println(nodesoftype.get(i));
		
		while((strLine=br1.readLine())!=null) {
			String []line = strLine.split("\t");
			int second_id = Integer.parseInt(line[1]);
			if (nodesoftype.contains(second_id)) {
				Tuple t = new Tuple(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
				list1.add(t);
			}
		}
		br1.close();
		br2.close();
	//	out.close();
		
		return list1;
		
		
	}

    
    public static void main(String args[]) throws IOException {
	
    }
}