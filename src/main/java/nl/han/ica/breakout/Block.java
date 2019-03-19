package nl.han.ica.breakout;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public abstract class Block extends GameObject implements ICollidableWithGameObjects {
	protected int color;
	protected int value;
	protected int durability;

	
	public Block(int height, int width, int color, int value, int durability) {
		this.height = height;
		this.width = width;
		this.color = color;
		this.value = value;
		this.durability = durability;
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(PGraphics g) {
		g.fill(color);
		g.rect(getX(), getY(), width, height);
	}

	@Override
	public abstract void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects);
		

}
