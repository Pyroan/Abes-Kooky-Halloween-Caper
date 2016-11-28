package com.vgdc.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.vgdc.utils.Constants;
import com.vgdc.utils.GameTimer;

/**
 * Controls/renders all of our UI objects.
 * @author Violet M.
 *
 */
public class UIController {
	// I'm very familiar with the feeling of having no earthly
	// idea what I'm doing.

	private Minimap minimap; // -shrug-
	private GameTimer timer; // -another, slightly bigger shrug
	private CandyCounter counter; // -a third, even bigger shrug-
	// Look, it's shrugs all the way down, ok?

	FreeTypeFontGenerator ftGen = new FreeTypeFontGenerator(Gdx.files.internal("Gamer.ttf"));
	FreeTypeFontParameter param = new FreeTypeFontParameter();
	BitmapFont font;

	public UIController()
	{
		// Set up some font stuff.
		param.size = 24;
		param.borderColor = Color.BLACK;
		param.borderWidth = 2;
		font = ftGen.generateFont(param);
		// Set up the timer.
		timer = new GameTimer();
//		minimap = new Minimap();
		counter = new CandyCounter();
	}

	public void update(float deltaTime)
	{
		timer.update(deltaTime);
//		minimap.update(deltaTime);
	}

	public void render(SpriteBatch batch)
	{
		drawBase();
		drawTime(batch, 10, (int)Constants.VIEWPORT_GUI_HEIGHT-10);
//		minimap.render(batch);
	}

	/**
	 * Draws all the boxes things should be put in.
	 */
	public void drawBase()
	{

	}

	/**
	 * Draws the GameTimer's time, in a box.
	 * @param batch
	 */
	public void drawTime(SpriteBatch batch, int x, int y)
	{
		// Draw the text
		String time = timer.getTime();
		font.setColor(Color.WHITE);
		font.draw(batch, time, x, y);
	}
}
