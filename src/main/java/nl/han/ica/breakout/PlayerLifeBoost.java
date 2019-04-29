package nl.han.ica.breakout;

import processing.core.PGraphics;

public class PlayerLifeBoost extends PowerUp {

	public PlayerLifeBoost(Breakout world) {
		super(world);

	}

	@Override
	public void boost() {
		world.getPlayer().setLives(world.getPlayer().getLives() + 1);
	}
	
	@Override
	public void draw(PGraphics g) {
		super.draw(g);
		g.fill(0);
		g.text("P", this.getX() - 3, this.getY() + 2);
	}

}
