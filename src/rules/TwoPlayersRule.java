package rules;

import core.Board;
import core.GameManager;
import core.Player;
import pieces.ChessPiece;
import pieces.King;

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
        return false;
    }

    @Override
    public boolean IsStaleMate(Player player) {
        return false;
    }

    @Override
    public boolean IsCastling() {
        return false;
    }

    @Override
    public boolean IsPawnPromotion() {
        return false;
    }

    @Override
    public boolean IsEnpassant() {
        return false;
    }
}