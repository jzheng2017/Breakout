package nl.han.ica.breakout;

import java.util.ArrayList;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;

public class Breakout extends GameEngine {
	private BlockSpawner blockSpawner;
	private Player player;
	private Level currentLevel;
	private ArrayList<Level> gameLevels;
	private Ball ball;
	private TextObject scoreBoardText;


	public static void main(String[] args) {
		Breakout tw = new Breakout();
		tw.runSketch();
	}

	@Override
	public void setupGame() {
		int worldWidth = 500;
		int worldHeight = 500;		
		createView(worldWidth, worldHeight);
		initializePlayer();
		createBall();
		initializeLevels();
		initializeScoreBoard(worldWidth, 100);
		createWorldBoundaries();
	}

	private void createView(int screenHeight, int screenWidth) {
		size(screenWidth, screenHeight);
		View view = new View(screenWidth, screenHeight);
		setView(view);
	}

	private void initializePlayer() {
		player = new Player(this);
		addGameObject(player, this.width / 3, this.height);

	}

	private void initializeLevels() {
		gameLevels = new ArrayList<Level>();
		gameLevels.add(createLevel(this, 5, 5, 1));
		gameLevels.add(createLevel(this, 15, 5, 2));
		currentLevel = gameLevels.get(0);
		initializeBlockSpawner();
		currentLevel.startLevel();
	}
	
	private Level createLevel(Breakout world, int amountHorizontal, int amountVertical, int level) {
		return new Level(world, amountHorizontal, amountVertical, level);
	}

	private void initializeScoreBoard(int scoreBoardWidth, int scoreBoardHeight) {
		int FONT_SIZE = 15;
		String text = "Score:";
		scoreBoardText = new TextObject(text, FONT_SIZE);
		scoreBoardText.setForeColor(255, 255, 255, 255);
		addGameObject(scoreBoardText, 0, 20);
	}


	public void refreshScoreBoard(String text) {
		scoreBoardText.setText(text);
	}

	public Level getCurrentLevel() {
		return this.currentLevel;
	}



	public void nextLevel() {
		currentLevel = gameLevels.get(gameLevels.indexOf(currentLevel) + 1);
		currentLevel.startLevel();
	}

	public void createBall() {
		final int BALL_DIAMETER = 30;
		final int BALL_X = (int)(player.getX() + player.getWidth() / 2);
		final int BALL_Y = (int)(this.getHeight() - player.getHeight() - BALL_DIAMETER / 2);
		ball = new Ball(this, BALL_DIAMETER, BALL_DIAMETER); 
		addGameObject(ball, BALL_X, BALL_Y);
	}

	private void initializeBlockSpawner() {
		blockSpawner = new BlockSpawner(this, currentLevel.getAmountHorizontal(), currentLevel.getAmountVertical());
	}
	
	private void createWorldBoundaries() {
		final int WORLD_WIDTH = this.getWidth();
		final int WORLD_HEIGHT = this.getHeight();
		final int BOUNDARIES_HORIZONTAL_HEIGHT = 1;
		final int BOUNDARIES_HORIZONTAL_WIDTH = WORLD_WIDTH;
		final int BOUNDARIES_VERTICAL_HEIGHT = WORLD_HEIGHT;
		final int BOUNDARIES_VERTICAL_WIDTH = 1;
		final int LEFT_SIDE_X = 0;
		final int LEFT_SIDE_Y = 0;
		final int RIGHT_SIDE_X = WORLD_WIDTH - BOUNDARIES_VERTICAL_WIDTH;
		final int RIGHT_SIDE_Y = 0;
		final int TOP_X = 0;
		final int TOP_Y = 0;
		final int BOTTOM_X = 0;
		final int BOTTOM_Y = WORLD_HEIGHT - BOUNDARIES_HORIZONTAL_HEIGHT;
		final int COLOR = 255;
		final int HEALTH = 99999999;
	//	addGameObject(new ObstacleBlock(BOUNDARIES_HORIZONTAL_HEIGHT, BOUNDARIES_HORIZONTAL_WIDTH, COLOR, HEALTH), BOTTOM_X, BOTTOM_Y); //TOP
		addGameObject(new ObstacleBlock(BOUNDARIES_HORIZONTAL_HEIGHT, BOUNDARIES_HORIZONTAL_WIDTH, COLOR, HEALTH), TOP_X, TOP_Y); //BOTTOM
		addGameObject(new ObstacleBlock(BOUNDARIES_VERTICAL_HEIGHT, BOUNDARIES_VERTICAL_WIDTH, COLOR, HEALTH), LEFT_SIDE_X, LEFT_SIDE_Y); //LEFT SIDE
		addGameObject(new ObstacleBlock(BOUNDARIES_VERTICAL_HEIGHT, BOUNDARIES_VERTICAL_WIDTH, COLOR, HEALTH), RIGHT_SIDE_X, RIGHT_SIDE_Y); //RIGHT SIDE
		
	}

	public BlockSpawner getBlockSpawner() {
		return blockSpawner;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Ball getBall() {
		return ball;
	}
	
	@Override
	public void update() {
		if (currentLevel.getBlocksLeft() <= 0) {
			nextLevel();
			System.out.println("Next level!");
		}
//		System.out.println(blockSpawner.getPowerUpList().size());
		refreshScoreBoard(String.format("Lives: %s | Score: %s | Ballspeed: %s | Playerspeed: %s | PowerUp: %s", player.getLives(), currentLevel.getScore(), (int)ball.getSpeed(), (int)player.getSpeed()));
	}

}
