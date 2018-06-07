package rules;

import core.Board;
import core.GameManager;
import core.Player;
import pieces.ChessPiece;
import pieces.King;
import pieces.Pawn;
import pieces.Rook;
import utils.Direction;
import utils.Movement;

public class FourPlayersRule implements Rule {

	@Override
	public boolean IsCheck(Player player) {
		return IsCheck(GameManager.runningGame.getBoard().getStatus(), player);
	}

	@Override
	public boolean IsCheck(ChessPiece[][] status, Player player) {
		GameManager gm = GameManager.runningGame;
        Board board = gm.getBoard();

        int[] kingPosition = null;
        boolean[][] enemyMovable = new boolean[board.getN()][board.getN()];

        for(ChessPiece[] line : status) {
            for(ChessPiece piece : line) {
                if(piece == null) continue;

                if(piece.getColor() == player.getColor()) {
                    if(piece instanceof King)
                        kingPosition = piece.getPosition();
                } else if(piece.getColor() != gm.getAlly(player).getColor()) {
                    	piece.showMovableArea(status, enemyMovable, player);
                }
            }
        }

        if(kingPosition != null)
            return enemyMovable[kingPosition[1]][kingPosition[0]];

		return false;
    }



	@Override
    public boolean IsCheckMate(Player player) {
        GameManager gm = GameManager.runningGame;
        Board board = gm.getBoard();
        ChessPiece[][] status = board.getStatus();

        int[] kingPosition = null;
        boolean[][] enemyMovable;
        boolean[][] allyMovable = new boolean[board.getN()][board.getN()];

        for(ChessPiece[] line : status) {
            for(ChessPiece piece : line) {
                if(piece == null) continue;

                if(piece.getColor() == gm.getCurrentTurn().getColor()
                        || piece.getColor() == gm.getAlly(gm.getCurrentTurn()).getColor()) {
                    piece.showMovableArea(status, allyMovable);
                    if(piece instanceof King)
                        kingPosition = piece.getPosition();
                }
            }
        }

        if(kingPosition == null) return false;

        boolean isStillCheck = true;
        for(int y=0; y<board.getN(); y++) {
            for(int x=0; x<board.getN(); x++) {
                if(allyMovable[y][x]) {
                    status = board.getStatus();
                    enemyMovable = new boolean[board.getN()][board.getN()];
                    board.killPiece(status, status[y][x]);

                    for(ChessPiece[] line : status) {
                        for(ChessPiece piece : line) {
                            if(piece == null) continue;

                            if(piece.getColor() == gm.getCurrentTurn().getColor()
                                    || piece.getColor() == gm.getAlly(gm.getCurrentTurn()).getColor()) {
                                if(piece instanceof King)
                                    kingPosition = piece.getPosition();
                            } else {
                                piece.showMovableArea(status, enemyMovable);
                            }
                        }
                    }

                    if(!enemyMovable[kingPosition[1]][kingPosition[0]]) {
                        isStillCheck = false;
                        break;
                    }
                }
            }
            if(!isStillCheck)
                break;
		}

		return isStillCheck;
    }

    @Override
    public boolean IsStaleMate(Player player) {
        GameManager gm = GameManager.runningGame;
        Board board = gm.getBoard();
        ChessPiece[][] status = board.getStatus();

        boolean[][] allyMovable = new boolean[board.getN()][board.getN()];

        for(ChessPiece[] line : status) {
            for(ChessPiece piece : line) {
                if(piece == null) continue;

                if(piece.getColor() == gm.getCurrentTurn().getColor()
                        || piece.getColor() == gm.getAlly(gm.getCurrentTurn()).getColor()) {
                    piece.showMovableArea(status, allyMovable);
                }
            }
        }

        boolean isMovableAllFalse = true;
        for(boolean[] line : allyMovable) {
            for(boolean b : line) {
                if(b) {
                    isMovableAllFalse = false;
                    break;
                }
            }
        }

        return isMovableAllFalse && !IsCheck(player);
    }



	@Override
	public boolean IsCastling(King king) {
    	if(king.getMoveCount() != 0) return false;

    	Board board = GameManager.runningGame.getBoard();
    	ChessPiece[][] status = board.getStatus();

    	switch (king.getColor()) {
			case RED:
				// Left Rook
				if(status[3][board.getN()-14] instanceof Rook && status[3][board.getN()-14].getMoveCount() == 0)
					return true;

				// Right Rook
				if(status[10][board.getN()-14] instanceof Rook && status[10][board.getN()-14].getMoveCount() == 0)
					return true;

				break;
			case WHITE:
				//Left Rook
				if(status[board.getN()-1][3] instanceof Rook && status[board.getN()-1][3].getMoveCount()==0)
					return true;

				//Right Rook
				if(status[board.getN()-1][10] instanceof Rook && status[board.getN()-1][10].getMoveCount()==0)
					return true;
				break;
			case GREEN:
				//Left Rook
				if(status[10][board.getN()-1] instanceof Rook && status[10][board.getN()-1].getMoveCount() == 0)
					return true;

				// Right Rook
				if(status[3][board.getN()-1] instanceof Rook && status[3][board.getN()-1].getMoveCount() == 0)
					return true;

				break;
			case BLACK:
				//Left Rook
				if(status[board.getN()-14][10] instanceof Rook && status[board.getN()-14][10].getMoveCount()==0)
					return true;

				//Right Rook
				if(status[board.getN()-14][3] instanceof Rook && status[board.getN()-14][3].getMoveCount()==0)
					return true;
				break;
		}

		return false;
	}

	@Override
	public boolean IsPawnPromotion(Movement mv) {
		Direction playerDirection;
    	ChessPiece piece = mv.getChessPiece();
    	if(piece instanceof Pawn) {
    		playerDirection = ((Pawn) piece).getDirection();

	    	switch(playerDirection) {
	    		case EAST :
	    			if(mv.getToPosition()[0]==7)
	    			   return true;
					break;
	    		case SOUTH:
	    			if(mv.getToPosition()[1]==7)
	    			   return true;
					break;
	    		case WEST:
	    			if(mv.getToPosition()[0]==6)
	    			   return true;
					break;
	    		case NORTH:
	    			if(mv.getToPosition()[1]==6)
	    			   return true;
					break;
	    		}

    	}


    	/* ������ �޾ƿ���
    	   1)������ �� -> 8��° �࿡ ���� �����ϴ°�? true -> �� ���� ������ ���ΰ�? true -> return true;
     	   2)������ �� -> 8��° ���� ���� �����ϴ°�? true -> �� ���� ������ ���ΰ�? true -> return true;
    	   3)�ʷϻ� �� -> 7��° �࿡ ���� �����ϴ°�? true -> �� ���� �ʷϻ� ���ΰ�? true -> return true;
    	   4)��� �� ->  7��° ���� ���� �����ϴ°�? true -> �� ���� ��� ���ΰ�? true -> return true;
    	   */
        return false;


	}
}
