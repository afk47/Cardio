package com.urcompany.cardio.controllers;

public class Timer {
	/*
	 * TODO Find a better way....
	 */
	public static double getTime() {
		return (double)System.nanoTime() / (double)1000000000L;
	}
}
