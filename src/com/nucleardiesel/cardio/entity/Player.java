package com.nucleardiesel.cardio.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.nucleardiesel.cardio.Main;
import com.nucleardiesel.cardio.cards.Cards;
import com.nucleardiesel.cardio.cards.Spells;
import com.nucleardiesel.cardio.gui.Drawable;
import com.nucleardiesel.cardio.gui.Window;
import com.nucleardiesel.cardio.scenes.BattleScene;
import com.nucleardiesel.cardio.texture.AnimationLoader;
import com.nucleardiesel.cardio.texture.Model;
import com.nucleardiesel.cardio.texture.Texture;

public class Player extends Entity {

	private boolean blocking = false;
	private int health = 1000;
	private BattleScene scene;
	private final String path = "characters/sword1/";

	public enum status {
		poisoned, bleeding, invulnerable, stunned;
	}

	public Player(Window window, BattleScene s) {
		super(window);
		setPath(path);
		scene = s;
		doDefaultAnimation();
		size = 1;
		setPosition(-350, -250);
	}

	public void update() {
		super.update();
		if (!currentAnimation.equals("death")) {

			if (currentAnimation.equals("jump") && animationCompleted) {
				idle();
			}
			if (currentAnimation.equals("jump")) {
				addPosition(new float[] { 0f, 10f, 0f });
			}
			if (!currentAnimation.equals("idle") && animationCompleted) {
				idle();
			}
			if (currentframe != lastFrame) {
				setFrame((int) Math.floor(currentframe));
			}
		} else {
			if (currentframe != lastFrame && !animationCompleted) {
				setFrame((int) Math.floor(currentframe));
			}
		}

	}

	public void attack() {
		setAnimation("attack1", 0.5f);
	}

	public void idle() {
		setAnimation("idle", 20f);

	}

	public void run() {
		if (currentAnimation != "attack1" && currentAnimation != "run" || !(currentframe < frames - 1))
			setAnimation("run", .5f);

	}

	@Override
	protected void doDefaultAnimation() {
		setAnimation("idle", 1);

	}

	public void turnLeft() {
		if (!flippedHorizontal) {
			flipHorizontal();
		}
		flippedHorizontal = true;
	}

	public void turnRight() {
		if (flippedHorizontal) {
			flipHorizontal();
		}
		flippedHorizontal = false;
	}

	public void jump() {
		setAnimation("jump", .1f);

	}

	public String getState() {
		return currentAnimation;
	}

	public void setHP(int hp) {
		health = hp;
	}

	public int getHP() {
		return health;
	}

	public void takeDamage(int dmg) {
		health -= dmg;
	}

	public void death() {
		setAnimation("death", .5f);
		currentAnimation = "death";
	}

	public void block() {
		blocking = true;
	}

	public void stopBlocking() {
		blocking = false;
	}

	public boolean isBlocking() {

		return blocking;
	}

	public void playFX(Spells s) {

		scene.playFX(s);
	}

}
