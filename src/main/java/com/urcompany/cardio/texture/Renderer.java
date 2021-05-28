package com.urcompany.cardio.texture;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;

import com.urcompany.cardio.Main;
import com.urcompany.cardio.gui.Drawable;

public class Renderer {

	public Camera camera;
	private Shader shader;


	public Renderer() {

		 glEnable(GL_BLEND);
		 glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		camera = new Camera(Main.win.getHeight(), Main.win.getWidth());
		shader = new Shader("shader");



	}



	public void render(Drawable[] drawables) {
		glClear(GL_COLOR_BUFFER_BIT);
		for(Drawable drawable : drawables) {
		drawable.bind(0);
		shader.bind();
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", drawable.translate());
		drawable.getModel().render();
		}
		//TODO Create render method with @Param Drawable which is anything that has to be drawn on screen
	}



	public void render() {
		// TODO Auto-generated method stub

	}




}
