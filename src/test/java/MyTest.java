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

}
