package nl.han.ica.breakout;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;

public class ObstacleBlock extends Block {

	public ObstacleBlock(int height, int width, int color, int health) {
		this.height = height;
		this.width = width;
		this.color = color;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}

}
