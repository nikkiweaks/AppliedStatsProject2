import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FirstSalter {
    public static void salterResults(File csv, int range) throws IOException{
		ArrayList<Integer> xValues = new ArrayList<>();
		ArrayList<Integer> yValues = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(csv));
		FileWriter resultsFile = new FileWriter("saltResults.csv");
		BufferedWriter test = new BufferedWriter(resultsFile);
		Random rd = new Random();
		String[] token = new String[2];
		String line;
		
		while((line = br.readLine()) != null) {
			token = line.split(",");
			xValues.add(Integer.parseInt(token[0]));
			yValues.add(Integer.parseInt(token[1]));
		}
		
		for(int i = 0; i < yValues.size(); i++) {
			int choice = rd.nextInt(1);
			if(choice == 0) {
				yValues.set(i, yValues.get(i) + rd.nextInt(range));
			}else {
				yValues.set(i, yValues.get(i) - rd.nextInt(range));
			}	
		}
        br.close();
		
        String[] dataPoints = new String[xValues.size()];

		for(int i = 0; i < xValues.size(); i++) {
			dataPoints[i] = (String.valueOf(xValues.get(i)) + ',' + String.valueOf(yValues.get(i)) + ',');
			String data = String.join(",", dataPoints[i]);
            test.write(data);
            test.newLine();
		}
        test.close();
	}

}
