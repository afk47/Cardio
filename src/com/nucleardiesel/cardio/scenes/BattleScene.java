package com.nucleardiesel.cardio.scenes;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;

import com.nucleardiesel.cardio.Main;
import com.nucleardiesel.cardio.cards.Card;
import com.nucleardiesel.cardio.cards.Cards;
import com.nucleardiesel.cardio.cards.Deck;
import com.nucleardiesel.cardio.controllers.BattleController;
import com.nucleardiesel.cardio.entity.Fireball;
import com.nucleardiesel.cardio.entity.Player;
import com.nucleardiesel.cardio.entity.SlashEffect;
import com.nucleardiesel.cardio.gui.Background;
import com.nucleardiesel.cardio.gui.Button;
import com.nucleardiesel.cardio.gui.CooldownIndicator;
import com.nucleardiesel.cardio.gui.Drawable;
import com.nucleardiesel.cardio.gui.Healthbar;
import com.nucleardiesel.cardio.gui.Scene;
import com.nucleardiesel.cardio.sound.SoundPlayer;
import com.nucleardiesel.cardio.cards.Spells;

public class BattleScene extends Scene {

	public BattleScene() {
		super("battlescene");
	}

	private Player p1;
	private Player p2;
	private Background bg;
	private Deck deck;
	private float cooldown = 0;
	private boolean oncooldown = false;
	private double time;
	private Healthbar p1hp;
	private Healthbar p2hp;
	private SoundPlayer audioPlayer;
	private BattleController battleController;

	@Override
	public void init() {
		battleController = new BattleController(this);
		bg = new Background(window);
		p1 = new Player(window, this);
		p2 = new Player(window, this);
		deck = new Deck(window, this);
		p1hp = new Healthbar(window, p1);
		p2hp = new Healthbar(window, p2);

		// sets pos of healthbars
		p1hp.addPosition(new float[] { 100, 0, 0 });
		p2.addPosition(new float[] { 400, 0, 0 });
		p2.flipHorizontal();
		p2hp.addPosition(new float[] { 1100, 0, 0 });
		addDrawables(new Drawable[] { bg, p1, p2, deck.getHand()[0], deck.getHand()[1], deck.getHand()[2],
				deck.getHand()[3], p1hp, p2hp });// adds all drawables to the screen to be rendered in order

		// CREATES Audio Player Then Plays Sound at file location
		audioPlayer = new SoundPlayer();
		audioPlayer.addSound("src/resources/textures/Music/Decisive Battle/xDeviruchi - Decisive Battle (Loop).ogg",
				true);
		audioPlayer.play(0);
		p2hp.setLeftJustified(false);
	}

	@Override
	public void update() {

		battleController.update();

		if (window.getInput().isKeyPressed(GLFW_KEY_RIGHT)) {
			controller.nextScene(); // switch to next scene
		}
		if (window.getInput().isKeyPressed(GLFW_KEY_LEFT)) {
			controller.previousScene(); // switch to previous scene
		}

	}

	public Deck getDeck() {
		return deck;
	}

	public Player getPlayer(int index) {

		switch (index) {

		case 0:
			return p1;
		case 1:
			return p2;
		default:
			return null;
		}

	}

	public BattleController getBattleController() {
		return battleController;
	}

	public void playFX(Spells s) {

		switch (s) {

		case fireball:
			Fireball fireball = new Fireball(window, this, getContents().size());
			fireball.setPosition(p1.getPosition()[0], p1.getPosition()[1]);
			addDrawable(fireball);
			break;
		}

	}

//	for (int i = 0; i < 4; i++) {
//		Card c = deck.getHand()[i];
//		if (!oncooldown && c.beenPlayed()) {
//			p1.attack();
//			p2hp.takeDamage(c.getDamage());
//			oncooldown = true;
//			cooldown = c.getCooldown();
//			addDrawable(new CooldownIndicator(window, cooldown));
//			// SlashEffect slash = new SlashEffect(window);
//			// slash.setPosition(p2.getPosition()[0],p2.getPosition()[1]);
//			// addDrawable(slash);
//			if ( p2hp.getHP() <= 0 && !p2.getState().equals("Death")) {
//				p2.death();
//			}
//		} else if (c.beenPlayed()) {
//			c.reset();
//
//		}
//	}
//

}
