package fr.esisar.px.game;

public class Player {
	
	private final String name;
	private int health;
	private int score;
	
	public Player(String name) {
		this.name = name;
		this.health = 100;
		this.score = 0;
	}
	
	public void setScore(int score) {
		this.score += score;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void resetScore() {
		this.score = 0;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return this.health;
	}

}
