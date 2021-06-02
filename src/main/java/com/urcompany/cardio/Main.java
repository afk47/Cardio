package com.urcompany.cardio;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.ArrayList;

import org.lwjgl.opengl.GL;

import com.urcompany.cardio.controllers.Timer;
import com.urcompany.cardio.entity.Player;
import com.urcompany.cardio.gui.Background;
import com.urcompany.cardio.gui.Button;
import com.urcompany.cardio.gui.Drawable;
import com.urcompany.cardio.gui.Window;
import com.urcompany.cardio.texture.Renderer;

import com.urcompany.cardio.texture.*;

public class Main implements Runnable {

	public static int score;
	private boolean fullscreen = true;

	private Thread thread;
	private boolean running = false;
	public static Window win;

	private String title = "Game";
	private boolean space = false;
	public static Renderer renderer;

	double frame_cap = 1.0 / 60.0;
	double time = Timer.getTime();
	double frame_time = 0;
	int frames = 0;
	private static double passed;

	// test
	public Player p1;
	public Background bg;
	private ArrayList<Drawable> drawables = new ArrayList<Drawable>(); // List of all drawables where the lower the
																		// index the farther back it is Example:
																		// background is furthest back
	private Button bttn;

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
		Window.setCallbacks();
		running = true;

		win = new Window();
		win.setFullscreen(!fullscreen);
		win.createWindow(title);
		GL.createCapabilities();
		p1 = new Player(win);
		bg = new Background(win);
		
		bttn = new Button(win);
		
		renderer = new Renderer();

		glEnable(GL_TEXTURE_2D);

		drawables.add(bg);
		drawables.add(p1);
		drawables.add(bttn);
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

		win.update();

		if (win.getInput().isKeyReleased(GLFW_KEY_SPACE)) {// temporary to test animation system
			p1.attack();
		}

		if (win.getInput().isKeyDown(GLFW_KEY_A)) {// temporary to test image translation
			p1.turnLeft();
			p1.run();
			p1.addPosition(new float[] { -20f, 0f, 0f });
		}
		if (win.getInput().isKeyReleased(GLFW_KEY_A)) {// temporary to test image translation
			p1.idle();
			p1.addPosition(new float[] { 20f, 0f, 0f });
		}
		if (win.getInput().isKeyDown(GLFW_KEY_D)) {// temporary to test image translation
			p1.turnRight();
			p1.run();
			p1.addPosition(new float[] { 20f, 0f, 0f });
		}
		if (win.getInput().isKeyReleased(GLFW_KEY_D)) {// temporary to test image translation
			p1.idle();
			p1.addPosition(new float[] { 20f, 0f, 0f });
		}
		if (win.getInput().isKeyReleased(GLFW_KEY_W)) {// temporary to test image translation
			p1.jump();

		}
		if (win.getInput().isKeyDown(GLFW_KEY_S)) {// temporary to test image translation
			p1.idle();
		}

		if (p1.getPosition()[1] >= -300 && !p1.getState().equals("Jump")) {
			p1.addPosition(new float[] { 0f, -20f, 0f });
		}

		if (win.getInput().isKeyPressed(GLFW_KEY_ESCAPE) || win.shouldClose()) {// window close shortcut (also
																				// temporary)
			running = false;
		}

		for (Drawable d : drawables) {// Iterates through all drawables and updates them in order
			d.update();
		}
		time = time_2;
	}

	/*
	 * Renders the main screen
	 * 
	 */
	private void render() {

		renderer.render(drawables);

	}

	/*
	 * Creates a new main thread
	 */
	public static void main(String[] args) {
		new Main().start();
	}

	public static void addScore(int i) {
		score += i;
	}

	public static double getPassed() {
		return passed;
	}

	public void addDrawable(Drawable d) {

	}

}