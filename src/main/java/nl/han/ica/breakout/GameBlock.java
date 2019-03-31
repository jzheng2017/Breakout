package nl.han.ica.breakout;

import java.util.List;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class GameBlock extends Block {
	private PowerUp powerUp;
	private Breakout world;
	private int value;
	protected final int textColor = 0; //black

	public GameBlock(int height, int width, int color, int value, int health, PowerUp powerUp, Breakout world) {
		setHeight(height);
		setWidth(width);
		this.color = color;
		this.value = value;
		this.health = health;
		this.powerUp = powerUp;
		this.world = world;

		//setGravity(0.01f);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Ball) {
				takeDamage(((Ball) g).getDamage());
				System.out.println("Block Collision!");
				if (isBlockDestroyed()) {
					destroyBlock();
					updateLevelBlockCountList();
					System.out.println("Block destroyed!");
					System.out.println(world.getCurrentLevel().getBlocksLeft());
					powerUp.drop();
				}
			}
		}
	}
	
	// checks if the blocks health is under 0, if it's under 0 then the blocks is
	// destroyed and should be deleted from the GameObject List
	private boolean isBlockDestroyed() {
		if (health <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//this function draws gameblock because it's in the gameblock class
	@Override
	public void draw(PGraphics g) {
		super.draw(g);
		g.fill(textColor);
		g.text(health, getX() + width / 2, getY() + height / 1.5f);
	}
	private void destroyBlock() {
		world.deleteGameObject(this);
		assignPointsToLevel();
	}
	
	private void assignPointsToLevel() {
		world.getCurrentLevel().setScore(world.getCurrentLevel().getScore() + value);
		System.out.println("Score: " + world.getCurrentLevel().getScore());
	}
	
	private void updateLevelBlockCountList() {
		world.getCurrentLevel().setBlocksLeft(world.getCurrentLevel().getBlocksLeft() - 1); //block is destroyed, so it has to be updated in the level block count list
	}

	public void takeDamage(int damage) {
		health -= damage;
	}
	


	
}
