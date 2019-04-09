package nl.han.ica.breakout;

import java.util.ArrayList;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;
import processing.event.KeyEvent;

public class Breakout extends GameEngine {
	private BlockSpawner blockSpawner;
	private Player player;
	private Level currentLevel;
	private ArrayList<Level> gameLevels;
	private Ball ball;
	private TextObject scoreBoardText;
	private TextObject gameMessage;
	private boolean gameRunning;

	public static void main(String[] args) {
		Breakout tw = new Breakout();
		tw.runSketch();
	}

	/**
	 * Creates a new game
	 */
	@Override
	public void setupGame() {
		int worldWidth = 500;
		int worldHeight = 500;
		createView(worldWidth, worldHeight);
		initializePlayer();
		createBall();
		initializeLevels();
		initializeTextObjects();
		gameRunning = true;
	}

	/**
	 * creates the View for display
	 * 
	 * @param screenHeight
	 * @param screenWidth
	 */
	private void createView(int screenHeight, int screenWidth) {
		size(screenWidth, screenHeight);
		View view = new View(screenWidth, screenHeight);
		setView(view);
	}

	/**
	 * Initializes the player
	 */
	private void initializePlayer() {
		player = new Player(this);
		addGameObject(player, this.width / 3, this.height);

	}

	/**
	 * Creates the levels for the game and starts the first level
	 */
	private void initializeLevels() {
		gameLevels = new ArrayList<Level>();
		gameLevels.add(createLevel(this, 2, 2, 1, 3));
		gameLevels.add(createLevel(this, 5, 5, 2, 5));
		currentLevel = gameLevels.get(0);
		initializeBlockSpawner();
		currentLevel.startLevel();
	}

	/**
	 * Creates a Level
	 * 
	 * @param world
	 * @param amountHorizontal
	 * @param amountVertical
	 * @param level
	 * @return Level
	 */
	private Level createLevel(Breakout world, int amountHorizontal, int amountVertical, int level,
			int amountOfObstacles) {
		return new Level(world, amountHorizontal, amountVertical, level, amountOfObstacles);
	}

	/**
	 * Creates scoreboard
	 * 
	 * @param scoreBoardWidth
	 * @param scoreBoardHeight
	 */
	private void initializeTextObjects() {
		int FONT_SIZE = 15;
		scoreBoardText = new TextObject("", FONT_SIZE);
		scoreBoardText.setForeColor(255, 255, 255, 255);
		addGameObject(scoreBoardText, 0, 20);

		int x = 10;
		int y = (2 * blockSpawner.getBlockSpawnerMargin()) + blockSpawner.getHeight();
		gameMessage = new TextObject("", FONT_SIZE);
		gameMessage.setForeColor(255, 255, 255, 255);
		addGameObject(gameMessage, x, y);
	}

	/**
	 * Updates the scoreboard
	 * 
	 * @param text
	 */
	public void refreshScoreBoard(String text) {
		scoreBoardText.setText(text);
	}

	/**
	 * Returns current level
	 * 
	 * @return currentLevel
	 */
	public Level getCurrentLevel() {
		return this.currentLevel;
	}

	/**
	 * Prepares for the next level by removing all things from the current level
	 */
	public void endLevel() {
		deleteAllPowerUps();
		deleteGameObject(ball);
		setPlayer(this.width / 3, this.height);
		player.setPlayerSpeed(player.getSTARTING_SPEED());
		deleteAllObstacles();
		
		createBall();

	}

	/**
	 * Deletes all GameObjects of the type ObstacleBlock from GameObject list
	 */
	public void deleteAllObstacles() {
		this.deleteAllGameObjectsOfType(ObstacleBlock.class);
	}

	/**
	 * Deletes all GameObjects of the type PowerUp from GameObject list
	 */
	private void deleteAllPowerUps() {
		this.deleteAllGameObjectsOfType(PlayerBoost.class);
		this.deleteAllGameObjectsOfType(BallBoost.class);
	}
	
	/**
	 * Sets the players coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public void setPlayer(int x, int y) {
		player.setX(x);
		player.setY(y);
	}

	/**
	 * Ends the current level and proceeds to go to the next level
	 */
	public void nextLevel() {

		if (gameLevels.indexOf(currentLevel) < gameLevels.size() - 1) {
			endLevel();
			currentLevel = gameLevels.get(gameLevels.indexOf(currentLevel) + 1);
			currentLevel.startLevel();
		} else {
			switchGameState();
			deleteAllObstacles();
			endGameMessage(String.format("Total score: %s | Press DOWN to start a new game.", getTotalScore()));
		}
	}

	/**
	 * Gets sum of all level scores
	 * 
	 * @return
	 */
	public int getTotalScore() {
		int sum = 0;

		for (Level l : gameLevels) {
			sum += l.getScore();
		}

		return sum;
	}

	/**
	 * Deletes all GameObjects and setups up a new game
	 */
	public void endGame() {
		deleteAllGameOBjects();
		setupGame();
	}

	/**
	 * Creates a Ball instance and adds it to the GameObject list
	 */
	public void createBall() {
		final int BALL_DIAMETER = 30;
		final int BALL_X = (int) (player.getX() + player.getWidth() / 2);
		final int BALL_Y = (int) (this.getHeight() - player.getHeight() - BALL_DIAMETER / 2);
		ball = new Ball(this, BALL_DIAMETER, BALL_DIAMETER);
		addGameObject(ball, BALL_X, BALL_Y);
	}

	/**
	 * Creates a BlockSpawner
	 */
	private void initializeBlockSpawner() {
		blockSpawner = new BlockSpawner(this, currentLevel.getAmountHorizontal(), currentLevel.getAmountVertical());
	}

	/**
	 * Returns BlockSpawner object
	 * 
	 * @return blockSpawner
	 */
	public BlockSpawner getBlockSpawner() {
		return blockSpawner;
	}

	/**
	 * Returns Player object
	 * 
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Message to be displayed when the game has ended
	 * 
	 * @param message
	 */
	public void endGameMessage(String message) {
		gameMessage.setText(message);
	}

	/**
	 * Returns Ball object
	 * 
	 * @return ball
	 */
	public Ball getBall() {
		return ball;
	}


	public boolean getGameState() {
		return gameRunning;
	}

	/**
	 * Switches game state
	 */
	public void switchGameState() {
		gameRunning = !gameRunning;
	}

	@Override
	public void update() {
		if (currentLevel.getBlocksLeft() <= 0 && gameRunning) {
			nextLevel();
			System.out.println("Next level!");
		}
		refreshScoreBoard(String.format("Lives: %s | Score: %s | Ball speed: %.0f / damage: %s | Player speed: %s",
				player.getLives(), currentLevel.getScore(), ball.getSpeed(), ball.getDamage(), player.getSpeed()));
	
	}

}
