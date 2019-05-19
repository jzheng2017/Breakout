package nl.han.ica.breakout;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public abstract class PowerUp extends GameObject implements ICollidableWithGameObjects {

	protected final int DIAMETER = 10;
	protected int color = 255;
	protected Breakout world;

	/**
	 * assign powerup to player depending on the kind of powerUp
	 */
	public abstract void boost();

	public PowerUp(Breakout world) {
		this.world = world;
		setHeight(30);
		setWidth(30);
		setVisible(false);
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(color);
		g.ellipse(getX(), getY(), getWidth(), getHeight());

	}

	/**
	 * Drop the PowerUp object
	 */
	public void drop() {
		setVisible(true);
		setGravity(0.03f);
	}

	@Override
	public void update() {
		if (getY() >= world.height + height) {
			world.deleteGameObject(this);
			System.out.println("Gone");
		}
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Player) {
				world.deleteGameObject(this);
				boost();
			}
		}
	}

}
