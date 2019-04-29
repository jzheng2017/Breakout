package nl.han.ica.breakout;

import processing.core.PGraphics;

public class BallDamageBoost extends PowerUp {

	public BallDamageBoost(Breakout world) {
		super(world);

	}

	@Override
	public void boost() {
		if (world.getBall().getDamage() + 1 <= world.getBall().getMAX_DAMAGE()) {
			world.getBall().setDamage(world.getBall().getDamage() + 1);
		}
	}
	

	@Override
	public void draw(PGraphics g) {
		super.draw(g);
		g.fill(0);
		g.text("B", this.getX() - 3, this.getY() + 2);
	}

}
