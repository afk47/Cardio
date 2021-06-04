package com.urcompany.cardio.gui;

import com.urcompany.cardio.render.Renderer;

public class SceneRenderer {

	Scene scene;
	
	public SceneRenderer(Scene s) {
		scene = s;
	}
	public void renderScene(){
		Renderer renderer = new Renderer();
		renderer.render(scene.getContents());
		
	}
	
	
}
