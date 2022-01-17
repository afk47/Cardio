package com.nucleardiesel.cardio.cards;

import java.util.ArrayList;
import java.util.Random;

import com.nucleardiesel.cardio.gui.Drawable;
import com.nucleardiesel.cardio.gui.Window;
import com.nucleardiesel.cardio.scenes.BattleScene;

public class Deck {

	ArrayList<Cards> deck = new ArrayList<Cards>();
	Card[] hand = new Card[4];
	int[] handIndex = new int[4];

	private Window window;
	private BattleScene scene;
	
	
	public Deck(Window win, BattleScene scene) {
		window = win;
		this.scene = scene;
		generateDefaultDeck();
		updateHand();
	}

	private void generateDefaultDeck() {
		deck.add(Cards.slash);
		deck.add(Cards.fireball);
		deck.add(Cards.slash);
		deck.add(Cards.fireball);
		deck.add(Cards.slash);
	    deck.add(Cards.fireball);
	    deck.add(Cards.slash);
		deck.add(Cards.fireball);
		deck.add(Cards.slash);
		deck.add(Cards.fireball);
		deck.add(Cards.slash);
		deck.add(Cards.fireball);
		deck.add(Cards.slash);
		deck.add(Cards.fireball);
		

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
					hand[i] = null;
					hand[i] = new Card(window,deck.get(handIndex[i]),scene);
					hand[i].setPosition(-800 + 200 * i, -500);
					cardplayed[i] = 1;
				}
				i++;
			}
			return cardplayed;
		} catch (NullPointerException e) {
			Random rand = new Random();
			
			handIndex = new int[] {rand.nextInt(deck.size()) , rand.nextInt(deck.size()), rand.nextInt(deck.size()), rand.nextInt(deck.size()) };

			for (int j = 0; j < handIndex.length; j++) {
				hand[j] = null;
				hand[j] = new Card(window,deck.get(handIndex[j]),scene);
				hand[j].setPosition(-800 + 200 * j, -500);
			}

		}
		return cardplayed;
	}

	public Card[] getHand() {

		return hand;
	}

}
