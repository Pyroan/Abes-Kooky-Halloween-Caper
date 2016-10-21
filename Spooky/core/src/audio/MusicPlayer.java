package audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicPlayer
{
	public static Music backgroundSong = Gdx.audio.newMusic
			(Gdx.files.internal("Piano Abuse.mp3"));

	public static Music nathanielSnoring = Gdx.audio.newMusic
			(Gdx.files.internal("Nathaniel Snoring.mp3"));
	
	public static Music wind = Gdx.audio.newMusic
			(Gdx.files.internal("wind.mp3"));

	public MusicPlayer()
	{
	}
}
