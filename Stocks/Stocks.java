import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Stocks{
    public static ArrayList<Integer> collectingStockData(File csv) throws IOException{

        //create arraylists for important information regarding the stocks
        ArrayList<Integer> open = new ArrayList<>();
		ArrayList<Integer> high = new ArrayList<>();
        ArrayList<Integer> low = new ArrayList<>();
        ArrayList<Integer> close = new ArrayList<>();

        //read in the csv
		BufferedReader br = new BufferedReader(new FileReader(csv));
		String[] token = new String[2];
		String line;

        //skips the first line
        br.readLine();

        //while there are still lines in the CSV, split each line into an array by the comma
        while((line = br.readLine()) != null) {
			token = line.split(",");

            //adds everything to the corasponding array, skips first index as it is the date
			open.add((int)(Double.parseDouble(token[1])));
			high.add((int)(Double.parseDouble(token[2])));
            low.add((int)(Double.parseDouble(token[3])));
            close.add((int)(Double.parseDouble(token[4])));
		}

        //only returns close, as thats the only information I needed for the stock bot
        return close;
    }

    //RSI calculator
    public static double RSI(int N, ArrayList<Integer> close, int startingDate){
        double RSI = 0;
        double RS = 0;
        double AvgU = 0;
        double AvgD = 0;
        double AvgUCount = 0;
        double AvgDCount = 0;

        //for starting dat to N, check if the closing stock of the previous day is more or less than the current day
        for(int i = startingDate; i < N; i++){

            //if the stock is higher than the previous day, add it to AvgU and add one to the count
            if(close.get(i) > close.get(i - 1)){
                AvgU = AvgU + (close.get(i - 1) - close.get(i));
                AvgUCount++;
            }

            //if the stock is lower than the previous day, add it to AvgD and add one to the count
            if(close.get(i) < close.get(i - 1)){
                AvgD = AvgD + (close.get(i) - close.get(i - 1));
                AvgDCount++;
            }
        }

        //divide AvgU by its count and AvgD by its count to find the means of both
        AvgU = AvgU / AvgUCount;
        AvgD = AvgD / AvgDCount;

        //calculates RSI
        RS = AvgD / AvgU;
        RSI = 100 - 100 / (1 + RS);
        return RSI;
    }

    //pretty much the same smoother as my previous smoother, just skips the first two lines and Arraylists are of doubles, not integers
    public static void smootherResults(File csv, int windowValue) throws IOException{

        //creates two arraylists to hold the open and close values
		ArrayList<Double> open = new ArrayList<>();
		ArrayList<Double> close = new ArrayList<>();

        //creates everything necessary to read from a csv and write into a new one
		FileWriter resultsFile = new FileWriter("smootherResults.csv");
		BufferedWriter test = new BufferedWriter(resultsFile);
		BufferedReader br = new BufferedReader(new FileReader(csv));
		String[] token = new String[2];
		String line;
		int count = 1;
		br.readLine();
        br.readLine();

        //while there are still lines in the CSV, split each line into an array by the comma
		while((line = br.readLine()) != null) {
			token = line.split(",");

            //adds an integer at index 0 of token to open and at index 1 of token to close
			open.add(Double.parseDouble(token[0]));
			close.add(Double.parseDouble(token[1]));
		}
		
        //for the size of the arraylist, set close[i] to the mean
		for(int i = 0; i < close.size(); i++) {

            //for the windowValue specified, see if there is a value before or after the i value
			for(int j = 1; j <= windowValue; j++) {

                //if there is something before the i value, add it to the value at location close[i]
				if(i - j >= 0){
                    close.set(i, close.get(i) + close.get(i - j));
                    count++;
                }

                //if there is something after the i value, add it to the value at location close[i]
                if(i + j < close.size()){
                    close.set(i, close.get(i) + close.get(i + j));
                    count++;
                }
				
			}

            //find the mean of the total values that was added up and return count to 1
            close.set(i, close.get(i) / count);
            count = 1;
		}
        br.close();
		
        //creates an array of strings of sthe same size as xValues
        String[] dataPoints = new String[close.size()];

        //for the size of xValues, add x and y values of i index into a String
		for(int i = 0; i < close.size(); i++) {
			dataPoints[i] = String.valueOf(open.get(i)) + ',' + String.valueOf(close.get(i)) + ',';
			String data = String.join(",", dataPoints[i]);

            //write the String onto a new CSV file and skip to next line
            test.write(data);
            test.newLine();
		}

        test.close();
    }
}