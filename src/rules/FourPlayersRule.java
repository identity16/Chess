package rules;

import core.Board;
import core.GameManager;
import core.Player;
import pieces.ChessPiece;
import pieces.King;

public class FourPlayersRule implements Rule {

    @Override
    public boolean IsCheck(Player player) {
        GameManager gm = GameManager.runningGame;
        Board board = gm.getBoard();
        ChessPiece[][] status = board.getStatus();

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