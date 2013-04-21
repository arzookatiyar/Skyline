import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import org.jgrapht.graph.*;
import org.jgrapht.alg.VertexCovers;

class findCover {
	
	//int adjacency_matrix[][] = new int[159651][159651];
	
		
	public void compute() throws IOException{
		FileInputStream stream = new FileInputStream("USA-road-d.CAL.gr");
		DataInputStream in = new DataInputStream(stream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		//MyGraph graph = new MyGraph();
		SimpleGraph<Integer, DefaultEdge> g = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
		

		String strLine;
		while ((strLine=br.readLine())!=null) {
			/*Every line is an edge*/
			String []line = strLine.split(" ");
			if (line.length == 4) {
				int source = Integer.parseInt(line[1]);
				int dest = Integer.parseInt(line[2]);
				if (!g.containsVertex(source))
					g.addVertex(source);

				if (!g.containsVertex(dest))
					g.addVertex(dest);
				
				//this.adjacency_matrix[source][dest] = 1;
				//graph.addEdge(source, dest);
	//			System.out.println(source+" "+dest);
				g.addEdge(source, dest);
			}		
		}
		br.close();
		
		HashSet answer = (HashSet)VertexCovers.find2ApproximationCover(g);

		int new_size = answer.size();
		//Iterator it = answer.iterator();
	/*	while(it.hasNext()){
			System.out.println((Integer)it.next());
		}*/
		int old_size  = g.vertexSet().size();
		System.out.println(old_size+ "  "+new_size);
		float ratio = ((float)new_size)/((float)old_size);
		System.out.println("Ratio "+ratio);
		
		
		 answer = (HashSet)VertexCovers.findGreedyCover(g);
		 new_size = answer.size();
		 old_size  = g.vertexSet().size();
		 System.out.println(old_size+ "  "+new_size);
		 ratio = ((float)new_size)/((float)old_size);
		 System.out.println("Ratio "+ratio);
		 
		
	}
	
	public static void main(String args[]) throws IOException {
		findCover f = new findCover();
		f.compute();
	}
}