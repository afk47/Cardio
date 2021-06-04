package com.urcompany.cardio.scenes;

import static org.lwjgl.glfw.GLFW.*;
import com.urcompany.cardio.entity.Player;
import com.urcompany.cardio.gui.*;

public class Test extends Scene {

    private Player p1;
    private Background bg;
    private Button bttn;

    public Test(Window w) {
        super(w, "Test Scene");
        init();
    }

    @Override
    public void init() {
        bg = new Background(win);
        p1 = new Player(win);
        bttn = new Button(win);

        addDrawables(new Drawable[] {bg, p1, bttn});
    }

    @Override
    public void update() {
		if (win.getInput().isKeyReleased(GLFW_KEY_SPACE)) {// temporary to test animation system
			p1.attack();
		}

		if (win.getInput().isKeyDown(GLFW_KEY_A)) {// temporary to test image translation
			p1.turnLeft();
			p1.run();
			p1.addPosition(new float[] { -20f, 0f, 0f });
		}
		if (win.getInput().isKeyReleased(GLFW_KEY_A)) {// temporary to test image translation
			p1.idle();
			p1.addPosition(new float[] { 20f, 0f, 0f });
		}
		if (win.getInput().isKeyDown(GLFW_KEY_D)) {// temporary to test image translation
			p1.turnRight();
			p1.run();
			p1.addPosition(new float[] { 20f, 0f, 0f });
		}
		if (win.getInput().isKeyReleased(GLFW_KEY_D)) {// temporary to test image translation
			p1.idle();
			p1.addPosition(new float[] { 20f, 0f, 0f });
		}
		if (win.getInput().isKeyReleased(GLFW_KEY_W)) {// temporary to test image translation
			p1.jump();

		}
		if (win.getInput().isKeyDown(GLFW_KEY_S)) {// temporary to test image translation
			p1.idle();
		}

		if (p1.getPosition()[1] >= -300 && !p1.getState().equals("Jump")) {
			p1.addPosition(new float[] { 0f, -20f, 0f });
		}

    }
}
