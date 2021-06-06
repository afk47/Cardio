package com.nucleardiesel.cardio.controllers;

import static org.lwjgl.glfw.GLFW.*;

import com.nucleardiesel.cardio.gui.Scene;
import com.nucleardiesel.cardio.gui.Window;

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
