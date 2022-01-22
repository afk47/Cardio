package com.nucleardiesel.cardio.cards;

import com.nucleardiesel.cardio.cards.Card.type;
import com.nucleardiesel.cardio.entity.Player;

/*
 * Enum containing all Spell Cards
 * 
 */
public enum Spells implements Cards {

	fireball("fireball",75,1f,"fireballCard");
	
	// Implementation For casting spells
	
	
	final String name;
	final String location;
	final int damage;
	final float cooldown;
	
	
	Spells(String name,int dmg, float cooldown, String location){
		
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
		return Card.type.Spell;
	}

	@Override
	public float cooldown() {
		return cooldown;
	}
	
	
	
	
	public static void cast(Spells s, Player user, Player target) {	
		switch (s) {
		case fireball:
			if(target.getHP() >0) {
			user.attack();
			user.playFX(s);
			target.takeDamage(s.damage());
			}
			break;
		default:
			break;

		}
		
		
	}
}