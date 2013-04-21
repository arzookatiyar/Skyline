package Code_woFileIO;
import java.util.ArrayList;

class Tuple_ArrayList {
    ArrayList<Tuple> skyline;
    ArrayList<Tuple> non_skyline;

    public Tuple_ArrayList(ArrayList<Tuple> skyline, ArrayList<Tuple> non_skyline) {
	this.skyline = skyline;
	this.non_skyline = non_skyline;
    }
    
    public static void print_Tuple_ArrayList(Tuple_ArrayList list) {
    	System.out.println("Tuple_ArrayList ----------------->");
    	System.out.println("Printing the skyline part.....");
    	for(int i=0; i<list.skyline.size(); i++) {
    	    Tuple t = (Tuple)(list.skyline.get(i));
    	    Tuple.print_Tuple(t);
    	    System.out.println();
    	}
    	System.out.println("Printing the non_skyline part.....");
    	for(int i=0; i<list.non_skyline.size(); i++) {
    	    Tuple t = (Tuple)(list.non_skyline.get(i));
    	    Tuple.print_Tuple(t);
    	    System.out.println();
    	}
    }
}