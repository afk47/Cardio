package com.nucleardiesel.cardio.sound;

import static org.lwjgl.openal.ALC10.*;

import java.io.File;
import java.util.ArrayList;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoundPlayer {

	private static final Logger logger = LoggerFactory.getLogger(SoundPlayer.class);
	private static long audioContext;
	private static long audioDevice;
	private static ArrayList<Sound> sounds = new ArrayList<Sound>();
	public SoundPlayer() {
		init();
	}
	public static void init() {
		try {

			String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
			audioDevice = alcOpenDevice(defaultDeviceName);

			int[] attributes = { 0 };
			audioContext = alcCreateContext(audioDevice, attributes);
			alcMakeContextCurrent(audioContext);

			ALCCapabilities alcCapabilities = ALC.createCapabilities(audioDevice);
			ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);

			if (!alCapabilities.OpenAL10) {
				logger.debug("Audio library not supported");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	public static void destroy() {

		alcDestroyContext(audioContext);
		alcCloseDevice(audioDevice);
	}

	public void addSound(String soundFile, boolean loops) {
		File file = new File(soundFile);
		sounds.add(new Sound(file.getAbsolutePath(), loops));
		
	}
	
	public void play(int index) {
		sounds.get(index).play();
	}
	
	public static ArrayList<Sound> getAllSounds(){
		return sounds;
	}
}
