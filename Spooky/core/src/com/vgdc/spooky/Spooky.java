package com.vgdc.spooky;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vgdc.utils.Constants;



public class Spooky extends ApplicationAdapter {
	SpriteBatch batch;
	TextureRegion img;

	Texture map;

	PlayerControls variableName;

	@Override
	public void create () {
		Assets.instance.init(new AssetManager());
		batch = new SpriteBatch();
		// Debugging: render an asset
		img = new TextureRegion(Assets.instance.snowTiles.tiles1);
		// Debugging: Make a map
		makeMap();
		initializeThings();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(map, 0,0, 720, 720f);
		batch.draw(img, 0, 0);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
//		img.dispose();

		map.dispose();
	}

	public void makeMap() {
		MapGenerator mg = new MapGenerator();
		long seed = 123456789; // seed can be up to 9 digits.
		map = new Texture(mg.generate(Constants.MAP_WIDTH, Constants.MAP_HEIGHT, seed));
	}

	public void initializeThings()
	{
		variableName = new PlayerControls();
	}
}
