package com.vgdc.utils;

import com.badlogic.gdx.Gdx;

/**
 * Constants class: Contains constant values to be used by all the code
 * @author Evan S.
 *
 */
public class Constants {

	// Are we debugging map generation?
	public static final boolean DEBUGGING_MAP = false;

	// Our default map size (in tiles)
	public static final int MAP_WIDTH = 32;
	public static final int MAP_HEIGHT = 32;

	// The default window size.
	// It's the number of units the camera can see.
	public static final float VIEWPORT_WIDTH = DEBUGGING_MAP ? MAP_WIDTH : 10;
	public static final float VIEWPORT_HEIGHT = DEBUGGING_MAP ? MAP_HEIGHT : 10;

	// The Viewport for the GUI
	public static final float VIEWPORT_GUI_WIDTH = 1280;
	public static final float VIEWPORT_GUI_HEIGHT = 720;

	// Reference to the Texture Atlas
	public static final String TEXTURE_ATLAS_OBJECTS = "tiles.atlas";

	// Number of Candies in the game.
	public static final int NUMBER_OF_CANDIES = 30;
}
