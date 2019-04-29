package nl.han.ica.breakout;

import processing.core.PGraphics;

public class PlayerSpeedBoost extends PowerUp {

	public PlayerSpeedBoost(Breakout world) {
		super(world);

	}

	@Override
	public void boost() {
		if (world.getPlayer().getPlayerSpeed() + 1 <= world.getPlayer().getMAX_SPEED()) {
			world.getPlayer().setPlayerSpeed(world.getPlayer().getPlayerSpeed() + 1);
		}
	}

	@Override
	public void draw(PGraphics g) {
		super.draw(g);
		g.fill(0);
		g.text("P", this.getX() - 3, this.getY() + 2);
	}
}
