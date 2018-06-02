package core;

import pieces.*;
import utils.ChessColor;
import utils.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TwoPlayersBoard extends Board {

    public TwoPlayersBoard(GameManager gameManager) {
		super(gameManager);

		this.width = 8;
		this.height = 8;

		System.out.println(Arrays.deepToString(this.getStatus()));

		setSize(new Dimension(Square.WIDTH * this.width, Square.HEIGHT * this.height));

		squares = new Square[this.width][this.height];


		GridBagConstraints c = new GridBagConstraints();

		for(int i=0; i<this.width; i++) {
			for(int j=0; j<this.height; j++) {

				// 사각형 생성
				Color squareColor = ((i + j) % 2 == 0) ? Square.COLOR_BRIGHT : Square.COLOR_DARK;
				squares[i][j] = new Square(i, j, squareColor);
				squares[i][j].setOpaque(true);

				if(status[i][j] != null)
					squares[i][j].setImage(status[i][j].getImage());

				c.gridx = j;
				c.gridy = i;

				add(squares[i][j], c);
			}
		}

    }

	@Override
	public void initStatus() {
		this.status = new ChessPiece[][] {
				{new Rook(0, 0, ChessColor.BLACK), new Knight(1, 0, ChessColor.BLACK), new Bishop(2,0, ChessColor.BLACK), new Queen(3, 0, ChessColor.BLACK), new King(4, 0, ChessColor.BLACK), new Bishop(5, 0, ChessColor.BLACK), new Knight(6, 0, ChessColor.BLACK), new Rook(7, 0, ChessColor.BLACK)},
				{new Pawn(0, 1, ChessColor.BLACK, Direction.NORTH), new Pawn(1, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(2, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(3, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(4, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(5, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(6, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(7, 1, ChessColor.BLACK, Direction.SOUTH)},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{new Pawn(0, 6, ChessColor.WHITE, Direction.NORTH), new Pawn(1, 6, ChessColor.WHITE, Direction.NORTH), new Pawn(2, 6, ChessColor.WHITE, Direction.NORTH), new Pawn(3, 6, ChessColor.WHITE, Direction.NORTH), new Pawn(4, 6, ChessColor.WHITE, Direction.NORTH), new Pawn(5, 6, ChessColor.WHITE, Direction.NORTH), new Pawn(6, 6, ChessColor.WHITE, Direction.NORTH), new Pawn(7, 6, ChessColor.WHITE, Direction.NORTH)},
				{new Rook(0, 7, ChessColor.WHITE), new Knight(1, 7, ChessColor.WHITE), new Bishop(2,7, ChessColor.WHITE), new Queen(3, 7, ChessColor.WHITE), new King(4, 7, ChessColor.WHITE), new Bishop(5, 7, ChessColor.WHITE), new Knight(6, 7, ChessColor.WHITE), new Rook(7, 7, ChessColor.WHITE)}
		};
	}
}