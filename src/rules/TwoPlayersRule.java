package rules;

import core.Board;
import core.GameManager;
import core.Player;
import pieces.ChessPiece;
import pieces.King;

import pieces.Pawn;
import utils.ChessColor;
import utils.Movement;

/**
 * @author 강소정
 */

public class TwoPlayersRule implements Rule {

    @Override
    public boolean IsCheck(Player player) {
		Board board = GameManager.runningGame.getBoard();
		ChessPiece[][] status = board.getStatus();

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
		// TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean IsStaleMate(Player player) {
		// TODO Auto-generated method stub
        return false;
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
   
}

    