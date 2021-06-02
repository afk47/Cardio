package com.urcompany.cardio.controllers;

import com.urcompany.cardio.entity.Player;
import com.urcompany.cardio.gui.Scene;
import com.urcompany.cardio.gui.Window;
import static org.lwjgl.glfw.GLFW.*;
public class SceneController {

	private Window window;
	private Scene scene;
	private Input input;
	
	public SceneController(Scene scene) {
		this.scene = scene;
		window = scene.getWindow();
		input = window.getInput();
	}
	
	public void pollPlayerInput() {
		
		input.isKeyDown(GLFW_KEY_W);
		input.isKeyDown(GLFW_KEY_A);
		input.isKeyDown(GLFW_KEY_S);
		input.isKeyDown(GLFW_KEY_D);
	}
}
