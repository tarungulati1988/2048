/**
 * 
 */
package com.game.model;


import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import com.game.view.GameBoard;

/**
 * @author tgulati
 * 
 */
public class NumberTile {
	public int value;
	public JLabel LABEL;

	public final Font font = new Font("Lithograph", Font.BOLD, 50);
	public final Font big_number = new Font("Lithograph", Font.BOLD, 35);
	public final Color DEFAULT = new Color(119, 110, 101);
	public final Color REMAINING = new Color(249, 246, 242);

	/**
	 * @param value
	 */
	public NumberTile(int value) {
		this.value = value;
		LABEL = new JLabel(Integer.toString(value));
		LABEL.setSize(40, 40);
		LABEL.setFont(font);
		LABEL.setForeground(DEFAULT);
	}

	/**
	 * 
	 */
	public void doubleValue() {
		value = value << 1;
		LABEL.setText(Integer.toString(value));
		if (value > 4) {
			LABEL.setForeground(REMAINING);
		}
		if (value == 2048) {
			GameBoard.gameStatus = true;
		}
	}
}
