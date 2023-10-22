import java.util.ArrayList;
import java.util.*;





public class BaccaratDealer {

    ArrayList<Card> deck;

    // This function creates the deck of cards. Since integers are required for the value, and some cards
    //have strings as values (kings, queens, etc.) we will have 1 represent Aces, 11 for the Jacks, 12 for Queens
    //and 13 for Kings. Once the deck is created, then it will be shuffled.
    public void generateDeck(){

        //Clear the deck so that when this function is called, there aren't more than 52 cards.
        deck.clear();

        //Add the cards into the deck
        for (int i = 1; i <= 12; i++) {
            deck.add(new Card("heart", i));
            deck.add(new Card("diamond", i));
            deck.add(new Card("spade", i));
            deck.add(new Card("club", i));
        }
        shuffleDeck();
    }

    // This function will return an ArrayList that has two cards in it, and then it will return that ArrayList
    public ArrayList<Card> dealHand() {
        ArrayList<Card> hand = new ArrayList<>();

        if (deckSize() < 2) {
            return null;
        }
        else {
            hand.add(deck.remove(deckSize() - 1));
            hand.add(deck.remove(deckSize() - 1));
            return hand;
        }
    }

    // Draws one card from the deck and returns i
    public Card drawOne(){

        if (deckSize() == 0){
            return null;
        }
        else {
            return deck.remove(deckSize() - 1);
        }

    }

    //This function shuffles the deck of cards by using the method shuffle() which does not return anything
    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    //This function returns the size of the deck
    public int deckSize() {
        return deck.size();
    }
}
