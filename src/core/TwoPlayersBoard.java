package core;

import pieces.ChessPiece;

import java.awt.*;

public class TwoPlayersBoard extends Board {
	public static final int WIDTH = 8;
	public static final int HEIGHT = 8;

    public TwoPlayersBoard(GameManager gameManager) {
    	super(gameManager);

    	squares = new Square[WIDTH][HEIGHT];
		status = new ChessPiece[8][8];

    	for(int i=0; i<WIDTH; i++) {
    		for(int j=0; j<HEIGHT; j++) {
				Color squareColor = ((i + j) % 2 == 0) ? Square.COLOR_BRIGHT : Square.COLOR_DARK;
				squares[i][j] = new Square(i, j, squareColor);
				add(squares[i][j]);
			}
		}


    }
}