package nl.han.ica.breakout;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;

public class Breakout extends GameEngine {
    private Player player;
    private Ball ball;
    // Deze regel maakt het makkelijker om te refereren naar je plaatjes.
    public static String MEDIA_URL = "src/main/java/nl/han/ica/TutorialWorld/media/";
    
    public static void main(String[] args) {
        Breakout tw = new Breakout();
        tw.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWidth = 500;
        int worldHeight = 500;
        
        // uiteraard kan je het toevoegen van
        // nieuwe game objects misschien het beste
        // in een aparte methode doen
        // i.p.v. de update zo groot te maken.
        player = new Player(this);
        addGameObject(player, worldWidth/3, worldHeight);
        ball = new Ball(this, 30, 30);
        addGameObject(ball, worldWidth/3, worldHeight - player.getHeight() - ball.getHeight()/2);
        View view = new View(worldWidth, worldHeight);

        setView(view);
        //view.setBackground(loadImage(MEDIA_URL.concat("PNG/Tiles/platformPack_tile004")));
        size(worldWidth, worldHeight);
    }

    @Override
    public void update() {
        // Dit doet nog helemaal niks
        
    }

}
