package com.vgdc.spooky;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vgdc.utils.Constants;



public class Spooky extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	Texture map;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		// Debugging: Make a map
		makeMap();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(map, Constants.DEFAULT_WIDTH/2, Constants.DEFAULT_HEIGHT/2);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();

		map.dispose();
	}

	public void makeMap() {
		MapGenerator mg = new MapGenerator();
		long seed = 123456789; // seed can be up to 9 digits.
		map = new Texture(mg.generate(Constants.MAP_WIDTH, Constants.MAP_HEIGHT, seed));
	}
}
