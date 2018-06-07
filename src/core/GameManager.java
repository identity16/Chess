package core;

import rules.FourPlayersRule;
import rules.Rule;
import rules.TwoPlayersRule;
import utils.ChessColor;
import utils.Movement;

import javax.swing.*;
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
    private Rule rule;


    // Constructor
    public GameManager(int numOfPlayers) throws Exception {
		runningGame = this;

		this.numOfPlayers = numOfPlayers;
        this.history = new ArrayList<>();
        this.players = new ArrayList<>();


		if(numOfPlayers == 2) {
			this.players.add(new Player(ChessColor.WHITE));
			this.players.add(new Player(ChessColor.BLACK));

			this.board = new TwoPlayersBoard();
			this.rule = new TwoPlayersRule();
		}
		else if(numOfPlayers == 4) {
			this.players.add(new Player(ChessColor.WHITE));
			this.players.add(new Player(ChessColor.RED));
			this.players.add(new Player(ChessColor.BLACK));
			this.players.add(new Player(ChessColor.GREEN));

			this.board = new FourPlayersBoard();
			this.rule = new FourPlayersRule();
		}
		else {
			throw new Exception("Wrong Player Numbers");
		}

		this.turn = this.players.get(0);
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

    public void printTurn() {
		JLabel label_turn = board.getTurnLabel();

		String turn_color;
		if(!turn.isAlive())
			turn_color = players.get((players.indexOf(this.turn) + 2) % numOfPlayers).getColor().toString();
		else
			turn_color = turn.getColor().toString();

		label_turn.setText("<html><font color='" + turn_color + "'>" + turn_color + "</font> TURN</html>");
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

    public int getNumOfPlayers() {
    	return numOfPlayers;
    }

	public Player[] getPlayers() {
		return this.players.toArray(new Player[0]);
	}

	public Rule getRule() {
    	return rule;
	}
}