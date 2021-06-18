package com.nucleardiesel.cardio.scenes;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;

import com.nucleardiesel.cardio.Main;
import com.nucleardiesel.cardio.cards.Card;
import com.nucleardiesel.cardio.cards.Deck;
import com.nucleardiesel.cardio.entity.Fireball;
import com.nucleardiesel.cardio.entity.Player;
import com.nucleardiesel.cardio.gui.Background;
import com.nucleardiesel.cardio.gui.Button;
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
	private Deck deck;
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
		deck = new Deck(window);
		p1hp = new Healthbar(window);
		p2hp = new Healthbar(window);
		p1hp.addPosition(new float[] { 100, 0, 0 });
		p2.addPosition(new float[] { 500, 0, 0 });
		p2.flipHorizontal();
		p2hp.addPosition(new float[] { 1100, 0, 0 });
		addDrawables(new Drawable[] { bg, p1, p2, deck.getHand()[0], deck.getHand()[1], deck.getHand()[2],
				deck.getHand()[3], p1hp, p2hp });

		// CREATES Audio Player Then Plays Sound at file location
		audioPlayer = new SoundPlayer();
		audioPlayer.addSound(
				"src/resources/textures/sounds/Music/Decisive Battle/xDeviruchi - Decisive Battle (Loop).ogg", true);
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

		if (window.getInput().isKeyPressed(GLFW_KEY_RIGHT)) {
			controller.nextScene(); // switch to next scene
		}
		if (window.getInput().isKeyPressed(GLFW_KEY_LEFT)) {
			controller.previousScene(); // switch to previous scene
		}

		int[] played = deck.updateHand();

		for (int i = 0; i < played.length; i++) {
			if (played[i] > 0) {
				setDrawable(deck.getHand()[i], i + 3);
			}
		}
		System.out.println(played[0] + ", " +played[1]+ ", " +played[2]+ ", " +played[3]);

	}
}
