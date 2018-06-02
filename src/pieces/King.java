package pieces;

import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import utils.ChessColor;

import java.util.*;

public class King extends ChessPiece {

    public King(int x, int y, ChessColor color) {
        super(x, y, color);
        ChessPieceSprite.ChessPieceSpriteType pieceType = null;

        switch(color) {
            case WHITE:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_KING;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_KING;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_KING;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_KING;
                break;
        }

        setImage(ChessPieceSprite.getInstace().getChessPiece(pieceType));
    }
    @Override
    public ArrayList<int[]> showMovableArea(int row, int column, ChessPiece[] board) {
        return null;
    }

    public void checkEnemyZone(ChessPiece[] board) {
        // TODO implement here
    }


}