package com.nucleardiesel.cardio.gui;

public interface Hoverable {

    // gets called once when initially hovered
    void hoverStart();

    // gets called once when no longer hovered
    void hoverEnd();

    // gets called every frame this object is getting hovered
    void hoverCallback();
	
}
