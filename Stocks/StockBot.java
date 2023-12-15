import java.util.ArrayList;

public class StockBot {
    public static void tradeEvaluator(ArrayList<Integer> close, int endingDate, int startingBal){
        Stocks evaluate = new Stocks();
        int buyShares = 0;
        int sellShares = 0;
        ArrayList<Double> RSI = new ArrayList<>();

        //for 1 to the endingDate specified, calculate the RSI and add it to the arraylist
        for(int i = 1; i < endingDate; i++){
            RSI.add(evaluate.RSI(2, close, i));
        }
        
        //for the size of RSI, check if user should buy or sell shares
        for(int i = 1; i < RSI.size(); i++){

            //if the RSI is higher than the previous RSI, buy 10 shares
            if(RSI.get(i) > RSI.get(i - 1)){
                buyShares = buyShares + 10;
            }

            //if the RSI is lower than the previous RSI, sell 10 shares
            if(RSI.get(i) < RSI.get(i - 1)){
                sellShares = sellShares + 10;
            }
        }

        //if the amount of shares sold is higher than bought, tell them to sell shares
        if(sellShares > buyShares){
            System.out.println("Recommend you sell shares: " + sellShares);

        //if the amount of shares bought it higher than sold, tell user to buy shares
        }else if (sellShares < buyShares){
            System.out.println("Recommend you buy shares: " + buyShares);

        //if they stayed the same, do nothing
        }else{
            System.out.println("Do nothing");
        }
        
    }
}
