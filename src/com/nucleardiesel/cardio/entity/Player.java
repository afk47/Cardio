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
	private float[] offset = {50, -50};
	
	/**
	 *  possible statuses of a player
	 *  @values poisoned, bleeding, invulnerable, or stunned
	 *
	 */
	public enum status {
		poisoned, bleeding, invulnerable, stunned;
	}

	public Player(Window window, BattleScene s) {
		super(window);
		setPath(path);
		scene = s;
		doDefaultAnimation();
		size = 4;
		setPosition(-350, -180);
	}

	public void update() {
		super.update();
		if (!currentAnimation.equals("death")) {

			
			if(animationCompleted) {
				if(currentAnimation.equals("attack1")) {
					addPosition(new float[] {-70,-67,0});
				}
				idle();
			}
			
			
			if (currentframe != lastFrame) {
				setFrame((int) Math.floor(currentframe));//Changes frame
			}
			
			
		} else {
			
			if ((int) Math.floor(currentframe) != lastFrame && !animationCompleted) {
				addPosition(new float[] {0,-30,0});
				setFrame((int) Math.floor(currentframe));
			}
			
			
		}

	}

	public void attack() {
		
		setAnimation("attack1", .5f, path);
		addPosition(new float[] {70,67,0});
	}

	

	public void idle() {
		setAnimation("idle", 2f, path);

	}

	public void run() {
		if (currentAnimation != "attack1" && currentAnimation != "run" || !(currentframe < frames - 1))
			setAnimation("run", .5f, path);

	}

	@Override
	protected void doDefaultAnimation() {
		setAnimation("idle", 1, path);

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
		setAnimation("jump", .1f, path);

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
		setAnimation("death", 2f, path);
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

	public float[] getOrigin() {
		float[] temp = getPosition().clone();
		
		temp[0] += offset[0];
		temp[1] += offset[1];
		
		return temp;
	}
}
