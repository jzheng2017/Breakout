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
	//private ArrayList<PowerUp> powerups; //list of the available powerups

	public BlockSpawner(Breakout world, int amountVertical, int amountHorizontal) {
		this.amountVertical = amountVertical;
		this.amountHorizontal = amountHorizontal;
		this.world = world;
		this.height = ((world.getHeight() - (blockSpawnerMargin * 2)) / 2);
		this.width = (world.getWidth() - (blockSpawnerMargin * 2));
		rnd = new Random();
		//initializePowerUps();
	}
	
	

	// generates the gameblocks (blocks that the player has to destroy to complete the level)
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
				PowerUp p = createPowerUp(rnd.nextBoolean());
				world.addGameObject(p, x + blockWidth / 2, y + blockHeight / 2);
				world.addGameObject(new GameBlock(blockHeight, blockWidth, color, value, health, p, world), x, y);
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

	//sets the amount of blocks left in the current level
	private void assignBlocksCountToLevel(int amount) {
		this.world.getCurrentLevel().setBlocksLeft(amount);
	}

	public void endLevel() {

	}

//	private void initializePowerUps() {
//		powerups = new ArrayList<PowerUp>();
//		powerups.add();
//		world.addGameObject(powerups.get(0));
//		System.out.println(powerups.get(0));
//	}
//	
	 // returns PowerUp object -> parameter boost with the value true gives BallPowerUp, false gives PlayerPowerUp
	private PowerUp createPowerUp(boolean boost) {
		return boost ? new BallBoost(30,30, world) : new PlayerBoost(30,30, world);
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
	
//	public ArrayList<PowerUp> getPowerUpList() {
//		return this.powerups;
//	}
}
