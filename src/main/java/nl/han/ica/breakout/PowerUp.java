package nl.han.ica.breakout;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;

public abstract class PowerUp implements ICollidableWithGameObjects  {
	
	protected final int DIAMETER = 10;
	
	public abstract void boost();
}
