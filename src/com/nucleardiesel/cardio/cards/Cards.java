package com.nucleardiesel.cardio.cards;

import com.nucleardiesel.cardio.entity.Player;

/*
 * Defines all available cards and values
 * 
 * 
 */

public interface Cards {

	public String toString();

	public String file();

	public int damage();

	public Card.type getType();

	public float cooldown();
	
	
	
}






