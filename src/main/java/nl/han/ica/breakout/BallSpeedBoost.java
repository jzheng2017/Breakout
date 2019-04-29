package nl.han.ica.breakout;

import processing.core.PGraphics;

public class BallSpeedBoost extends PowerUp {

	public BallSpeedBoost(Breakout world) {
		super(world);
	}

	@Override
	public void boost() {
		if (world.getBall().getSpeed() + 1 <= world.getBall().getMAX_SPEED()) {
			world.getBall().setSpeed((world.getBall().getSpeed() + 1));
		}
	}

	@Override
	public void draw(PGraphics g) {
		super.draw(g);
		g.fill(0);
		g.text("B", this.getX() - 3, this.getY() + 2);
	}
}
