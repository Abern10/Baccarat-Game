import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;


import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

class MyTest {

	// Card constructor class test
	// This test will create a card and check to see if the variables are set correctly
	@Test
	public void testCardConstructor() {
		Card card = new Card("Hearts", 10);

		// Check if the suite and value were set correctly
		assertEquals("Hearts", card.suite);
		assertEquals(10, card.value);
	}

	// Tests for Baccarat Dealer Class

	// This t
	@Test
	// This test will make sure that 52 cards are created
	void generateDeckTest() {
		BaccaratDealer d = new BaccaratDealer();
		d.generateDeck();
		assertEquals(52, d.deckSize());
	}

	@Test
	// This test makes sure that even if you call generateDeck() multiple times, it will only have 52 cards
	void generateDeckTestMultiple() {
		BaccaratDealer d = new BaccaratDealer();
		d.generateDeck();
		d.generateDeck();
		assertEquals(52, d.deckSize());
	}

	@Test
	// This test will see if the correct number of cards is returned in the array that is made
	void dealerHandTest() {
		BaccaratDealer d = new BaccaratDealer();
		d.generateDeck();

		ArrayList<Card> hand = d.dealHand();

		assertEquals(2, hand.size());

	}

	@Test
	// This test will make sure that nothing is returned if there is no deck made
	public void testDealHandEmptyDeck() {
		BaccaratDealer dealer = new BaccaratDealer();

		// Attempt to deal a hand from an empty deck
		ArrayList<Card> hand = dealer.dealHand();

		// Check that the hand is null
		assertNull(hand);

		// Check that the deck size remains 0
		assertEquals(0, dealer.deckSize());
	}

	@Test
	// This test will make sure that a card is returned from the deck and its not null
	public void drawOneTest(){
		BaccaratDealer d = new BaccaratDealer();
		d.generateDeck();

		Card c = d.drawOne();

		assertNotNull(c);
	}

	@Test
	// This test will make sure to return null if there are no more cards
	public void drawOneNoCardsLeftTest() {
		BaccaratDealer d = new BaccaratDealer();
		for (int i = 0; i < 52; i++) {
			Card c = d.drawOne();
		}
		assertNull(d.drawOne());
	}

	@Test
	// This test will check to see if shuffle decks works by making a copy of an array and then
	// calling the function and then comparing them.
	public void testShuffleDeckChangesOrder() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();

		// Creating a copy of the original deck
		ArrayList<Card> originalDeck = new ArrayList<>(dealer.deck);

		dealer.shuffleDeck();

