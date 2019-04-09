package nl.han.ica.breakout;

import java.util.List;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class GameBlock extends Block {
	private PowerUp powerUp;
	private int value;
	private final int textColor = 255; // white

	public GameBlock(int height, int width, int color, int value, int health, PowerUp powerUp, Breakout world) {
		setHeight(height);
		setWidth(width);
		this.color = color;
		this.value = value;
		this.health = health;
		this.powerUp = powerUp;
		this.world = world;

	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Ball) {
				takeDamage(((Ball) g).getDamage());
				if (isBlockDestroyed()) {
					destroyBlock();
					updateLevelBlockCountList();
					if (hasPowerUp()) {
						powerUp.drop();
					}
				}
			}
		}
	}

	/**
	 * Checks whether the GameBlock has a PowerUp or not
	 * 
	 * @return
	 */
	private boolean hasPowerUp() {
		return powerUp == null ? false : true;
	}

	@Override
	public void draw(PGraphics g) {
		super.draw(g);
		g.fill(textColor);
		g.text(health, getX() + width / 2, getY() + height / 1.5f);
	}

	/**
	 * Destroys the current Block instance
	 */

	@Override
	protected void destroyBlock() {
		super.destroyBlock();
		assignPointsToLevel();
	}

	/**
	 * Assigns points for the block destroyed to the current level score
	 */
	private void assignPointsToLevel() {
		world.getCurrentLevel().setScore(world.getCurrentLevel().getScore() + value);
	}

	/**
	 * Updates the current level block count
	 */
	private void updateLevelBlockCountList() {
		world.getCurrentLevel().setBlocksLeft(world.getCurrentLevel().getBlocksLeft() - 1); // block is destroyed, so it
																							// has to be updated in the
																							// level block count list
	}

}
