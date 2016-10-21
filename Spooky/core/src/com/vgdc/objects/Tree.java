package com.vgdc.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vgdc.spooky.Assets;

public class Tree extends AbstractGameObject{

	// Texture Region of the tree asset
	private TextureRegion reg;

	public Tree() {
		init();
	}

	// Sets some stuff, finds reg.
	private void init() {
		dimension.set(1,1);
		origin.set(dimension.x/2, dimension.y/2);
		reg = Assets.instance.tree.tree1;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(reg.getTexture(), position.x, position.y,
				origin.x, origin.y, dimension.x, dimension.y,
				scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), false, false);
	}

}
