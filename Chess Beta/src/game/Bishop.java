package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bishop extends Piece {
	ImageIcon imgIcon;

	public Bishop(boolean isWhite) {
		super(isWhite);
		if (isWhite) {
			Image print = new ImageIcon("C:\\Users\\Igor\\eclipse-workspace\\Chess Beta\\src\\game\\whiteBishop.png")
					.getImage();
			Image img = print.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
			imgIcon = new ImageIcon(img);
		} else {
			Image print = new ImageIcon("C:\\Users\\Igor\\eclipse-workspace\\Chess Beta\\src\\game\\blackBishop.png")
					.getImage();
			Image img = print.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
			imgIcon = new ImageIcon(img);
		}
	}

	@Override
	public int[][] move(Piece[][] board, int i, int j) {
		int[][] possibility = new int[27][2];
		int cont = 0;
		int aux = 0;

		for (int c = 0; c < 27; c++) {
			possibility[c][0] = -1;
			possibility[c][1] = -1;
		}

		if (7 - i < 7 - j) {
			aux = 7 - i;
		} else {
			aux = 7 - j;
		}

		for (int x = 1; x <= aux; x++) {
			if (board[i + x][j + x] == null) {
				possibility[cont][0] = i + x;
				possibility[cont++][1] = j + x;
			} else {
				if (isWhite() != board[i + x][j + x].isWhite()) {
					possibility[cont][0] = i + x;
					possibility[cont++][1] = j + x;
					break;
				} else {
					break;
				}
			}
		}

		if (7 - i < j) {
			aux = 7 - i;
		} else {
			aux = j;
		}

		for (int x = 1; x <= aux; x++) {
			if (board[i + x][j - x] == null) {
				possibility[cont][0] = i + x;
				possibility[cont++][1] = j - x;
			} else {
				if (isWhite() != board[i + x][j - x].isWhite()) {
					possibility[cont][0] = i + x;
					possibility[cont++][1] = j - x;
					break;
				} else {
					break;
				}
			}
		}

		if (i < j) {
			aux = i;
		} else {
			aux = j;
		}

		for (int x = 1; x <= aux; x++) {
			if (board[i - x][j - x] == null) {
				possibility[cont][0] = i - x;
				possibility[cont++][1] = j - x;
			} else {
				if (isWhite() != board[i - x][j - x].isWhite()) {
					possibility[cont][0] = i - x;
					possibility[cont++][1] = j - x;
					break;
				} else {
					break;
				}
			}
		}

		if (i < 7 - j) {
			aux = i;
		} else {
			aux = 7 - j;
		}

		for (int x = 1; x <= aux; x++) {
			if (board[i - x][j + x] == null) {
				possibility[cont][0] = i - x;
				possibility[cont++][1] = j + x;
			} else {
				if (isWhite() != board[i - x][j + x].isWhite()) {
					possibility[cont][0] = i - x;
					possibility[cont++][1] = j + x;
					break;
				} else {
					break;
				}
			}
		}

		return possibility;
	}

	@Override
	public ImageIcon getImgIcon() {
		return imgIcon;
	}
	
	@Override
	public boolean check(Piece[][] board,int i,int j) {
		int[][] possibility = move(board, i, j);
		
		for( int c = 0; possibility[c][0] >= 0; c++ ) { 
			if(board[possibility[c][0]][possibility[c][1]] instanceof King && board[possibility[c][0]][possibility[c][1]].isWhite() != isWhite()) {
				return true;
			}
		}
		return false;
	}

}
