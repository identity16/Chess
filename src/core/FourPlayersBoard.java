package core;

import pieces.*;
import utils.ChessColor;
import utils.Direction;

import java.awt.*;

public class FourPlayersBoard extends Board {

    public FourPlayersBoard(GameManager gameManager) {
        super(gameManager);
        this.width = 14;
		this.height = 14;

		setSize(new Dimension(700, 700));

		squares = new Square[this.width][this.height];

		GridBagConstraints c = new GridBagConstraints();

		for(int x=0; x<this.width; x++) {
			for(int y=0; y<this.height; y++) {
				// �궗媛곹삎�씠 議댁옱�븯吏� �븡�뒗 �쁺�뿭 skip
				if((x < 3 && (y < 3 || y >= this.width - 3))
						|| ((x >= this.height - 3 && (y < 3 || y >= this.width - 3)))) continue;

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
				{null, null, null, new Rook(3, 0, ChessColor.BLACK), new Knight(4, 0, ChessColor.BLACK), new Bishop(5,0, ChessColor.BLACK), new King(6, 0, ChessColor.BLACK), new Queen(7, 0, ChessColor.BLACK), new Bishop(8, 0, ChessColor.BLACK), new Knight(9, 0, ChessColor.BLACK), new Rook(10, 0, ChessColor.BLACK), null, null, null},
				{null, null, null, new Pawn(3, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(4, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(5, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(6, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(7, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(8, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(9, 1, ChessColor.BLACK, Direction.SOUTH), new Pawn(10, 1, ChessColor.BLACK, Direction.SOUTH), null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{new Rook(0, 3, ChessColor.RED), new Pawn(1, 3, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 3, ChessColor.GREEN, Direction.WEST), new Rook(13, 3, ChessColor.GREEN)},
				{new Knight(0, 4, ChessColor.RED), new Pawn(1, 4, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 4, ChessColor.GREEN, Direction.WEST), new Knight(13, 4, ChessColor.GREEN)},
				{new Bishop(0, 5, ChessColor.RED), new Pawn(1, 5, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 5, ChessColor.GREEN, Direction.WEST), new Bishop(13, 5, ChessColor.GREEN)},
				{new King(0, 6, ChessColor.RED), new Pawn(1, 6, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 6, ChessColor.GREEN, Direction.WEST), new Queen(13, 6, ChessColor.GREEN)},
				{new Queen(0, 7, ChessColor.RED), new Pawn(1, 7, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 7, ChessColor.GREEN, Direction.WEST), new King(13, 7, ChessColor.GREEN)},
				{new Bishop(0, 8, ChessColor.RED), new Pawn(1, 8, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 8, ChessColor.GREEN, Direction.WEST), new Bishop(13, 8, ChessColor.GREEN)},
				{new Knight(0, 9, ChessColor.RED), new Pawn(1, 9, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 9, ChessColor.GREEN, Direction.WEST), new Knight(13, 9, ChessColor.GREEN)},
				{new Rook(0, 10, ChessColor.RED), new Pawn(1, 10, ChessColor.RED, Direction.EAST), null, null, null, null, null, null, null, null, null, null, new Pawn(12, 10, ChessColor.GREEN, Direction.WEST), new Rook(13, 10, ChessColor.GREEN)},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, new Pawn(3, 12, ChessColor.WHITE, Direction.NORTH), new Pawn(4, 12, ChessColor.WHITE, Direction.NORTH), new Pawn(5, 12, ChessColor.WHITE, Direction.NORTH), new Pawn(6, 12, ChessColor.WHITE, Direction.NORTH), new Pawn(7, 12, ChessColor.WHITE, Direction.NORTH), new Pawn(8, 12, ChessColor.WHITE, Direction.NORTH), new Pawn(9, 12, ChessColor.WHITE, Direction.NORTH), new Pawn(10, 12, ChessColor.WHITE, Direction.NORTH), null, null, null},
				{null, null, null, new Rook(3, 13, ChessColor.WHITE), new Knight(4, 13, ChessColor.WHITE), new Bishop(5,13, ChessColor.WHITE), new Queen(6, 13, ChessColor.WHITE), new King(7, 13, ChessColor.WHITE), new Bishop(8, 13, ChessColor.WHITE), new Knight(9, 13, ChessColor.WHITE), new Rook(10, 13, ChessColor.WHITE), null, null, null}
		};
	}

}