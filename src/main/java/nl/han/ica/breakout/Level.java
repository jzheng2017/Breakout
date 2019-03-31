package nl.han.ica.breakout;

public class Level {
	private Breakout world;
	private int blocksLeft; //amounts blocks left in the current level
	private boolean startOfGame;
	private int level;
	private int score;
	private int amountHorizontal;
	private int amountVertical;
	public Level(Breakout world, int amountHorizontal, int amountVertical, int level) {
		setLevel(level);
		setStartOfGame(false);
		this.world = world;
		this.amountHorizontal = amountHorizontal;
		this.amountVertical = amountVertical;
	}

	//calls all methods necessary for starting the level
	public void startLevel() {
		world.getBlockSpawner().setAmountHorizontal(amountHorizontal);
		world.getBlockSpawner().setAmountVertical(amountVertical);
		world.getBlockSpawner().generateBlocks();	
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAmountHorizontal() {
		return amountHorizontal;
	}

	public int getAmountVertical() {
		return amountVertical;
	}

	public boolean isStartOfGame() {
		return startOfGame;
	}

	public boolean getStartOfGame() {
		return startOfGame;
	}
	public void setStartOfGame(boolean startOfGame) {
		this.startOfGame = startOfGame;
	}

	
}
