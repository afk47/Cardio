package com.nucleardiesel.cardio.controllers;

public class Timer {
	/**
	 *
	 *  gets time in seconds
	 */
	public static double getTime() {
		return (double)System.nanoTime() / (double)1000000000L;
	}

}
