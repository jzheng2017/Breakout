package nl.han.ica.breakout;

import java.util.List;
import java.util.Random;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Ball extends GameObject implements ICollidableWithGameObjects {
	private Breakout world;
	private int damage = 1;
	private final int startingSpeed = 3;
	private final int MAX_SPEED = 5;
	private final int MAX_DAMAGE = 3;
	private boolean freeShooting;
	private Random rnd;

	public Ball(Breakout world, int width, int height) {
		setWidth(width);
		setHeight(width);
		this.world = world;
		freeShooting = true;
		setSpeed(startingSpeed);
	}

	/**
	 * Handles the collision interaction with an object, calculates which direction
	 * the ball has to bounce to
	 * 
	 * @param go
	 */
	public void Bounce(GameObject go) {
		setDirectionSpeed((getAngleFrom(go) + 180), getSpeed());
	}

	/**
	 * Handles the collision interaction with the borders, calculates which
	 * direction the ball has to bounce to
	 * 
	 * @param go If the ball object has hit the top border or not
	 */
	public void Bounce(boolean top) {

		if (!top) {
			setxSpeed(-getxSpeed());
		} else {
			setySpeed(-getySpeed());
		}

	}

	/**
	 * Bounce the ball object in the direction based on the x and y given in the
	 * parameters
	 * 
	 * @param x
	 * @param y
	 */
	public void Bounce(int x, int y) {
		setDirection(getAngleFrom(x, y) + 90);
	}

	@Override
	public void update() {
	
		if (getX() <= 0) {
			Bounce(false);
		}
		if (getY() <= 0) {

			Bounce(true);
		}
		if (getX() >= world.width - width) {

			Bounce(false);
		}
		if (getY() >= world.getHeight()) {
			world.getPlayer().decreaseLife();

			if (world.getPlayer().getLives() > 0) {

				world.createBall();
			} else {
				world.deleteAllObstacles();
				world.endGameMessage(
						String.format("Total score: %s | Press DOWN to start a new game.", world.getTotalScore()));
				world.switchGameState();

			}
			world.deleteGameObject(this);

		}
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		if (keyCode == world.UP && freeShooting) {
			Bounce(world.getWidth() / 2, (int) (world.getHeight() - getHeight()));
			freeShooting = false;
		}
	}

	@Override
	public void draw(PGraphics g) {
		if (freeShooting) {
			follow(world.getPlayer());
		}

		g.fill(255, 0, 0);
		g.rect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Player || g instanceof Block) {

				if (!freeShooting) {
					Bounce(g);
				}

			}

		}
	}

	/**
	 * Follows parent GameObject x and y
	 */
	public void follow(GameObject g) {

		setX((int) (g.getX() + (g.getWidth() / 2.5)));
		setY(((int) g.getY() - this.getHeight()));

	}

	/**
	 * returns ball damage
	 * 
	 * @return
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * sets ball damage
	 * 
	 * @param damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * returns the starting speed of when the ball was created
	 * 
	 * @return
	 */
	public int getStartingSpeed() {
		return startingSpeed;
	}

	/**
	 * Returns current object max speed
	 * @return
	 */
	public int getMAX_SPEED() {
		return MAX_SPEED;
	}

	/**
	 * Returns current object max damage
	 * @return
	 */
	public int getMAX_DAMAGE() {
		return MAX_DAMAGE;
	}

	/**
	 * Returns whether the player has to shoot the ball or not
	 * @return
	 */
	public boolean getFreeShooting() {
		return freeShooting;
	}

	/**
	 * Sets Free Shooting 
	 * @param freeShooting
	 */
	public void setFreeShooting(boolean freeShooting) {
		this.freeShooting = freeShooting;
	}
}
