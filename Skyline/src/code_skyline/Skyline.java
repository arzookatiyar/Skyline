package code_skyline;

import java.io.*;
import java.util.*;	
import java.lang.*;

public class Skyline{
	
	public static void skyline(Tuple test, ArrayList<Tuple> sky){
		Iterator I=sky.iterator();
		while (I.hasNext()){
			Tuple x=(Tuple)(I.next());
			Tuple temp=dominator(x,test);
			if (temp==x){
				return;
			}
		}

		sky.add(test);
	}
	

	public static boolean dominates(double[] a, double[] b){
		int i=0;
		boolean inequal=false;
		while (i<a.length && a[i]<=b[i]){
			if(!inequal && a[i]!=b[i])
				inequal = true;
			i++;
		}
		if (i<a.length || !inequal)
			return false;
		return true;
	}
	
	public static boolean dominates(int[] a, int[] b){
		int i=0;
		boolean inequal=false;
		while (i<a.length && a[i]<=b[i]){
			if(!inequal && a[i]!=b[i])
				inequal = true;
			i++;
		}
		if (i<a.length || !inequal)
			return false;
		return true;
	}
	
	/*
	public static DTuple dominator(DTuple a, DTuple b){
		if (dominates(a.rec, b.rec)){
			return a;
		}
		else if (dominates(b.rec,a.rec)){
			return b;
		}
		else
			return null;
	}
	*/
	public static Tuple dominator(Tuple a, Tuple b){
		if (dominates(a.rec, b.rec)){
			return a;
		}
		else if (dominates(b.rec,a.rec)){
			return b;
		}
		else
			return null;
	}

/*
	public static void skyline(DTuple test, ArrayList<DTuple> sky){
		Iterator I=sky.iterator();
		while (I.hasNext()){
			DTuple x=(DTuple)(I.next());
			DTuple temp=dominator(x,test);
			if (temp==x){
				return;
			}
		}
		sky.add(test);
	}
*/
	public static void main(String args[]) {
		//generateGridData("test.data", 1);
		String s = "a b c d";
		String[] row = s.split(" ", 2);
		for(String x:row){
			System.out.println(x);
		}
		
	}
	
	
}
