package com.vgdc.spooky;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;
import com.vgdc.utils.Constants;

/**
 * Contains references to all of our assets.
 * @author Derek B.
 *
 */
public class Assets implements Disposable, AssetErrorListener
{
	public static final String TAG = Assets.class.getName();

	public static final Assets instance = new Assets();

	private AssetManager assetManager;

	private Assets()
	{
	}

	public AssetBush bush;
	public AssetTiles snowTiles;
	public AssetTree tree;
	public AssetRock rock;

	public void init (AssetManager assetManager) {
		this.assetManager = assetManager;
		// set asset manager error handler
		assetManager.setErrorListener(this);

		// load texture atlas
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS,
				TextureAtlas.class);

		// start loading assets and wait until finished
		assetManager.finishLoading();
		Gdx.app.debug(TAG,  "# of assets loaded: "
			+ assetManager.getAssetNames().size);
		for (String a: assetManager.getAssetNames()) {
			Gdx.app.debug(TAG, "asset: " + a);
		}

		TextureAtlas atlas =
				assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);

		// Enable texture filtering for pixel smoothing.
		for (Texture t : atlas.getTextures()) {
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}

		// create game resource objects
		bush = new AssetBush(atlas);
		snowTiles = new AssetTiles(atlas);
		tree = new AssetTree(atlas);
		rock = new AssetRock(atlas);
	}
	@Override
	public void dispose() {
		assetManager.dispose();
	}


	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Wouldn't load asset '"
				+ asset.fileName + "'", (Exception) throwable);
	}

	public class AssetTiles
	{

		public final AtlasRegion tiles1;
		public AssetTiles(TextureAtlas atlas)
		{
			tiles1 = atlas.findRegion("snowtile");
		}
	}


	public class AssetTree
	{

		public final AtlasRegion tree1;

		public AssetTree(TextureAtlas atlas)
		{
			tree1 = atlas.findRegion("tree1");
		}

	}

	public class AssetRock
	{
		public final AtlasRegion rock;
		public AssetRock(TextureAtlas atlas)
		{
			rock = atlas.findRegion("rock1");
		}
	}

	public class AssetBush
	{

		public final AtlasRegion bush1;
		public AssetBush(TextureAtlas atlas)
		{
			bush1 = atlas.findRegion("bush");
		}
	}


}

