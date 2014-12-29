/**
 * 
 */
package com.game.main;

import java.awt.Dimension;

import static com.game.constants.Constants2048.*;

import javax.swing.JFrame;

import com.game.view.GameBoard;

/**
 * @author tgulati
 * 
 */
public class StartGame {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");
		JFrame window = new JFrame();
		window.setSize(new Dimension(WIDTH, HEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setContentPane(new GameBoard());
		window.pack();
		window.setVisible(true);
	}

}
