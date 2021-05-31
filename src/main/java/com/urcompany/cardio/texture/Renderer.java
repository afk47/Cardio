package com.urcompany.cardio.texture;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.urcompany.cardio.Main;
import com.urcompany.cardio.controllers.*;
import com.urcompany.cardio.gui.*;
import com.urcompany.cardio.texture.*;

public class Renderer {
	private Shader shader;

	public Renderer() {

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		shader = new Shader("shader");

	}

	public void render(ArrayList<Drawable> drawables) {
		glClear(GL_COLOR_BUFFER_BIT);
		for (Drawable drawable : drawables) {
			drawable.bind(0);
			shader.bind();
			shader.setUniform("sampler", 0);
			shader.setUniform("projection", drawable.translate());
			drawable.getModel().render();
			
		}
		// TODO Create render method with @Param Drawable which is anything that has to
		// be drawn on screen
	}

	public void render() {
		// TODO Auto-generated method stub

	}

}
