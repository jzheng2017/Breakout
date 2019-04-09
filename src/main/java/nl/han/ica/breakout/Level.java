package nl.han.ica.breakout;

public class Level {
	private Breakout world;
	private int blocksLeft; // amounts blocks left in the current level
	private int amountOfObstacles;
	private int level;
	private int score;
	private int amountHorizontal;
	private int amountVertical;

	public Level(Breakout world, int amountHorizontal, int amountVertical, int level, int amountOfObstacles) {
		setLevel(level);
		this.amountOfObstacles = amountOfObstacles;
		this.world = world;
		this.amountHorizontal = amountHorizontal;
		this.amountVertical = amountVertical;
	}

	/**
	 * calls all methods necessary for starting the level
	 */
	public void startLevel() {
		world.getBlockSpawner().setAmountHorizontal(amountHorizontal);
		world.getBlockSpawner().setAmountVertical(amountVertical);
		world.getBlockSpawner().generateBlocks();
		world.getBlockSpawner().generateObstacles(amountOfObstacles);

	}

	/**
	 * Returns amount of block level
	 * @return
	 */
	public int getBlocksLeft() {
		return blocksLeft;
	}

	/**
	 * Sets amount of block left of the level
	 * @param blocksLeft
	 */
	public void setBlocksLeft(int blocksLeft) {
		this.blocksLeft = blocksLeft;
	}

	/**
	 * Returns level score
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets level score
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Returns level
	 * @return
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets integer level
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Returns amount of blocks on horizontal line
	 * @return
	 */
	public int getAmountHorizontal() {
		return amountHorizontal;
	}
	
	/**
	 * Returns amount of blocks on vertical line
	 * @return
	 */
	public int getAmountVertical() {
		return amountVertical;
	}

}
