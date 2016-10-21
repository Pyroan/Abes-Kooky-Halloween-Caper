package com.vgdc.objects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends AbstractGameObject
{
	Pixmap pixmap = new Pixmap(200, 200, Format.RGBA8888);
	public Player()
	{
		pixmap.setColor(0f,0f,0f,1f);
		pixmap.drawCircle(50, 50, 50);
	}
	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}
	
}
