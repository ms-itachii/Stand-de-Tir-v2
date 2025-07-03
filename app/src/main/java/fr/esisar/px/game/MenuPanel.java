package fr.esisar.px.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel{
	
	private JButton playButton;
	private GameUI gameUI;
	private static final long serialVersionUID = 1L;
	
	public MenuPanel(GameUI gameUI) {
		this.gameUI = gameUI;
		setLayout(new GridBagLayout());
		playButton = new JButton("Play game");
        playButton.addActionListener(new PlayButtonListener());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(playButton, gbc);
	}
	
	private class PlayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handlePlayButtonClick();
        }
    }

    // Méthode pour gérer les clics
    private void handlePlayButtonClick() {
    	gameUI.showGamePanel();
        //JOptionPane.showMessageDialog(this, "fr.esisar.px.game Started!");
    }

    
}
