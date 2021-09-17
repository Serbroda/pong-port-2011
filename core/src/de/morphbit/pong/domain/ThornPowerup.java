package de.morphbit.pong.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ThornPowerup extends Powerup {

	private static final long serialVersionUID = 1316600035982923422L;

	public ThornPowerup(float fieldWidth, float fieldHeight) {
		super(new Texture(Gdx.files.internal("img/pu_thorn_bw.png")), fieldWidth, fieldHeight);
	}

}
