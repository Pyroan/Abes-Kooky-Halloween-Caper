package com.vgdc.objects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Our Player: A small pink rectangle..
 * @author Violet M.
 *
 */
public class Candy extends AbstractGameObject
{
	private Texture tex;

	public boolean collected;

	Pixmap pixmap = new Pixmap(200, 200, Format.RGBA8888);
	public Candy()
	{
		dimension.set(1, 1);
		pixmap.setColor(1f,0f,.75f,1f);
		pixmap.fillRectangle(30, 50, pixmap.getWidth() - 60, pixmap.getHeight()-100);
		tex = new Texture(pixmap);
		collected = false;
		bounds.set(0,0, dimension.x, dimension.y);
		origin.set(dimension.x/2, dimension.y/2);
		scale.set(.50f, .50f);
	}
	@Override
	public void render(SpriteBatch batch) {
		if (collected) return;
		batch.draw(tex, position.x, position.y, origin.x,
				origin.y, dimension.x, dimension.y, scale.x,
				scale.y, rotation, 0, 0, tex.getWidth(),
				tex.getHeight(), false, false);
	}
	@Override
	public float getWidth() {
		return tex.getWidth();
	}
	@Override
	public float getHeight() {
		return tex.getHeight();
	}

}
