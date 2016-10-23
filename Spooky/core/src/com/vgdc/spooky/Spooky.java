package com.vgdc.spooky;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vgdc.objects.Tree;
import com.vgdc.utils.Constants;



public class Spooky extends ApplicationAdapter {
	
	SpriteBatch batch;

	PlayerControls variableName;

	PlayerControls controller;

	WorldRenderer worldRenderer;
	WorldController worldController;

	public Level level;

	@Override
	public void create () {
		Assets.instance.init(new AssetManager());
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		batch = new SpriteBatch();
		initializeThings();

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		worldController.update(Gdx.graphics.getDeltaTime());
		worldRenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	@Override
	public void dispose() {
		worldRenderer.dispose();
		Assets.instance.dispose();
	}

	public void initializeThings()
	{
		controller = new PlayerControls();
	}

}
