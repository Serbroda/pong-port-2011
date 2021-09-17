package de.morphbit.pong.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Paddle {

	private static final long serialVersionUID = 435953026373161762L;
	
	public Player(Texture texture, float x, float y, float fieldHeight) {
		super(texture, x, y, fieldHeight);
	}

	@Override
	public void move() {
		float mousePosToWorld = -(Gdx.input.getY() - fieldHeight + height / 2);
		float mouseLerp = y + (mousePosToWorld - y) * .3f;

		if (mouseLerp > (fieldHeight - height)) {
			mouseLerp = fieldHeight - height;
		} else if (mouseLerp < 0f) {
			mouseLerp = 0f;
		}
		setY(mouseLerp);
	}
}
