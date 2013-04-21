import java.io.*;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.NeighborIndex;
import java.util.Hashtable;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Enumeration;

class Streaming_VertexCover {

	static Hashtable table = new Hashtable();
	static ArrayList vertex_list = new ArrayList();
	
	public static Set<Integer> compute_disk (String file) throws IOException {
		FileInputStream stream = new FileInputStream(file);
		DataInputStream in = new DataInputStream(stream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		SimpleGraph<Integer, DefaultWeightedEdge> g = new SimpleGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		String strLine;
		while ((strLine=br.readLine())!=null) {
			/*Every line is an edge*/
			String []line = strLine.split(" ");
			//System.out.println(line[0]);
			if (line.length == 4) {
				int source = Integer.parseInt(line[1]);
				int dest = Integer.parseInt(line[2]);
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
					g.setEdgeWeight(edge, (double)Integer.parseInt(line[3]));
			}		
		}
		br.close();
		
		Set<Integer> vertexSet = g.vertexSet();
				
		TreeSet<Integer> sorted_vertexSet = new TreeSet<Integer>();
		sorted_vertexSet.addAll(vertexSet);
		System.out.println("Formed a graph");
		
		Iterator it = sorted_vertexSet.iterator();
		//System.out.println(sorted_vertexSet.size());
	/*	while(it.hasNext()) {
			System.out.println((Integer)it.next());
		}
		System.out.println("ENDS...");*/		
		//System.out.println("Size of the vertex cover "+VertexCoverAlgo(g, sorted_vertexSet));
	//	System.out.println("Size of the vertex cover "+VertexCoverAlgo_List(g, sorted_vertexSet));
		return VertexCoverAlgo_List(g, sorted_vertexSet);
	}
	
	
	public static Set<Integer> compute_graph (SimpleGraph<Integer, DefaultWeightedEdge> g) throws IOException {
		Set<Integer> vertexSet = g.vertexSet();
				
		TreeSet<Integer> sorted_vertexSet = new TreeSet<Integer>();
		sorted_vertexSet.addAll(vertexSet);
		
		Iterator it = sorted_vertexSet.iterator();
		//System.out.println(sorted_vertexSet.size());
	/*	while(it.hasNext()) {
			System.out.println((Integer)it.next());
		}
		System.out.println("ENDS...");*/		
		//System.out.println("Size of the vertex cover "+VertexCoverAlgo(g, sorted_vertexSet));
	//	System.out.println("Size of the vertex cover "+VertexCoverAlgo_List(g, sorted_vertexSet));
		return VertexCoverAlgo_List(g, sorted_vertexSet);
	}
	
	
	/*
	 * Uses a hastable as specified in the paper
	 */
	
	public static Set<Integer> VertexCoverAlgo(SimpleGraph<Integer, DefaultWeightedEdge> g, TreeSet<Integer> sorted_vertex) {
		Iterator it = sorted_vertex.iterator();
		NeighborIndex<Integer, DefaultWeightedEdge> adjacency_list = new NeighborIndex<Integer, DefaultWeightedEdge>(g);
		
		LinkedList free_keys = new LinkedList();
		int size_cover = 0;
		Set vertex_cover = new HashSet();

		while(it.hasNext()) {
			int vertex = (Integer)it.next();
			System.out.println(" Considering vertex "+vertex);
			if (table.containsValue(vertex)) {
				//System.out.println(vertex);
				vertex_cover.add(vertex);
				++size_cover;
				/*remove this vertex from the table*/
				Enumeration e = table.keys();
				while (e.hasMoreElements()) {
					int next_key = (Integer)e.nextElement();
					if ((Integer)(table.get(next_key)) == vertex) {
						free_keys.add(next_key);
						table.remove(next_key);
					}
				}
				continue;
			}
			else {
				ArrayList<Integer> list = (ArrayList)adjacency_list.neighborListOf(vertex);
				for (int i=0; i<list.size(); i++) {
					int vertex_n = list.get(i);
					if (vertex_n > vertex && !(table.containsValue(vertex_n))) {  //ordered after vertex
						//System.out.println(vertex);
						vertex_cover.add(vertex);
						++size_cover;
						if (free_keys.size()>0) {
							table.put(free_keys.remove(0), vertex_n);
						}
						else {
							table.put(table.size(), vertex_n);
						}
						break;
					}
				}
			}			
		}		
		return vertex_cover;
	}
	

	/*
	 * Uses a Linkedllist not like hashtable in the paper	 */


	public static Set<Integer> VertexCoverAlgo_List(SimpleGraph<Integer, DefaultWeightedEdge> g, TreeSet<Integer> sorted_vertex) {
		Iterator it = sorted_vertex.iterator();
		//System.out.println("Starting "+sorted_vertex.size());
		NeighborIndex<Integer, DefaultWeightedEdge> adjacency_list = new NeighborIndex<Integer, DefaultWeightedEdge>(g);
		
		int size_cover = 0;
		Set vertex_cover = new HashSet();
		
		while(it.hasNext()) {
			int vertex = (Integer)it.next();
			System.out.println(" Considering vertex "+vertex);
			if (vertex_list.contains(vertex)) {
				//System.out.println(vertex);
				vertex_cover.add(vertex);
				++size_cover;
				/*remove this vertex from the list*/
				vertex_list.remove((Integer)vertex);
				continue;
			}
			else {
				ArrayList<Integer> list = (ArrayList)adjacency_list.neighborListOf(vertex);
				for (int i=0; i<list.size(); i++) {
					int vertex_n = list.get(i);
					if (vertex_n > vertex && !(vertex_list.contains(vertex_n))) {  //ordered after vertex
						//System.out.println(vertex);
						vertex_cover.add(vertex);
						++size_cover;
						vertex_list.add(vertex_n);
						break;
					}
				}
			}			
		}
		//System.out.println("Ending "+vertex_cover.size());
		return vertex_cover;
	}
	

	
	
	public static void main(String args[]) throws IOException {
		Streaming_VertexCover vv = new Streaming_VertexCover();
		System.out.println("The size of the vertex cover is " + vv.compute_disk("USA-road-d.CAL.gr"));
	}
}