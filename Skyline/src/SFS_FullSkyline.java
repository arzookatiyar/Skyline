import java.io.*;
import java.util.*;


class SFS_FullSkyline {
    // ArrayList<Tuple> data = new ArrayList<Tuple>();
    static TreeSet<Tuple> sortedData=new TreeSet<Tuple>(new Comp());
    
    public static ArrayList<Tuple> computeFullSkyline(String filename, boolean is_route, boolean is_dest) throws IOException {
	/* Two types of files to compute skyline
	   1) ---> edges type node1, node2, distance, probability
	   2) ---> route found till present node1, distance, probability, route_id
	*/
	FileInputStream stream = new FileInputStream(filename);
	DataInputStream in = new DataInputStream(stream);
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	String strLine;
	int count=0;
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
		count++;
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
				count++;
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
				count++;
			}				
		}
		}
	
	//System.out.println(" Sorted Data");
	
	/*Iterator it = sortedData.iterator();
	while(it.hasNext()) {
	    Tuple t = (Tuple)(it.next());
	    Tuple.print_Tuple(t);
	    System.out.println("      "+(t.distance+(1/t.probability))+"  "+t.distance+" "+(1/t.probability));
	    System.out.println();
	}
	System.out.println("Count is "+count);*/
	//now sortedData has the sorted data, we now want to calculate the skyline
	Iterator it1 = sortedData.iterator();
	ArrayList<Tuple> skyline = new ArrayList<Tuple>();
	if (it1.hasNext())
		skyline.add((Tuple)it1.next());
	while (it1.hasNext()) {
	    Tuple test = (Tuple)it1.next();
	    if(SFS_Algorithm.is_skyline(test, skyline, true)) {
	    	//System .out.println();
	    	skyline.add(test);
	    	//System.out.println("Added to the skyline ");
	    	//Tuple.print_Tuple(test);
	    }
	}
	//System .out.println();

	//System.out.println("SKYLINE "+skyline.size());
	

	/*for(int i=0; i<skyline.size(); i++) {
	    Tuple t = (Tuple)(skyline.get(i));
	    Tuple.print_Tuple(t);
	    System.out.println();
	}

	System.out.println("SKYLINE ENDS");*/
	sortedData.clear();
	br.close();
	
	return skyline;
    }
    
    
    
    public static ArrayList<Tuple> computeFullSkyline(ArrayList<Tuple> list, boolean is_route, boolean is_dest) throws IOException {
    	/* Two types of files to compute skyline
    	   1) ---> edges type node1, node2, distance, probability
    	   2) ---> route found till present node1, distance, probability, route_id
    	*/
 /*   	FileInputStream stream = new FileInputStream(filename);
    	DataInputStream in = new DataInputStream(stream);
    	BufferedReader br = new BufferedReader(new InputStreamReader(in));*/
    	
    //	String strLine;
    	//System.out.println("Inside full skyline "+filename);
    	if (is_route) {
    		//System.out.println("A route compute full skyline");
    	   // while ((strLine=br.readLine())!=null) {
    		for (int i=0; i<list.size(); i++) {
    		//String []strLinearray = strLine.split("\t");
    			Tuple t = (Tuple)list.get(i);
    		int node1 = t.node_id1;
    		int distance = t.distance;
    		double probability = t.probability;
    		int route_id = t.path_id;
    		Tuple t1 = new Tuple(node1, distance, probability, route_id);
    		sortedData.add(t1);
    		
    	    }
    	}
    	else {
    		if (!is_dest) {
    			//System.out.println("Not a route compute full skyline "+filename);
    			for (int i=0; i<list.size(); i++) {
    				//String []strLinearray = strLine.split("\t");
    				Tuple t = (Tuple)list.get(i);
    				int node1 = t.node_id1;
    				int node2 = t.node_id2;
    				int distance = t.distance;
    				double probability =  t.probability;
    				Tuple t1 = new Tuple(node1, node2, distance, probability);
    				sortedData.add(t1);
    			}	
    	    }
    		else {
    			//System.out.println("Not a route compute full skyline dest "+filename);
    		//	while((strLine=br.readLine())!=null) {
    			for (int i=0; i<list.size(); i++) {
    				//String []strLinearray = strLine.split("\t");
    				Tuple t = (Tuple)list.get(i);
    				int node1 = t.node_id1;
    				//int node2 = Integer.parseInt(strLinearray[1]);
    				int distance = t.distance;
    				double probability = t.probability;
    				Tuple t1 = new Tuple(node1, distance, probability);
    				sortedData.add(t1);
    			}				
    		}
    		}
    	
    	//System.out.println(" Sorted Data");
    	
    	/*Iterator it = sortedData.iterator();
    	while(it.hasNext()) {
    	    Tuple t = (Tuple)(it.next());
    	    Tuple.print_Tuple(t);
    	    System.out.println("      "+(t.distance+(1/t.probability))+"  "+t.distance+" "+(1/t.probability));
    	    System.out.println();
    	}*/
    	
    	//now sortedData has the sorted data, we now want to calculate the skyline
    	Iterator it1 = sortedData.iterator();
    	ArrayList<Tuple> skyline = new ArrayList<Tuple>();
    	if (it1.hasNext())
    		skyline.add((Tuple)it1.next());
    	while (it1.hasNext()) {
    	    Tuple test = (Tuple)it1.next();
    	    if(SFS_Algorithm.is_skyline(test, skyline, true)) {
    	    	//System .out.println();
    	    	skyline.add(test);
    	    	//System.out.println("Added to the skyline ");
    	    	//Tuple.print_Tuple(test);
    	    }
    	}
    	//System .out.println();

    	//System.out.println("SKYLINE "+skyline.size());
    	

    	/*for(int i=0; i<skyline.size(); i++) {
    	    Tuple t = (Tuple)(skyline.get(i));
    	    Tuple.print_Tuple(t);
    	    System.out.println();
    	}*/

//    	System.out.println("SKYLINE ENDS");
    	sortedData.clear();
    	//br.close();
    	
    	return skyline;
        }
        
    
    public static ArrayList<Tuple> computeFullSkyline_final(String filename, boolean is_route, boolean is_dest) throws IOException {
    	/* Two types of files to compute skyline
    	   1) ---> edges type node1, node2, distance, probability
    	   2) ---> route found till present node1, distance, probability, route_id
    	*/
    	FileInputStream stream = new FileInputStream(filename);
    	DataInputStream in = new DataInputStream(stream);
    	BufferedReader br = new BufferedReader(new InputStreamReader(in));
    	String strLine;
    	
    	ArrayList<Tuple> list = new ArrayList<Tuple>();
    	int count=0;
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
    		list.add(t);
    		count++;
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
    				list.add(t);
    				count++;
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
    				list.add(t);
    				count++;
    			}				
    		}
    		}
    	
    	//System.out.println(" Sorted Data");
    	
    	/*Iterator it = sortedData.iterator();
    	while(it.hasNext()) {
    	    Tuple t = (Tuple)(it.next());
    	    Tuple.print_Tuple(t);
    	    System.out.println("      "+(t.distance+(1/t.probability))+"  "+t.distance+" "+(1/t.probability));
    	    System.out.println();
    	}
    	System.out.println("Count is "+count);*/
    	//now sortedData has the sorted data, we now want to calculate the skyline
    	/*Iterator it1 = sortedData.iterator();
    	ArrayList<Tuple> skyline = new ArrayList<Tuple>();
    	if (it1.hasNext())
    		skyline.add((Tuple)it1.next());
    	while (it1.hasNext()) {
    	    Tuple test = (Tuple)it1.next();
    	    if(SFS_Algorithm.is_skyline(test, skyline, true)) {
    	    	//System .out.println();
    	    	skyline.add(test);
    	    	//System.out.println("Added to the skyline ");
    	    	//Tuple.print_Tuple(test);
    	    }
    	}*/
    	//System .out.println();

    	//System.out.println("SKYLINE "+skyline.size());
    	

    	/*for(int i=0; i<skyline.size(); i++) {
    	    Tuple t = (Tuple)(skyline.get(i));
    	    Tuple.print_Tuple(t);
    	    System.out.println();
    	}

    	System.out.println("SKYLINE ENDS");*/
    	//sortedData.clear();
    	br.close();
    	
    	return list;
        }
        

    
    
    
    public static void main(String args[]) throws IOException {
	
	
    }
}


