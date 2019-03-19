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
	private Level level;
	Random rnd;
	private ArrayList<IPowerUp> powerups;
	
	public BlockSpawner(Breakout world, Level level, int amountVertical, int amountHorizontal) {
	this.amountVertical = amountVertical;
	this.amountHorizontal = amountHorizontal;
	this.world = world;
	this.level = level;
	this.height = ((world.height - (blockSpawnerMargin * 2)) / 2);
	this.width = (world.width - (blockSpawnerMargin * 2));	
	initializePowerUps();
	}
	
	//generates the blocks
	public void generateBlocks() {
		int x = blockSpawnerMargin;
		int y = blockSpawnerMargin;
		int counter = 0;
		int blockWidth = calculateBlockWidth();
		int blockHeight = calculateBlockHeight();
		for (int i = 0; i < amountHorizontal; i++) {
			
			for (int j = 0; j < amountVertical; j++) {
				x +=  blockWidth;
				counter++;
				world.addGameObject(new GameBlock(blockHeight, blockWidth, 255,5,5,powerups.get(0)),x,y);
				System.out.println("Block created!" + counter + " blockHeight " + blockHeight + " BlockWidth " + blockWidth + " BlockspawnerWidth" + world.width );
			}
			x = blockSpawnerMargin;
			y += blockHeight;
		}
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
