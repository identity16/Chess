package core;

import utils.ChessColor;

public class Player {
	private ChessColor color;
	private boolean isAlive;

	Player(ChessColor color) {
		this.color = color;
		this.isAlive = true;
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
}
