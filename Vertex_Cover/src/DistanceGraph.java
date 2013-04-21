import java.io.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Hashtable;

import org.jgrapht.alg.NeighborIndex;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;

class DistanceGraph {
	
	
	
	/*
	 * Read a file containing the edges and output a file with the adjacent lists!!! TODO
	 */
	
	public static String distance_graph_memory(SimpleGraph<Integer, DefaultWeightedEdge> g, Set<Integer> cover_vertex) throws IOException{
		
		/* First compute the adjacency list files from the graph!!!
		 * But this is the case when the graph does not fit in the memory
		 * And we can write to this file by multiple scan of the original
		 * graph
		 * */
		String file = "adjacencylist_graph.txt";
	    FileWriter fstream  = new FileWriter(file);
	    BufferedWriter out = new BufferedWriter(fstream);

		
		NeighborIndex<Integer, DefaultWeightedEdge> adjacency_list = new NeighborIndex<Integer, DefaultWeightedEdge>(g);
		Set<Integer> vertices = g.vertexSet();
		
		Iterator it = vertices.iterator();
		
		while(it.hasNext()) {
			int vertex = (Integer)it.next();
			ArrayList<Integer> list = (ArrayList)adjacency_list.neighborListOf(vertex);
			out.write(String.valueOf(vertex));
	
			for (int i=0; i<list.size(); i++) {
				out.write("\t");
				out.write(String.valueOf((Integer)list.get(i)));	
				DefaultWeightedEdge edge = g.getEdge(vertex, (Integer)list.get(i));
				out.write("\t");
				double weight = g.getEdgeWeight(edge);
				out.write(Double.toString(weight));
			}
			out.write("\n");			
		}
		
		out.close();
		return file;
	}
	
	
	public static void CreateEdge(SimpleGraph<Integer, DefaultWeightedEdge> output_graph, int source, int destination, double weight) {
		DefaultWeightedEdge edge = output_graph.getEdge(source, destination);
		DefaultWeightedEdge edge1 = output_graph.getEdge(destination, source);
		if (edge==null && edge1==null) {
		//	System.out.println("New Edge");
			DefaultWeightedEdge new_edge = output_graph.addEdge(source, destination);
			output_graph.setEdgeWeight(new_edge, weight);
		}
		else {
			if (edge!=null) {
				double prev_weight = output_graph.getEdgeWeight(edge);
				//System.out.println("Already Present");
				if (weight < prev_weight) {
					output_graph.setEdgeWeight(edge, weight);
					//System.out.println("Modify");
				}
			}
			if (edge1!=null) {
				double prev_weight = output_graph.getEdgeWeight(edge1);
				//System.out.println("Already Present");
				if (weight < prev_weight) {
					output_graph.setEdgeWeight(edge1, weight);
					//System.out.println("Modify");
				}				
			}
		}		
	}
	
