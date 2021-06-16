package com.nucleardiesel.cardio.controllers;

import com.nucleardiesel.cardio.gui.Scene;
import com.nucleardiesel.cardio.gui.Window;
import com.nucleardiesel.cardio.render.Renderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class SceneController {

	private static final Logger logger = LoggerFactory.getLogger(SceneController.class);

	private final ArrayList<Scene> scenes;
	private final Window window;
	private final Renderer renderer;
	private int currentScene = 0;

	public SceneController(Window win) {
		this(win, new Scene[]{});
	}

	public SceneController(Window win, Scene[] scenes) {
		this.scenes = new ArrayList<>();
		renderer = new Renderer();
		window = win;

		init(scenes);
	}

	private void init(Scene[] scenes) {
		for (Scene scene : scenes) {
			addScene(scene);
		}
	}

	public void update() {
		scenes.get(currentScene).updateScene();
	}

	public void render() {
		renderer.render(scenes.get(currentScene).getContents());
	}

	// gets first scene ID with this name. -1 if not found.
	public int getSceneId(String name) {
		for (int i = 0; i < scenes.size(); i++) {
			if (scenes.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public void setScene(int sceneId) {
		if (sceneId < 0 || sceneId >= scenes.size()) {
			logger.error("Tried to switch to invalid sceneId.");
			return;
		}
		currentScene = sceneId;
	}

	public void nextScene() {
		setScene(currentScene + 1);
	}

	public void previousScene() {
		setScene(currentScene - 1);
	}

	public void addScene(Scene scene) {
		scene.setController(this);
		scene.setWindow(window);
		scene.init();
		this.scenes.add(scene);
	}

	public void removeScene(int sceneId) {
		if (sceneId == currentScene) {
			logger.error("Cannot remove currently active scene.");
			return;
		}
		scenes.remove(sceneId);
	}

	public int getCurrentScene() {
		return currentScene;
	}

	public Window getWindow() {
		return window;
	}

	public Renderer getRenderer() {
		return renderer;
	}

}
