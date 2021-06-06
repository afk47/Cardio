package com.nucleardiesel.cardio.scenes;

import com.nucleardiesel.cardio.gui.Background;
import com.nucleardiesel.cardio.gui.Drawable;
import com.nucleardiesel.cardio.gui.Scene;

import static org.lwjgl.glfw.GLFW.*;

public class SwitchTest2 extends Scene {

    private Background bg;

    public SwitchTest2() {
        super("SwitchTest2");
    }

    @Override
    public void init() {
        bg = new Background(window);

        addDrawables(new Drawable[]{bg});
    }

    @Override
    public void update() {
        if (window.getInput().isKeyPressed(GLFW_KEY_F)) {
            //controller.setScene(0); //switch to switchtest1
            controller.previousScene();
        }
        if (window.getInput().isKeyPressed(GLFW_KEY_RIGHT)) {
            controller.nextScene(); // switch to next scene
        }

        if (window.getInput().isKeyPressed(GLFW_KEY_LEFT)) {
            controller.previousScene(); // switch to previous scene
        }
    }
}
