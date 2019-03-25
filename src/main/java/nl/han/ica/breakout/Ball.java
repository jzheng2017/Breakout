package nl.han.ica.breakout;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Ball extends GameObject implements ICollidableWithGameObjects {
	private int x;
	private int y;
	private Breakout world;
	private int damage = 1;

	public Ball(Breakout world, int width, int height) {
		this.width = width;
		this.height = width;
		this.world = world;
		setSpeed(3); // sets this objects speed
	}

	public void Bounce(GameObject go) {
		setDirectionSpeed((getAngleFrom(go) + 180) % 360, getSpeed());
	}

	public void Bounce() {
		setDirection((getDirection() + 180) % 360);
	}

	@Override
	public void update() {
		if (getX() - width / 2 <= 0) {
			Bounce();
		}
		if (getY() <= 0) {

			Bounce();
		}
		if (getX() >= world.width - width) {

			Bounce();
		}
		if (getY() >= world.height + height) {
			System.out.println("Lost");


		}
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(255, 0, 0);
		g.ellipse(getX(), getY(), width, height);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Player || g instanceof Block) {
				Bounce(g);
				System.out.println("Collision!");
			}

		}
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}
