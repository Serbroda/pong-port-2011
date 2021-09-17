package de.morphbit.pong.domain;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

import de.morphbit.pong.util.Utils;

public class Powerup extends GameElement {

	private static final long serialVersionUID = 1L;
	
	private boolean shouldShow;
	private boolean destroyed;
	private float fieldWidth;
	private float fieldHeight;

	public Powerup(Texture texture, float fieldWidth, float fieldHeight) {
		super(texture, 0, 0);
		this.fieldWidth = fieldWidth;
		this.fieldHeight = fieldHeight;
		setShouldShow(false);
		setRandomPosition();
	}

	public boolean isShouldShow() {
		return shouldShow;
	}

	public void setShouldShow(boolean shouldShow) {
		this.shouldShow = shouldShow;
	}
	
	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroy) {
		this.destroyed = destroy;
		if(destroy) {
			setShouldShow(false);
		}
	}

	public void calcShouldShow() {
		if(!isDestroyed() && !isShouldShow()) {
			Random rnd = new Random();
			int rnd1 = rnd.nextInt(2147483647);
			if(rnd1 < 2500000) {
				this.shouldShow = true;
			}
		}
	}
	
	public void setRandomPosition() {
		float effectiveXStart = 0 + 80;
		float effectiveXEnd = this.fieldWidth - 80;
		float effectiveYStart = 0 + 80;
		float effectiveYEnd = this.fieldHeight - 80;
		
		this.x = Utils.random(effectiveXStart, effectiveXEnd);
		this.y = Utils.random(effectiveYStart, effectiveYEnd);
	}

}
