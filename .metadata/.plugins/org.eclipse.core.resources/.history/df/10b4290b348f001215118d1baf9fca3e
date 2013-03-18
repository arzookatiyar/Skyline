import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

class SFS_Algorithm {
    
    public static boolean is_skyline(Tuple test, ArrayList<Tuple>skyline, boolean full_skyline) {
	Iterator it = skyline.iterator();
	boolean is_dominated = false;
	while (it.hasNext()) {
	    Tuple t = (Tuple)(it.next());
	    if (full_skyline) {
		if(full_dominates(test, t)){
			//System.out.println();
			//System.out.println("is dominated by-------");
			//Tuple.print_Tuple(test);
			//System.out.println();
			//Tuple.print_Tuple(t);
			//System.out.println();
		    return false;
		}
	    }
	    else {
		if(local_dominates(test, t))
		    return false;
	    }	    
	}
	//if the test is dominated then we ignore it, else add it in the dominator list
	//skyline.add(test);

	return true;
    }

    /* Returns true of sky dominates test else returns false*/

    public static boolean local_dominates(Tuple test, Tuple sky) {
	/*Check only if the tuple dominates in the join attributes*/
	if (test.is_edge) {
	    if (test.node_id2 == sky.node_id2)
		return false;
	}
	else {
	    if (test.node_id1 == sky.node_id1)
		return false;
	}
	return false;  //we donot have any criteria for the local dominance!!
    }
    

    public static boolean full_dominates(Tuple test, Tuple sky) {
	if (test.is_edge) {
	    if (test.node_id2 == sky.node_id2 && test.node_id1 == sky.node_id1) {
		if(test.distance >= sky.distance && test.probability <= sky.probability) {
		    return true;
		}
		else
			return false;
	    }
	    else
	    	return false;
	}
	else {
	    if (test.node_id1 == sky.node_id1) {
		if (test.distance >= sky.distance && test.probability <= sky.probability) {
		    return true;
		}
	    }
	}
	return false;
    }

    public static void main(String args[]) throws IOException {
	
    }
}
