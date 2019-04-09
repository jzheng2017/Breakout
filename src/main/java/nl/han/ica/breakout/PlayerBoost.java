package nl.han.ica.breakout;

import java.util.Random;

import processing.core.PGraphics;

public class PlayerBoost extends PowerUp {
	private powerUp powerUp;

	// different kinds of powerUps
	public enum powerUp {
		Speed, Width, Life;

		/**
		 * Returns random powerup
		 * 
		 * @return
		 */
		public static powerUp getRandomPowerUp() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
	}

	public PlayerBoost(int height, int width, Breakout world, powerUp powerUp) {
		super(world);
		this.powerUp = powerUp;
		setHeight(height);
		setWidth(width);
	}

	@Override
	public void draw(PGraphics g) {
		super.draw(g);
		g.fill(0);
		g.text("P", this.getX() - 3, this.getY() + 2);
	}

	@Override
	public void boost() {
		switch (powerUp) {
		case Width:
			if (world.getPlayer().getWidth() * 1.1 <= world.getPlayer().getMAX_WIDTH()) {
				world.getPlayer().setWidth((int) (world.getPlayer().getWidth() * 1.1));
			}
			break;
		case Speed:
			if (world.getPlayer().getPlayerSpeed() + 1 <= world.getPlayer().getMAX_SPEED()) {
				world.getPlayer().setPlayerSpeed(world.getPlayer().getPlayerSpeed() + 1);
			}
			break;
		case Life:
			world.getPlayer().setLives(world.getPlayer().getLives() + 1);
			break;
		default:
			break;
		}
		System.out.println("Player " + this.powerUp);
	}

	/**
	 * returns powerup
	 * 
	 * @return
	 */
	public powerUp getPowerUp() {
		return powerUp;
	}

}