		assertNotEquals(originalDeck, dealer.deck);
	}

	@Test
	// This test will make sure that shuffle deck won't change the size of the deck that it shuffles
	public void testShuffleDeckPreservesDeckSize() {
		BaccaratDealer d = new BaccaratDealer();
		d.generateDeck();

		int deckSize = d.deckSize();
		d.shuffleDeck();

		assertEquals(deckSize, d.deckSize());
	}

	@Test
	// This function will check the deck size once a deck is created
	public void testDeckSize() {
		BaccaratDealer d = new BaccaratDealer();
		d.generateDeck();

		assertEquals(52, d.deckSize());
	}

	@Test
	// This function checks to see if the deck size is correct once a card is removed
	public void testSizeAfterDraw() {
		BaccaratDealer d = new BaccaratDealer();
		d.generateDeck();
		Card c = d.drawOne();
		
		assertEquals(51, d.deckSize());
	}


	// Tests for BaccaratGameLogic

	// Test for whoWon method (player wins)
	@Test
	public void testWhoWonPlayerWins() {
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();

		ArrayList<Card> playerHand = new ArrayList<>();
		playerHand.add(new Card("Spade", 10));
		playerHand.add(new Card("Heart", 7));

		ArrayList<Card> bankerHand = new ArrayList<>();
		bankerHand.add(new Card("Club", 6));
		bankerHand.add(new Card("Diamond", 5));

		String result = gameLogic.whoWon(playerHand, bankerHand);

		// Player hand total is 17, Banker hand total is 11, so Player should win
		assertEquals("Player", result);
	}

	// Test for whoWon method (banker wins)
	@Test
	public void testWhoWonBankerWins() {
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();

		ArrayList<Card> playerHand = new ArrayList<>();
		playerHand.add(new Card("Spade", 8));
		playerHand.add(new Card("Heart", 6));

		ArrayList<Card> bankerHand = new ArrayList<>();
		bankerHand.add(new Card("Club", 10));
		bankerHand.add(new Card("Diamond", 9));

		String result = gameLogic.whoWon(playerHand, bankerHand);

		// Player hand total is 14, Banker hand total is 19, so Banker should win
		assertEquals("Banker", result);
	}

	// Test for handTotal with no face cards
	@Test
	public void testHandTotalNoFaceCards() {
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();

		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("Spade", 6));
		hand.add(new Card("Heart", 2));

		int result = gameLogic.handTotal(hand);

		// The total of the hand is 6 + 2, which is 8
		assertEquals(8, result);
	}

	// Test for handtotal with face cards
	@Test
	public void testHandTotalWithFaceCards() {
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();

		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("Club", 10));
		hand.add(new Card("Diamond", 3));

		int result = gameLogic.handTotal(hand);

		// The total of the hand is 0 (10 is treated as 0) + 3, which is 3
		assertEquals(3, result);
	}

	// Test to see if the baker is allowed to draw (should not draw additional card)
	@Test
	public void testEvaluateBankerDrawShouldNotDraw() {
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();

		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("Spade", 9));
		hand.add(new Card("Heart", 10));

		Card playerCard = new Card("Diamond", 9);

		boolean result = gameLogic.evaluateBankerDraw(hand, playerCard);

		// Hand total is 1 (6 + 5), which is less than 7, so Banker should not draw
		assertFalse(result);
	}

	// Test to see if the banker is allowed to draw (should be able to draw)
	@Test
	public void testEvaluateBankerDrawShouldDraw() {
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();

		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("Club", 6));
		hand.add(new Card("Diamond", 5));

		Card playerCard = new Card("Spade", 8);

		boolean result = gameLogic.evaluateBankerDraw(hand, playerCard);

		// Hand total is 1 (6 + 6), which is less than 2,  and the player's card is 8, so Banker should draw
		assertTrue(result);
	}

	// Test to see if the player is allowed to draw (should be able to draw)
	@Test
	public void testEvaluatePlayerDrawShouldDraw() {
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();

		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("Spade", 13));
		hand.add(new Card("Heart", 3));

		boolean result = gameLogic.evaluatePlayerDraw(hand);

		// Hand total is 3 (13->(0) + 3), which is less than 6, so Player should draw
		assertTrue(result);
	}

	// Test to see if the player is allowed to draw (should not be able to draw additional card)
	@Test
	public void testEvaluatePlayerDrawShouldNotDraw() {
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();

		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("Club", 13));
		hand.add(new Card("Diamond", 7));

		boolean result = gameLogic.evaluatePlayerDraw(hand);

		// Hand total is 13 (13->(0) + 7), which is greater than or equal to 6, so Player should not draw
		assertFalse(result);
	}

	// Test to see if player sets bet and is correct
	@Test
	public void testEvaluateWinningsPlayerWins() {
		BaccaratGame game = new BaccaratGame();
		game.setBetPlayer = true; // Player bet is set
		game.setBetBanker = false;
		game.setBetDraw = false;

		game.totalWinnings = 0; // Initial total winnings
		game.currentBet = 10; // Current bet

		game.playerHand = new ArrayList<>();
		game.playerHand.add( new Card("heart", 9));
		game.playerHand.add( new Card("heart", 13));

		game.bankerHand = new ArrayList<>();
		game.bankerHand.add( new Card("heart", 8));
		game.bankerHand.add( new Card("heart", 13));


		// Player wins, so total winnings should increase by the current bet
		assertEquals(10,game.evaluateWinnings());
	}

	// Test to see if player sets bet but does not bet correctly
	@Test
	public void testEvaluateWinningsNoWin() {
		BaccaratGame game = new BaccaratGame();
		game.setBetPlayer = false;
		game.setBetBanker = true; // Banker bet is set
		game.setBetDraw = false;

		game.totalWinnings = 0; // Initial total winnings
		game.currentBet = 10; // Current bet

		game.playerHand = new ArrayList<Card>();
		game.playerHand.add( new Card("heart", 9));
		game.playerHand.add( new Card("heart", 13));

		game.bankerHand = new ArrayList<Card>();
		game.bankerHand.add( new Card("heart", 8));
		game.bankerHand.add( new Card("heart", 13));


		// Player loses the bet, so total winnings remain the same
		assertEquals(0, game.evaluateWinnings());
	}


}
