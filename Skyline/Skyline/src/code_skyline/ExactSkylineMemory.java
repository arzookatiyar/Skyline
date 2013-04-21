package code_skyline;

import java.io.*;
import java.util.*;	
import java.lang.*;

public class ExactSkylineMemory{
	
	public static ArrayList<Tuple> exactSky(String file){
		ArrayList<Tuple> Sky=new ArrayList<Tuple>();

		//readData line by line
		
		try{
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String temp;
			String[] row;
			
			ArrayList<Tuple> data = new ArrayList<Tuple>();
			
			while ((temp=br.readLine())!=null){
				row = temp.split(" ",2);
				Tuple t = new Tuple(row[1], Integer.parseInt(row[0]));
				data.add(t);
			}
			
			int size = data.size();
			long startTime = System.nanoTime();
			
			for(int i=0;i<size;i++){
				//modify skyline
				Skyline.skyline(data.get(i), Sky);
			}
			
			long endTime = System.nanoTime();
			
			FileWriter fstream1 = new FileWriter("TimeExactSkyMemory.txt", true);
			BufferedWriter out1 = new BufferedWriter(fstream1);
			out1.write("file:"+file+"\n");
			out1.write(""+(endTime-startTime)+"\n");
			out1.close();
			
		}catch(Exception e){System.out.println("exactSky(): "+e);}
		return Sky;
	}
	
	public static void writeToFile(ArrayList<Tuple> a, String file){
		try{
		FileWriter fstream = new FileWriter(file);
		BufferedWriter out = new BufferedWriter(fstream);
		for (Tuple x: a){
			out.write(Integer.toString(x.ID)+" ");
		}
		out.close();
		}catch(Exception e){System.out.println("writeToFile():"+e);}
	}

	public static void main(String args[]) {
		
		String datafile = args[0];
		ArrayList<Tuple> exactSkyID=exactSky(datafile+"_sort.data");
	
		//System.out.println("exact done!");
		
		//writeToFile(exactSkyID, datafile+"_sort_exactsky.data_temp");
		
	}
	
	
}
