package com.nucleardiesel.cardio.gui;

import com.nucleardiesel.cardio.render.Renderer;

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
