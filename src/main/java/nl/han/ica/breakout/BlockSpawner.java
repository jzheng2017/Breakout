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

	public BlockSpawner(Breakout world, Level level, int amountVertical, int amountHorizontal) {
	this.amountVertical = amountVertical;
	this.amountHorizontal = amountHorizontal;
	this.world = world;
	this.level = level;
	this.height = world.height / 2;
	this.width = (world.width - (blockSpawnerMargin * 2));	
	}
	
	//generates the blocks
	public void generateBlocks() {
		
		for (int i = 0; i < amountVertical; i++) {
			for (int j = 0; j < amountHorizontal; j++) {
				world.addGameObject(new Block(calculateBlockHeight(), calculateBlockWidth(), ));
			}
		}
	}
	
	private float calculateBlockWidth() {
		return (width / amountHorizontal);
	}
	
	private float calculateBlockHeight() {
		return (height / amountVertical);
	}
}
