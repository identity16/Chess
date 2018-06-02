package utils;

import pieces.ChessPiece;

public class Movement {
	public enum MoveType {
		MOVE, CHANGED
	}


	private MoveType type;
    private ChessPiece chessPiece;
    private ChessPiece changedPiece;
    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;

    public Movement(ChessPiece chessPiece, int fromRow, int fromCol, int toRow, int toCol) {
		this.type = MoveType.MOVE;
        this.chessPiece = chessPiece;
		this.changedPiece = chessPiece;
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
    }

    public Movement(ChessPiece oldPiece, ChessPiece newPiece) {
    	this.type = MoveType.CHANGED;
    	this.chessPiece = oldPiece;
    	this.changedPiece = newPiece;
		this.fromRow = this.toRow = oldPiece.getPosition()[0];
    	this.fromCol = this.toCol = oldPiece.getPosition()[1];
	}

	public MoveType getType() {
    	return this.type;
	}

    public ChessPiece getChessPiece() {
        return this.chessPiece;
    }

    public ChessPiece getChangedPiece() {
    	return this.changedPiece;
	}

    public int[] getFromPosition() {
        return new int[] {fromRow, fromCol};
    }

    public int[] getToPosition() {
        // TODO implement here
		return new int[] {toRow, toCol};
    }

}