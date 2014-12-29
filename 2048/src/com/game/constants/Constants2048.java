/**
 * 
 */
package com.game.constants;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;

/**
 * @author tgulati
 * 
 */
public class Constants2048 {
	public static final int START_2 = 2;
	public static final int START_4 = 4;
	public static final int HEIGHT = 489;
	public static final int WIDTH = 489;
	public static final int SEPARATION = 4;
	public static final int TILE_WIDTH = 119;
	public static final int TILE_CENTER = 119 >> 1;
	public static final int RANDOM = 101;
	public static final byte ROWS_COLS = 4;
	public static final byte ACTUAL_END = ROWS_COLS - 1;
	public static final byte DUMMY_END = 0;
	public static final byte LEFT_INCREMENT = 1;
	public static final byte RIGHT_INCREMENT = -1;
	public static final byte LEFT = 37;
	public static final byte RIGHT = 39;
	public static final byte UP = 38;
	public static final byte DOWN = 40;

	public static final Color BACKGROUND = new Color(187, 173, 160);
	public static final Color DEFAULT_NUMBER_TILE = new Color(204, 192, 179);
	public static final Color TWO = new Color(238, 228, 218);
	public static final Color FOUR = new Color(237, 224, 200);
	public static final Color EIGHT = new Color(242, 177, 121);
	public static final Color SIXTEEN = new Color(245, 149, 98);
	public static final Color THIRTYTWO = new Color(246, 124, 95);
	public static final Color SIXTYFOUR = new Color(246, 94, 59);
	public static final Color REMAINING = new Color(237, 204, 97);

	public static final RenderingHints RENDERING_HEIGHT = new RenderingHints(
			RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	// x and y positions of the four possible places of a tile.
	public static final int JUMPS[] = { SEPARATION,
			(TILE_WIDTH + SEPARATION),
			((TILE_WIDTH << 1) + SEPARATION),
			(((TILE_WIDTH << 1) + TILE_WIDTH) + SEPARATION) };
	
	public static boolean IS_MOVED = false;
	public final Font END = new Font("Lithograph", Font.BOLD, 50);

}
