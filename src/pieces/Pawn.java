package pieces;

import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import utils.ChessColor;
import utils.Direction;

import java.util.*;

public class Pawn extends ChessPiece {

    private Direction direction;

    public Pawn(int x, int y, ChessColor color, Direction direction) {
        super(x, y, color);
        this.direction = direction;

        ChessPieceSprite.ChessPieceSpriteType pieceType = null;

        switch(color) {
            case WHITE:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_PAWN;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_PAWN;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_PAWN;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_PAWN;
                break;
        }

        setImage(ChessPieceSprite.getInstace().getChessPiece(pieceType));
    }

    @Override
    public ArrayList<boolean[]> showMovableArea(ChessPiece[][] status) {
        return null;
    }

    @Override
    public void setColor(ChessColor color) {
        this.color = color;

        ChessPieceSprite.ChessPieceSpriteType pieceType = null;

        switch (color) {
            case WHITE:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_PAWN;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_PAWN;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_PAWN;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_PAWN;
                break;
        }

        setImage(ChessPieceSprite.getInstace().getChessPiece(pieceType));
    }

	public Direction getDirection() {
		return direction;
	}
    
    
}