package core;

import pieces.*;
import utils.ChessColor;
import utils.Direction;

import java.awt.*;

public class TwoPlayersBoard extends Board {

    public TwoPlayersBoard() {
		super();

		this.N = 8;
		final int squareLen = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / (this.N + 3);

		squares = new Square[this.N][this.N];


		GridBagConstraints c = new GridBagConstraints();

		for(int x = 0; x<this.N; x++) {
			for(int y = 0; y<this.N; y++) {

				// 사각형 생성
				Color squareColor = ((x + y) % 2 == 0) ? Square.COLOR_BRIGHT : Square.COLOR_DARK;
				squares[y][x] = new Square(x, y, squareLen, squareColor);
				squares[y][x].setOpaque(true);

				if(status[y][x] != null)
					squares[y][x].setImage(status[y][x].getImage());

				c.gridx = x;
				c.gridy = y;

				add(squares[y][x], c);
			}
		}

    }

	// 초기 말 세팅
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