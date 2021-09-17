package de.morphbit.pong.domain;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Paddle {

	private static final long serialVersionUID = 4850529602276357773L;

	private List<Ball> balls;

	public Enemy(Texture texture, float x, float y, float fieldHeight, List<Ball> balls) {
		super(texture, x, y, fieldHeight);
		this.balls = balls;
	}

	public List<Ball> getBalls() {
		return balls;
	}

	public void setBalls(List<Ball> balls) {
		this.balls = balls;
	}

	@Override
	public void move() {
		checkForBall(getNearestBall());
	}

	private Ball getNearestBall() {
		Ball currentBall = balls.get(0);
		for (int i = 0; i < balls.size(); i++) {
			if (balls.get(i).x > currentBall.x) {
				currentBall = balls.get(i);
			}
		}
		return currentBall;
	}

	private void checkForBall(Ball ball) {
		if (ball.isEnraged()) {
			if(ball.y < getY() + getHeight()/2)
				applyPos(y + 10); 
			else {
				applyPos(y - 10);
			}
		} else {
			float smooth = .015f;

			if (ball.direction.x > 0 && (ball.getX() < fieldHeight / 2)) {
				smooth = .075f;
			} else if (ball.direction.x > 0 && (ball.getX() > fieldHeight / 2)) {
				smooth = .17f;
			}

			float newBallPosY = (ball.getCenterY() - height / 2);
			float newPos = y + (newBallPosY - y) * smooth;

			applyPos(newPos);
		}
	}
	
	private void applyPos(float posY)  {
		if (posY > (fieldHeight - height)) {
			posY = fieldHeight - height;
		} else if (posY < 0f) {
			posY = 0f;
		}
		setY(posY);
	}

}
