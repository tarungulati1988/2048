/**
 * 
 */
package com.game.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.game.model.NumberTile;

import static com.game.constants.Constants2048.*;

/**
 * @author tgulati
 * 
 */
public class GameBoard extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NumberTile gamePanel[][];
	public byte currentNumberTile;
	public static boolean gameStatus;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		IS_MOVED = false;
		byte key = (byte) e.getKeyCode();
		if (key == LEFT) {
			IS_MOVED = horizontalPressed(ACTUAL_END, LEFT_INCREMENT);
		} else if (key == RIGHT) {
			IS_MOVED = horizontalPressed(DUMMY_END, RIGHT_INCREMENT);
		} else if (key == UP) {
			gamePanel = rotateRight(gamePanel);
			IS_MOVED = horizontalPressed(DUMMY_END, RIGHT_INCREMENT);
			gamePanel = rotateLeft(gamePanel);
		} else if (key == DOWN) {
			gamePanel = rotateRight(gamePanel);
			IS_MOVED = horizontalPressed(ACTUAL_END, LEFT_INCREMENT);
			gamePanel = rotateLeft(gamePanel);
		}
		Generate(IS_MOVED);
		repaint();
	}

	/**
	 * @param is_moved
	 */
	public void Generate(boolean is_moved) {
		if (is_moved) {
			Random row_col = new Random();
			byte row = (byte) row_col.nextInt(ROWS_COLS);
			byte col = (byte) row_col.nextInt(ROWS_COLS);
			int two_four = row_col.nextInt(RANDOM);

			if (two_four % 2 == 0) {
				if (gamePanel[row][col] == null) {
					gamePanel[row][col] = new NumberTile(START_2);
					currentNumberTile++;
				} else {
					Generate(is_moved);
				}
			} else {
				if (gamePanel[row][col] == null) {
					gamePanel[row][col] = new NumberTile(START_4);
					currentNumberTile++;
				} else {
					Generate(is_moved);
				}
			}
		}
	}

	/**
	 * @param tile
	 * @return
	 */
	public NumberTile[][] rotateLeft(NumberTile tile[][]) {
		NumberTile new_image[][] = new NumberTile[ROWS_COLS][ROWS_COLS];

		for (int y = 0; y < ROWS_COLS; y++) {
			for (int x = 0; x < ROWS_COLS; x++) {
				new_image[x][y] = tile[y][ACTUAL_END - x];
			}
		}

		return new_image;
	}

	/**
	 * @param tile
	 * @return
	 */
	public NumberTile[][] rotateRight(NumberTile tile[][]) {
		NumberTile new_image[][] = new NumberTile[ROWS_COLS][ROWS_COLS];
		for (int y = 0; y < ROWS_COLS; y++) {
			for (int x = 0; x < ROWS_COLS; x++) {
				new_image[x][ACTUAL_END - y] = tile[y][x];
			}
		}

		return new_image;
	}

	/**
	 * @param leftOrRight
	 * @param increment
	 * @return
	 */
	public boolean horizontalPressed(byte leftOrRight, byte increment) {
		byte compare = (byte) (increment + leftOrRight);
		byte whichEnd = (byte) (ACTUAL_END - leftOrRight);

		for (byte row = 0; row < ROWS_COLS; row++) {
			moveRow(row, whichEnd, compare, increment);
		}

		// merge_row
		for (byte y = 0; y < ROWS_COLS; y++) {
			byte x = whichEnd;
			while (x != compare && x != leftOrRight) {
				if (gamePanel[y][x] != null && gamePanel[y][x + increment] != null
						&& gamePanel[y][x].value == gamePanel[y][x + increment].value) {
					gamePanel[y][x].doubleValue();
					remove(gamePanel[y][x + increment].LABEL);
					gamePanel[y][x + increment] = null;
					currentNumberTile--;
					IS_MOVED = true;
					x = (byte) (x + (increment + increment));
				} else {
					x = (byte) (x + increment);
				}
			}
			moveRow(y, whichEnd, compare, increment);
		}

		return IS_MOVED;

	}

	/**
	 * @param row
	 * @param whichEnd
	 * @param compare
	 * @param increment
	 */
	public void moveRow(byte row, byte whichEnd, byte compare, byte increment) {
		ArrayList<NumberTile> tempRow = new ArrayList<NumberTile>();
		byte col;
		for (col = whichEnd; col != compare; col = (byte) (col + increment)) {
			if (gamePanel[row][col] != null) {
				tempRow.add(gamePanel[row][col]);
			}
		}

		byte next = 0;
		for (col = whichEnd; col != compare; col = (byte) (col + increment)) {
			try {
				if (tempRow.get(next) != gamePanel[row][col]) {
					IS_MOVED = true;
					gamePanel[row][col] = tempRow.get(next);
				}
			} catch (IndexOutOfBoundsException E) {
				gamePanel[row][col] = null;
			}

			next++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	public GameBoard() {
		setBackground(BACKGROUND);
		setPreferredSize(new Dimension(WIDTH, WIDTH));
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);

		RENDERING_HEIGHT.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		gamePanel = new NumberTile[ROWS_COLS][ROWS_COLS];
		gameStatus = false;

		// same as generate method, but thought it'd be a waste
		// to call it when we're initializing.
		Random row_col = new Random();
		byte row = (byte) row_col.nextInt(ROWS_COLS);
		byte col = (byte) row_col.nextInt(ROWS_COLS);
		int two_four = row_col.nextInt(RANDOM);

		if (two_four % 2 == 0) {
			gamePanel[row][col] = new NumberTile(START_2);
		} else {
			gamePanel[row][col] = new NumberTile(START_4);
		}
		currentNumberTile++;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setRenderingHints(RENDERING_HEIGHT);
		for (byte row = 0; row < ROWS_COLS; row++) {
			int Y_jump = JUMPS[row];
			for (byte col = 0; col < ROWS_COLS; col++) {
				int X_jump = JUMPS[col];

				if (gamePanel[row][col] == null) {
					graphics2D.setColor(DEFAULT_NUMBER_TILE);
					graphics2D.fillRoundRect(X_jump, Y_jump, TILE_WIDTH,
							TILE_WIDTH, 80, 80);
				} else {
					int value = gamePanel[row][col].value;
					JLabel temp = gamePanel[row][col].LABEL;

					if (value == 2) {
						graphics2D.setColor(TWO);
						temp.setLocation(X_jump + TILE_CENTER - 18, Y_jump
								+ TILE_CENTER - 20);
					} else if (value == 4) {
						graphics2D.setColor(FOUR);
						temp.setLocation(X_jump + TILE_CENTER - 18, Y_jump
								+ TILE_CENTER - 20);
					} else if (value == 8) {
						graphics2D.setColor(EIGHT);
						temp.setLocation(X_jump + TILE_CENTER - 18, Y_jump
								+ TILE_CENTER - 20);
					} else if (value == 16) {
						graphics2D.setColor(SIXTEEN);
						temp.setLocation(X_jump + TILE_CENTER - 28, Y_jump
								+ TILE_CENTER - 23);
					} else if (value == 32) {
						graphics2D.setColor(THIRTYTWO);
						temp.setLocation(X_jump + TILE_CENTER - 28, Y_jump
								+ TILE_CENTER - 23);
					} else if (value == 64) {
						graphics2D.setColor(SIXTYFOUR);
						temp.setLocation(X_jump + TILE_CENTER - 30, Y_jump
								+ TILE_CENTER - 23);
					} else if (value < 1024) {
						graphics2D.setColor(REMAINING);
						temp.setLocation(X_jump + TILE_CENTER - 45, Y_jump
								+ TILE_CENTER - 20);
					} else {
						graphics2D.setColor(REMAINING);
						temp.setFont(gamePanel[row][col].big_number);
						temp.setLocation(X_jump + TILE_CENTER - 45, Y_jump
								+ TILE_CENTER - 15);
					}

					graphics2D.fillRoundRect(X_jump, Y_jump, TILE_WIDTH,
							TILE_WIDTH, 80, 80);
					add(temp);
				}

			}
		}

		if (!gameStatus) {
			if (currentNumberTile == 16) {
				boolean check = false;
				for (byte x = 0; x < ROWS_COLS; x++) {
					try {
						byte y = 0;
						while (y != ROWS_COLS) {
							if (y + 1 <= ACTUAL_END && x + 1 <= ACTUAL_END) {
								if (gamePanel[x][y].value == gamePanel[x][y + 1].value
										|| gamePanel[x][y].value == gamePanel[x + 1][y].value) {
									check = true;
									break;
								} else {
									y++;
								}
							} else if (y + 1 <= ACTUAL_END) {
								if (gamePanel[x][y].value == gamePanel[x][y + 1].value) {
									check = true;
									break;
								} else {
									y++;
								}
							} else {
								if (gamePanel[x][y].value == gamePanel[x + 1][y].value) {
									check = true;
									break;
								} else {
									y++;
								}
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						break;
					}
					if (check) {
						break;
					}
				}

				if (!check) {
					System.out.println("Lost");
					setEnabled(false);
					try {
						this.finalize();
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("Won");
			setEnabled(false);
			try {
				this.finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

	}

}
