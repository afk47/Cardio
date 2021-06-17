package com.nucleardiesel.cardio.scenes;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;

import com.nucleardiesel.cardio.Main;
import com.nucleardiesel.cardio.entity.Fireball;
import com.nucleardiesel.cardio.entity.Player;
import com.nucleardiesel.cardio.gui.Background;
import com.nucleardiesel.cardio.gui.Button;
import com.nucleardiesel.cardio.gui.Card;
import com.nucleardiesel.cardio.gui.Drawable;
import com.nucleardiesel.cardio.gui.Healthbar;
import com.nucleardiesel.cardio.gui.Scene;
import com.nucleardiesel.cardio.sound.SoundPlayer;

public class BattleScene extends Scene {

	public BattleScene() {
		super("battlescene");
	}

	private Player p1;
	private Player p2;
	private Background bg;
	private Card card;
	private float cooldown = 0;
	private boolean oncooldown = false;
	private double time;
	private Healthbar p1hp;
	private Healthbar p2hp;
	private SoundPlayer audioPlayer;

	@Override
	public void init() {
		bg = new Background(window);
		p1 = new Player(window);
		p2 = new Player(window);
		card = new Card(window);
		p1hp = new Healthbar(window);
		p2hp = new Healthbar(window);
		p2.addPosition(new float[] { 500, 0, 0 });
		p2.flipHorizontal();
		p2hp.addPosition(new float[] { 1200, 0, 0 });
		addDrawables(new Drawable[] { bg, p1, p2, card, p1hp, p2hp });

		//CREATES Audio Player Then Plays Sound at file location
		audioPlayer = new SoundPlayer();
		audioPlayer.addSound("src/resources/textures/sounds/jingles_STEEL07.ogg", true);
		audioPlayer.play(0);

	}

	@Override
	public void update() {
		time += Main.getPassed();
		if (!oncooldown) {
			time = 0;
		}
		if (time > cooldown && oncooldown) {
			oncooldown = false;
			time = 0;
		}

		if (card.beenPlayed() && !oncooldown) {
			Fireball fireball = new Fireball(window, this, getContents().size());
			fireball.setPosition(p1.getPosition()[0], p1.getPosition()[1]);
			p1.attack();
			addDrawable(fireball);

			cooldown = card.getCooldown();
			oncooldown = true;
			if (p2hp.getHP() > 0) {
				p2hp.setHP(p2hp.getHP() - card.getDamage() * 50);
				p2hp.addPosition(new float[] { card.getDamage() * 20 });
			}
			if (p2hp.getHP() <= 0 && !p2.getState().equals("Death")) {

				p2.death();
			}
			card = new Card(window);
			System.out.println(card.beenPlayed());
			setDrawable(card, 3);

		}

		if (window.getInput().isKeyPressed(GLFW_KEY_RIGHT)) {
			controller.nextScene(); // switch to next scene
		}
		if (window.getInput().isKeyPressed(GLFW_KEY_LEFT)) {
			controller.previousScene(); // switch to previous scene
		}

	}
}
