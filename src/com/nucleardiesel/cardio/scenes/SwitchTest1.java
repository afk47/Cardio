package com.nucleardiesel.cardio.scenes;

import com.nucleardiesel.cardio.entity.Player;
import com.nucleardiesel.cardio.gui.Drawable;
import com.nucleardiesel.cardio.gui.Scene;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;

public class SwitchTest1 extends Scene {

    private Player p1;

    public SwitchTest1() {
        super("SwitchTest1");
    }

    @Override
    public void init() {
        p1 = new Player(window);

        addDrawables(new Drawable[]{p1});
    }

    @Override
    public void update() {
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
        if (window.getInput().isKeyPressed(GLFW_KEY_F)) {
            //controller.setScene(1); //switch to switchtest2
            controller.nextScene();
        }
        if (window.getInput().isKeyPressed(GLFW_KEY_RIGHT)) {
            controller.nextScene(); // switch to next scene
        }

        if (window.getInput().isKeyPressed(GLFW_KEY_LEFT)) {
            controller.previousScene(); // switch to previous scene
        }

        if (p1.getPosition()[1] >= -300 && !p1.getState().equals("Jump")) {
            p1.addPosition(new float[] { 0f, -20f, 0f });
        }


    }
}
