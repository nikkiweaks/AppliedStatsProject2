public class StatsLibraryTwoTest {
    public static void main(String[]args){
        StatsLibraryPartTwo test = new StatsLibraryPartTwo();

        double[] newTest = test.uniformDistribution(0, 40, 0, 15);
        System.out.println("The uniform Distribution is: " + newTest[0]);
        System.out.println("The variance of the uniform distribution is: " + newTest[1]);
        System.out.println("The expected value of the uniform distribution is: " + newTest[2]);
        System.out.println();

        double tester = test.conditionalDistrbution(.13, .53);
        System.out.println("The conditional Distribution is: " + tester);
        System.out.println();

        test.independence(2, 3, 6);
        test.independence(.2, .4, .06);
    }
}
