package de.morphbit.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import de.morphbit.pong.accessor.TableAccessor;
import de.morphbit.pong.manager.GameScreenManager;

public class PongGame extends Game {

	public static final int APP_WIDTH = 800;
	public static final int APP_HEIGHT = 480;
	public static final int APP_FPS = 60;
	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 480;
	
	private SpriteBatch batch;
	private TweenManager tweenManager;
	private AssetManager assetManager;
	private GameScreenManager screenManager;

	@Override
	public void create() {
		setTweenManager(new TweenManager());
		Tween.setCombinedAttributesLimit(4);
		Tween.registerAccessor(Table.class, new TableAccessor());

		setAssetManager(new AssetManager());
		
		batch = new SpriteBatch();
        
        screenManager = new GameScreenManager(this);
	}

	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		assetManager.dispose();
		screenManager.dispose();
	}

	public TweenManager getTweenManager() {
		return tweenManager;
	}

	public void setTweenManager(TweenManager tweenManager) {
		this.tweenManager = tweenManager;
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}
	
	public GameScreenManager getGameScreenManager() {
		return this.screenManager;
	}
}
