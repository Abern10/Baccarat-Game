import java.util.ArrayList;
import java.util.*;


public class BaccaratGameLogic {
//    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
//
//    }



    //This method will return the total points of a hand
    //If the hand total is greater than 9, it will mod the value by 10,
    //in order to get the digit in the ones place (rightmost).
    public int handTotal(ArrayList<Card> hand){
        int handTotal = 0;
        for (Card c: hand) {
            int value = c.value;

            if (value < 10) {
                handTotal += value;
            }
        }
        if (handTotal >= 10){
            return handTotal % 10;
        }
        return handTotal;
    }


}
