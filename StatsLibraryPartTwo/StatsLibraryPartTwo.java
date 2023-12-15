public class StatsLibraryPartTwo {
    public double[] uniformDistribution(double a, double b, double c, double d){

        //create an array of doubles with a length of 3
        double[] result = new double[3];

        double union = (d - c) / (b - a);
        double variance = Math.pow(2, (b - a) / 2);
        double expected = (a + b) / 2;

        //puts the answer to union at index 0, the variance at index 1, and the expected at index 2
        result[0] = union;
        result[1] = variance;
        result[2] = expected;

        return result;

    }

    public double conditionalDistrbution(double probabilityXY, double probabilityY){

        //finds the probability of two already known probabilities
        double results = (probabilityXY / probabilityY);

        return results;
    }

    public void independence(double x, double y, double xy){

        //if x * y is the same as xy, then declare they are independent
        if(xy == x * y){
            System.out.println("x and y are independent");
        
        //if it equals anything other than the same as xy, declare they are dependent
        }else{
            System.out.println("x and y are dependent");
        }
    }
}
