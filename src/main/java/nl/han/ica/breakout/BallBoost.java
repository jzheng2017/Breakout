package nl.han.ica.breakout;

import java.util.List;
import java.util.Random;

import nl.han.ica.breakout.PlayerBoost.powerUp;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class BallBoost extends PowerUp {
	private powerUp powerUp;

	public enum powerUp {
		Speed, Damage;
		/**
		 * Gets random PowerUp from the powerUp enum
		 * 
		 * @return
		 */
		public static powerUp getRandomPowerUp() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
	}

	@Override
	public void draw(PGraphics g) {
		super.draw(g);
		g.fill(0);
		g.text("B", this.getX() - 3, this.getY() + 2);
	}

	public BallBoost(int height, int width, Breakout world, powerUp powerUp) {
		super(world);
		this.powerUp = powerUp;

	}

	@Override
	public void boost() {
		switch (powerUp) {
		case Speed:
			if (world.getBall().getSpeed() + 1 <= world.getBall().getMAX_SPEED()) {
				world.getBall().setSpeed((world.getBall().getSpeed() + 1));
			}
			break;
		case Damage:
			if (world.getBall().getDamage() + 1 <= world.getBall().getMAX_DAMAGE()) {
				world.getBall().setDamage(world.getBall().getDamage() + 1);
			}
			break;
		default:
			break;
		}
		System.out.println("Ball " + this.powerUp);
	}

}
