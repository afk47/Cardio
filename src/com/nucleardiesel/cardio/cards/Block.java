package com.nucleardiesel.cardio.cards;

import com.nucleardiesel.cardio.cards.Card.type;

public enum Block implements Cards {
	lightBlock("lightBlock",0,.5f,"Card");
	
	final String name;
	final String location;
	final int damage;
	final float cooldown;
	
	
	Block(String name,int dmg, float cooldown, String location){
		
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
		return Card.type.Block;
	}

	@Override
	public float cooldown() {
		return cooldown;
	}
}