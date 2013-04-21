package code_skyline;
/*
	Reads data line by line and finds out skyline (classic way). Expects data to be sorted beforehand.
	First column in the data is ID.
	
	inputfile: args[0] (original name)
	output:
	exactskyids:args[0]_sort_exactsky.data
	time: TimeExactSky.txt
*/

import java.io.*;
import java.util.*;	
import java.lang.*;

public class ExactSkyline{
	
	public static ArrayList<Tuple> exactSky(String file){
		ArrayList<Tuple> Sky=new ArrayList<Tuple>();

		//readData line by line
		
		try{
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String temp;
			String[] row;
			while ((temp=br.readLine())!=null){
				row = temp.split(" ",2);
				Tuple t = new Tuple(row[1], Integer.parseInt(row[0]));
				//modify skyline
				Skyline.skyline(t, Sky);
			}
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
		long startTime = System.nanoTime();
		String datafile = args[0];
		ArrayList<Tuple> exactSkyID=exactSky(datafile+"_sort.data");
	
		System.out.println("exact done!");
		long endTime = System.nanoTime();
		writeToFile(exactSkyID, datafile+"_sort_exactsky.data");
		
		try{
			FileWriter fstream = new FileWriter("TimeExactSky.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			
			FileWriter fstreamTime = new FileWriter("TimeWithoutGridding.txt", true);
			BufferedWriter outTime = new BufferedWriter(fstreamTime);
			
			// write #exactskytime #exactskysize
			outTime.write(""+ (endTime-startTime)+" "+exactSkyID.size()+"\n");
			outTime.close();
			
			out.write("file:"+args[0]+"\n");
			out.write("time:"+(endTime-startTime)+"\n");
			out.write("size:"+exactSkyID.size()+"\n\n");
			out.close();				
		}catch(Exception e){System.out.println("main():"+e);}

	}
	
	
}
