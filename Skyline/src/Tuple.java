public class Tuple {

    int node_id1;
    int node_id2;
    int distance;
    double probability;
    boolean is_edge;
    int path_id;

    public Tuple (int node_id1, int node_id2, int distance, double probability) {
	this.node_id1 = node_id1;
	this.node_id2 = node_id2;
	this.distance = distance;
	this.probability = probability;
	this.is_edge = true;
    }    

    public Tuple (int node_id1, int distance, double probability, int path_id) {
	this.node_id1 = node_id1;
	this.distance = distance;
	this.probability = probability;
	this.is_edge = false;
	this.path_id = path_id;
    }
    
    public Tuple (int node_id1, int distance, double probability) {
    	this.node_id1 = node_id1;
    	this.distance = distance;
    	this.probability = probability;
    	this.is_edge = false;
    }

    public static void print_Tuple(Tuple t) {
	System.out.print(t.node_id1+"\t");
	if (t.is_edge) {
	    System.out.print(t.node_id2+"\t");
	}
	System.out.print(t.distance+"\t");
	System.out.print(t.probability+"\t");
    }

}