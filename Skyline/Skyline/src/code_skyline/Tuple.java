package code_skyline;

import java.io.*;
import java.util.*;	
import java.lang.*;

class Tuple{
	int[] rec;
	int ID;
	int sum;
	
	public Tuple(String s){
		String[] temp = s.split(" ");
		int iter=0;
		this.rec=new int[temp.length];
		while (iter<rec.length){
			rec[iter]=Integer.parseInt(temp[iter]);
			iter++;
		}
	}
	public Tuple(String s, int ID){
		this.ID=ID;
		String[] temp = s.split(" ");
		int iter=0;
		this.rec=new int[temp.length];
		while (iter<rec.length){
			rec[iter]=Integer.parseInt(temp[iter]);
			iter++;
		}
	}
	
	public Tuple(int[] a, int ID){
		this.ID=ID;
		this.rec=a;
	}
	
	public Tuple(DoubleTuple t, int n){
		this.rec = new int[t.rec.length];
		for(int i=0;i<this.rec.length;i++){
			this.rec[i] = (int) Math.ceil(t.rec[i] * n);
		}
	}
	
	public void writeToFileWithoutId(BufferedWriter out) throws Exception{
		for(int x: rec){
			out.write(x+" ");
		}
		out.write("\n");
	}
	
	public void writeToFile(BufferedWriter out) throws Exception{
		out.write(ID+" ");
		for(int x: rec){
			out.write(x+" ");
		}
		out.write("\n");
	}
	
	public static void computeEntropy(ArrayList<Tuple> data) {

		for(Tuple t: data) {
			t.sum = 0;
			for(int i=0;i<t.rec.length;i++) {
				t.sum = t.sum + t.rec[i];
			}
			
		}
	}
	
	
}
