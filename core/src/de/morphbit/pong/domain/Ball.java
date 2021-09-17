package de.morphbit.pong.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.morphbit.pong.util.Utils;

public class Ball extends GameElement {

	private static final long serialVersionUID = 6823377999809625372L;

	public final int MAX_SPEED = 1000;
	public final int BALL_SPEED = 300;
	public final int ADD_SPEED = 15;

	private boolean isEnraged;
	private int currentSpeed;
	public Vector2 direction;

	public Ball(Texture image, float x, float y) {
		super(image, x, y);
		this.direction = new Vector2();

		currentSpeed = BALL_SPEED;
		setRandomStart();
	}
	
	public void reset() {
		this.resetPosition();
		this.currentSpeed = BALL_SPEED;
		this.direction = new Vector2();
		setRandomStart();
	}
	
	private void setRandomStart() {
		float dirx;
		int lr = Utils.random(0, 1);
		if(lr > 0) {
			dirx = .45f;
		} else {
			dirx = -.45f;
		}
		direction.set(dirx, .20f).nor();
	}

	public void move(float delta) {
		x += currentSpeed * direction.x * delta;
		y += currentSpeed * direction.y * delta;
	}

	public void reverseX() {
		this.direction.x *= -1;
	}

	public void reverseY() {
		this.direction.y *= -1;
	}
	
	public void increaseSpeed() {
		if(currentSpeed < MAX_SPEED) {
			this.currentSpeed += 20;			
		}
	}

	public boolean isEnraged() {
		return isEnraged;
	}

	public void setEnraged(boolean isEnraged) {
		this.isEnraged = isEnraged;
	}
}
