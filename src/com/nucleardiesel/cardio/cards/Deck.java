package com.nucleardiesel.cardio.cards;

import java.util.ArrayList;
import java.util.Random;

import com.nucleardiesel.cardio.gui.Drawable;
import com.nucleardiesel.cardio.gui.Window;

public class Deck {

	ArrayList<String> deck = new ArrayList<String>();
	Card[] hand = new Card[4];
	int[] handIndex = new int[4];

	private Window window;

	public Deck(Window win) {
		window = win;
		generateDefaultDeck();
		updateHand();
	}

	private void generateDefaultDeck() {
		deck.add("Fireball");
		deck.add("Fireball");
		deck.add("Fireball");
		deck.add("Fireball");
		deck.add("Fireball");
		deck.add("Fireball");
		deck.add("Fireball");
		deck.add("Fireball");
		deck.add("Fireball");
		deck.add("Fireball");
		deck.add("Fireball");
		deck.add("Fireball");
		deck.add("Fireball");
		deck.add("Fireball");
		

	}

	public Deck(String file) {
		// Read deck from file
	}

	/**
	 * @return a random card that is not the in the current hand
	 * 
	 */
	private int getNextCardIndex() {// TODO Make more efficient
		Random rand = new Random();
		int card = rand.nextInt(deck.size() - 4);
	    for (int exclude : handIndex) {
	        if (card < exclude) {
	            break;
	        }
	        card++;
	    }
	    return card;
	}

	public int[] updateHand() {
		int i = 0;
		int[] cardplayed = new int[] {0,0,0,0};
		try {
			for (Card c : hand) {
				if (c.beenPlayed()) {
					handIndex[i] = getNextCardIndex();
					hand[i] = new Card(window,deck.get(handIndex[i]));
					hand[i].setPosition(-800 + 200 * i, -500);
					cardplayed[i] = 1;
				}
				i++;
			}
			return cardplayed;
		} catch (NullPointerException e) {
			handIndex = new int[] { getNextCardIndex(), getNextCardIndex(), getNextCardIndex(), getNextCardIndex() };

			for (int j = 0; j < handIndex.length; j++) {
				hand[j] = new Card(window,deck.get(handIndex[j]));
				hand[j].reset();
				hand[j].setPosition(-800 + 200 * j, -500);
			}

		}
		return cardplayed;
	}

	public Card[] getHand() {

		return hand;
	}

}
