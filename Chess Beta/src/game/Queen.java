package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Queen extends Piece {
	ImageIcon imgIcon;

	public Queen(boolean isWhite) {
		super(isWhite);
		if (isWhite) {
			Image print = new ImageIcon("C:\\Users\\Igor\\eclipse-workspace\\Chess Beta\\src\\game\\whiteQueen.png")
					.getImage();
			Image img = print.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
			imgIcon = new ImageIcon(img);
		} else {
			Image print = new ImageIcon("C:\\Users\\Igor\\eclipse-workspace\\Chess Beta\\src\\game\\blackQueen.png")
					.getImage();
			Image img = print.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
			imgIcon = new ImageIcon(img);
		}
	}

	@Override
	public int[][] move(Piece[][] board, int i, int j) {
		int[][] possibility = new int[27][2];
		int x = 0;
		int m = 0;
		
		Bishop bishop = new Bishop(isWhite());
		Rook rook = new Rook(isWhite());

		possibility = bishop.move(board, i, j);
		
		for (int c = 0; c < 27; c++) {
			if (possibility[c][0] == -1) {
				x = c;
				break;
			}
		}
		
		int[][] aux = new int[27][2];
		aux = rook.move(board, i, j);
		
		for (int c = x; c < 27; c++) {
			possibility[c][0] = aux[m][0];
			possibility[c][1] = aux[m++][1];
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
