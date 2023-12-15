import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StockBotTester {
    public static void main(String[]args) throws IOException{
        Stocks tester = new Stocks();
        StockBot test = new StockBot();
        int startingBal = 10000;
        ArrayList<Integer> close = new ArrayList<>();
        File sample = new File("AMZN.csv");
        File sample2 = new File("AMZNCloseAndOpen.csv");

        close = tester.collectingStockData(sample);
        tester.smootherResults(sample2, 3);
        test.tradeEvaluator( close, 10, startingBal);
    }
}
