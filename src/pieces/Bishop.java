package pieces;

import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import utils.ChessColor;

import java.util.*;

public class Bishop extends ChessPiece {

	public Bishop(int x, int y, ChessColor color) {
		super(x, y, color);

		ChessPieceSprite.ChessPieceSpriteType pieceType = null;

		switch (color) {
			case WHITE:
				pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_BISHOP;
				break;
			case RED:
				pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_BISHOP;
				break;
			case BLACK:
				pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_BISHOP;
				break;
			case GREEN:
				pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_BISHOP;
				break;
		}

		setImage(ChessPieceSprite.getInstace().getChessPiece(pieceType));
	}

	@Override
	public ArrayList<boolean[]> showMovableArea(ChessPiece[][] status) {
		return null;
	}
}