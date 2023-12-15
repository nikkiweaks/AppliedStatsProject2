import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class FirstPlotter {
    public static void plotterResults(int m, int b, int minRange, int totalRuns, int interval) throws IOException{
		
		//creates all necessary components to write into a csv file
		FileWriter resultsFile = new FileWriter("plotResults.csv");
        BufferedWriter test = new BufferedWriter(resultsFile);

		//creates two arraylists to hold the x and y values
		ArrayList<Integer> xValues = new ArrayList<>();
		ArrayList<Integer> yValues = new ArrayList<>();
		
		//for the total range of the program that are within the interval, solve the equation with the given x
		for(int i = 0; i <= totalRuns; i++) {
			//if it is within the interval, add in x to xValues and solve the equation, add that to yValues
			if(i % interval == 0) {
				xValues.add(minRange + i);
				yValues.add((m * (minRange + i)) + b);
			}
		}
		//creates an array of strings to help print data onto csv
        String[] dataPoints = new String[totalRuns + 1];
		
		//for the size of the arraylist, print x and y values into an array to create a String
		for(int i = 0; i < xValues.size(); i++) {
			dataPoints[i] = (String.valueOf(xValues.get(i)) + ',' + String.valueOf(yValues.get(i)) + ',');
			String data = String.join(",", dataPoints[i]);
			//writes the string onto the csv and skips to the next line
            test.write(data);
            test.newLine();
		}
        test.close();
	}

}
