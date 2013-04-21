import java.io.*;

class DecreaseId {
	public static void main(String args[]) throws IOException {
		FileInputStream stream = new FileInputStream("roadNet-CA.txt");
		DataInputStream in = new DataInputStream(stream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
	    FileWriter fstream = new FileWriter("roadNet-CA_small.txt");
	    BufferedWriter out = new BufferedWriter(fstream);	


		String strLine;
		
		while ((strLine=br.readLine())!=null) {
			String []line = strLine.split("\t");
			//System.out.println(line[0]+" "+line[1]);
			if (line.length == 2) {
				int source = Integer.parseInt(line[0]);
				int dest = Integer.parseInt(line[1]);
				if (source <1965 && dest <1965) {
					out.write(String.valueOf(source));
					out.write("\t");
					out.write(String.valueOf(dest));
					out.write("\n");
				}
			}
			
		}
		
		br.close();
		out.close();
		
	}
}