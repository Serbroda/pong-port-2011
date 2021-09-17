package de.morphbit.pong.util;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import de.morphbit.pong.domain.Dimension;

public final class Utils {

	private static GlyphLayout layout = new GlyphLayout();
	
	private Utils() {
	}
	
	public static int random(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static float random(float min, float max) {
	    return (float)random((int)min, (int)max);
	}
	
	public static Dimension getFontBounds(BitmapFont font, String text) {
		layout.setText(font, text);
		return new Dimension(layout.width, layout.height);
	}
	
	public static Texture createRectImage(int width, int height, Color color) {
		Pixmap ballPixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
		ballPixmap.setColor(color);
		ballPixmap.fill();
		return new Texture(ballPixmap);
	}
}
