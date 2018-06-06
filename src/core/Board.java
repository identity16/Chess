package core;

import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import kr.ac.cau.mecs.lenerd.chess.ImagePanel;
import pieces.*;
import utils.Movement;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 *
 * @author �떊�썝以�
 *
 */
public abstract class Board extends JPanel {
	
	private GameManager gameManager;
	protected int width;
	protected int height;
    protected Square[][] squares;
    protected ChessPiece[][] status;

	private ChessPiece selectedPiece;

    public Board(GameManager gameManager) {
    	this.gameManager = gameManager;
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
		
		initStatus();
	}

	public abstract void initStatus();



	// 蹂대뱶�뙋 �젋�뜑留�
    public void renderBoard(List<Movement> moves) {
		RepaintManager.currentManager(this).markCompletelyDirty(this);

		for(Movement move : moves) {
			if(move.getType() == Movement.MoveType.MOVE) {
				int[] from = move.getFromPosition();
				int[] to = move.getToPosition();

				squares[from[1]][from[0]].setImage(null);
				squares[to[1]][to[0]].setImage(move.getChessPiece().getImage());

				status[to[1]][to[0]] = move.getChessPiece();
				status[from[1]][from[0]] = null;

				move.getChessPiece().setPosition(to[0], to[1]);
			}
			else if(move.getType() == Movement.MoveType.CHANGED) {
				int[] to = move.getToPosition();

				if(move.getChangedPiece() != null)
					squares[to[1]][to[0]].setImage(move.getChangedPiece().getImage());

				status[to[1]][to[0]] = move.getChangedPiece();
			}
			else {
				return ;
			}
		}


		RepaintManager.currentManager(this).paintDirtyRegions();

		gameManager.changeTurn();
		System.out.println("Turn : " + gameManager.getCurrentTurn().getColor());
	}

	// 留� �씠�룞
	public Movement movePiece(ChessPiece[][] status, ChessPiece piece, int toX, int toY) {
		int[] from = piece.getPosition();

		// �긽�깭 蹂�寃�
		status[from[1]][from[0]] = null;
		status[toY][toX] = piece;

		return new Movement(piece, from[0], from[1], toX, toY);
    }

    // 留먯쓣 蹂대뱶�뿉�꽌 �젣嫄�
    public Movement killPiece(ChessPiece[][] status, ChessPiece piece) {
    	int[] piecePosition = piece.getPosition();
    	Square targetSquare = squares[piecePosition[1]][piecePosition[0]];

    	// �궘�젣
    	targetSquare.removeAll();
    	status[piecePosition[1]][piecePosition[0]] = null;

    	return new Movement(piece, null);
    }

    // 留먯쓽 醫낅쪟瑜� 蹂�寃�
    public Movement changePiece(ChessPiece[][] status, ChessPiece oldPiece, ChessPiece newPiece) {
		int[] piecePosition = oldPiece.getPosition();

		// �긽�깭 蹂�寃�
		status[piecePosition[1]][piecePosition[0]] = newPiece;

		return new Movement(oldPiece, newPiece);
    }


	public ChessPiece[][] getStatus() {
		return status.clone();
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public ChessPiece getSelectedPiece() {
		return selectedPiece;
	}

	public void setSelectedPiece(ChessPiece selectedPiece) {
		this.selectedPiece = selectedPiece;
	}

}