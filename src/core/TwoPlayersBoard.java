package core;

import pieces.*;
import utils.ChessColor;
import utils.Direction;

import java.awt.*;

public class TwoPlayersBoard extends Board {

    public TwoPlayersBoard(GameManager gameManager) {
		super(gameManager);

		this.width = 8;
		this.height = 8;

		setPreferredSize(new Dimension(Square.WIDTH * this.width, Square.HEIGHT * this.height));

		squares = new Square[this.width][this.height];


		GridBagConstraints c = new GridBagConstraints();

		for(int x=0; x<this.width; x++) {
			for(int y=0; y<this.height; y++) {

				// �궗媛곹삎 �깮�꽦
				Color squareColor = ((x + y) % 2 == 0) ? Square.COLOR_BRIGHT : Square.COLOR_DARK;
				squares[y][x] = new Square(this, x, y, squareColor);
				squares[y][x].setOpaque(true);

				if(status[y][x] != null)
					squares[y][x].setImage(status[y][x].getImage());

				c.gridx = x;
				c.gridy = y;

				add(squares[y][x], c);
			}
		}

    }

	@Override
	public void initStatus() {
		this.status = new ChessPiece[][] {
				{new Rook(0, 0, ChessColor.BLACK), new Knight(1, 0, ChessColor.BLACK), new Bishop(2,0, ChessColor.BLACK), new King(3, 0, ChessColor.BLACK), new Queen(4, 0, ChessColor.BLACK), new Bishop(5, 0, ChessColor.BLACK), new Knight(6, 0, ChessColor.BLACK), new Rook(7, 0, ChessColor.BLACK)},
				{new Pawn(0, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(1, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(2, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(3, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(4, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(5, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(6, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(7, 1, ChessColor.BLACK, Direction.SOUTH)},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{new Pawn(0, 6, ChessColor.WHITE, Direction.NORTH), new Pawn(1, 6, ChessColor.WHITE, Direction.NORTH), new Pawn(2, 6, ChessColor.WHITE, Direction.NORTH), new Pawn(3, 6, ChessColor.WHITE, Direction.NORTH), new Pawn(4, 6, ChessColor.WHITE, Direction.NORTH), new Pawn(5, 6, ChessColor.WHITE, Direction.NORTH), new Pawn(6, 6, ChessColor.WHITE, Direction.NORTH), new Pawn(7, 6, ChessColor.WHITE, Direction.NORTH)},
				{new Rook(0, 7, ChessColor.WHITE), new Knight(1, 7, ChessColor.WHITE), new Bishop(2,7, ChessColor.WHITE), new Queen(3, 7, ChessColor.WHITE), new King(4, 7, ChessColor.WHITE), new Bishop(5, 7, ChessColor.WHITE), new Knight(6, 7, ChessColor.WHITE), new Rook(7, 7, ChessColor.WHITE)}
		};
	}
}