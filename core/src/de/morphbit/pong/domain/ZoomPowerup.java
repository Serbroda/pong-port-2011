package de.morphbit.pong.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ZoomPowerup extends Powerup {

	private static final long serialVersionUID = 8230531160684893334L;

	public ZoomPowerup(float fieldWidth, float fieldHeight) {
		super(new Texture(Gdx.files.internal("img/pu_zoom_bw.png")), fieldWidth, fieldHeight);
	}

}
