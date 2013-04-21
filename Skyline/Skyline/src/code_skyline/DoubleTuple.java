package code_skyline;

import java.io.*;
import java.util.*;	
import java.lang.*;

class DoubleTuple{
    double[] rec;
    int ID;
    int sum;
    
    public DoubleTuple(int dim){
	rec = new double[dim];
    }
    public DoubleTuple(String s){
	String[] temp = s.split(" ");
	int iter=0;
	this.rec=new double[temp.length];
	while (iter<rec.length){
	    rec[iter]=Double.parseDouble(temp[iter]);
	    iter++;
	}
    }
    
    public static double distance(DoubleTuple a, DoubleTuple b){
	double res = 0.0;
	for(int i=0;i<a.rec.length;i++){
	    res += (a.rec[i]-b.rec[i])*(a.rec[i]-b.rec[i]);
	}
	return Math.sqrt(res);
    }
    
    public void writeToFile(BufferedWriter out) throws Exception{
	for(int i=0;i<this.rec.length;i++){
	    out.write(this.rec[i]+" ");
	}
	out.write("\n");
    }
    
    
    
}
