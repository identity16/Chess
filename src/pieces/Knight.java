package pieces;

import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import utils.ChessColor;

import java.util.*;

public class Knight extends ChessPiece {

    public Knight(int x, int y, ChessColor color) {
        super(x, y, color);

        ChessPieceSprite.ChessPieceSpriteType pieceType = null;

        switch(color) {
            case WHITE:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_KNIGHT;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_KNIGHT;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_KNIGHT;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_KNIGHT;
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
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_KNIGHT;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_KNIGHT;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_KNIGHT;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_KNIGHT;
                break;
        }

        setImage(ChessPieceSprite.getInstace().getChessPiece(pieceType));
    }


}