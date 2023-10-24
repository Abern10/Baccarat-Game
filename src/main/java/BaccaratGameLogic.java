import java.util.ArrayList;
import java.util.*;


public class BaccaratGameLogic extends BaccaratDealer {
    public String whoWon(ArrayList<Card> playerHand, ArrayList<Card> bankerHand) {
        String player = "Player";
        String banker = "Banker";
        String draw = "Draw";

        // FIX THIS

        //If both cards are dealt and both banker and player have the same total, it will be counted as a draw
        if ((handTotal(playerHand) == 8 && handTotal(bankerHand) == 8) || (handTotal(playerHand) == 9 && handTotal(bankerHand) == 9)) {
            return draw;
        }

        // If the banker has a higher total, it will return banker
        if ((handTotal(playerHand) == 8 && handTotal(bankerHand) == 9)) {
            return banker;
        }

        // If the player has a higher hand, it will return player
        if ((handTotal(playerHand) == 9 && handTotal(bankerHand) == 8)) {
            return player;
        }

//        if (handTotal(playerHand) == handTotal(bankerHand)) {
//
//        }


        // If the player is able to draw a card, it will add the card to players hand
//        if(evaluatePlayerDraw(playerHand)) {
//            playerHand.add(drawOne());
//            if (evaluateBankerDraw(bankerHand, playerHand.get(2))) {
//                bankerHand.add(drawOne());
//            }
//
//        }
//        else {
//            if (evaluateBankerDraw(bankerHand, new Card(null, -1))) {
//                bankerHand.add(drawOne());
//            }
//        }
        // Remove this return this is temporary
        return draw;

    }



    // This method will return the total points of a hand
    // If the hand total is greater than 9, it will mod the value by 10,
    // in order to get the digit in the ones place (rightmost).
    public int handTotal(ArrayList<Card> hand){
        int handTotal = 0;

        // For loop to loop through the hand and adds the value of each card to the handTotal
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


    // This method will return a boolean value to show if a card should be drawn or not.
    // the player will always draw if their handtotal is less than 6 (0-5). The banker will
    // only collect a card if their total is 2 or less. If their total is 3, and the players
    // card is not 8, the banker will collect a card. If the handtotal is 4, and the card is equal to
    // null(-1), 2, 3, 4,5, 6, or 7, they will collect. If the hand total is 5, and the player car is null(-1),
    // 4,5, 6, or 7, it will return true. If the handtotal is 6, they will onyl collect if the card value is
    // 6 or 7 so itll return true. Else return false.
    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
        if (handTotal(hand) >= 7 ) {
            return false;
        }
        if (handTotal(hand) <= 2) {
            return true;
        }
        else {
            if(handTotal(hand) == 3 && (playerCard.value != 8)) {
                return true;
            }
            if (handTotal(hand) == 4 && (playerCard.value == -1 || playerCard.value == 2 || playerCard.value == 3 || playerCard.value == 4 || playerCard.value == 5 || playerCard.value == 6 || playerCard.value == 7 )) {
                return true;
            }
            if (handTotal(hand) == 5 && (playerCard.value == -1 || playerCard.value == 4 || playerCard.value == 5 || playerCard.value == 6 || playerCard.value == 7 )) {
                return true;
            }
            if(handTotal(hand) == 6 && (playerCard.value == 6 || playerCard.value == 7 )) {
                return true;
            }
            return false;
        }
    }


    // This function will return true if the players handtotal is less than 6,
    // otherwise it will return true.
    public boolean evaluatePlayerDraw(ArrayList<Card> hand) {
        if (handTotal(hand) < 6) {
            return true;
        }
        return false;
    }


}
