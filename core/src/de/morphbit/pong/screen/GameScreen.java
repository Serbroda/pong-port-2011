package de.morphbit.pong.screen;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import de.morphbit.pong.PongGame;
import de.morphbit.pong.domain.Ball;
import de.morphbit.pong.domain.Dimension;
import de.morphbit.pong.domain.DoubleBallPowerup;
import de.morphbit.pong.domain.Enemy;
import de.morphbit.pong.domain.Paddle;
import de.morphbit.pong.domain.Player;
import de.morphbit.pong.domain.Powerup;
import de.morphbit.pong.domain.ThornPowerup;
import de.morphbit.pong.domain.ZoomPowerup;
import de.morphbit.pong.util.Utils;

public class GameScreen extends AbstractScreen {

	World world;
	OrthographicCamera camera;

	List<Ball> balls;
	List<Powerup> powerups;
	Paddle player1;
	Paddle player2;
	BitmapFont scoreFont;

	public GameScreen(PongGame game) {
		super(game);

		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, PongGame.V_WIDTH, PongGame.V_HEIGHT);
		this.scoreFont = getScoreFont();
	}

	@Override
	public void show() {
		world = new World(new Vector2(0f, 0f), false);

		balls = new ArrayList<Ball>();
		powerups = new ArrayList<Powerup>();

		player1 = new Player(Utils.createRectImage(10, 60, Color.WHITE), 20f, camera.viewportHeight / 2,
				camera.viewportHeight);
		player2 = new Enemy(Utils.createRectImage(10, 60, Color.WHITE), camera.viewportWidth - 20f - 10,
				camera.viewportHeight / 2, camera.viewportHeight, balls);

		balls.add(new Ball(Utils.createRectImage(12, 12, Color.WHITE), camera.viewportWidth / 2,
				camera.viewportHeight / 2));

		powerups.add(new ZoomPowerup(camera.viewportWidth, camera.viewportHeight));
		powerups.add(new DoubleBallPowerup(camera.viewportWidth, camera.viewportHeight));
		powerups.add(new ThornPowerup(camera.viewportWidth, camera.viewportHeight));

		game.getBatch().setProjectionMatrix(this.camera.combined);
	}

	@Override
	public void update(float delta) {
		world.step(1f / PongGame.APP_FPS, 6, 2);

		player1.move();
		player2.move();
		for (Ball ball : balls) {
			ball.move(delta);
		}

		checkForPaddleCollision();
		checkForWallCollision();
		checkForOutOfFieldCollision();
		checkForPowerupCollision();

		stage.act(delta);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		batchDraw();
	}

	private void batchDraw() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawPaddles();
		drawBalls();
		drawScores();
		drawPowerups();

		batch.end();
	}

	private void drawPaddles() {
		batch.draw(player1.getTexture(), player1.getX(), player1.getY());
		batch.draw(player2.getTexture(), player2.getX(), player2.getY());
	}

	private void drawBalls() {
		for (Ball ball : balls) {
			batch.draw(ball.getTexture(), ball.getX(), ball.getY());
		}
	}

	private void drawScores() {
		Dimension player1Dim = Utils.getFontBounds(scoreFont, String.valueOf(player1.getScore()));
		Dimension player2Dim = Utils.getFontBounds(scoreFont, String.valueOf(player2.getScore()));
		scoreFont.draw(batch, String.valueOf(player1.getScore()),
				(camera.viewportWidth * .25f) - (player1Dim.getWidth() / 2), camera.viewportHeight - 5);
		scoreFont.draw(batch, String.valueOf(player2.getScore()),
				(camera.viewportWidth * .75f) - (player2Dim.getWidth() / 2), camera.viewportHeight - 5);
	}

	private void drawPowerups() {
		for (Powerup p : powerups) {
			p.calcShouldShow();
			if (p.isShouldShow()) {
				batch.draw(p.getTexture(), p.getX(), p.getY());
			}
		}
	}

	private void checkForPowerupCollision() {
		for(int i = 0; i < balls.size(); i++) {
			for (Powerup p : powerups) {
				if (checkForPupwerupCollision(balls.get(i), p)) {
					p.setDestroyed(true);
				}
			}
		}
	}

	private boolean checkForPupwerupCollision(Ball ball, Powerup powerup) {
		if (!powerup.isShouldShow()) {
			return false;
		}
		if (Intersector.overlaps(ball, powerup)) {
			if (powerup instanceof ZoomPowerup) {
				if (ball.direction.x > 0) {
					player1.setTexture(Utils.createRectImage(10, 100, Color.WHITE));
				} else {
					player2.setTexture(Utils.createRectImage(10, 100, Color.WHITE));
				}
			} else if (powerup instanceof DoubleBallPowerup) {
				balls.add(new Ball(Utils.createRectImage(12, 12, Color.LIGHT_GRAY), camera.viewportWidth / 2,
						camera.viewportHeight / 2));
			} else if (powerup instanceof ThornPowerup) {
				ball.setEnraged(true);
				ball.setTexture(Utils.createRectImage(12, 12, Color.RED));
			}
			return true;
		}
		return false;
	}

	private void checkForPaddleCollision() {
		for (Ball ball : balls) {
			checkForPaddleCollision(ball, player1, false);
			checkForPaddleCollision(ball, player2, true);
		}
	}

	private void checkForPaddleCollision(Ball ball, Paddle paddle, boolean isRight) {
		if (Intersector.overlaps(paddle, ball)) {
			if(ball.isEnraged()) {
				paddle.increaseScore();

				resetPlayers();
				resetBalls();
				resetPowerups();
			} else {
				float dirX = paddle.getHeight() / 2;
				if (isRight) {
					dirX *= -1;
				}
				ball.direction.x = dirX;

				float middlePanel = paddle.getY() + paddle.getHeight() / 2 - ball.getHeight();
				float ballPointPanel = -(middlePanel - ball.y);
				ball.direction.y = ballPointPanel;
				ball.direction.nor();

				ball.increaseSpeed();
			}
		}
	}

	private void checkForWallCollision() {
		for (Ball ball : balls) {
			checkForWallCollision(ball);
		}
	}

	private void checkForWallCollision(Ball ball) {
		if (ball.getTop() > camera.viewportHeight) {
			ball.reverseY();
			ball.setTop(camera.viewportHeight);
		} else if (ball.getY() < 0) {
			ball.reverseY();
			ball.setBottom(0f);
		}
	}

	private void checkForOutOfFieldCollision() {
		for(int i = 0; i < balls.size(); i++) {
			checkForOutOfFieldCollision(balls.get(i));
		}
	}

	private void checkForOutOfFieldCollision(Ball ball) {
		if (ball.getRight() < 0) {
			if(ball.isEnraged()) {
				ball.reverseX();
				ball.setRight(0);
				ball.increaseSpeed();
			} else {
				player2.increaseScore();

				resetPlayers();
				resetBalls();
				resetPowerups();
			}
		} else if (ball.getLeft() > camera.viewportWidth) {
			if(ball.isEnraged()) {
				ball.reverseX();
				ball.setRight(camera.viewportWidth);
				ball.increaseSpeed();
			} else {
				player1.increaseScore();

				resetPlayers();
				resetBalls();
				resetPowerups();
			}
		}
	}

	private void resetBalls() {
		balls.clear();
		balls.add(new Ball(Utils.createRectImage(12, 12, Color.WHITE), camera.viewportWidth / 2,
				camera.viewportHeight / 2));
	}
	private void resetPlayers() {
		player1.resetPosition();
		player1.setTexture(Utils.createRectImage(10, 60, Color.WHITE));
		player2.resetPosition();
		player2.setTexture(Utils.createRectImage(10, 60, Color.WHITE));
	}

	private void resetPowerups() {
		for (Powerup p : powerups) {
			p.setShouldShow(false);
			p.setDestroyed(false);
			p.setRandomPosition();
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		super.dispose();
		world.dispose();
		player1.dispose();
		player2.dispose();
		for (Ball b : balls) {
			b.dispose();
		}
	}

	private BitmapFont getScoreFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PixelCaps.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 30;
		parameter.color = Color.WHITE;
		BitmapFont titleFont = generator.generateFont(parameter);
		titleFont.setUseIntegerPositions(false);
		generator.dispose();

		return titleFont;
	}

}
