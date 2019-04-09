package nl.han.ica.breakout;

import java.util.ArrayList;
import java.util.Random;

public class BlockSpawner {
	private int height;
	private int width;
	private final int blockSpawnerMargin = 50;
	private int amountVertical;
	private int amountHorizontal;
	private Breakout world;
	Random rnd;

	public BlockSpawner(Breakout world, int amountVertical, int amountHorizontal) {
		this.amountVertical = amountVertical;
		this.amountHorizontal = amountHorizontal;
		this.world = world;
		this.height = ((world.getHeight() - (blockSpawnerMargin * 2)) / 2);
		this.width = (world.getWidth() - (blockSpawnerMargin * 2));
		rnd = new Random();
	}

	/**
	 * Generates the gameblocks for the current level (blocks that the player has to
	 * destroy to complete the level)
	 */
	public void generateBlocks() {
		int x = blockSpawnerMargin;
		int y = blockSpawnerMargin;
		int color = 0;
		int value = 5;
		int health = 1;
		int blocksCreated = 0;
		int blockWidth = calculateBlockWidth();
		int blockHeight = calculateBlockHeight();
		final int currentLevel = world.getCurrentLevel().getLevel();
		for (int i = 0; i < amountVertical; i++) {
			for (int j = 0; j < amountHorizontal; j++) {
				blocksCreated++;
				color = rnd.nextInt(3);
				value = rnd.nextInt(currentLevel + 2) + 1;
				health = rnd.nextInt(value) + 1;
				PowerUp p = null;
				if (rnd.nextBoolean()) {
					p = createPowerUp(rnd.nextBoolean());
					world.addGameObject(p, x + blockWidth / 2, y + blockHeight / 2);
				}

				world.addGameObject(new GameBlock(blockHeight, blockWidth, color , value, health, p, world), x, y);
				x += blockWidth;
			}
			x = blockSpawnerMargin;
			y += blockHeight;
		}
		assignBlocksCountToLevel(blocksCreated);
	}

	/**
	 * Generates obstacles for the current level
	 */
	public void generateObstacles(int amount) {
		int blockHeight = 10;
		int blockWidth = rnd.nextInt(10) + 30;
		int color = 255;
		int health = rnd.nextInt(10) + 5;

		for (int i = 0; i < amount; i++) {
			int x = rnd.nextInt(width - blockWidth);
			int y = rnd.nextInt((world.getHeight() - (world.getHeight() - height) - blockHeight
					- (int) world.getPlayer().getHeight() + blockSpawnerMargin - (int) world.getBall().getHeight()))
					+ height + blockSpawnerMargin;
			world.addGameObject(new ObstacleBlock(blockHeight, blockWidth, color, health, world, rnd.nextBoolean()), x,
					y);
		}
	}

	/**
	 * sets the amount of blocks left in the current level
	 * 
	 * @param amount Amount of blocks
	 */
	private void assignBlocksCountToLevel(int amount) {
		this.world.getCurrentLevel().setBlocksLeft(amount);
	}

	/**
	 * Returns PowerUp object -> parameter boost with the value true gives
	 * BallPowerUp, false gives PlayerPowerUp
	 * 
	 * @param boost
	 * @return boost
	 */
	private PowerUp createPowerUp(boolean boost) {
		return boost ? new BallBoost(30, 30, world, BallBoost.powerUp.getRandomPowerUp())
				: new PlayerBoost(30, 30, world, PlayerBoost.powerUp.getRandomPowerUp());
	}

	/**
	 * Calculate Block Width based on blockSpawnerWidth
	 * 
	 * @return blockWidth
	 */
	private int calculateBlockWidth() {
		return (int) (width / amountHorizontal);
	}

	/**
	 * Calculate Block Height based on blockSpawnerWidth
	 * 
	 * @return blockHeight
	 */
	private int calculateBlockHeight() {
		return (int) (height / amountVertical);
	}

	/**
	 * Sets the amount of blocks on the vertical line
	 * 
	 * @return Amount Vertical
	 */
	public int getAmountVertical() {
		return amountVertical;
	}

	/**
	 * Sets the amount of blocks on the vertical line
	 */
	public void setAmountVertical(int amountVertical) {
		this.amountVertical = amountVertical;
	}

	/**
	 * Gets the amount of blocks on the horizontal line
	 * 
	 * @return amountHorizontal
	 */
	public int getAmountHorizontal() {
		return amountHorizontal;
	}

	/**
	 * Sets the amount of blocks on the horizontal line
	 */
	public void setAmountHorizontal(int amountHorizontal) {
		this.amountHorizontal = amountHorizontal;
	}

	/**
	 * gets blockSpawner margin
	 * 
	 * @return blockSpawner
	 */
	public int getBlockSpawnerMargin() {
		return blockSpawnerMargin;
	}

	/**
	 * Gets blockSpawner height
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets blockSpawner Width
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}
}
