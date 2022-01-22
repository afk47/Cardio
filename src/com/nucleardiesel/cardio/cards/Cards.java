package com.nucleardiesel.cardio.cards;

import com.nucleardiesel.cardio.entity.Player;



/**
 * Interface that allows polymorphism between card type enums (Spells, Block, And Attack)
 * 
 */
public interface Cards {

	public String toString();

	/**
	 * 
	 * @return image location
	 */
	public String file();

	/**
	 * 
	 * @return amount of damage card deals
	 */
	public int damage();

	/**
	 * 
	 * @return Card type
	 */
	public Card.type getType();

	/**
	 * 
	 * @return cooldown in seconds
	 */
	public float cooldown();
	
	
	
}






