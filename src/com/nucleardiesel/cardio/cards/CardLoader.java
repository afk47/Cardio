package com.nucleardiesel.cardio.cards;

public class CardLoader {

	
	public static Card loadCard(Cards c, Card card){
		
		
		switch(c) {
		
		case fireball: 
			card.setCooldown(1.2f);
			card.setDamage(75);
			card.setType(Card.type.Attack);
		break;
		
		}
		
		return card;
		
	}
}
