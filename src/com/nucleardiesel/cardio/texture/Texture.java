package com.nucleardiesel.cardio.texture;

import static org.lwjgl.opengl.GL13.*;
import org.lwjgl.opengl.GL11;

public class Texture {
	private int textureID;
	private TextureLoader texture;
	public Texture(String file) {
		textureID = GL11.glGenTextures();
		texture = TextureLoader.loadImage("src/resources/textures" + file);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, texture.getWidth(), texture.getHeight(), 0, GL11.GL_RGBA,
				GL11.GL_UNSIGNED_BYTE, texture.getImage());
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		
	}

	public void bind(int sampler) {
		if(sampler >= 0 && sampler <= 31) {	
		
		glActiveTexture(GL_TEXTURE0 + sampler);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		
		}
	}

	public void remove() {
		GL11.glDeleteTextures(textureID);

	}

	public int getTextureID() {
		return textureID;
	}
	
	public TextureLoader getTexture() {
		return texture;
	}


}
