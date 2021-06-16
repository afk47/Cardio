package com.nucleardiesel.cardio.controllers;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;

import org.joml.Vector3f;

public class Input {
	private long window;

	private boolean keys[];

	private static Vector3f mousePos = new Vector3f();
	private static double[] x = new double[1], y = new double[1];
	private static int[] winWidth = new int[1], winHeight = new int[1];
	static int oldState = GLFW_RELEASE;
	
	public Input(long window) {
		
		this.window = window;
		this.keys = new boolean[GLFW_KEY_LAST];
		for (int i = 0; i < GLFW_KEY_LAST; i++)
			keys[i] = false;
	}

	public boolean isKeyDown(int key) {
		return glfwGetKey(window, key) == 1;
	}

	public boolean isKeyPressed(int key) {
		return (isKeyDown(key) && !keys[key]);
	}

	public boolean isKeyReleased(int key) {
		return (!isKeyDown(key) && keys[key]);
	}

	public boolean isMouseButtonDown(int button) {
		oldState  = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT);
		return glfwGetMouseButton(window, button) == 1;
	}
	
	public boolean isMouseButtonReleased(int button) {
		int newState = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT);
		if (newState == GLFW_RELEASE && oldState == GLFW_PRESS) {
			oldState = newState;
			return true;
		}
		oldState = newState;
		return false;
	}

	public Vector3f getMousePosition() {
		glfwGetCursorPos(window, x, y);

		glfwGetWindowSize(window, winWidth, winHeight);
		
		mousePos.set((float) x[0] - (winWidth[0] / 2.0f), -((float) y[0] - (winHeight[0] / 2.0f)), 0);

		return mousePos;
	}

	public void update() {
		for (int i = 32; i < GLFW_KEY_LAST; i++)
			keys[i] = isKeyDown(i);
	}
}