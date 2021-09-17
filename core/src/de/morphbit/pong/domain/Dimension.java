package de.morphbit.pong.domain;

public class Dimension {

	private float width;
	private float height;
	
	public Dimension() {
	}
	public Dimension(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	
}
