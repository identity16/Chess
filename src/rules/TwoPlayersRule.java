package rules;

import core.Board;
import core.GameManager;
import core.Player;
import pieces.ChessPiece;
import pieces.King;

import pieces.Pawn;
import utils.ChessColor;
import utils.Movement;

import java.util.List;

/**
 * @author 강소정
 */

public class TwoPlayersRule implements Rule {

	@Override
	public boolean IsCheck(Player player) {
		return IsCheck(GameManager.runningGame.getBoard().getStatus(), player);
	}

	@Override
	public boolean IsCheck(ChessPiece[][] status, Player player) {
		Board board = GameManager.runningGame.getBoard();

		int[] kingPosition = null;
		boolean[][] enemyMovable = new boolean[board.getN()][board.getN()];

		for(ChessPiece[] line : status) {
			for(ChessPiece piece : line) {
				if(piece == null) continue;

				if(piece.getColor() == player.getColor()) {
					if(piece instanceof King)
						kingPosition = piece.getPosition();
				} else {
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
		if(!IsCheck(player)) return false;

		Board board = GameManager.runningGame.getBoard();
		ChessPiece[][] status = board.getStatus();

		boolean[][] allyMovable = new boolean[board.getN()][board.getN()];

		for (ChessPiece[] line : status) {
			for (ChessPiece piece : line) {
				if (piece == null) continue;

				if (piece.getColor() == player.getColor()) {
					piece.showMovableArea(status, allyMovable);
				}
			}
		}

		boolean isAllFalse = true;

		for(boolean[] line : allyMovable) {
			for(boolean b : line) {
				if(b) {
					isAllFalse = false;
					break;
				}
			}
		}

        return isAllFalse;
    }

    @Override
    public boolean IsStaleMate(Player player) {
		if(IsCheck(player)) return false;

		Board board = GameManager.runningGame.getBoard();
		ChessPiece[][] status = board.getStatus();

		boolean[][] allyMovable = new boolean[board.getN()][board.getN()];

		for (ChessPiece[] line : status) {
			for (ChessPiece piece : line) {
				if (piece == null) continue;

				if (piece.getColor() == player.getColor()) {
					piece.showMovableArea(status, allyMovable);
				}
			}
		}

		boolean isAllFalse = true;

		for(boolean[] line : allyMovable) {
			for(boolean b : line) {
				if(b) {
					isAllFalse = false;
					break;
				}
			}
		}

		return isAllFalse;
    }

    

    @Override
    public boolean IsPawnPromotion(Movement mv) {
		ChessColor color = GameManager.runningGame.getCurrentTurn().getColor();
		if(mv.getChessPiece() instanceof Pawn) {
			switch(color) {
				//WHITE_PAWN의 toY 값이 0일 때랑, BLACK_PAWN의 toY값이 7일 때
				case WHITE:
					if(mv.getToPosition()[1] == 0)
						return true;
					break;
				case BLACK:
					if(mv.getToPosition()[1] == 7)
						return true;
					break;
			}
		}

		return false;
    }


	@Override
	public boolean IsCastling(King king) {
		// TODO Auto-generated method stub
		return false;
	}

	public int[] IsEnpassant(Pawn pawn) {
		ChessPiece[][] status = GameManager.runningGame.getBoard().getStatus();
		int[] myPosition = pawn.getPosition();
		int[] resultPosition = new int[2];
		resultPosition[1] = myPosition[1];

		// Left
		resultPosition[0] = myPosition[0]-1;

		if(resultPosition[0] >= 0) {
			ChessPiece target = status[resultPosition[1]][resultPosition[0]];
			if (target instanceof Pawn && (target.getColor() != pawn.getColor()) && target.getMoveCount() == 0) {
				List<Movement> history = GameManager.runningGame.getHistory();

				// 직전에 움직인 폰이 아닌 경우
				if(history.get(history.size()-1).getChessPiece() != status[resultPosition[1]][resultPosition[0]])
					return null;

				if(target.getColor() == ChessColor.WHITE && resultPosition[1] == 4)
					return resultPosition;

				if(target.getColor() == ChessColor.BLACK && resultPosition[1] == 3)
					return resultPosition;
			}
		}

		// Right
		resultPosition[0] = myPosition[0]+1;

		if(resultPosition[0] <= 7) {
			ChessPiece target = status[resultPosition[1]][resultPosition[0]];
			if (target instanceof Pawn && (target.getColor() != pawn.getColor()) && target.getMoveCount() == 0) {
				List<Movement> history = GameManager.runningGame.getHistory();

				// 직전에 움직인 폰이 아닌 경우
				if(history.get(history.size()-1).getChessPiece() != status[resultPosition[1]][resultPosition[0]])
					return null;

				if(target.getColor() == ChessColor.WHITE && resultPosition[1] == 4)
					return resultPosition;

				if(target.getColor() == ChessColor.BLACK && resultPosition[1] == 3)
					return resultPosition;
			}
		}

		return null;
	}
   
}

    