import org.jfree.chart.ChartPanel;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class PlotterWithJFreeChart extends ApplicationFrame {

   //creates the line graph 
   public PlotterWithJFreeChart(String applicationTitle , String chartTitle, int m, int b, int minRange, int totalRuns, int interval) {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createLineChart(chartTitle,"X","Y", createDataset(m, b, minRange, totalRuns, interval), PlotOrientation.VERTICAL, true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
   }

   //Creates the dataset to be used in the graph
   public DefaultCategoryDataset createDataset(int m, int b, int minRange, int totalRuns, int interval) {
      ArrayList<Integer> xValues = new ArrayList<>();
		ArrayList<Integer> yValues = new ArrayList<>();

      //for how large they want the range to be, commit the equation and add it to yValues, only if it is within the interval
      for(int i = 0; i <= totalRuns; i++) {
			if(i % interval == 0) {
				xValues.add(minRange + i);
				yValues.add((m * (minRange + i)) + b);
			}
		}

      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      //Add the x and y values to dataset for line "x"
      for(int i = 0; i < yValues.size(); i++){
         dataset.addValue(yValues.get(i), "x", String.valueOf(xValues.get(i)));
      }
      return dataset;
   }
}
