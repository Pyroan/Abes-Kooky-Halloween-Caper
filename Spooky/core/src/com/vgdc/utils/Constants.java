package com.vgdc.utils;
/**
 * Constants class: Contains values to be used by all the code
 * @author Evan S.
 *
 */
public class Constants {

	// Are we debugging map generation?
	public static final boolean DEBUGGING_MAP = true;

	// Our default map size (in tiles)
	public static final int MAP_WIDTH = 32;
	public static final int MAP_HEIGHT = 32;
	
	// The default window size.
	// I don't fully understand how this works.
	public static final float VIEWPORT_WIDTH = DEBUGGING_MAP ? MAP_WIDTH : 6;
	public static final float VIEWPORT_HEIGHT = DEBUGGING_MAP ? MAP_HEIGHT : 6;

	// Reference to the Texture Atlas
	public static final String TEXTURE_ATLAS_OBJECTS = "tiles.atlas";
}
