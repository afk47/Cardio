package com.nucleardiesel.cardio.cards;

import com.nucleardiesel.cardio.cards.Card.type;

/**
 * 
 * Attack type cards
 * @param name 
 * @param damage 
 * @param file 
 * @param cooldown
 * @param type 
 */
public enum Attack implements Cards {
	slash("slash",50,1f,"Card");

	final String name;
	final String location;
	final int damage;
	final float cooldown;
	
	
	Attack(String name,int dmg, float cooldown, String location){
		
		this.name = name;
		damage = dmg;
		this.cooldown = cooldown;
		this.location = location;
		
	}
	
	@Override
	public int damage() {
		return damage;
	}

	@Override
	public String file() {
		return(location);
	}

	@Override
	public String toString() {

		return(name);
	}

	@Override
	public type getType() {
		return Card.type.Attack;
	}

	@Override
	public float cooldown() {
		return cooldown;
	}
}
