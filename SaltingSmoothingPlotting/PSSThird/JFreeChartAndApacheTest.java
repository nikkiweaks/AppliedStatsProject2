import java.io.File;
import java.io.IOException;
import org.jfree.ui.RefineryUtilities;

public class JFreeChartAndApacheTest {
    public static void main(String[]args)throws IOException{
        PlotterWithJFreeChart chart = new PlotterWithJFreeChart("JFreeChart of Y = Mx + b","Y = mx + b", 5, 2, -10, 20, 1);
        SmootherWithApache test = new SmootherWithApache();
        FirstSalter salterTest = new FirstSalter();
        File sample = new File("sample.csv");

        chart.pack( );
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible( true );

        test.smoothingWithApache(sample, 3);

        salterTest.salterResults(sample, 1000);
    }
}
