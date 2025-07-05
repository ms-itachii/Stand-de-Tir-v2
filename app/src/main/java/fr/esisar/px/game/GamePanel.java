package fr.esisar.px.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel{
	
	private int fps = 30; 
	private int delay = 1000 / fps; // delay in milliseconds
	int[] frameCounter = {0};
	
	private Player player;
	private Monster target; 
	private int mode = 0;   //There are two modes, 0 is the normal one
	
	private Image background;
	private static final long serialVersionUID = 1L;
	
	public GamePanel(String playerName) {
		this.player = new Player(playerName);
		this.background = new ImageIcon(getClass().getResource("/assets/background/back.png")).getImage();
		this.target = new Monster("Aigle");
		
		Timer timer = new Timer(delay, e -> {
			frameCounter[0]++;
		    if (frameCounter[0] % (fps / 10) == 0) { 
		        target.nextFrame();
		    }
		    repaint();          
        });
        timer.start();
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }
	
	public void playMode1() {
		
	}
	
	
	private void deleteMonster() {
		target.setAlive(false);
		target.currentFrame = 0;
	}
	
	private void handleMouseClick(MouseEvent e) {
		int mouseX = e.getX();
	    int mouseY = e.getY();

	    int[] pos = target.getCoordinates();
	    int[] size = target.getTargetDimensions();

	    Rectangle targetBounds = new Rectangle(pos[0], pos[1], size[0], size[1]);

	    if (targetBounds.contains(mouseX, mouseY)) {
	        deleteMonster();
	    }

    }
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(target.getCurrentImage(), target.getCoordinates()[0], target.getCoordinates()[1], target.getTargetDimensions()[0], target.getTargetDimensions()[1], this);
    }

}
