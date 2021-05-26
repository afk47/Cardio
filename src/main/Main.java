package main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.IntBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import gui.Window;
import texture.Camera;
import texture.Material;
import texture.Model;
import texture.Renderer;
import texture.Shader;

public class Main implements Runnable {

	public static int width = 1280;// Window Width 
	public static int height = 720;// Window Height
	public static int score;
	private boolean fullscreen = false;

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
		win.setFullscreen(fullscreen);
		win.createWindow("Game");
		GL.createCapabilities();

		
		renderer = new Renderer();

		glEnable(GL_TEXTURE_2D);
	}

	/*
	 * Main Loop while running is true calls Update and Render methods Sets running
	 * to false when window closes
	 */
	@Override
	public void run() {
		init();
		boolean can_render = false;

		double unprocessed = 0;
		while (running) {

			double time_2 = Timer.getTime();
			double passed = time_2 - time;
			unprocessed += passed;
			frame_time += passed;

			time = time_2;
			while (unprocessed >= frame_cap) {
				unprocessed -= frame_cap;
				can_render = true;
				update();

				if (frame_time >= 1.0) {
					frame_time = 0;
					System.out.println("FPS: " + frames);
					frames = 0;
				}

			}	
			
			if(can_render) {
				render();
		
				if (win.shouldClose()) {
					running = false;

				}
				win.swapBuffers();
				frames++;
			}

		}
	}

	/*
	 * updates screen and checks for new events
	 * 
	 */
	private void update() {
		win.update();

		

	}

	/*
	 * Renders the main screen
	 * 
	 */
	private void render() {
		renderer.render();
		
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

}
