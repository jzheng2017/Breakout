package nl.han.ica.breakout;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;

public class ObstacleBlock extends Block {
	private boolean moving;

	public ObstacleBlock(int height, int width, int color, int health, Breakout world, boolean moving) {
		if (moving) {
			setxSpeed(-1);
		}
		this.health = health;
		this.moving = moving;
		setHeight(height);
		setWidth(width);
		this.world = world;
		this.color = color;
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Ball) {
				if (!((Ball) g).getFreeShooting()) {
					takeDamage(((Ball) g).getDamage());
					if (isBlockDestroyed()) {
						destroyBlock();

					}
				}
			}

		}

	}

	@Override
	public void update() {
		if (getX() + getWidth() <= 0) {
			setX(world.getWidth());
		}
	}

}
