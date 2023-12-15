import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.math4.legacy.stat.descriptive.DescriptiveStatistics;

public class SmootherWithApache{
    public static void smoothingWithApache(File csv, int windowValue)throws IOException{
        //creates all file readers and writers
		FileWriter resultsFile = new FileWriter("smootherResultsUsingApache.csv");
		BufferedWriter test = new BufferedWriter(resultsFile);
		BufferedReader br = new BufferedReader(new FileReader(csv));

        //creates an array of strings that is the length of 2 and an arraylist of integers
		String[] token = new String[2];
        ArrayList<Integer> yValues = new ArrayList<>();

		String line;
        long nLines = 0;

        //Uses the DescriptiveStatistics class from Apache to help create a rolling mean and set the size of values it would take at windowValue
        DescriptiveStatistics stats = new DescriptiveStatistics();
        stats.setWindowSize(windowValue);

        //while loop that reads through the csv file, spliting the x and y values with token and adding to nLines with every new line
        while((line = br.readLine()) != null) {
			token = line.split(",");
			nLines++;
            //turns the string of Y into a double and adds it to stats
            stats.addValue(Double.parseDouble(token[1]));
            //if the number of lines is the same as the windowValue inputed, find the mean and return nLines to 0
            if (nLines == windowValue) {
                nLines = 0;
                yValues.add((int)(stats.getMean()));
            }
		}
        //writes data into a new csv file
        for(int i = 0; i < yValues.size(); i++){
            String data = String.valueOf(i + 1) + "," + String.valueOf(yValues.get(i));
            test.write(data);
            test.newLine();
        }
        test.close();

    }
}