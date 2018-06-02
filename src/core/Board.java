package core;

import kr.ac.cau.mecs.lenerd.chess.ImagePanel;
import pieces.*;
import utils.Movement;

import javax.swing.*;


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
	}



    public Square[][] getSquares() {
        return squares;
    }

    public ChessPiece[][] getStatus() {
        return status;
    }

    public void movePiece(Movement move) {
        ChessPiece piece = move.getChessPiece();
        int[] from = move.getFromPosition();
        int[] to = move.getToPosition();

        Square fromSquare = squares[from[0]][from[1]];
        Square toSquare = squares[to[0]][to[1]];

        if(fromSquare.getComponentCount() > 0) {
        	// GUI 상에서의 이동
			fromSquare.removeAll();
			toSquare.removeAll();

			ImagePanel piecePanel = new ImagePanel();
			piecePanel.setImage(piece.getImage());

			toSquare.add(piecePanel);

			// 상태 변경
			status[from[0]][from[1]] = null;
			status[to[0]][to[1]] = piece;

			// history 기록
			gameManager.getHistory().add(move);
		} else {
        	throw new Error("No Component In This Square!");
		}
    }

    // 말을 보드에서 제거
    public void killPiece(ChessPiece piece) {
    	int[] piecePosition = piece.getPosition();
    	Square targetSquare = squares[piecePosition[0]][piecePosition[1]];

    	// 삭제
    	targetSquare.removeAll();
    	status[piecePosition[0]][piecePosition[1]] = null;

    	// history 기록
    	gameManager.getHistory().add(new Movement(piece, null));
    }

    // 말의 종류를 변경
    public void changePiece(ChessPiece oldPiece, ChessPiece newPiece) {
    	int[] piecePosition = oldPiece.getPosition();
		Square targetSquare = squares[piecePosition[0]][piecePosition[1]];

		// 삭제
		targetSquare.removeAll();

		// 새 패널 추가
		ImagePanel newPiecePanel = new ImagePanel();
		newPiecePanel.setImage(newPiece.getImage());

		targetSquare.add(newPiecePanel);

		// 상태 변경
		status[piecePosition[0]][piecePosition[1]] = newPiece;

		// history 기록
        gameManager.getHistory().add(new Movement(oldPiece, newPiece));

    }

}