package com.vgdc.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * UI element for displaying the number of candies collected
 * so far.
 * @author Violet M.
 *
 */
public class Counter {

	// Amount collected so far.
	private int count;
	// The total number
	private int total;

	public Counter(int number)
	{
		count = 0;
		total = number;
	}
	void render(SpriteBatch batch, int x, int y)
	{
		String s = count + "/" + total;
		Fonts.instance.gamer.draw(batch, s, x, y);
	}
}
