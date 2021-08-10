package game;

import javax.swing.JButton;

public class Button extends JButton {
    private int i, j;	
	
	public Button(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
 
}
