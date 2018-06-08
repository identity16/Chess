package core;

import pieces.*;
import rules.FourPlayersRule;
import utils.ChessColor;
import utils.Direction;

import java.awt.*;

// 4인용 보드
public class FourPlayersBoard extends Board {

	// Constructor
	FourPlayersBoard() {

        super(new Square[14][14]);
		Square[][] squares = getSquares();
		int N = getN();
		final int squareLen = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / (N + 3);

		GridBagConstraints c = new GridBagConstraints();

		for(int x = 0; x<N; x++) {
			for(int y = 0; y<N; y++) {
				// 사각형이 존재하지 않는 영역 skip
				if((x < 3 && (y < 3 || y >= N - 3))
						|| ((x >= N - 3 && (y < 3 || y >= N - 3)))) continue;

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

	@Override
	public void initStatus() {
		this.status = new ChessPiece[][] {
				{null, null, null, new Rook(3, 0, ChessColor.BLACK), new Knight(4, 0, ChessColor.BLACK), new Bishop(5,0, ChessColor.BLACK), new King(6, 0, ChessColor.BLACK), new Queen(7, 0, ChessColor.BLACK), new Bishop(8, 0, ChessColor.BLACK), new Knight(9, 0, ChessColor.BLACK), new Rook(10, 0, ChessColor.BLACK), null, null, null},
				{null, null, null, new Pawn(3, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(4, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(5, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(6, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(7, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(8, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(9, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(10, 1, ChessColor.BLACK, Direction.SOUTH), null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Rook(0, 3, ChessColor.RED), new Pawn(1, 3, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 3, ChessColor.GREEN, Direction.WEST), new Rook(13, 3, ChessColor.GREEN)},
				{new Knight(0, 4, ChessColor.RED), new Pawn(1, 4, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 4, ChessColor.GREEN, Direction.WEST), new Knight(13, 4, ChessColor.GREEN)},
				{new Bishop(0, 5, ChessColor.RED), new Pawn(1, 5, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 5, ChessColor.GREEN, Direction.WEST), new Bishop(13, 5, ChessColor.GREEN)},
				{new Queen(0, 6, ChessColor.RED),  new Pawn(1, 6, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 6, ChessColor.GREEN, Direction.WEST), new King(13, 6, ChessColor.GREEN)},
				{new King(0, 7, ChessColor.RED), new Pawn(1, 7, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 7, ChessColor.GREEN, Direction.WEST), new Queen(13, 7, ChessColor.GREEN)},
				{new Bishop(0, 8, ChessColor.RED), new Pawn(1, 8, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 8, ChessColor.GREEN, Direction.WEST), new Bishop(13, 8, ChessColor.GREEN)},
				{new Knight(0, 9, ChessColor.RED), new Pawn(1, 9, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 9, ChessColor.GREEN, Direction.WEST), new Knight(13, 9, ChessColor.GREEN)},
				{new Rook(0, 10, ChessColor.RED), new Pawn(1, 10, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 10, ChessColor.GREEN, Direction.WEST), new Rook(13, 10, ChessColor.GREEN)},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, new Pawn(3, 12, ChessColor.WHITE, Direction.NORTH), new Pawn(4, 12, ChessColor.WHITE, Direction.NORTH), new Pawn(5, 12, ChessColor.WHITE, Direction.NORTH), new Pawn(6, 12, ChessColor.WHITE, Direction.NORTH), new Pawn(7, 12, ChessColor.WHITE, Direction.NORTH), new Pawn(8, 12, ChessColor.WHITE, Direction.NORTH), new Pawn(9, 12, ChessColor.WHITE, Direction.NORTH), new Pawn(10, 12, ChessColor.WHITE, Direction.NORTH), null, null, null},
				{null, null, null, new Rook(3, 13, ChessColor.WHITE), new Knight(4, 13, ChessColor.WHITE), new Bishop(5,13, ChessColor.WHITE), new Queen(6, 13, ChessColor.WHITE), new King(7, 13, ChessColor.WHITE), new Bishop(8, 13, ChessColor.WHITE), new Knight(9, 13, ChessColor.WHITE), new Rook(10, 13, ChessColor.WHITE), null, null, null}
		};
	}

}