	public static SimpleGraph<Integer, DefaultWeightedEdge> distance_graph_file(String graph_file, Set<Integer> vertex_cover) throws IOException{
		
		SimpleGraph<Integer, DefaultWeightedEdge> output_graph = new SimpleGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Iterator it_cover = vertex_cover.iterator();
		//System.out.println(vertex_cover.size());
		
		while(it_cover.hasNext()) {
			output_graph.addVertex((Integer)it_cover.next());
		}
		
		/* In the graph file, the graph is stored as an adjacency list*/
		FileInputStream stream1 = new FileInputStream(graph_file);
		DataInputStream in1 = new DataInputStream(stream1);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));

		
		String strLine1;
		int block_size=1000;
		int count = 0;
		while((strLine1 = br1.readLine())!=null) {
			String strLine2;
			
			/*Read a block in memory as an array of linkedlists
			 * Experimentally find how many lines are possible*/
			Hashtable<Integer, LinkedList<Integer>> block1 = new Hashtable<Integer, LinkedList<Integer>>();
			Hashtable<Integer, LinkedList<Integer>> block2 = new Hashtable<Integer, LinkedList<Integer>>();

			String []line = strLine1.split("\t");
			LinkedList temp = new LinkedList();
			for (int i=1; i<line.length; i=i+2) {				
				temp.add(Integer.parseInt(line[i]));
				temp.add(Double.parseDouble(line[i+1]));
			}
			block1.put(Integer.parseInt(line[0]), temp);
			++count;
			
			for (int i=0; i<block_size-1; i++) {
				strLine1 = br1.readLine();
				if (strLine1!=null) {
					line = strLine1.split("\t");
					temp = new LinkedList();
					for (int j=1; j<line.length; j=j+2) {
						temp.add(Integer.parseInt(line[j]));
						temp.add(Double.parseDouble(line[j+1]));
					}
					block1.put(Integer.parseInt(line[0]), temp);
					++count;
				}
				else {
					break;
				}
			}

			//System.out.println("Relation 1 "+ count);
			int count2 = 0;
			
			FileInputStream stream2 = new FileInputStream(graph_file);
			DataInputStream in2 = new DataInputStream(stream2);
			BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
			
			
			while ((strLine2=br2.readLine())!=null) {
				//System.out.println("Reading next line");
				line = strLine2.split("\t");
				temp = new LinkedList();
				for (int i=1; i<line.length; i=i+2) {
					temp.add(Integer.parseInt(line[i]));
					temp.add(Double.parseDouble(line[i+1]));
				}
				block2.put(Integer.parseInt(line[0]), temp);
				++count2;
				
				for (int i=0; i<block_size-1; i++) {
					strLine2 = br2.readLine();
					if (strLine2!=null) {
						line = strLine2.split("\t");
						temp = new LinkedList();
						for (int j=1; j<line.length; j=j+2) {
							temp.add(Integer.parseInt(line[j]));
							temp.add(Double.parseDouble(line[j+1]));
						}
						block2.put(Integer.parseInt(line[0]), temp);
						++count2;
					}
					else {
						break;
					}
				}
				
				//System.out.println("Inner Relation2 "+count2+"  "+count);
				/*Here there are two blocks block1 and block2 in the memory*/
				
				Set keyset1 = block1.keySet();
				Set keyset2 = block2.keySet();
				
				Iterator it1 = keyset1.iterator();
				Iterator it2 = keyset2.iterator();
				
				while (it1.hasNext()) {  //Step 4
					int source = (Integer)it1.next();
					//System.out.println("############################SOURCE#############################");
					//System.out.println(source);
					LinkedList source_list = ((LinkedList)block1.get(source));
					
					/*		for (int q=0; q<source_list.size(); q=q+2) {
								System.out.print((Integer)source_list.get(q)+"  "+(Double)source_list.get(q+1)+"     ");
							}
							System.out.println();*/

					if (vertex_cover.contains(source)) { //Step 4 ends
						while(it2.hasNext()) { //Step 5
							int dest = (Integer)it2.next();
							
							double edge_weight = 0.0;
							for (int q=0; q<source_list.size(); q=q+2) {
								if ((Integer)source_list.get(q) == dest) {  //Step 6
									edge_weight = (Double)source_list.get(q+1);
									if (!vertex_cover.contains(dest)) { //Step 7
										LinkedList adj_list = (LinkedList)block2.get(dest);
										for(int l=0; l<adj_list.size(); l=l+2) { //Step 8
											//We want to create edge between src and the elements in the adj_list.
											//We need not check if these vertices lie in the vertex cover because they ought to lie in one
											if (source != (Integer)adj_list.get(l)) {
												CreateEdge(output_graph, source, (Integer)adj_list.get(l), edge_weight+(Double)adj_list.get(l+1)); //Step 9
												//System.out.println("Edge1 "+source+"  "+(Integer)adj_list.get(l)+"  "+edge_weight+(Double)adj_list.get(l+1));
												//System.out.println(adj_list.size()+" pp "+l);
											}
												
										}
									}
									else { //Step 10
										//We want create an edge between the src and dest
										CreateEdge(output_graph, source, dest, edge_weight); //Step 11
										//System.out.println("Edge2 "+source+"  "+dest+" "+edge_weight);
									}

								}
								else {
									//System.out.println("Cannot join ");
								}
							}
							
						}
						
					}
					else {
						continue;
					}
				}
				
				block2.clear();
			}
			br2.close();

			block1.clear();
		}
		
		br1.close();
		
		
		/*Here we have assumed that the final graph can be stored in the memory
		which might not be the case
		Need to modify that. by not forming the vertices but to write them in the file*/
		return output_graph;
	}

	
	
	
	public static SimpleGraph<Integer, DefaultWeightedEdge> distance_graph_graph(SimpleGraph<Integer, DefaultWeightedEdge> g, Set<Integer> vertex_cover) throws IOException{
		
		SimpleGraph<Integer, DefaultWeightedEdge> output_graph = new SimpleGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Iterator it_cover = vertex_cover.iterator();
		
		while(it_cover.hasNext()) {
			output_graph.addVertex((Integer)it_cover.next());
		}
		
		NeighborIndex<Integer, DefaultWeightedEdge> adjacency_list = new NeighborIndex<Integer, DefaultWeightedEdge>(g);

		it_cover = vertex_cover.iterator();
		//int count_s = 0;
		//System.out.println("vertex cover "+vertex_cover.size());
		int count = 0;
		while(it_cover.hasNext()) {
			int source = (Integer)it_cover.next();
		//	++count_s;
			//System.out.println("Count "+(++count));
			ArrayList<Integer> list = (ArrayList)adjacency_list.neighborListOf(source);
			for (int i=0; i<list.size(); i++) {
				int dest = (Integer)list.get(i);
				DefaultWeightedEdge edge1 = g.getEdge(source, dest);
				double weight1 = g.getEdgeWeight(edge1);

				if (!vertex_cover.contains(dest)) {
					ArrayList adj_list = (ArrayList)adjacency_list.neighborListOf(dest);
					for(int l=0; l<adj_list.size(); l++) { //Step 8
						//We want to create edge between src and the elements in the adj_list.
						//We need not check if these vertices lie in the vertex cover because they ought to lie in one
						int end_vertex = (Integer)adj_list.get(l);
						DefaultWeightedEdge edge2 = g.getEdge(dest, end_vertex);
						double weight2 = g.getEdgeWeight(edge2);
						if (source != (Integer)adj_list.get(l) && vertex_cover.contains(end_vertex)){
							CreateEdge(output_graph, source, (Integer)adj_list.get(l), weight1+weight2); //Step 9
							//System.out.println("Creates an edge between1 "+source+"  "+(Integer)adj_list.get(l)+"  "+(weight1+weight2));
						}
					}					
				}
				else {
					CreateEdge(output_graph, source, dest, weight1); //Step 11
					//System.out.println("Creates an edge between2 "+source+"  "+dest+"  "+weight1);
				}
			}
		}
		//System.out.println("Total number of sources "+count_s);
		return output_graph;
	}
	
	
	
	public static void main(String args[]) throws IOException {
		
	}
}