package nl.han.ica.breakout;

import processing.core.PGraphics;

public class PlayerWidthBoost extends PowerUp {

	public PlayerWidthBoost(Breakout world) {
		super(world);
	}

	@Override
	public void boost() {
		if (world.getPlayer().getWidth() * 1.1 <= world.getPlayer().getMAX_WIDTH()) {
			world.getPlayer().setWidth((int) (world.getPlayer().getWidth() * 1.1));
		}
	}

	@Override
	public void draw(PGraphics g) {
		super.draw(g);
		g.fill(0);
		g.text("P", this.getX() - 3, this.getY() + 2);
	}
}
