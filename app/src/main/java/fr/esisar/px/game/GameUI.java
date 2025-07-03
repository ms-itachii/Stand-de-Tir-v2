package fr.esisar.px.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI {
	
	private JFrame frame;
	private LoginPanel loginPanel;
	private MenuPanel menuPanel;
	private GamePanel gamePanel;
	
	public GameUI() {
		frame = new JFrame("Shooter");
        frame.setSize(1920, 1080); // width, height
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.showLoginPanel();
        frame.setVisible(true);
	}
	
	public void showLoginPanel() {
		loginPanel = new LoginPanel();
		frame.setContentPane(loginPanel); 
        frame.revalidate();
        frame.repaint();
	}
	
	public void showMenuPanel() {
		menuPanel = new MenuPanel(this);
		frame.setContentPane(menuPanel); 
        frame.revalidate();
        frame.repaint();
	}
	
	public void showGamePanel() {
		gamePanel = new GamePanel("itachi");
		frame.setContentPane(gamePanel); 
        frame.revalidate();
        frame.repaint();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GameUI());
	}

}
