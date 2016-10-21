package com.vgdc.utils;
/**
 * Constants class: Contains values to be used by all the code
 * @author Violet M.
 *
 */
public final class Constants {

	//pixels per meter
	//use to convert to box2d units
	//so setting boxshape and getting object position
	//giving units -> divide
	//getting units -> multiply
	public static final float PPM = 32;

	// The default window size.
	public static final float VIEWPORT_WIDTH = 5.0f;
	public static final float VIEWPORT_HEIGHT = 5.0f;

	// Our default map size (in tiles)
	// 	not sure but going to assume MUST BE POWER OF 2!!!!!
	public static final int MAP_WIDTH = 32;
	public static final int MAP_HEIGHT = 32;

	// Reference to the Texture Atlas
	public static final String TEXTURE_ATLAS_OBJECTS = "tiles.atlas";
}
