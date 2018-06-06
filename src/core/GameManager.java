package core;

import utils.ChessColor;
import utils.Movement;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
	public static GameManager runningGame;
	
	private int numOfPlayers;
    private List<Player> players;
    private Player turn;
    private List<Movement> history;
    private Board board;

    // �깮�꽦�옄
    public GameManager(int numOfPlayers) throws Exception {
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
			throw new Exception("Wrong Player Numbers");
		}

		this.turn = this.players.get(0);
		GameManager.runningGame = this;
    }

    // �븘援� �뵆�젅�씠�뼱 由ы꽩
    public Player getAlly(Player player) {
    	if(numOfPlayers == 4)
    		return players.get((players.indexOf(player) + 2) % players.size());
    	else
    		return null;
	}

	// �꽩 蹂�寃�
    public void changeTurn() {
    	int nextIdx = (players.indexOf(this.turn) + 1) % numOfPlayers;

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
    
    public int getNumOfPlayers() {
    	return numOfPlayers;
    }
}