package core;

import pieces.ChessPiece;
import utils.ChessColor;
import utils.Movement;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
	// Fields
	public static GameManager runningGame;

	private int numOfPlayers;
    private List<Player> players;
    private Player turn;
    private List<Movement> history;
    private Board board;


    // Constructor
    public GameManager(int numOfPlayers) throws Exception {
		this.numOfPlayers = numOfPlayers;
        this.history = new ArrayList<>();
        this.players = new ArrayList<>();

        if(numOfPlayers == 2) {
			this.players.add(new Player(ChessColor.WHITE));
			this.players.add(new Player(ChessColor.BLACK));

			this.board = new TwoPlayersBoard();
		}
		else if(numOfPlayers == 4) {
			this.players.add(new Player(ChessColor.WHITE));
			this.players.add(new Player(ChessColor.RED));
			this.players.add(new Player(ChessColor.BLACK));
			this.players.add(new Player(ChessColor.GREEN));

			this.board = new FourPlayersBoard();
		}
		else {
			throw new Exception("Wrong Player Numbers");
		}

		this.turn = this.players.get(0);
		GameManager.runningGame = this;
    }

    // Get My Team Player
    public Player getAlly(Player player) {
    	if(numOfPlayers == 4)
    		return players.get((players.indexOf(player) + 2) % players.size());
    	else
    		return null;
	}

	// Change turn to next player
    public void changeTurn() {
    	int nextIdx = (players.indexOf(this.turn) + 1) % numOfPlayers;

        this.turn = players.get(nextIdx);
    }

    // Return Current Turn Player
    public Player getCurrentTurn() {

    	if(turn.isAlive())
        	return turn;
    	else if(numOfPlayers == 4 && getAlly(turn).isAlive())
    		return getAlly(turn);
    	else
    		return null;

    }

    // Get A Player Of Specific Color
    public Player getPlayer(ChessColor color) {
    	for(Player p : this.players) {
    		if(p.getColor() == color)
    			return p;
		}
    	return null;
	}

	// Set Player to Dead
    public void killPlayer(Player player) {
    	player.setAlive(false);
    }

    // Getter
    public List<Movement> getHistory() {
        return history;
    }

    public Board getBoard() {
    	return board;
	}

	public Player[] getPlayers() {
		return this.players.toArray(new Player[0]);
	}
}