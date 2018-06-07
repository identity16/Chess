package utils;

import pieces.ChessPiece;

public class Movement {
	public enum MoveType {
		MOVE, CHANGED
	}

	private MoveType type;
    private ChessPiece chessPiece;
    private ChessPiece changedPiece;
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;

    public Movement(ChessPiece chessPiece, int fromX, int fromY, int toX, int toY) {
		this.type = MoveType.MOVE;
        this.chessPiece = chessPiece;
		this.changedPiece = chessPiece;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    public Movement(ChessPiece oldPiece, ChessPiece newPiece) {
    	this.type = MoveType.CHANGED;
    	this.chessPiece = oldPiece;
    	this.changedPiece = newPiece;
    	if(newPiece != null) {
			this.fromX = this.toX = newPiece.getPosition()[0];
			this.fromY = this.toY = newPiece.getPosition()[1];
		} else {
			this.fromX = this.toX = oldPiece.getPosition()[0];
			this.fromY = this.toY = oldPiece.getPosition()[1];
		}
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
        return new int[] {fromX, fromY};
    }

    public int[] getToPosition() {
		return new int[] {toX, toY};
    }

}