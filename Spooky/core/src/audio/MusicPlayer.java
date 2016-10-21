package audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicPlayer
{
	public static Music song1 = Gdx.audio.newMusic
			(Gdx.files.internal("Piano Abuse.mp3"));

	private MusicPlayer()
	{
	}

	public static Music getMusic()
	{
		return song1;

	}
}
