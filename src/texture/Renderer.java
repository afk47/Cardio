package texture;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.opengl.GL11.*;
import main.Input;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import main.Main;

public class Renderer {

	public Camera camera;
	private float[] vertices;

	private float[] texture;

	private int[] indices;

	private Model model;
	private Shader shader;

	private Material mat;
	private Material mat2;
	private Matrix4f target;
	private Matrix4f scale;

	public Renderer() {

		 glEnable(GL_BLEND);
		 glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		camera = new Camera(Main.height, Main.width);
		
		vertices = new float[] { -0.5f, 0.5f, 0, 0.5f, 0.5f, 0, 0.5f, -0.5f, 0, -0.5f, -0.5f, 0, };

		texture = new float[] { 0, 0, 1, 0, 1, 1, 0, 1, };

		indices = new int[] { 0, 1, 2, 2, 3, 0 };

		model = new Model(vertices, texture, indices);
		shader = new Shader("shader");

		mat = new Material("testImage.jpg");
		mat2 = new Material("testImage2.jpeg");
		target = new Matrix4f();
		scale = new Matrix4f().translate(new Vector3f(0, 0, 0)).scale(500);

	}

	/*
	 *  Example of rendering "ball" object 
	 *  
	 * 	ball.getMaterial().bind(0);
		ball.update();
		shader.bind();
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", ball.getCamera().getProjection().mul(ball.getTarget()));
		ball.getModel().render(); 
	 * 
	 */
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT);

		target = scale;
		
		
		


		
		
	
		
	}
	
	
}
