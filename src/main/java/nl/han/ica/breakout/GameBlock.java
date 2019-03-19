package nl.han.ica.breakout;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;

public class GameBlock extends Block {
	private IPowerUp powerUp;
	
	public GameBlock(int height, int width, int color, int value, int durability, IPowerUp powerUp) {
		super(height, width, color, value, durability);
		this.powerUp = powerUp;
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
            if (g instanceof Ball) {
          	takeDamage(((Ball) g).getDamage());
          	System.out.println("Block Collision!");
            } 
		}
	}
	
	public void takeDamage(int damage) {
		durability -= damage;
	}
	
	

}
