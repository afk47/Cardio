package com.nucleardiesel.cardio;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import com.nucleardiesel.cardio.controllers.SceneController;
import org.lwjgl.opengl.GL;
import com.nucleardiesel.cardio.controllers.Timer;
import com.nucleardiesel.cardio.gui.Scene;
import com.nucleardiesel.cardio.gui.Window;
import com.nucleardiesel.cardio.scenes.BattleScene;
import com.nucleardiesel.cardio.scenes.SwitchTest1;
import com.nucleardiesel.cardio.scenes.SwitchTest2;
import com.nucleardiesel.cardio.scenes.Test;
import com.nucleardiesel.cardio.sound.SoundPlayer;

public class Main implements Runnable {

	private boolean fullscreen = false;

	private Thread thread;
	private boolean running = false;
	public static Window win;

	private final String title = "Cardio";
	private static SceneController controller;

	long frame_cap = 1000 / 60;
	double time = Timer.getTime();
	double frame_time = Timer.getTime();
	int frames = 0;
	private static double passed;

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

		controller = new SceneController(win, new Scene[] {new BattleScene(), new SwitchTest1(),new SwitchTest2()});
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
			controller.render();
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
			SoundPlayer.destroy();
		}

		win.update();
		controller.update();

		time = time_2;
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
	
	public static SceneController getController() {
		return controller;
	}

}