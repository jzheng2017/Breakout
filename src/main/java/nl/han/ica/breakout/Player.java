package nl.han.ica.breakout;

import java.util.List;

import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Player extends GameObject {
	private int lives;
	private Breakout world;
	private final int MAX_WIDTH;
	private final int MAX_SPEED = 5;
	private final int STARTING_SPEED = 2;
	private int playerSpeed;
	
	public Player(Breakout world) {
		setHeight(30);
		setWidth(150);
		MAX_WIDTH = (int) (world.getWidth() / 2);
		this.world = world;
		this.setLives(3);
		setPlayerSpeed(2);
	
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		System.out.println(this.getSpeed());
		if (keyCode == world.LEFT) {
			setDirectionSpeed(270, getPlayerSpeed());
		} else if (keyCode == world.RIGHT) {
			setDirectionSpeed(90, getPlayerSpeed());
		} else if (keyCode == world.DOWN && !world.getGameState()) {
			world.endGame();

		}
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(255);
		g.rect(getX(), getY(), getWidth(), getHeight());

	}

	@Override
	public void update() {
		if (getX() <= 0) {
			setxSpeed(0);
			setX(0);
		}
		if (getY() <= 0) {
			setySpeed(0);
			setY(0);
		}
		if (getX() >= world.getWidth() - getWidth()) {
			setxSpeed(0);
			setX(world.getWidth() - getWidth());
		}
		if (getY() >= world.getHeight() - getHeight()) {
			setySpeed(0);
			setY(world.getHeight() - getHeight());
		}
	}

	/**
	 * Gets the amount of lives left of the player
	 * @return
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Decrease player life by 1
	 */
	public void decreaseLife() {

		setLives(lives - 1);

	}

	/**
	 * Sets the players lives
	 * @param lives
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	/**
	 * Returns players max width
	 * @return
	 */
	public int getMAX_WIDTH() {
		return MAX_WIDTH;
	}

	/**
	 * Returns players max speed
	 * @return
	 */
	public int getMAX_SPEED() {
		return MAX_SPEED;
	}

	public int getPlayerSpeed() {
		return playerSpeed;
	}

	public void setPlayerSpeed(int playerSpeed) {
		this.playerSpeed = playerSpeed;
	}

	public int getSTARTING_SPEED() {
		return STARTING_SPEED;
	}

}