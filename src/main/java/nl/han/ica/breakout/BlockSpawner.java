package nl.han.ica.breakout;

import java.util.ArrayList;
import java.util.Random;

public class BlockSpawner {
	private float height;
	private float width;
	private final int blockSpawnerMargin = 50;
	private int amountVertical;
	private int amountHorizontal;
	private Breakout world;
	Random rnd;
	private ArrayList<IPowerUp> powerups;

	public BlockSpawner(Breakout world, int amountVertical, int amountHorizontal) {
		this.amountVertical = amountVertical;
		this.amountHorizontal = amountHorizontal;
		this.world = world;
		this.height = ((world.height - (blockSpawnerMargin * 2)) / 2);
		this.width = (world.width - (blockSpawnerMargin * 2));
		rnd = new Random();
		initializePowerUps();
	}

	// generates the blocks
	public void generateBlocks() {
		int x = blockSpawnerMargin;
		int y = blockSpawnerMargin;
		int color = 255;
		int value = 5;
		int health = 1;
		int blocksCreated = 0;
		int blockWidth = calculateBlockWidth();
		int blockHeight = calculateBlockHeight();
		final int currentLevel = world.getCurrentLevel().getLevel();
		for (int i = 0; i < amountVertical; i++) {
			for (int j = 0; j < amountHorizontal; j++) {
				blocksCreated++;
				value = rnd.nextInt(currentLevel+2) + 1;
				health = rnd.nextInt(value) + 1;
				world.addGameObject(new GameBlock(blockHeight, blockWidth, color, value, health, powerups.get(0), world), x, y);
				//debug purposes
				System.out.println("Block created!" + blocksCreated + " blockHeight " + blockHeight + " BlockWidth "
						+ blockWidth + " BlockspawnerWidth" + world.width);
				x += blockWidth;
			}
			x = blockSpawnerMargin;
			y += blockHeight;
		}
		assignBlocksCountToLevel(blocksCreated);
	}

	private void assignBlocksCountToLevel(int amount) {
		this.world.getCurrentLevel().setBlocksLeft(amount);
	}

	public void endLevel() {

	}

	private void initializePowerUps() {
		powerups = new ArrayList<IPowerUp>();
		powerups.add(new BallBoost());
	}

	private int calculateBlockWidth() {
		return (int) (width / amountHorizontal);
	}

	private int calculateBlockHeight() {
		return (int) (height / amountVertical);
	}

	public int getAmountVertical() {
		return amountVertical;
	}

	public void setAmountVertical(int amountVertical) {
		this.amountVertical = amountVertical;
	}

	public int getAmountHorizontal() {
		return amountHorizontal;
	}

	public void setAmountHorizontal(int amountHorizontal) {
		this.amountHorizontal = amountHorizontal;
	}
}
