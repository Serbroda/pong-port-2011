package de.morphbit.pong.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import aurelienribon.tweenengine.Tween;
import de.morphbit.pong.PongGame;

public abstract class AbstractScreen implements Screen {

	protected final PongGame game;
	
	protected Stage stage;
	protected SpriteBatch batch;
	protected Skin skin;
	protected Tween currentTm;
	
	public AbstractScreen(final PongGame game) {
		this.game = game;
		this.batch = new SpriteBatch();
		this.stage = new Stage();
		this.skin = new Skin(Gdx.files.internal("uiskin.json"));
	}
	
	public abstract void update(float delta);
	
	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	@Override
	public void resize(int width, int height) {
		this.stage.getViewport().update(width, height, true);	
	}
	
	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}
}
