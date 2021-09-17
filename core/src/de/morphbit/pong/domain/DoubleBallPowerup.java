package de.morphbit.pong.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class DoubleBallPowerup extends Powerup {

	private static final long serialVersionUID = 3857326717719311195L;

	public DoubleBallPowerup(float fieldWidth, float fieldHeight) {
		super(new Texture(Gdx.files.internal("img/pu_double_bw.png")), fieldWidth, fieldHeight);
	}

}
