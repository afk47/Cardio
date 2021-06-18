package com.nucleardiesel.cardio.scenes;

import static org.lwjgl.glfw.GLFW.*;

import com.nucleardiesel.cardio.Main;
import com.nucleardiesel.cardio.cards.Card;
import com.nucleardiesel.cardio.entity.Fireball;
import com.nucleardiesel.cardio.entity.Player;
import com.nucleardiesel.cardio.gui.*;

public class Test extends Scene {

	private Player p1;
	private Background bg;
	private Button bttn;
	private Card card;
	private float cooldown = 0;
	private boolean oncooldown = false;
	private double time;

	public Test() {
		super("Test Scene");
	}

	@Override
	public void init() {
		bg = new Background(window);
		p1 = new Player(window);
		bttn = new Button(window);
		
		addDrawables(new Drawable[] { bg, p1, card});
	}

	@Override
	public void update() {
		time += Main.getPassed();
		if(!oncooldown) {
			time = 0;
		}
		if (time > cooldown && oncooldown) {
			oncooldown = false;
			time = 0;
		}

		if (window.getInput().isKeyReleased(GLFW_KEY_SPACE)) {// temporary to test animation system
			p1.attack();
		}

		if (window.getInput().isKeyDown(GLFW_KEY_A)) {// temporary to test image translation
			p1.turnLeft();
			p1.run();
			p1.addPosition(new float[] { -20f, 0f, 0f });
		}
		if (window.getInput().isKeyReleased(GLFW_KEY_A)) {// temporary to test image translation
			p1.idle();
			p1.addPosition(new float[] { 20f, 0f, 0f });
		}
		if (window.getInput().isKeyDown(GLFW_KEY_D)) {// temporary to test image translation
			p1.turnRight();
			p1.run();
			p1.addPosition(new float[] { 20f, 0f, 0f });
		}
		if (window.getInput().isKeyReleased(GLFW_KEY_D)) {// temporary to test image translation
			p1.idle();
			p1.addPosition(new float[] { 20f, 0f, 0f });
		}
		if (window.getInput().isKeyReleased(GLFW_KEY_W)) {// temporary to test image translation
			p1.jump();

		}
		if (window.getInput().isKeyDown(GLFW_KEY_S)) {// temporary to test image translation
			p1.idle();
		}

		if (p1.getPosition()[1] >= -300 && !p1.getState().equals("Jump")) {
			p1.addPosition(new float[] { 0f, -20f, 0f });
		}

		if (window.getInput().isKeyPressed(GLFW_KEY_RIGHT)) {
			controller.nextScene(); // switch to next scene
		}

		if (window.getInput().isKeyPressed(GLFW_KEY_LEFT)) {
			controller.previousScene(); // switch to previous scene
		}

		if (card.beenPlayed() && !oncooldown) {
			Fireball fireball = new Fireball(window, this, getContents().size());
			fireball.setPosition(p1.getPosition()[0],p1.getPosition()[1]);
			addDrawable(fireball);
			cooldown = card.getCooldown();
			oncooldown = true;
			setDrawable(card, 2);
		}

		if (getContents().get(getContents().size() - 1).getState().equals("NULL")) {// Checks for flag of NULL and
																					// removes last case of a object
																					// with flag BAD IMPLEMENTATION JUST
																					// TO PREVENT LAG ATM
			removeDrawable(getContents().size() - 1);
		}

	}
}
