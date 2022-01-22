package com.nucleardiesel.cardio.controllers;

import com.nucleardiesel.cardio.Main;
import com.nucleardiesel.cardio.cards.Card;
import com.nucleardiesel.cardio.cards.Spells;
import com.nucleardiesel.cardio.entity.Player;
import com.nucleardiesel.cardio.scenes.BattleScene;

public class BattleController {

	private BattleScene battle;

	private double time;
	private double time2;
	private double cooldownp1 = 0;
	private double cooldownp2 = 0;
	private boolean oncooldownp1 = false;
	private boolean oncooldownp2 = false;
	private Player player;
	private Player opponent;

	public BattleController(BattleScene scene) {
		battle = scene;

	}

	/**
	 * Battle Logic 
	 * 
	 */
	public void update() {

		time += Main.getPassed();
		time2 += Main.getPassed();
		if (!oncooldownp1) {
			time = 0;
		} else if (time > cooldownp1) {
			oncooldownp1 = false;
			time = 0;
		}
		if (!oncooldownp2) {
			time2 = 0;
		} else if (time > cooldownp2) {
			oncooldownp2 = false;
			time2 = 0;
		}
		
		
		int[] played = battle.getDeck().updateHand();

		for (int a = 0; a < played.length; a++) {
			if (played[a] > 0) {

				battle.setDrawable(battle.getDeck().getHand()[a], a + 3);
			
			}

		}

	}

	
	
	/**
	 * Logic for playing cards 
	 * applys damage, checks cooldowns etc.
	 * 
	 * @param card card that is being played
	 * @param i which player played the card
	 * @return whether the card was played
	 */
	public boolean playCard(Card card, int i) {
	
		if ((i == 0 && !oncooldownp1) || (i == 1 && !oncooldownp2)) {
			card.reset();

			if (i == 0) { 
				oncooldownp1 = true;
				cooldownp1 = card.getCooldown();
				player = battle.getPlayer(i);
				opponent = battle.getPlayer(1);
				

			} else {
				oncooldownp2 = true;
				cooldownp2 = card.getCooldown();
				player = battle.getPlayer(i);
				opponent = battle.getPlayer(0);
			}

			
			switch(card.getType()) {
			case Attack:
				player.attack();
				if (!(opponent.getHP() <= 0) && !opponent.isBlocking()) {
					opponent.takeDamage(card.getDamage());
				}
				if(opponent.isBlocking()) {
					opponent.takeDamage(card.getDamage() / 10);
				}
				
				break;
			case Block:
				player.block();
				break;
			case Spell:
				if (!(opponent.getHP() <= 0)) {
				Spells.cast((Spells) card.getName(),player, opponent);
				}
				break;
			
			}
			if (opponent.getHP() <= 0 && !opponent.getState().equals("Death")) {
				opponent.setHP(0);
				opponent.death();
			}
			
			return true;
		}

		return false;
	}
}
