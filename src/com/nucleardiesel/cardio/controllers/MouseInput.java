package com.nucleardiesel.cardio.controllers;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MouseInput extends GLFWCursorPosCallback{
	public static double mouseX = 0;
	public static double mouseY = 0;

	@Override
	public void invoke(long window, double xpos, double ypos) {
		mouseX = xpos;
		mouseY = ypos;

	}
}