class Comp implements Comparator {
    public int compare(Object first, Object second) {
	Tuple A = (Tuple)first;
	Tuple B = (Tuple)second;
	
	double entropy_funcA = A.distance + (1/A.probability);
	double entropy_funcB = B.distance + (1/B.probability);

	if (entropy_funcA<entropy_funcB)
	    return -1;
	else
	    return 1;
	
	
/*	if (A.is_edge == true) {
	    //is the relation of the edges : for local skyline computation we need to take care of the join attribute as there are no local attribute, for skylines in general we need to take care of all the attributes (distance, prob,
		double entropy_funcA = A.distance + (1/A.probability);
		double entropy_funcB = B.distance + (1/B.probability);

	    if (A.node_id2 == B.node_id2 && A.node_id1 == B.node_id1) {
		if (entropy_funcA<entropy_funcB)
		    return -1;
		else
		    return 1;
	    }
	    else {
	    	if (entropy_funcA < entropy_funcB) 
	    		return -1;
	    	else
	    		return 1;
	    }
	}
	else {
		double entropy_funcA = A.distance + (1/A.probability);
		double entropy_funcB = B.distance + (1/B.probability);

	    if (A.node_id1 == B.node_id1) {
		if (entropy_funcA<entropy_funcB)
		    return -1;
		else
		    return 1;
	    }
	    else {
	    	if (entropy_funcA < entropy_funcB) 
	    		return -1;
	    	else
	    		return 1;
	    }
	}*/
		
    }
}