package com.nucleardiesel.cardio.cards;

import com.nucleardiesel.cardio.cards.Attack;
import com.nucleardiesel.cardio.cards.Block;
import com.nucleardiesel.cardio.cards.Card.type;
import com.nucleardiesel.cardio.cards.Spells;

public class CardLoader {

	/**
	 * Loads a card objects data by its name
	 * @param c card name 
	 * @param card to modify
	 * @return card that was passed in
	 */
	public static Card loadCard(Cards cardname, Card card) {

		switch (cardname.getType()) {

		case Spell:
			return loadCard((Spells) cardname, card);
		case Attack:
			return loadCard((Attack) cardname, card);
		case Block:
			return loadCard((Block) cardname, card);
		}
		return card;
	}


	public static Card loadCard(Spells cardname, Card card) {
		card.setType(type.Spell);
		switch (cardname) {

		case fireball:
			card.setCooldown(1.2f);
			card.setDamage(75);
			card.setAnimation("fireballCard", 0);
			break;

		}
		return card;
	}
	public static Card loadCard(Attack c, Card card) {
		card.setType(type.Attack);
		switch (c) {

		case slash:
			card.setCooldown(1.2f);
			card.setDamage(75);
			card.setAnimation("Card", 0);
			break;

		}
		return card;
	}
	public static Card loadCard(Block c, Card card) {
		card.setType(type.Block);
		switch (c) {

		case lightBlock:
			card.setCooldown(0.5f);
			card.setAnimation("Card", .05f);
			break;
		default:
			break;
		}

		return card;
	}

}
