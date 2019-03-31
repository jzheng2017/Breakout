package nl.han.ica.breakout;

import java.util.List;

import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Player extends GameObject implements ICollidableWithGameObjects {
	private int lives;
	private Breakout world;
	private PowerUp powerUp;

	public Player(Breakout world) {
		setHeight(30);
		setWidth(150);
		this.world = world;
		this.setLives(3);
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		final int speed = 2;
		if (keyCode == world.LEFT) {
			setDirectionSpeed(270, speed);
		} else if (keyCode == world.RIGHT) {
			setDirectionSpeed(90, speed);
		} 
//		else if (keyCode == world.UP) {
//			if (world.getCurrentLevel().getStartOfGame()) {
//				world.getCurrentLevel().setStartOfGame(false);
//				world.getBall().setSpeed(world.getBall().getStartingSpeed());
//			}
//		}

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

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

	}

	public void setPowerUp(PowerUp powerUp) {
		this.powerUp = powerUp;
	}

	public int getLives() {
		return lives;
	}

	public void decreaseLife() {
		setLives(lives - 1);
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

}