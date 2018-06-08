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

import java.util.ArrayList;
import java.util.List;

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
		ChessPiece king = null;

		boolean[][] allyMovable = new boolean[board.getN()][board.getN()];

		for(ChessPiece[] line : status) {
			for(ChessPiece piece : line) {
				if(piece == null) continue;



				if(piece.getColor() == player.getColor()) {
					if(piece instanceof King)
						king = piece;
					piece.showMovableArea(status, allyMovable);
				}
			}
		}

		if(king != null) {
			king.showMovableArea(status, allyMovable);
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

		return isMovableAllFalse && IsCheck(player);
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

                if(piece.getColor() == player.getColor()) {
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
	public List<int[][]> IsCastling(King king) {
    	if(king.getMoveCount() != 0) return null;
    	int CastleKing[][];
		List<int[][]> list = new ArrayList<>();

    	Board board = GameManager.runningGame.getBoard();

    	ChessPiece[][] status = board.getStatus();
    	if(!IsCheck(GameManager.runningGame.getPlayer(king.getColor()))) {
			switch (king.getColor()) {
				case RED:
					// Left Rook
					if(status[3][board.getN()-14] instanceof Rook && status[3][board.getN()-14].getMoveCount() == 0 )
						if(status[7][board.getN()-14] instanceof King && status[7][board.getN()-14].getMoveCount() == 0)
							if(status[4][0]==null && status[5][0]==null && status[6][0]==null) {
								CastleKing = new int[][] {{0,5},{0,6}};
								/*if(getChessPiece().getPosition()[1]==0)*/
								list.add(CastleKing);
							}

					// Right Rook
					if(status[10][board.getN()-14] instanceof Rook && status[10][board.getN()-14].getMoveCount() == 0)
						if(status[7][board.getN()-14] instanceof King && status[7][board.getN()-14].getMoveCount() == 0)
							if(status[8][0]==null && status[9][0]==null) {
								CastleKing = new int[][] {{0,9},{0,8}};
								list.add(CastleKing);
							}

					break;
				case WHITE:
					//Left Rook
					if(status[board.getN()-1][3] instanceof Rook && status[board.getN()-1][3].getMoveCount()== 0)
						if(status[board.getN()-1][7] instanceof King && status[board.getN()-1][7].getMoveCount() == 0)
							if(status[13][4]==null && status[13][5]==null && status[13][6]==null) {
								CastleKing = new int[][] {{5,13},{6,13}};
								list.add(CastleKing);
							}

					//Right Rook
					if(status[board.getN()-1][10] instanceof Rook && status[board.getN()-1][10].getMoveCount()== 0)
						if(status[board.getN()-1][7] instanceof King && status[board.getN()-1][7].getMoveCount() == 0)
							if(status[13][8]==null && status[13][9]==null) {
								CastleKing = new int[][] {{9,13},{8,13}};
								list.add(CastleKing);
							}

					break;
				case GREEN:
					//Left Rook
					if(status[10][board.getN()-1] instanceof Rook && status[10][board.getN()-1].getMoveCount() == 0)
						if(status[6][board.getN()-1] instanceof King && status[6][board.getN()-1].getMoveCount() == 0)
							if(status[7][13]==null && status[8][13]==null && status[9][13]==null){
								CastleKing = new int[][] {{13,8},{13,7}};
								list.add(CastleKing);
							}

					// Right Rook
					if(status[3][board.getN()-1] instanceof Rook && status[3][board.getN()-1].getMoveCount() == 0)
						if(status[6][board.getN()-1] instanceof King && status[6][board.getN()-1].getMoveCount() == 0)
							if(status[4][13]==null && status[5][13]==null) {
								CastleKing = new int[][] {{13,4},{13,5}};
								list.add(CastleKing);
							}

					break;
				case BLACK:
					//Left Rook
					if(status[board.getN()-14][10] instanceof Rook && status[board.getN()-14][10].getMoveCount()==0)
						if(status[board.getN()-14][6] instanceof King && status[board.getN()-14][6].getMoveCount() == 0)
							if(status[0][7]==null && status[0][8]==null && status[0][9]==null) {
								CastleKing = new int[][] {{8,0},{7,0}};
								list.add(CastleKing);

							}

					//Right Rook
					if(status[board.getN()-14][3] instanceof Rook && status[board.getN()-14][3].getMoveCount()==0)
						if(status[board.getN()-14][6] instanceof King && status[board.getN()-14][6].getMoveCount() == 0)
							if(status[0][4]==null && status[0][5]==null) {
								CastleKing = new int[][] {{4,0},{5,0}};
								list.add(CastleKing);
							}
					break;
			}
    	}

		return list;
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
