package core;

import utils.ChessColor;

public class Player {
	private ChessColor color;
	private boolean isAlive;
	private boolean drawRequest;
	private boolean surrenderRequest;

	Player(ChessColor color) {
		this.color = color;
		this.isAlive = true;
		this.drawRequest = false;
		this.surrenderRequest = false;
	}

	public ChessColor getColor() {
		return color;
	}

	boolean isAlive() {
		return isAlive;
	}

	void setAlive(boolean alive) {
		isAlive = alive;
	}

	public boolean getDrawRequest() {
		return drawRequest;
	}

	public boolean getSurrenderRequest() {
		return surrenderRequest;
	}

	public void setDrawRequest(boolean drawRequest) {
		this.drawRequest = drawRequest;
	}

	public void setSurrenderRequest(boolean surrenderRequest) {
		this.surrenderRequest = surrenderRequest;
	}
}
