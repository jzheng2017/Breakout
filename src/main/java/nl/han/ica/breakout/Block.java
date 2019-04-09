package nl.han.ica.breakout;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public abstract class Block extends GameObject implements ICollidableWithGameObjects {
	protected int color;
	protected int health;
	protected Breakout world;

	@Override
	public void update() {
	}

	@Override
	public void draw(PGraphics g) {
		switch (color) {
		case 0: // red
			g.fill(255,0,0);
			break;
		case 1: //green
			g.fill(0,255,0);
			break;
		case 2: //brown
			g.fill(77,0,51);
			break;
		default:
			g.fill(255);
			break;	
		}
		
		g.rect(getX(), getY(), getWidth(), getHeight());

	}

	@Override
	public abstract void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects);

	/**
	 * Handles the damage received
	 * 
	 * @param damage
	 */
	public void takeDamage(int damage) {
		health -= damage;
	}

	protected boolean isBlockDestroyed() {
		if (health <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if the blocks health is under 0, if it's under 0 then the blocks is
	 * destroyed and should be deleted from the GameObject list
	 * 
	 * @return
	 */
	protected void destroyBlock() {
		world.deleteGameObject(this);
	}

}
