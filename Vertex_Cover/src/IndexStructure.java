import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

import org.jgrapht.alg.NeighborIndex;
import org.jgrapht.alg.VertexCovers;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;

class IndexStructure {
	
	
	public static SimpleGraph<Integer, DefaultWeightedEdge> createGraphfromFile(String file) throws IOException{
		FileInputStream stream = new FileInputStream(file);
		DataInputStream in = new DataInputStream(stream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		SimpleGraph<Integer, DefaultWeightedEdge> g = new SimpleGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		String strLine;
		while ((strLine=br.readLine())!=null) {
			/*Every line is an edge*/
			String []line = strLine.split("\t");
			//System.out.println(line[0]);
			if (line.length == 2) { 
				int source = Integer.parseInt(line[0]);
				int dest = Integer.parseInt(line[1]);
//				System.out.println(source+"  "+dest);
				if (!g.containsVertex(source))
					g.addVertex(source);

				if (!g.containsVertex(dest))
					g.addVertex(dest);
				
				//this.adjacency_matrix[source][dest] = 1;
				//graph.addEdge(source, dest);
				//System.out.println(source+" "+dest);
				DefaultWeightedEdge edge = g.addEdge(source, dest);
				if (edge!=null)
					g.setEdgeWeight(edge, 1.0);
			}		
		}
		br.close();
		return g;
	}
	
	public static void createStructure() throws IOException{
		//Streaming_VertexCover vc = new Streaming_VertexCover(); 
		Set<Integer> vertex_cover = Streaming_VertexCover.compute_disk ("roadNet-CA.txt");  //vertex cover
		System.out.println("Iteration 0 : "+vertex_cover.size());

		SimpleGraph<Integer, DefaultWeightedEdge> g = createGraphfromFile("roadNet-CA.txt");
		//DistanceGraph dg = new DistanceGraph();
		String file = DistanceGraph.distance_graph_memory(g, vertex_cover);
		SimpleGraph<Integer, DefaultWeightedEdge> distance_g = DistanceGraph.distance_graph_graph(g, vertex_cover);
		
		//SimpleGraph<Integer, DefaultWeightedEdge> distance_g = DistanceGraph.distance_graph_graph(g, vertex_cover);
//		SimpleGraph<Integer, DefaultWeightedEdge> distance_g1 = DistanceGraph.distance_graph_file(file, vertex_cover);
//		SimpleGraph<Integer, DefaultWeightedEdge> distance_g2 = DistanceGraph.distance_graph_graph(g, vertex_cover);
//		printtoFile(distance_g1, 1);
//		printtoFile(distance_g2, 2);
//		System.exit(0);
/*		Set set1_vertices = distance_g1.vertexSet();
		Set set2_vertices = distance_g2.vertexSet();
		
//		Iterator itt1 = set1_edges.iterator();
//		Iterator itt11 = set2_edges.iterator();
		
		Iterator itt2 = set1_vertices.iterator();
		Iterator itt22 = set2_vertices.iterator();
		
/*		while(itt1.hasNext()) {
			DefaultWeightedEdge edge = (DefaultWeightedEdge)itt1.next();
			distance_g.addEdge(distance_g1.getEdgeSource(edge), distance_g1.getEdgeTarget(edge));
		}
		
		System.out.println("Number of vertices in the resulting graph "+set1_vertices.size()+"  "+set2_vertices.size());
		
		
		while(itt2.hasNext()) {
			while(itt22.hasNext()) {
				int source = (Integer)itt2.next();
				int dest = (Integer)itt22.next();
				if ((distance_g1.getEdge(source, dest) == null && distance_g2.getEdge(source, dest)!=null )
						|| (distance_g1.getEdge(source, dest) != null && distance_g2.getEdge(source, dest)==null )) {
					System.out.println("Diff edge");
				}
				else {
					System.out.println("Same edge");
				}
			}
		}

		System.exit(0);
		*/
		
		TreeSet sorted_vertex = new TreeSet(); 
		sorted_vertex.addAll(distance_g.vertexSet());
		int init_size = vertex_cover.size();
		int curr_size = 0;
		int count = 1;
		while (vertex_cover.size() > 10 && init_size!=curr_size ) {
			init_size = curr_size;
			sorted_vertex = new TreeSet(); 
			sorted_vertex.addAll(distance_g.vertexSet());
			Streaming_VertexCover vc1 = new Streaming_VertexCover();
			vertex_cover.clear();
			Set <Integer>vertex_cover1 = Streaming_VertexCover.VertexCoverAlgo_List(distance_g, sorted_vertex);
			vertex_cover.addAll(vertex_cover1);
			curr_size = vertex_cover.size();
			System.out.println("Iteration "+count+" : "+vertex_cover.size());
			Set answer = VertexCovers.find2ApproximationCover(distance_g);
			System.out.println("With the available algo "+answer.size());
			++count;
			//DistanceGraph dg1 = new DistanceGraph();
			Set prev_edges = distance_g.edgeSet();
			Set prev_vertices = distance_g.vertexSet();
			//distance_g.removeAllEdges(prev_edges);
			//distance_g.removeAllVertices(prev_vertices);
			//SimpleGraph<Integer, DefaultWeightedEdge> distance_g1 = DistanceGraph.distance_graph_graph(distance_g, vertex_cover);
			distance_g = DistanceGraph.distance_graph_graph(distance_g, vertex_cover);
			/*Set edges = distance_g.edgeSet();
			Set vertices = distance_g.vertexSet();
			Iterator itt1 = edges.iterator();
			Iterator itt2 = vertices.iterator();
			
			while(itt1.hasNext()) {
				DefaultWeightedEdge edge = (DefaultWeightedEdge)itt1.next();
				distance_g.addEdge(distance_g1.getEdgeSource(edge), distance_g1.getEdgeTarget(edge));
			}
			
			while(itt2.hasNext()) {
				distance_g.addVertex((Integer)itt2.next());
			}*/
		}
		
	}
	
	public static void printtoFile(SimpleGraph<Integer, DefaultWeightedEdge> g, int method) throws IOException{
		String file = "step1_distance"+method+".txt";
	    FileWriter fstream  = new FileWriter(file);
	    BufferedWriter out = new BufferedWriter(fstream);

		NeighborIndex<Integer, DefaultWeightedEdge> adjacency_list = new NeighborIndex<Integer, DefaultWeightedEdge>(g);
		Set vertices = g.vertexSet();
		
		Iterator it = vertices.iterator();
		
		while(it.hasNext()) {
			int source = (Integer)it.next();
			ArrayList<Integer> list = (ArrayList)adjacency_list.neighborListOf(source);
			for (int i=0; i<list.size(); i++) {
				out.write(String.valueOf(source));
				out.write("\t");
				out.write(String.valueOf(list.get(i)));
				out.write("\n");
			}
		}
	    
	    out.close();
	}
	
	
	public static void main(String args[]) throws IOException {
		createStructure();
	}
}