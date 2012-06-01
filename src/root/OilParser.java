package root;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OilParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(new File("oliveOilsTRAIN.dat"));

		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		
		File outfile = new File("oliveOilsTRAIN.nn");
		FileWriter writer = new FileWriter(outfile ,true);
		
		Pattern pat = Pattern.compile("(\\d*\\.\\d+) (\\d*\\.\\d+) (\\d*\\.\\d+) (\\d*\\.\\d+) (\\d*\\.\\d+) (\\d*\\.\\d+) (\\d*\\.\\d+) (\\d*\\.\\d+) (\\d) (\\d) (\\d) (\\d) (\\d) (\\d) (\\d) (\\d) (\\d)");

		writer.write("G BEGINGROUP OilTest" + System.getProperty("line.separator"));
		while ((strLine = br.readLine()) != null) {
			Matcher mat = pat.matcher(strLine);
			if(mat.find()) {
				for(int i = 1; i < 18; i++) {
					if(i == 1) {
						writer.write("G IN");
					}
					if(i < 8) {
						writer.write(" " + mat.group(i));
					} 
					if (i == 8) {
						writer.write(System.getProperty("line.separator") + "G OUT");
					}
					if( i > 8) {
						writer.write(" " + mat.group(i));
					}
				}
				writer.write(System.getProperty("line.separator"));
				//System.out.println("found " + mat.groupCount() + " groups");
			}
		}
		writer.write("G END" + System.getProperty("line.separator"));
		writer.flush();
		writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
