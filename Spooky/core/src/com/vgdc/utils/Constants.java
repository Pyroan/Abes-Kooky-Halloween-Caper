package com.vgdc.utils;
/**
 * Constants class: Contains values to be used by all the code
 * @author Violet M.
 *
 */
public class Constants {

	// Are we debugging stuff?
	public static final boolean DEBUGGING_MAP = false;
	
	// The default window size.
	public static final float VIEWPORT_WIDTH = DEBUGGING_MAP ? 16 : 6;
	public static final float VIEWPORT_HEIGHT = DEBUGGING_MAP ? 16 : 6;

	// Our default map size (in tiles)
	// 	not sure but going to assume MUST BE POWER OF 2!!!!!
	public static final int MAP_WIDTH = 32;
	public static final int MAP_HEIGHT = 32;

	// Reference to the Texture Atlas
	public static final String TEXTURE_ATLAS_OBJECTS = "tiles.atlas";
}
