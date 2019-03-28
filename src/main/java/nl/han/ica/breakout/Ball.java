package nl.han.ica.breakout;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Ball extends GameObject implements ICollidableWithGameObjects {
	private Breakout world;
	private int damage = 1;
	private final int startingSpeed = 3;

	public Ball(Breakout world, int width, int height) {
		this.width = width;
		this.height = width;
		this.world = world;
	}

	public void Bounce(GameObject go) {
		setDirectionSpeed((getAngleFrom(go) + 180) % 360, getSpeed());
	}

	public void Bounce() {
//		if(getDirection() >= 180) {
//		setDirection((getDirection() + 180) % 360);
//		}
//		else {
//			setDirection((getDirection() - 180) % 360);
//		}
		setDirection((getDirection() + 180) % 360);
	}

	@Override
	public void update() {
//		if (getX() - width / 2 <= 0) {
//			Bounce();
//		}
//		if (getY() <= 0) {
//
//			Bounce();
//		}
//		if (getX() >= world.width - width) {
//
//			Bounce();
//		}
//		if (getY() >= world.height + height) {
//			System.out.println("Lost");
//			world.getPlayer().decreaseLife();
//		}
	}
	
	public void resetBall() {
		
	}
	@Override
	public void draw(PGraphics g) {
		if (world.getCurrentLevel().isStartOfGame()) {
			world.getBall().follow(world.getPlayer());
		}
		g.fill(255, 0, 0);
		g.ellipse(getX(), getY(), width, height);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Player || g instanceof Block) {
				if (!world.getCurrentLevel().isStartOfGame()) {
				Bounce(g);
				}
				System.out.println("Collision!");
			}

		}
	}
	
	/*
	 * 
	 * follows parent x and y
	 * doesn't work yet
	 *
	 */
	public void follow(GameObject g) {

		setX((int)(g.getX() + (g.getWidth()/2)));
		setY((int)g.getY());
	
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getStartingSpeed() {
		return startingSpeed;
	}

}
