package de.morphbit.pong.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import de.morphbit.pong.PongGame;

public class LoadingScreen extends AbstractScreen {

	Table table;
	
	public LoadingScreen(final PongGame game) {
		super(game);
		
		table = new Table();
		table.setFillParent(true);
		
		Label titleLabel = new Label("HAPPY PONG Loading", skin);
		
		TextButton btnSettings = new TextButton("Back", skin);
		btnSettings.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Gdx.input.setInputProcessor(null);
			}
		});

		table.add(titleLabel);
		table.row();
		table.add(btnSettings).width(200).height(75);;
		
		stage.addActor(table);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
}
