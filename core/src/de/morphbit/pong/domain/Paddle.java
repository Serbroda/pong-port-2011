package de.morphbit.pong.domain;

import com.badlogic.gdx.graphics.Texture;

public abstract class Paddle extends GameElement {

	private static final long serialVersionUID = 7659600287873637515L;
	private int score;

	protected final float fieldHeight;
	
	public Paddle(Texture texture, float x, float y, float fieldHeight) {
		super(texture, x, y);
		this.fieldHeight = fieldHeight;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void increaseScore() {
		this.score += 1;
	}
	
	public abstract void move() ;

}
