package de.morphbit.pong.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

public abstract class GameElement extends Rectangle implements Disposable {

	private static final long serialVersionUID = 1L;

	protected Texture texture;

	protected float initX;
	protected float initY;
	
	public GameElement(Texture texture, float x, float y) {
		this.initX = x;
		this.initY = y;
		setTexture(texture);
		resetPosition();
	}
	
	public void resetPosition() {
		this.x = this.initX;
		this.y = this.initY;
	}

	public Texture getTexture() {
		return this.texture;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
		this.width = texture.getWidth();
		this.height = texture.getHeight();
	}
	
	public float getTop() {
        return this.y + this.height;
    }

    public void setTop(float posY) {
        this.y = posY - this.height;
    }

    public void setBottom(float posY) {
        this.y = posY;
    }

    public float getRight() {
        return this.x + this.width;
    }

    public void setRight(float posX) {
        this.x = posX - this.width;
    }

    public float getBottom() {
        return getY();
    }
    
    public float getLeft() {
    	return getX();
    }
    
    public float getCenterX() {
    	return getLeft() + this.width / 2;
    }
    
    public float getCenterY() {
    	return getTop() + this.height / 2;
    }
    
    @Override
    public void dispose() {
    	this.texture.dispose();
    }
}
