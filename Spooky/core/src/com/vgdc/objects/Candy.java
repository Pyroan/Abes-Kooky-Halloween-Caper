package com.vgdc.objects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vgdc.spooky.Assets;

/**
 * Our Player: A small pink rectangle..
 * @author Violet M.
 *
 */
public class Candy extends AbstractGameObject
{
	private TextureRegion reg;

	public boolean collected;

	Pixmap pixmap = new Pixmap(200, 200, Format.RGBA8888);
	public Candy()
	{
		dimension.set(1, 1);
		pixmap.setColor(1f,0f,.75f,1f);
		pixmap.fillRectangle(30, 50, pixmap.getWidth() - 60, pixmap.getHeight()-100);
		reg = Assets.instance.candy.candy;
		
		collected = false;
		bounds.set(dimension.x * .25f, dimension.y * .25f);
		origin.set(dimension.x/2, dimension.y/2);
		scale.set(.50f, .50f);
	}
	@Override
	public void render(SpriteBatch batch) {
		if (collected) return;
		batch.draw(reg.getTexture(), position.x, position.y,
				origin.x, origin.y, dimension.x, dimension.y,
				scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), false, false);
	}
	@Override
	public float getWidth() {
		return reg.getRegionWidth();
	}
	@Override
	public float getHeight() {
		return reg.getRegionHeight();
	}

}
