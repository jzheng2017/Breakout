package nl.han.ica.breakout;

public class Level {
	private BlockSpawner blockSpawner;
	private Breakout world;
	private int currentLevel;
	private final float amountBlocksIncrement = 1.2f; //increment amount of blocks every next level
	
	public Level(Breakout world) {
		this.currentLevel = 1;
		this.world = world;
		blockSpawner = new BlockSpawner(world, this, 10, 5 );
		blockSpawner.generateBlocks();	
	}
	
	public void nextLevel() {
		currentLevel++;
	}
}
