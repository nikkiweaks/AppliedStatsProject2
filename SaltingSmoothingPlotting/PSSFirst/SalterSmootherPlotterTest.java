import java.io.File;
import java.io.IOException;

public class SalterSmootherPlotterTest {
    public static void main(String[] args)throws IOException{
        File sample = new File("sample2.csv");
        FirstSalter salterTest = new FirstSalter();
        FirstSmoother smootherTest = new FirstSmoother();
        FirstPlotter plotterTest = new FirstPlotter();

        salterTest.salterResults(sample, 1000);
        smootherTest.smootherResults(sample, 3);
        plotterTest.plotterResults(5, 2, -10, 10, 20, 1);
    }
}
