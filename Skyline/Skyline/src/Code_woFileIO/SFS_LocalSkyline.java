package Code_woFileIO;
import java.io.*;
import java.util.*;

class SFS_LocalSkyline {
    ArrayList<Tuple> data = new ArrayList<Tuple>();
    static TreeSet<Tuple> sortedData=new TreeSet<Tuple>(new LocalComp());
    
    
    public static Tuple_ArrayList computeLocalSkyline(ArrayList<Tuple> list, boolean is_route) {
	//Tuple will contain the node1, distance and probability
	for (int i=0; i<list.size(); i++) {
	    sortedData.add(list.get(i));
	}
	//This will sort the data. Now we need to compute the skyline
	Iterator it1 = sortedData.iterator();
	ArrayList<Tuple> skyline = new ArrayList<Tuple>();
	ArrayList<Tuple> non_skyline = new ArrayList<Tuple>();
	if (it1.hasNext())
		//skyline.add((Tuple)it1.next());
		non_skyline.add((Tuple)it1.next());
	while (it1.hasNext()) {
	    Tuple test = (Tuple)it1.next();
	    if(SFS_Algorithm.is_skyline(test, skyline, false)) {
		//skyline.add(test);
	    	non_skyline.add(test);
	    }
	    else
		//non_skyline.add(test);
	    	skyline.add(test);
	}		
	
	Tuple_ArrayList local_list = new Tuple_ArrayList(skyline, non_skyline);
	sortedData.clear();
	//Tuple_ArrayList.print_Tuple_ArrayList(local_list);
	return local_list;
    }
    
    
    public static TreeSet<Tuple> computeLocalSkyline(String filename, boolean is_route) throws IOException{
	
	FileInputStream stream = new FileInputStream(filename);
	DataInputStream in = new DataInputStream(stream);
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	String strLine;
	if (is_route) {
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
	
	Iterator it = sortedData.iterator();
	while(it.hasNext()) {
	    Tuple t = (Tuple)(it.next());
	  //  Tuple.print_Tuple(t);
	}
	
	//now sortedData has the sorted data, we now want to calculate the skyline       	
	br.close();
	return sortedData;
    }

    public static void main(String args[]) throws IOException {
	
    }
}


class LocalComp implements Comparator{
    public int compare(Object first, Object second) {
	Tuple A = (Tuple)first;
	Tuple B = (Tuple)second;
	if (A.is_edge == true) {
		//System.out.println("is_edge "+A.node_id2+"  "+B.node_id2);
	    //is the relation of the edges : for local skyline computation we need to take care of the join attribute as there are no local attribute, for skylines in general we need to take care of all the attributes (distance, prob, 
	    if (A.node_id2 == B.node_id2)
		/*		double entropy_funcA = A.distance + (1/A.probability);
		double entropy_funcB = B.distance + (1/B.probability);
		if (entropy_funcA>=entropy_funcB)
		    return -1;
		else
		    return 1;
		    }*/
		return 1;
	    else
		return -1;	    
	}
	else {
	    if (A.node_id1 == B.node_id1) 
		/*	double entropy_funcA = A.distance + (1/A.probability);
		double entropy_funcB = B.distance + (1/B.probability);
		if (entropy_funcA>=entropy_funcB)
		    return -1;
		else
		    return 1;
		    }*/
		return 1;
	    else
		return -1;	    	    
	
	    }
    }
}