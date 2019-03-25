package nl.han.ica.breakout;

import java.util.ArrayList;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;

public class Breakout extends GameEngine {
	private BlockSpawner blockSpawner;
    private Player player;
    private Level currentLevel;
    private ArrayList<Level> gameLevels;
    private TextObject scoreBoardText;
  
    public static void main(String[] args) {
        Breakout tw = new Breakout();
        tw.runSketch();
    }
    
    @Override
    public void setupGame() {
        int worldWidth = 500;
        int worldHeight = 500;
        size(worldWidth, worldHeight);
        View view = new View(worldWidth, worldHeight);
        initializePlayer();
        createBall();
        initializeLevels();
        initializeScoreBoard(width, 100);
        setView(view);
    }
    
    public void initializePlayer() {
        player = new Player(this);
        addGameObject(player, this.width/3, this.height);

    }
    
    public void initializeLevels() {
    	gameLevels = new ArrayList<Level>();
    	gameLevels.add(new Level(this, 2, 1, 1));
    	gameLevels.add(new Level(this, 15, 5, 2));
    	currentLevel = gameLevels.get(0);
        initializeBlockSpawner();
    	currentLevel.startLevel();
    }
    
    public void initializeScoreBoard(int scoreBoardWidth, int scoreBoardHeight) {
    	final int FONT_SIZE = 15;
    	String text = "Score:";
    	Dashboard dashBoard = new Dashboard(0,0, scoreBoardWidth, scoreBoardHeight);
    	scoreBoardText = new TextObject(text, FONT_SIZE);
    	scoreBoardText.setForeColor(255, 255, 255, 255);
    	addGameObject(scoreBoardText);
    	addDashboard(dashBoard);
    }
    
    public void refreshScoreBoard(){
    	scoreBoardText.setText("test");
    }
    public Level getCurrentLevel() {
    	return this.currentLevel;
    }
    
    public void nextLevel() {
    	currentLevel = gameLevels.get(gameLevels.indexOf(currentLevel) + 1);
    	currentLevel.startLevel();
    }
    public void createBall() {
    	 final int BALL_DIAMETER = 30;
    	 addGameObject(new Ball(this, BALL_DIAMETER, BALL_DIAMETER), this.width/2, this.height - player.getHeight() - BALL_DIAMETER / 2);
    }
    
    public void initializeBlockSpawner() {
    	blockSpawner = new BlockSpawner(this, currentLevel.getAmountHorizontal(), currentLevel.getAmountVertical());
    }
    public BlockSpawner getBlockSpawner() {
    	return blockSpawner;
    }
    @Override
    public void update() {
        if (currentLevel.getBlocksLeft() <= 0) {
        	nextLevel();
        	System.out.println("Next level!");
        }
        //refreshScoreBoard();
    }

}
