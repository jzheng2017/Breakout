package nl.han.ica.breakout;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class PlayerBoost extends PowerUp {

	public PlayerBoost(int height, int width, Breakout world) {
		super(world);
		//setGravity(0.1f);		
		setHeight(height);
		setWidth(width);
	}

	@Override
	public void boost() {
		world.getPlayer().setWidth((int)(world.getPlayer().getWidth() * 1.1));
	}

	

}
