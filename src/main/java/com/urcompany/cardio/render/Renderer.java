package com.urcompany.cardio.render;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import com.urcompany.cardio.gui.*;

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
	}
	
	public Shader getShader() {
		return shader;
	}

	public void render() {
		// TODO Auto-generated method stub

	}

}
