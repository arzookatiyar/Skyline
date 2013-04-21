package Code_woFileIO;

import java.util.Hashtable;
import java.io.*;
import java.util.Arrays;

class ThresholdAlgorithm {

    static Hashtable table = new Hashtable();
    static int k = 0;
    static int count_iter = 0;

    public static boolean not_present(int [][]Path, int node, int current_length) {
	for (int i=0; i<current_length; i++) {
	    if (Path[i][0] == node)
		return false;
	}	
	return true;
    }
    
    public static int[][] find_path(int current_length, int [][]Path, int [][]best_Path, int query, int current_sum, int best_sum) {
	//System.out.println("iteration number "+(++count_iter));
	//System.out.println(current_length);
	/*for (int i=0; i<k; i++) {
	    System.out.print(Path[i][0]+"  ");
	}
	System.out.println();
	for (int i=0; i<k; i++) {
	    System.out.print(Path[i][1]+"  ");
	}
	
	System.out.println();*/
	++count_iter;
	if (current_length == 0) {
	    return best_Path;
	}
	else {
	    int node = Path[current_length-1][0];
	    int index = Path[current_length-1][1];
	    int [][]array = (int[][])table.get(node);
	    //	    System.out.println(array+"  "+node+" "+index);
	    int next_node = 0;
	    boolean found_next = false;
	    while(!found_next && index<array.length) {
		next_node =  array[index][0];
		found_next = not_present(Path, next_node, current_length);     	
		index++;
	    }
	    --index;
	    /*Next node is not already in the path*/
	    //System.out.println(next_node+" "+index+"  "+(array.length-1));
	    Path[current_length-1][1] = index;
	    if (next_node == 0 || found_next==false) {
		//System.out.println("end");
		Path[current_length-1][0] = 0;
		Path[current_length-1][1] = 0;
		/* We can change the implementation to remove storing the last distance added */
		current_sum = current_sum - Path[current_length-1][2];
		Path[current_length-1][2] = 0;
		current_length--;
		return find_path(current_length, Path, best_Path, query, current_sum, best_sum);
	    }
	    
	    int distance_added = array[index][1];
	    int new_length = current_sum + distance_added;
	    
	    if (new_length > best_sum) {
		if (current_length == 1) {
		    return best_Path;
		}
		Path[current_length-1][0] = 0;
		Path[current_length-1][1] = 0;
		/* We can change the implementation to remove storing the last distance added */
		current_sum = current_sum - Path[current_length-1][2];
		Path[current_length-1][2] = 0;
		current_length--;
	    }
	    else {
		/* Case when new_length < best_sum and the current_length+1 is not equal to k then 
		   we will need to find the neighbour from the next_node added. */
		if (current_length+1 < k) {
		    Path[current_length-1][1]++;
		    current_length++;
		    Path[current_length-1][0] = next_node;
		    Path[current_length-1][1] = 0;
		    Path[current_length-1][2] = distance_added;
		    current_sum = new_length;
		}
		else {
		    /*We have found a path of length k  such that its distance is smaller than the
		      best_sum and hence we update best_sum and best_Path to the current */
		    best_sum = new_length;
		    for (int p=0; p<current_length; p++) {
			best_Path[p][0] = Path[p][0];
			best_Path[p][1] = Path[p][2];
		    }
		    best_Path[current_length][0] = next_node;
		    best_Path[current_length][1] = distance_added;

		    Path[current_length-1][0] = 0;
		    Path[current_length-1][1] = 0;		    
		    current_sum = current_sum - Path[current_length-1][2];
		    Path[current_length-1][2] = 0;
		    --current_length;
		}
	    }
	    return find_path(current_length, Path, best_Path, query, current_sum, best_sum);
	}
    }
    
    
    public static void main(String args[]) throws IOException{
	/*Read from the file and store in the Hashtable.
	  hashtable stores the ids as the keys and the array object as the corresponding values., In these arrays the ids are stored in the ascending order of distances from this id */
	FileInputStream fstream = new FileInputStream("data_10_lesspoints.txt");
	DataInputStream in  = new DataInputStream(fstream);
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	String strLine;
	
	int []id_array = new int[10];
	int counting = 0;
	int avg_degree = 0;
	while((strLine = br.readLine()) != null) { //end of the file
	    if (strLine.equals("NEW")) {
		/* the next starts with a new hastable entry */
		//Assuming the file reads as : 
		// NEW
		// id
		// counter
		// id
		// distance
		// id
		// distance and so on...
		int id = Integer.parseInt(br.readLine());
		id_array[counting] = id;
		++counting;
		int counter = Integer.parseInt(br.readLine());
		avg_degree += counter;
		//System.out.println(counter+"  counter");
		//int [][]new_array = new int[counter][2];
		table.put(id, new int[counter][2]);
		for (int i=0; i<counter; i++) {
		    int id1 = Integer.parseInt(br.readLine());
		    int distance1 = Integer.parseInt(br.readLine());
		    //	    System.out.println(i+"  i "+id1+"  "+distance1);
		    ((int[][])table.get(id))[i][0] = id1;
		    ((int[][])table.get(id))[i][1] = distance1;
		}		
	    }
	}
	//System.out.println("Average degree "+(avg_degree/(id_array.length)));
	
	//for (int i = 1; i<=6; i++) {
	//int i1 = 19514897;
	//    System.out.println(i1);
	// int[][] ar = ((int[][])table.get(i1));
	    //    System.out.println(ar.length+ " two lengths "+ar[1].length);
	    //  for (int j=0; j<ar.length; j++) {
	    //		System.out.println(ar[j][0]+ " "+ar[j][1]);
	    //  }
	    //}
	

	BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
	//k = Integer.parseInt(br1.readLine());
	for (k = 2; k<=9; k++) {
	    //System.out.println(k);
	    for (int i=0; i<id_array.length; i++) {
		//System.out.println(i);
		//	    int query_id = Integer.parseInt(br1.readLine());
		int query_id = id_array[i];
		int [][]Path = new int[k][3];
		Path[0][0] = query_id;
		Path[0][1] = 0;
		Path[0][2] = 0;
		int [][]best_Path = new int[k][2];
		int best_sum = Integer.MAX_VALUE;
		int current_sum = 0;
		
		long startTime = System.nanoTime();
		int initial_count = count_iter;
		best_Path = find_path(1, Path, best_Path, query_id, current_sum, best_sum);
		//System.out.println(count_iter-initial_count);
		long endTime = System.nanoTime();
		//System.out.println("time "+(endTime-startTime));
		
		/*		for (int q=0; q<k; q++) {
		    System.out.println(best_Path[q][0]+"  "+best_Path[q][1]);
		    }*/
	    }
	    //System.out.println(count_iter+" for k = "+k+"  "+(count_iter/(id_array.length)));
	    count_iter = 0;
	}
    }
}