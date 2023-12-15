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

		//creates two arraylists to hold the x and y values
		ArrayList<Integer> xValues = new ArrayList<>();
		ArrayList<Integer> yValues = new ArrayList<>();

		//creates everything necessary to read from a csv and write into a new one
		BufferedReader br = new BufferedReader(new FileReader(csv));
		FileWriter resultsFile = new FileWriter("saltResults.csv");
		BufferedWriter test = new BufferedWriter(resultsFile);
		Random rd = new Random();
		String[] token = new String[2];
		String line;

		//while there are still lines in the CSV, split each line into an array by the comma
		while((line = br.readLine()) != null) {
			token = line.split(",");

			//adds an integer at index 0 of token to xValues and at index 1 of token to yValues
			xValues.add(Integer.parseInt(token[0]));
			yValues.add(Integer.parseInt(token[1]));
		}
		
		//for the size of the arraylist, randomly choose 0 or 1
		for(int i = 0; i < yValues.size(); i++) {
			int choice = rd.nextInt(1);

			//if random chooses 0, add a random number to the value at yValues[i]
			if(choice == 0) {
				yValues.set(i, yValues.get(i) + rd.nextInt(range));
			//if random chooses 1, subtract a random number to the value at yValues[i]
			}else {
				yValues.set(i, yValues.get(i) - rd.nextInt(range));
			}	
		}
        br.close();
		
		//creates an array of strings of sthe same size as xValues
        String[] dataPoints = new String[xValues.size()];

		//for the size of xValues, add x and y values of i index into a String
		for(int i = 0; i < xValues.size(); i++) {
			dataPoints[i] = (String.valueOf(xValues.get(i)) + ',' + String.valueOf(yValues.get(i)) + ',');
			String data = String.join(",", dataPoints[i]);

			//write the String onto a new CSV file and skip to next line
            test.write(data);
            test.newLine();
		}
        test.close();
	}

}
