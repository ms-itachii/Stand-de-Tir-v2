package fr.esisar.px.game;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Monster {
	
	private String name;
	private Image[] aliveAnimation;
	private Image[] deathAnimation;
	protected int currentFrame = 0;
	private final int[] targetDimensions = new int[] {40, 41};
	private int[] coordinates = new int[] {1920, 50};
	private int speed = 15;           //Default speed is 15
	private boolean alive = true;
	
	public Monster(String name) {
		this.name = name;
		this.aliveAnimation = new Image[4];
		this.deathAnimation = new Image[5];
		this.loadImages();
		
	}

	private void loadImages() {
		for(int i=1; i<5; i++) {
			String path = "/assets/monsters/" + this.name + i + ".png";
	        URL url = getClass().getResource(path);
			this.aliveAnimation[i-1] = new ImageIcon(url).getImage();
		}
		for(int i=1; i<6; i++) {
			String path = "/assets/monsters/death" + i + ".png";
	        URL url = getClass().getResource(path);
			this.deathAnimation[i-1] = new ImageIcon(url).getImage();
		}
	}
	
	public Image getCurrentImage() {
		if(alive) return aliveAnimation[currentFrame];
		else return deathAnimation[currentFrame];
    }

    public void nextFrame() {
    	if (alive) {
    		currentFrame = (currentFrame + 1) % aliveAnimation.length;
    		coordinates[0] -= speed;     //Change only x coordinate 
    	}
    	else {
    		if(currentFrame < deathAnimation.length) {
    			currentFrame ++;
    		}
    		else {
    			alive = true;
    			resetPosition();
    			currentFrame = 0;
    		}
    	}
    }
    
    private void resetPosition() {
    	setCoordinates(1920, 50);
    }
    
    public int[] getTargetDimensions() {
    	return this.targetDimensions;
    }
    
    public int[] getCoordinates() {
    	return this.coordinates;
    }
    
    public void setCoordinates(int x, int y) {
    	this.coordinates = new int[] {x,y};
    }
    
    public void setAlive(boolean alive) {
    	this.alive = alive;
    }
    
    public Boolean getAlive() {
    	return this.alive;
    }
    
}
