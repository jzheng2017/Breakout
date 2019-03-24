package nl.han.ica.breakout;

public class Level {
	private BlockSpawner blockSpawner;
	private Breakout world;
	private int blocksLeft; //amounts blocks left in the current level
	private int level;
	private int score;
	
	public Level(Breakout world, int amountHorizontal, int amountVertical, int level) {
		this.level = level;
		this.world = world;
		blockSpawner = new BlockSpawner(world, amountHorizontal, amountVertical);
	}

	//calls all methods necessary for starting the level
	public void startLevel() {
		blockSpawner.generateBlocks();	
	}
	public int getBlocksLeft() {
		return blocksLeft;
	}

	public void setBlocksLeft(int blocksLeft) {
		this.blocksLeft = blocksLeft;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
