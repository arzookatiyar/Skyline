import org.jgrapht.graph.*;
import org.jgrapht.EdgeFactory;
import org.jgrapht.alg.*;
import org.jgrapht.event.*;
import org.jgrapht.traverse.*;


public class MyGraph {
    private SimpleGraph<Integer, DefaultEdge> g = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
    
    public void addVertex(int id) {
    	g.addVertex(id);
    }
    
    public void addEdge(int id_s, int id_d) {
    	g.addEdge(id_s, id_d);
    }
    
    public SimpleGraph<Integer, DefaultEdge> getGraph() {
    	return g;
    }
}