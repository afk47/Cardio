package com.nucleardiesel.cardio;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.opengl.GL;

import com.nucleardiesel.cardio.controllers.Timer;
import com.nucleardiesel.cardio.gui.Scene;
import com.nucleardiesel.cardio.gui.Window;
import com.nucleardiesel.cardio.scenes.Test;
import com.nucleardiesel.cardio.texture.Renderer;

public class Main implements Runnable {

	private boolean fullscreen = false;

	private Thread thread;
	private boolean running = false;
	public static Window win;

	private String title = "Cardio";
	public static Renderer renderer;

	long frame_cap = 1000 / 60;
	double time = Timer.getTime();
	double frame_time = Timer.getTime();
	int frames = 0;
	private static double passed;

	//temp
	public Scene[] scenes;
	public int currentScene = 0;

	/*
	 * creates new Main Thread and starts it
	 *
	 */
	public void start() {
		running = false;
		thread = new Thread(this, "Game");
		thread.start();
	}

	/*
	 * Initializes the window and sets the main thread to running
	 *
	 */
	private void init() {
		running = true;
		Window.setCallbacks();
		win = new Window();
		win.setFullscreen(fullscreen);
		win.createWindow(title);
		GL.createCapabilities();
		glEnable(GL_TEXTURE_2D);
		renderer = new Renderer();

		//temp
		scenes = new Scene[] {new Test(win)};
	}

	/*
	 * Main Loop while running is true calls Update and Render methods Sets running
	 * to false when window closes
	 */
	@Override
	public void run() {
		init();
		while (running) {
			update();
			render();
			win.swapBuffers();
		}
	}

	/*
	 * updates screen and checks for new events
	 *
	 */
	private void update() { // A Timer that keeps track of how long since this was last called in Seconds
		double time_2 = Timer.getTime();
		passed = time_2 - time;

		// caps updates at 60 per second if no vsync. crappy way of doing it tho
		try {
			Thread.sleep(frame_cap);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		frames++;
		if (time_2 - frame_time >= 1) {
			System.out.println("fps: " + frames);
			frames = 0;
			frame_time = time_2;
		}

		if (win.getInput().isKeyPressed(GLFW_KEY_ESCAPE) || win.shouldClose()) { // window close shortcut (also temporary)
			running = false;
		}

		win.update();

		scenes[currentScene].updateScene();

		time = time_2;
	}

	/*
	 * Renders the main screen
	 *
	 */
	private void render() {
		renderer.render(scenes[currentScene].getContents());
	}

	/*
	 * Creates a new main thread
	 */
	public static void main(String[] args) {
		new Main().start();
	}

	public static double getPassed() {
		return passed;
	}

}