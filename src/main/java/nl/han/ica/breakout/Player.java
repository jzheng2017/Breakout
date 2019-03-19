package nl.han.ica.breakout;

import java.util.List;

import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Player extends GameObject implements ICollidableWithGameObjects{
	final int size = 50;

    private Breakout world;
    private IPowerUp powerUp;

    public Player(Breakout world) {
        this.height = 30;
        this.width = 150;
        this.world = world;
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 2;
        if (keyCode == world.LEFT) {
          setDirectionSpeed(270, speed);
        }
        else if (keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
          }	
       
    }
 
    
    @Override
    public void draw(PGraphics g) {
    	g.fill(255);
    	g.rect(getX(), getY(), this.width, this.height);

    }
    @Override
    public void update() {
        if (getX() <= 0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY() <= 0) {
            setySpeed(0);
            setY(0);
        }
        if (getX() >= world.width - width) {
            setxSpeed(0);
            setX(world.width - width);
        }
        if (getY() >= world.height - height) {
            setySpeed(0);
            setY(world.height - height);
        }
    }

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
	
	}

	public void setPowerUp(IPowerUp powerUp) {
		this.powerUp = powerUp;
	}

}