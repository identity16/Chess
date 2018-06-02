package core;

import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import kr.ac.cau.mecs.lenerd.chess.ImagePanel;
import pieces.*;
import utils.Movement;

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author 신원준
 *
 */
public abstract class Board extends JPanel {

	private GameManager gameManager;
	protected int width;
	protected int height;
    protected Square[][] squares;
    protected ChessPiece[][] status;

    public Board(GameManager gameManager) {
    	this.gameManager = gameManager;
		setLayout(new GridBagLayout());

		initStatus();
	}

	public abstract void initStatus();

    public ChessPiece[][] getStatus() {
        return status.clone();
    }

    // 보드판 렌더링
    public void renderBoard(Movement[] moves) {
		RepaintManager.currentManager(this).markCompletelyDirty(this);

		for(Movement move : moves) {

			if(move.getType() == Movement.MoveType.MOVE) {
				int[] from = move.getFromPosition();
				int[] to = move.getToPosition();

				squares[from[0]][from[1]].setImage(null);
				squares[to[0]][to[1]].setImage(move.getChessPiece().getImage());

				status[to[0]][to[1]] = status[from[0]][from[1]];
				status[from[0]][from[1]] = null;
			}
			else if(move.getType() == Movement.MoveType.CHANGED) {
				int[] to = move.getToPosition();

				squares[to[0]][to[1]].setImage(move.getChangedPiece().getImage());
				status[to[0]][to[1]] = move.getChangedPiece();
			}
			else {
				return ;
			}

		}

    	for(int i=0; i<width; i++) {
    		for(int j=0; j<height; j++) {
    			if(squares[i][j] == null)
    				continue;

    			if(status[i][j] == null)
    				squares[i][j].setImage(null);

    			squares[i][j].setImage(status[i][j].getImage());
			}
    	}

		RepaintManager.currentManager(this).paintDirtyRegions();
	}

	// 말 이동
	public Movement movePiece(ChessPiece[][] status, ChessPiece piece, int toX, int toY) {
		int[] from = piece.getPosition();

		// 상태 변경
		status[from[0]][from[1]] = null;
		status[toX][toY] = piece;

		return new Movement(piece, from[0], from[1], toX, toY);
    }

    // 말을 보드에서 제거
    public Movement killPiece(ChessPiece[][] status, ChessPiece piece) {
    	int[] piecePosition = piece.getPosition();
    	Square targetSquare = squares[piecePosition[0]][piecePosition[1]];

    	// 삭제
    	targetSquare.removeAll();
    	status[piecePosition[0]][piecePosition[1]] = null;

    	return new Movement(piece, null);
    }

    // 말의 종류를 변경
    public Movement changePiece(ChessPiece[][] status, ChessPiece oldPiece, ChessPiece newPiece) {
		int[] piecePosition = oldPiece.getPosition();

		// 상태 변경
		status[piecePosition[0]][piecePosition[1]] = newPiece;

		return new Movement(oldPiece, newPiece);
    }

}