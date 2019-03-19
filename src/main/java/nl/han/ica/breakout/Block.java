package nl.han.ica.breakout;

import java.util.Random;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Block extends GameObject {
	private int color;
	private int value;
	private int durability;
	private IPowerUp powerUp;
	
	public Block(int height, int width, int color, int value, int durability, IPowerUp powerUp) {
		this.height = height;
		this.width = width;
		this.color = color;
		this.value = value;
		this.durability = durability;
		this.powerUp = powerUp;
		
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
	
	

}
