package core;

import utils.ChessColor;

public class Player {
	private ChessColor color;
	private boolean isAlive;

	public Player(ChessColor color) {
		this.color = color;
		this.isAlive = true;
	}

	public ChessColor getColor() {
		return color;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean alive) {
		isAlive = alive;
	}
}
