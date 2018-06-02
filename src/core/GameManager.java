package core;

import utils.ChessColor;
import utils.Movement;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

	private int numOfPlayers;
    private List<Player> players;
    private Player turn;
    private List<Movement> history;
    private Board board;

    // 생성자
    public GameManager(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
        this.history = new ArrayList<>();
        this.players = new ArrayList<>();

        if(numOfPlayers == 2) {
			this.players.add(new Player(ChessColor.WHITE));
			this.players.add(new Player(ChessColor.BLACK));

			this.board = new TwoPlayersBoard(this);
		}
		else if(numOfPlayers == 4) {
			this.players.add(new Player(ChessColor.WHITE));
			this.players.add(new Player(ChessColor.RED));
			this.players.add(new Player(ChessColor.BLACK));
			this.players.add(new Player(ChessColor.GREEN));

			this.board = new FourPlayersBoard(this);
		}
		else {
			throw new Error("Wrong Player Numbers");
		}
    }

    // 아군 플레이어 리턴
    public Player getAlly(Player player) {
    	if(numOfPlayers == 4)
    		return players.get((players.indexOf(player) + 2) % players.size());
    	else
    		return null;
	}

	// 턴 변경
    public void changeTurn() {
    	int nextIdx = (players.indexOf(this.turn) + 1) % 2;

        this.turn = players.get(nextIdx);
    }

    public Player getCurrentTurn() {

    	if(turn.isAlive())
        	return turn;
    	else if(getAlly(turn).isAlive())
    		return getAlly(turn);
    	else
    		return null;

    }


    public void killPlayer(Player player) {
    	player.setAlive(false);
    }

    public List<Movement> getHistory() {
        return history;
    }

    public Board getBoard() {
    	return board;
	}
}