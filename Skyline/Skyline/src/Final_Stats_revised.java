import java.io.*;
import java.util.ArrayList;

class Final_Stats_revised {
	static String folder = "Sample_revisedtemp30/";
	static int number = 30;
    static ArrayList nodes_list = new ArrayList();


	public static void main(String args[]) throws IOException{
		
	    FileWriter fw11 = new FileWriter(folder+"stats_"+number+".txt");
	    BufferedWriter bw11 = new BufferedWriter(fw11);

		FileInputStream stream = new FileInputStream(folder+"relation_node_"+number+".txt");
		DataInputStream in = new DataInputStream(stream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		
		while ((strLine = br.readLine())!=null) {
			String[] str_array = strLine.split("\t");
			nodes_list.add(Integer.parseInt(str_array[0]));
		}
		
	    Relations s = new Relations();
	    s.find_Relations();
	    
		
		for (int i=0; i<nodes_list.size(); i++) {
		//for (int i=0; i<1; i++) {
		    FileWriter fw = new FileWriter(folder+"relation_s"+number+".txt");
		    BufferedWriter bw2 = new BufferedWriter(fw);
		    bw2.write(String.valueOf(nodes_list.get(i)));
		    bw2.close();
		    
		for (int j=0; j<nodes_list.size(); j++) {
		//    for (int j=13; j<14; j++) {
				System.out.println(i+"   "+j);
				if (i!=j) {
				    FileWriter fw1 = new FileWriter(folder+"relation_d"+number+".txt");
				    BufferedWriter bw1 = new BufferedWriter(fw1);
				    bw1.write(String.valueOf(nodes_list.get(j)));
				    bw1.close();
				    s.update_sd();
				    long start_time1 = System.nanoTime(); 
				    	
				    ArrayList number_results1 = Aggregated_Skyline.find_aggregatedskyline();
				    long end_time1 = System.nanoTime();
				    long start_time2 = System.nanoTime(); 
				    ArrayList number_results2 = ASJQ_revised.find_aggregatedskyline();
				    long end_time2 = System.nanoTime();
				    
				    bw11.write(String.valueOf(nodes_list.get(i)));
				    bw11.write("\t");
				    bw11.write(String.valueOf(nodes_list.get(j)));
				    bw11.write("\t");
				    for (int k=0; k<number_results1.size(); k++) {
				    	bw11.write(String.valueOf(number_results1.get(k)));
				    	bw11.write("\t");
				    }
				   // bw11.write(String.valueOf(number_results1)); //Change this!!
				    bw11.write("\t");
				    bw11.write(String.valueOf((end_time1-start_time1)/1000));
				    bw11.write("\t");
				    for (int k=0; k<number_results2.size(); k++) {
				    	bw11.write(String.valueOf(number_results2.get(k)));
				    	bw11.write("\t");
				    }

				    bw11.write("\t");
				    bw11.write(String.valueOf((end_time2-start_time2)/1000));
				    bw11.write("\n");				    
				}
			}						
		}
		bw11.close();
	}
}