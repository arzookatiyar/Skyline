import java.io.*;
import java.util.Hashtable;
import java.util.ArrayList;

class Relations_5 {
    Hashtable table = new Hashtable();
     ArrayList prob_list = new ArrayList();
     ArrayList type1_list = new ArrayList();
     ArrayList type2_list = new ArrayList();
     ArrayList type3_list = new ArrayList();
     ArrayList type4_list = new ArrayList();
     ArrayList type5_list = new ArrayList();
     Hashtable node_table = new Hashtable();
     ArrayList node_list = new ArrayList(); 
     int g_counter = 0;
	static String folder = "Sample30_revised30/";
	static int number = 30;
    int counter1 = 0;                               
    int [][]map_array = new int[number*5][number*5];

    public void find_Relations() throws IOException {
    	
        FileInputStream stream_n = new FileInputStream(folder+"relation_node_"+number+".txt");
        DataInputStream in_n = new DataInputStream(stream_n);
        BufferedReader br_n = new BufferedReader(new InputStreamReader(in_n));
        String strLine_n;	
        
        while ((strLine_n=br_n.readLine())!=null) {
        	String[] array = strLine_n.split("\t");
        	node_table.put(Integer.parseInt(array[0]), counter1);
        	//System.out.println(Integer.parseInt(array[0]));
        	node_list.add(Integer.parseInt(array[0]));
        	++counter1;
        }
	
    	
	FileInputStream stream = new FileInputStream(folder+"data2_"+number+".txt");
	DataInputStream in = new DataInputStream(stream);
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	String strLine;
	
	while ((strLine = br.readLine()) != null) {
	    if (strLine.equals("NEW")) {
		int id = Integer.parseInt(br.readLine());
		int counter = Integer.parseInt(br.readLine());
		if(counter > 0) {
		    table.put(id, new int[counter][2]);
		    
		    for (int i=0; i<counter; i++) {
			int id1 = Integer.parseInt(br.readLine());
			int distance1 = Integer.parseInt(br.readLine());
			//double probability = Double.parseDouble(br.readLine());
			//can optimise this as the probability is added to the nodes itself*/
			//prob_list.add(probability);
			((int [][])table.get(id))[i][0] = id1;
			((int [][])table.get(id))[i][1] = distance1;
			//((int [][])table.get(id))[i][2] = g_counter;
			map_array[(Integer)node_table.get(id)][(Integer)node_table.get(id1)] = distance1;

			++g_counter;
		    }
		}
	    }
	}
	
	
	int counter2 = 0;
	for (int i=0; i<map_array.length; i++) {
		for (int j=0; j<map_array[0].length; j++) {
			if (map_array[i][j] > 0) {
				if (map_array[j][i] == 0) {
					++counter2;
					map_array[j][i] = map_array[i][j];
				}
			}
		}
	}
	
	//System.out.println("Not symmetric points "+(counter2/2));

	
	/*TYPE 1*/
	br.close();
	
	stream = new FileInputStream(folder+"order_"+number+".txt");
	in = new DataInputStream(stream);
	br = new BufferedReader(new InputStreamReader(in));
	int source_type = Integer.parseInt(br.readLine());
	int dest_type = source_type;
	while ((strLine = br.readLine()) != null) {
	    dest_type = Integer.parseInt(strLine);
	    //type1_list.add(type1_id);
	}
	br.close();
	


	stream = new FileInputStream(folder+"type1_"+number+".txt");
	in = new DataInputStream(stream);
	br = new BufferedReader(new InputStreamReader(in));

	while ((strLine = br.readLine()) != null) {
	    int type1_id = Integer.parseInt(strLine);
	    type1_list.add(type1_id);
	}
	br.close();
	
	/*TYPE 2*/

	stream = new FileInputStream(folder+"type2_"+number+".txt");
	in = new DataInputStream(stream);
	br = new BufferedReader(new InputStreamReader(in));
	
	while ((strLine = br.readLine()) != null) {
	    int type2_id = Integer.parseInt(strLine);
	    type2_list.add(type2_id);
	}
	br.close();
	
	/*TYPE 3*/
	
	stream = new FileInputStream(folder+"type3_"+number+".txt");
	in = new DataInputStream(stream);
	br = new BufferedReader(new InputStreamReader(in));
	
	while ((strLine = br.readLine()) != null) {
	    int type3_id = Integer.parseInt(strLine);
	    type3_list.add(type3_id);
	}
	br.close();
	
	stream = new FileInputStream(folder+"type4_"+number+".txt");
	in = new DataInputStream(stream);
	br = new BufferedReader(new InputStreamReader(in));
	
	while ((strLine = br.readLine()) != null) {
	    int type4_id = Integer.parseInt(strLine);
	    type4_list.add(type4_id);
	}
	br.close();

	stream = new FileInputStream(folder+"type5_"+number+".txt");
	in = new DataInputStream(stream);
	br = new BufferedReader(new InputStreamReader(in));
	
	while ((strLine = br.readLine()) != null) {
	    int type5_id = Integer.parseInt(strLine);
	    type5_list.add(type5_id);
	}
	br.close();

       	
	FileWriter fw = new FileWriter(folder+"relation_type1.txt");
	BufferedWriter bw = new BufferedWriter(fw);
	
	for(int j=0; j<type1_list.size(); j++) {
	    int search_id = (Integer)type1_list.get(j);
	   // System.out.println(search_id);
	    if (table.get(search_id)!=null) {
		for (int i=0; i<((int [][])table.get(search_id)).length; i++) {
		    //int [][]neighbours_list = (int [][])table.get(search_id);
		    //	System.out.println(((int [][])table.get(19514897))[i][0]+" "+((int [][])table.get(19514897))[i][1]+" "+((int [][])table.get(search_id)).length);
		    if ((int [][])table.get(search_id) != null) {
			//		System.out.println("Enters");
			//bw.write("1233");
			bw.write(String.valueOf(search_id));
			bw.write("\t");
			bw.write(String.valueOf(((int [][])table.get(search_id))[i][0]));
			bw.write("\t");
			bw.write(String.valueOf(((int [][])table.get(search_id))[i][1]));
			//bw.write("\t");
			//bw.write((prob_list.get(((int [][])table.get(search_id))[i][2])).toString());
			bw.write("\n");
			map_array[(Integer)node_table.get(search_id)][(Integer)node_table.get(((int [][])table.get(search_id))[i][0])] = 0;
		    }
		}
	    }
	    
	    int index = (Integer)node_table.get(search_id);
	    for (int i=0; i<counter1; i++) {
	    	//System.out.println("Enters the loop!! "+search_id+" "+(node_list.get(i))+" "+map_array[index][i]);
	    	if (map_array[index][i] > 0) {
				bw.write(String.valueOf(search_id));
				bw.write("\t");
				bw.write(String.valueOf(node_list.get(i)));
				bw.write("\t");
				bw.write(String.valueOf(map_array[index][i]));
				bw.write("\n");
				//map_array[index][i] = 0;	    			    		
	    	}
	    }

	    
	}	
	bw.close();

	
	fw = new FileWriter(folder+"relation_type2.txt");
	BufferedWriter bw1 = new BufferedWriter(fw);
	
	for(int j=0; j<type2_list.size(); j++) {
	    int search_id = (Integer)type2_list.get(j);
	    //System.out.println(search_id);
	    if (table.get(search_id)!=null) {
		for (int i=0; i<((int [][])table.get(search_id)).length; i++) {
		    //int [][]neighbours_list = (int [][])table.get(search_id);
		    //	System.out.println(((int [][])table.get(19514897))[i][0]+" "+((int [][])table.get(19514897))[i][1]+" "+((int [][])table.get(search_id)).length);
		    if ((int [][])table.get(search_id) != null) {
			//		System.out.println("Enters");
			//bw.write("1233");
			bw1.write(String.valueOf(search_id));
			bw1.write("\t");
			bw1.write(String.valueOf(((int [][])table.get(search_id))[i][0]));
			bw1.write("\t");
			bw1.write(String.valueOf(((int [][])table.get(search_id))[i][1]));
			//bw1.write("\t");
			//bw1.write((prob_list.get(((int [][])table.get(search_id))[i][2])).toString());
			bw1.write("\n");
			map_array[(Integer)node_table.get(search_id)][(Integer)node_table.get(((int [][])table.get(search_id))[i][0])] = 0;
		    }
		}
	    }
	    
	    int index = (Integer)node_table.get(search_id);
	    for (int i=0; i<counter1; i++) {
	    	if (map_array[index][i] > 0) {
				bw1.write(String.valueOf(search_id));
				bw1.write("\t");
				bw1.write(String.valueOf(node_list.get(i)));
				bw1.write("\t");
				bw1.write(String.valueOf(map_array[index][i]));
				bw1.write("\n");
				//map_array[index][i] = 0;	    			    		
	    	}
	    }

	    
	}	
	bw1.close();

	fw = new FileWriter(folder+"relation_type3.txt");
	BufferedWriter bw2 = new BufferedWriter(fw);
	
	for(int j=0; j<type3_list.size(); j++) {
	    int search_id = (Integer)type3_list.get(j);
	   // System.out.println(search_id);
	    if (table.get(search_id)!=null) {
		for (int i=0; i<((int [][])table.get(search_id)).length; i++) {
		    //int [][]neighbours_list = (int [][])table.get(search_id);
		    //	System.out.println(((int [][])table.get(19514897))[i][0]+" "+((int [][])table.get(19514897))[i][1]+" "+((int [][])table.get(search_id)).length);
		    if ((int [][])table.get(search_id) != null) {
			//		System.out.println("Enters");
			//bw.write("1233");
			bw2.write(String.valueOf(search_id));
			bw2.write("\t");
			bw2.write(String.valueOf(((int [][])table.get(search_id))[i][0]));
			bw2.write("\t");
			bw2.write(String.valueOf(((int [][])table.get(search_id))[i][1]));
			//bw2.write("\t");
			//bw2.write((prob_list.get(((int [][])table.get(search_id))[i][2])).toString());
			bw2.write("\n");
			map_array[(Integer)node_table.get(search_id)][(Integer)node_table.get(((int [][])table.get(search_id))[i][0])] = 0;
		    }
		}
	    }
	    
	    int index = (Integer)node_table.get(search_id);
	    for (int i=0; i<counter1; i++) {
	    	if (map_array[index][i] > 0) {
				bw2.write(String.valueOf(search_id));
				bw2.write("\t");
				bw2.write(String.valueOf(node_list.get(i)));
				bw2.write("\t");
				bw2.write(String.valueOf(map_array[index][i]));
				bw2.write("\n");
				//map_array[index][i] = 0;	    			    		
	    	}
	    }

	    
	}	
	bw2.close();	

	
	
	FileWriter fw3 = new FileWriter(folder+"relation_type4.txt");
	BufferedWriter bw3 = new BufferedWriter(fw3);
	
	for(int j=0; j<type4_list.size(); j++) {
	    int search_id = (Integer)type4_list.get(j);
	   // System.out.println(search_id);
	    if (table.get(search_id)!=null) {
		for (int i=0; i<((int [][])table.get(search_id)).length; i++) {
		    //int [][]neighbours_list = (int [][])table.get(search_id);
		    //	System.out.println(((int [][])table.get(19514897))[i][0]+" "+((int [][])table.get(19514897))[i][1]+" "+((int [][])table.get(search_id)).length);
		    if ((int [][])table.get(search_id) != null) {
			//		System.out.println("Enters");
			//bw.write("1233");
			bw3.write(String.valueOf(search_id));
			bw3.write("\t");
			bw3.write(String.valueOf(((int [][])table.get(search_id))[i][0]));
			bw3.write("\t");
			bw3.write(String.valueOf(((int [][])table.get(search_id))[i][1]));
			//bw.write("\t");
			//bw.write((prob_list.get(((int [][])table.get(search_id))[i][2])).toString());
			bw3.write("\n");
			map_array[(Integer)node_table.get(search_id)][(Integer)node_table.get(((int [][])table.get(search_id))[i][0])] = 0;

		    }
		}
	    }
	    
	    int index = (Integer)node_table.get(search_id);
	    for (int i=0; i<counter1; i++) {
	    	if (map_array[index][i] > 0) {
				bw3.write(String.valueOf(search_id));
				bw3.write("\t");
				bw3.write(String.valueOf(node_list.get(i)));
				bw3.write("\t");
				bw3.write(String.valueOf(map_array[index][i]));
				bw3.write("\n");
				//map_array[index][i] = 0;	    			    		
	    	}
	    }

	    
	}	
	bw3.close();


	FileWriter fw4 = new FileWriter(folder+"relation_type5.txt");
	BufferedWriter bw4 = new BufferedWriter(fw4);
	
	for(int j=0; j<type5_list.size(); j++) {
	    int search_id = (Integer)type5_list.get(j);
	   // System.out.println(search_id);
	    if (table.get(search_id)!=null) {
		for (int i=0; i<((int [][])table.get(search_id)).length; i++) {
		    //int [][]neighbours_list = (int [][])table.get(search_id);
		    //	System.out.println(((int [][])table.get(19514897))[i][0]+" "+((int [][])table.get(19514897))[i][1]+" "+((int [][])table.get(search_id)).length);
		    if ((int [][])table.get(search_id) != null) {
			//		System.out.println("Enters");
			//bw.write("1233");
			bw4.write(String.valueOf(search_id));
			bw4.write("\t");
			bw4.write(String.valueOf(((int [][])table.get(search_id))[i][0]));
			bw4.write("\t");
			bw4.write(String.valueOf(((int [][])table.get(search_id))[i][1]));
			//bw.write("\t");
			//bw.write((prob_list.get(((int [][])table.get(search_id))[i][2])).toString());
			bw4.write("\n");
			map_array[(Integer)node_table.get(search_id)][(Integer)node_table.get(((int [][])table.get(search_id))[i][0])] = 0;

		    }
		}
	    }
	    
	    int index = (Integer)node_table.get(search_id);
	    for (int i=0; i<counter1; i++) {
	    	if (map_array[index][i] > 0) {
				bw4.write(String.valueOf(search_id));
				bw4.write("\t");
				bw4.write(String.valueOf(node_list.get(i)));
				bw4.write("\t");
				bw4.write(String.valueOf(map_array[index][i]));
				bw4.write("\n");
				//map_array[index][i] = 0;	    			    		
	    	}
	    }


	    
	}	
	bw4.close();
	
	
    }
    
    
    public void update_sd() throws IOException{
    	
    	
       	FileInputStream stream11 = new FileInputStream(folder+"order_"+number+".txt");
    	DataInputStream in11 = new DataInputStream(stream11);                           
    	BufferedReader br11 = new BufferedReader(new InputStreamReader(in11));          
    	int source_type = Integer.parseInt(br11.readLine());                          
    	int dest_type = source_type;                                                
    	String strLine;                                                             
    	while ((strLine = br11.readLine()) != null) {                                 
    	    dest_type = Integer.parseInt(strLine);                                  
    	    //type1_list.add(type1_id);                                             
    	}                                                                           
    	br11.close();                                                                 
    	
    	/* When the source is one of the nodes in the graph then the file will only contain a id in the file. 
 	   Else the relation file will be complete!! */
 	/* since the type is decided even for the 1st case, we will need to define a relation after we know the sequence of the types in the path*/
    	
       	
    	FileInputStream stream = new FileInputStream(folder+"relation_s"+number+".txt");
    	DataInputStream in = new DataInputStream(stream);
    	BufferedReader br = new BufferedReader(new InputStreamReader(in));
    	
    	FileWriter fw;
    	BufferedWriter bw2;

 	 	
 	String[] line = br.readLine().split("\t");
 	br.close();
 	if (line.length == 1) {
 	    fw = new FileWriter(folder+"relationchng_s"+number+".txt");
 	    bw2 = new BufferedWriter(fw);
 	    //for(int j=0; j<type1_list.size(); j++) {
 	    //int search_id = (Integer)type1_list.get(j);
 	    int search_id = Integer.parseInt(line[0]);
	    	int counter = 0;

 	    /*int source_type = -1;
	    if (type1_list.contains(search_id))
 	    	source_type = 1;
 	    else if (type2_list.contains(search_id))
 	    	source_type = 2;
 	   else if (type3_list.contains(search_id))
	    	source_type = 3;
	    else if (type4_list.contains(search_id))
 	    	source_type = 4;
 	   else if (type5_list.contains(search_id))
	    	source_type = 5;*/
 	    
 	   // System.out.println(search_id);
 	    if (table.get(search_id)!=null) {
 		for (int i=0; i<((int [][])table.get(search_id)).length; i++) {
 			
 		    //int [][]neighbours_list = (int [][])table.get(search_id);
 		    //	System.out.println(((int [][])table.get(19514897))[i][0]+" "+((int [][])table.get(19514897))[i][1]+" "+((int [][])table.get(search_id)).length);
 		    if ((int [][])table.get(search_id) != null) {
 			if (source_type == 1) {
 			    if (!type1_list.contains(((int [][])table.get(search_id))[i][0]))
 				continue;
 			}
 			if (source_type == 2) {
 			    if (!type2_list.contains(((int [][])table.get(search_id))[i][0]))
 				continue;
 			}
 			if (source_type == 3) {
 			    if (!type3_list.contains(((int [][])table.get(search_id))[i][0]))
 				continue;
 			}

 			if (source_type == 4) {
 			    if (!type4_list.contains(((int [][])table.get(search_id))[i][0]))
 				continue;
 			}

 			if (source_type == 5) {
 			    if (!type5_list.contains(((int [][])table.get(search_id))[i][0]))
 				continue;
 			}

 			
 			//System.out.println("Enters");
 			//bw.write("1233");
 			//bw2.write(String.valueOf(search_id));
 			//bw2.write("\t");
 			bw2.write(String.valueOf(((int [][])table.get(search_id))[i][0]));
 			bw2.write("\t");
 			bw2.write(String.valueOf(((int [][])table.get(search_id))[i][1]));
 			bw2.write("\t");
 			bw2.write(String.valueOf(counter));
 			bw2.write("\n");
 			map_array[(Integer)node_table.get(search_id)][(Integer)node_table.get(((int [][])table.get(search_id))[i][0])] = 0;
 			++counter;
 		    }
 		}
 	    }
 	    
	    int index = (Integer)node_table.get(search_id);
	    for (int i=0; i<counter1; i++) {
	    	
	    	if (map_array[index][i] > 0) {
	    		//System.out.println("Change the source file ");
//				bw2.write(String.valueOf(search_id));
//				bw2.write("\t");
				bw2.write(String.valueOf(node_list.get(i)));
				bw2.write("\t");
				bw2.write(String.valueOf(map_array[index][i]));
	 			bw2.write("\t");
	 			bw2.write(String.valueOf(counter));
	 			++counter;
				bw2.write("\n");
				//map_array[index][i] = 0;	    			    		
	    	}
	    } 	    

 	    
 	    
 	    // }	
 	    bw2.close();
 	    	    
 	}
 	else {
 	    //the relation is already prepared then in this case we need to keep only those s->n where n belongs to the first type
 	    
 	    
 	}

 	/* When the destination is one of the nodes in the graph then the file will only contain a id in the file. 
 	   Else the relation file will be complete!! */
 	/* since the type is decided even for the 1st case, we will need to define a relation after we know the sequence of the types in the path*/
 	
 	stream = new FileInputStream(folder+"relation_d"+number+".txt");
 	in = new DataInputStream(stream);
 	br = new BufferedReader(new InputStreamReader(in));
 	
 	line = br.readLine().split("\t");
 	br.close();
 	if (line.length == 1) {
 	//	System.out.println("DESTINATION....."+dest_type);
 	    fw = new FileWriter(folder+"relationchng_d"+number+".txt");
 	    bw2 = new BufferedWriter(fw);
 	    //for(int j=0; j<type1_list.size(); j++) {
 	    //int search_id = (Integer)type1_list.get(j);
 	    int search_id = Integer.parseInt(line[0]);
 	    
 	    /*int dest_type = -1;
	    if (type1_list.contains(search_id))
 	    	dest_type = 1;
 	    else if (type2_list.contains(search_id))
 	    	dest_type = 2;
 	   else if (type3_list.contains(search_id))
	    	dest_type = 3;
	    else if (type4_list.contains(search_id))
 	    	dest_type = 4;
 	   else if (type5_list.contains(search_id))
	    	dest_type = 5;*/
 	    
 	  //  System.out.println(search_id);
 	    if (table.get(search_id)!=null) {
 		for (int i=0; i<((int [][])table.get(search_id)).length; i++) {
 		    //int [][]neighbours_list = (int [][])table.get(search_id);
 		    //	System.out.println(((int [][])table.get(19514897))[i][0]+" "+((int [][])table.get(19514897))[i][1]+" "+((int [][])table.get(search_id)).length);
 			//System.out.println("Table not empty!!");
 		    if ((int [][])table.get(search_id) != null) {
 			if (dest_type == 1) {
 			    if (!type1_list.contains(((int [][])table.get(search_id))[i][0]))
 				continue;
 			}
 			if (dest_type == 2) {
 			    if (!type2_list.contains(((int [][])table.get(search_id))[i][0]))
 				continue;
 			}
 			if (dest_type == 3) {
 			    if (!type3_list.contains(((int [][])table.get(search_id))[i][0])){
 			    	//System.out.println("not contained ");
 				continue;
 			    }
 			}
 			
 			if (dest_type == 4) {
 			    if (!type4_list.contains(((int [][])table.get(search_id))[i][0]))
 				continue;
 			}

 			if (dest_type == 5) {
 			    if (!type5_list.contains(((int [][])table.get(search_id))[i][0]))
 				continue;
 			}


 			//System.out.println("Enters");
 			//bw.write("1233");
 			//bw2.write(String.valueOf(search_id));
 			//bw2.write("\t");
 			bw2.write(String.valueOf(((int [][])table.get(search_id))[i][0]));
 			bw2.write("\t");
 			bw2.write(String.valueOf(((int [][])table.get(search_id))[i][1]));
 			//bw2.write("\t");
 			//bw2.write((prob_list.get(((int [][])table.get(search_id))[i][2])).toString());
 			bw2.write("\n");
 			map_array[(Integer)node_table.get(search_id)][(Integer)node_table.get(((int [][])table.get(search_id))[i][0])] = 0;

 		    }
 		}
 	    }
 	    
	    int index = (Integer)node_table.get(search_id);
	    for (int i=0; i<counter1; i++) {
	    	
	    	if (map_array[index][i] > 0) {
//				bw2.write(String.valueOf(search_id));
//				bw2.write("\t");
				bw2.write(String.valueOf(node_list.get(i)));
				bw2.write("\t");
				bw2.write(String.valueOf(map_array[index][i]));
				bw2.write("\n");
				//map_array[index][i] = 0;	    			    		
	    	}
	    } 	    

 	    
 	    // }	
 	    bw2.close();	    
 	}
 	else {	    
 	    //the relation is already prepared then in this case we need to keep only those s->n where n belongs to the first type	    
 	    
 	}
 	
    	
    }
    
    public static void main(String args[]) throws IOException{
    	Relations_5 s5 = new Relations_5();
    	s5.find_Relations();
    	s5.update_sd();
    }
    
    }