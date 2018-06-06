package pieces;

import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import utils.ChessColor;

import java.util.*;

import core.GameManager;

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
	public boolean[][] showMovableArea(ChessPiece[][] status) {
		
		if(GameManager.runningGame.getNumOfPlayers() == 2)
    		return showMovableArea(status, new boolean[8][8]);
    	else
    		return showMovableArea(status, new boolean[14][14]);
	}
	
	public boolean[][] showMovableArea(ChessPiece[][] status, boolean[][] movableArr) {
		GameManager gm = GameManager.runningGame;
    	ChessPiece selectedPiece = gm.getBoard().getSelectedPiece();
		int[] location = selectedPiece.getPosition();
		int[] saveLocation = selectedPiece.getPosition();
		
		if(gm.getNumOfPlayers() == 2) {
			if(selectedPiece.getColor() == gm.getCurrentTurn().getColor()) {
				//오른쪽 위 대각선 방향
				while((location[1] - 1) >= 0 && (location[0] + 1) <= 7) {
					if(status[location[1] - 1][location[0] + 1] == null)
						movableArr[location[1] - 1][location[0] + 1] = true;
					else if(isEnemy(status[location[1] - 1][location[0] + 1]) == false)
						break;
					else if(isEnemy(status[location[1] - 1][location[0] + 1]) == true) {
						movableArr[location[1] - 1][location[0] + 1] = true;
						break;
					}
					
					location[1]--;
					location[0]++;
				}
				
				location = saveLocation;
				//왼쪽 위 대각선 방향
				while((location[1] - 1) >= 0 && (location[0] - 1) >= 0) {
					if(status[location[1] - 1][location[0] - 1] == null)
						movableArr[location[1] - 1][location[0] - 1] = true;
					else if(isEnemy(status[location[1] - 1][location[0] - 1]) == false)
						break;
					else if(isEnemy(status[location[1] - 1][location[0] - 1]) == true) {
						movableArr[location[1] - 1][location[0] - 1] = true;
						break;
					}
					
					location[1]--;
					location[0]--;
				}
				
				location = saveLocation;
				//오른쪽 아래 대각선 방향
				while((location[1] + 1) <= 7 && (location[0] + 1) <= 7) {
					if(status[location[1] + 1][location[0] + 1] == null)
						movableArr[location[1] + 1][location[0] + 1] = true;
					else if(isEnemy(status[location[1] + 1][location[0] + 1]) == false)
						break;
					else if(isEnemy(status[location[1] + 1][location[0] + 1]) == true) {
						movableArr[location[1] + 1][location[0] + 1] = true;
						break;
					}
					
					location[1]++;
					location[0]++;
				}
				
				location = saveLocation;
				//왼쪽 아래 대각선 방향
				while((location[1] + 1) <= 7 && (location[0] - 1) >= 0) {
					if(status[location[1] + 1][location[0] - 1] == null)
						movableArr[location[1] + 1][location[0] - 1] = true;
					else if(isEnemy(status[location[1] + 1][location[0] - 1]) == false)
						break;
					else if(isEnemy(status[location[1] + 1][location[0] - 1]) == true) {
						movableArr[location[1] + 1][location[0] - 1] = true;
						break;
					}
					
					location[1]++;
					location[0]--;
				}
			}
		}
		else {
			if(selectedPiece.getColor() == gm.getCurrentTurn().getColor()) {
				//오른쪽 위 대각선 방향
				while((location[1] - 1) >= 0 && (location[0] + 1) <= 13) {
					if(status[location[1] - 1][location[0] + 1] == null)
						movableArr[location[1] - 1][location[0] + 1] = true;
					else if(isEnemy(status[location[1] - 1][location[0] + 1]) == false)
						break;
					else if(isEnemy(status[location[1] - 1][location[0] + 1]) == true) {
						movableArr[location[1] - 1][location[0] + 1] = true;
						break;
					}
					
					location[1]--;
					location[0]++;
				}
				
				location = saveLocation;
				//왼쪽 위 대각선 방향
				while((location[1] - 1) >= 0 && (location[0] - 1) >= 0) {
					if(status[location[1] - 1][location[0] - 1] == null)
						movableArr[location[1] - 1][location[0] - 1] = true;
					else if(isEnemy(status[location[1] - 1][location[0] - 1]) == false)
						break;
					else if(isEnemy(status[location[1] - 1][location[0] - 1]) == true) {
						movableArr[location[1] - 1][location[0] - 1] = true;
						break;
					}
					
					location[1]--;
					location[0]--;
				}
				
				location = saveLocation;
				//오른쪽 아래 대각선 방향
				while((location[1] + 1) <= 13 && (location[0] + 1) <= 13) {
					if(status[location[1] + 1][location[0] + 1] == null)
						movableArr[location[1] + 1][location[0] + 1] = true;
					else if(isEnemy(status[location[1] + 1][location[0] + 1]) == false)
						break;
					else if(isEnemy(status[location[1] + 1][location[0] + 1]) == true) {
						movableArr[location[1] + 1][location[0] + 1] = true;
						break;
					}
					
					location[1]++;
					location[0]++;
				}
				
				location = saveLocation;
				//왼쪽 아래 대각선 방향
				while((location[1] + 1) <= 13 && (location[0] - 1) >= 0) {
					if(status[location[1] + 1][location[0] - 1] == null)
						movableArr[location[1] + 1][location[0] - 1] = true;
					else if(isEnemy(status[location[1] + 1][location[0] - 1]) == false)
						break;
					else if(isEnemy(status[location[1] + 1][location[0] - 1]) == true) {
						movableArr[location[1] + 1][location[0] - 1] = true;
						break;
					}
					
					location[1]++;
					location[0]--;
				}
				
			}
		}
		
		return movableArr;
	}
}