import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FirstSmoother {
    public static void smootherResults(File csv, int windowValue) throws IOException{

		//creates two arraylists to hold the x and y values
		ArrayList<Integer> xValues = new ArrayList<>();
		ArrayList<Integer> yValues = new ArrayList<>();

		//creates everything necessary to read from a csv and write into a new one
		FileWriter resultsFile = new FileWriter("smootherResults.csv");
		BufferedWriter test = new BufferedWriter(resultsFile);
		BufferedReader br = new BufferedReader(new FileReader(csv));
		String[] token = new String[2];
		String line;
		int count = 1;
		
		//while there are still lines in the CSV, split each line into an array by the comma
		while((line = br.readLine()) != null) {
			token = line.split(",");

			//adds an integer at index 0 of token to xValues and at index 1 of token to yValues
			xValues.add(Integer.parseInt(token[0]));
			yValues.add(Integer.parseInt(token[1]));
		}
		
		//for the size of the arraylist, set yValues[i] to the mean
		for(int i = 0; i < yValues.size(); i++) {

			//for the windowValue specified, see if there is a value before or after the i value
			for(int j = 1; j <= windowValue; j++) {

				//if there is something before the i value, add it to the value at location yValues[i]
				if(i - j >= 0){
                    yValues.set(i, yValues.get(i) + yValues.get(i - j));
                    count++;
                }

				//if there is something after the i value, add it to the value at location yValues[i]
                if(i + j < yValues.size()){
                    yValues.set(i, yValues.get(i) + yValues.get(i + j));
                    count++;
                }
				
			}

			//find the mean of the total values that was added up and return count to 1
            yValues.set(i, yValues.get(i) / count);
            count = 1;
		}
        br.close();
		
		//creates an array of strings of sthe same size as xValues
        String[] dataPoints = new String[xValues.size()];

		//for the size of xValues, add x and y values of i index into a String
		for(int i = 0; i < xValues.size(); i++) {
			dataPoints[i] = String.valueOf(xValues.get(i)) + ',' + String.valueOf(yValues.get(i)) + ',';
			String data = String.join(",", dataPoints[i]);

			//write the String onto a new CSV file and skip to next line
            test.write(data);
            test.newLine();
		}

        test.close();
	}

}
