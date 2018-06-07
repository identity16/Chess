package pieces;

import core.Player;
import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import utils.ChessColor;

import java.util.*;

import core.GameManager;

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
    public boolean[][] showMovableArea(ChessPiece[][] status) {

    	if(GameManager.runningGame.getNumOfPlayers() == 2)
    		return showMovableArea(status, new boolean[8][8]);
    	else
    		return showMovableArea(status, new boolean[14][14]);
    }

    @Override
    public void setColor(ChessColor color) {
        this.color = color;

        ChessPieceSprite.ChessPieceSpriteType pieceType = null;

        switch (color) {
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
	public boolean[][] showMovableArea(ChessPiece[][] status, boolean[][] movableArr) {
		return showMovableArea(status, movableArr, gm.getCurrentTurn());
	}

	@Override
	public boolean[][] showMovableArea(ChessPiece[][] status, boolean[][] movableArr, Player turn) {
    	GameManager gm = GameManager.runningGame;
    	boolean[][] enemyMovable = new boolean[status.length][status.length];

    	int[][] area = {
				{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}
		};

    	// 다른 왕일 때,
    	if(this.getColor() != turn.getColor() &&
				(gm.getNumOfPlayers() == 2 || this.getColor() != gm.getAlly(turn).getColor())) {

    		for(int[] a : area) {
    			int x = this.getPosition()[0] + a[0];
    			int y = this.getPosition()[1] + a[1];

    			if(x < 0 || x >= status.length) continue;
    			if(y < 0 || y >= status.length) continue;

    			movableArr[y][x] = true;
			}
		}
    	else {
			for (ChessPiece[] line : status) {
				for (ChessPiece piece : line) {
					if (piece == null) continue;

					if (isEnemy(piece)) {
						piece.showMovableArea(status, enemyMovable);
					}
				}
			}


			for(int[] a : area) {
				int x = this.getPosition()[0] + a[0];
				int y = this.getPosition()[1] + a[1];

				if(x < 0 || x >= status.length) continue;
				if(y < 0 || y >= status.length) continue;

				if(status[y][x] != null && !isEnemy(status[y][x])) continue;
				if(enemyMovable[y][x]) continue;

				movableArr[y][x] = true;
			}
		}

    	return movableArr;
    }
}