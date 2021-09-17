package de.morphbit.pong.manager;

import java.util.HashMap;

import de.morphbit.pong.PongGame;
import de.morphbit.pong.screen.AbstractScreen;
import de.morphbit.pong.screen.GameScreen;
import de.morphbit.pong.screen.LoadingScreen;
import de.morphbit.pong.screen.MenuScreen;

public class GameScreenManager {

	public enum STATE {
		MAIN_MENU,
		PLAY,
		SETTINGS;
	}
	
	private final PongGame game;
	
	private HashMap<STATE, AbstractScreen> screens;
	
	public GameScreenManager(PongGame game) {
		this.game = game;
		initScreens();
		setScreen(STATE.PLAY);
	}
	
	private void initScreens() {
		screens = new HashMap<STATE, AbstractScreen>();
		screens.put(STATE.MAIN_MENU, new MenuScreen(game));
		screens.put(STATE.PLAY, new GameScreen(game));
		screens.put(STATE.SETTINGS, new LoadingScreen(game));
	}
	
	public void setScreen(STATE nextScreen) {
		game.setScreen(screens.get(nextScreen));
	}
	
	public void dispose() {
		for(AbstractScreen screen : screens.values()) {
			if(screen != null) {
				screen.dispose();
			}
		}
	}
}
