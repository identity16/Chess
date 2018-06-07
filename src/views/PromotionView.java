package views;

import core.Board;
import core.GameManager;
import pieces.*;
import utils.Movement;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.List;

public class PromotionView extends JPanel {
	private ChessPiece target;
	private List<Movement> moves;
	private ChessPiece[][] status;

	public PromotionView(ChessPiece target, ChessPiece[][] status, List<Movement> moves) {
		this.target = target;
		this.moves = moves;
		this.status = status;

		JButton Queen = new JButton("Queen");
		JButton Rook = new JButton("Rook");
		JButton Bishop = new JButton("Bishop");
		JButton Knight = new JButton("Knight");


		JPanel panel = new JPanel();
		panel.add(Queen);
		panel.add(Rook);
		panel.add(Bishop);
		panel.add(Knight);

		panel.setLayout(new GridLayout(2, 2));
		add(panel);

		ListenerClass listener = new ListenerClass();
		Queen.addActionListener(listener);
		Rook.addActionListener(listener);
		Bishop.addActionListener(listener);
		Knight.addActionListener(listener);

	}

	public List<Movement> getMoves() {
		return moves;
	}

	public ChessPiece getTarget() {
		return target;
	}

	public ChessPiece[][] getStatus() {
		return status;
	}
}

class ListenerClass implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		PromotionView v = ((PromotionView) ((JButton)e.getSource()).getParent().getParent());
		ChessPiece target = v.getTarget();
		//1. 말을 퀸으로 바꾸고
		Board board = GameManager.runningGame.getBoard();
		ChessPiece newPiece;

		Movement prevMove = v.getMoves().get(v.getMoves().size()-1);

		switch (e.getActionCommand()) {
			case "Queen":
				newPiece = new Queen(prevMove.getToPosition()[0], prevMove.getToPosition()[1], target.getColor());
				break;
			case "Rook":
				newPiece = new Rook(prevMove.getToPosition()[0], prevMove.getToPosition()[1], target.getColor());
				break;
			case "Bishop":
				newPiece = new Bishop(prevMove.getToPosition()[0], prevMove.getToPosition()[1], target.getColor());
				break;
			default:
				newPiece = new Knight(prevMove.getToPosition()[0], prevMove.getToPosition()[1], target.getColor());
				break;
		}

		v.getMoves().add(board.changePiece(v.getStatus(), target, newPiece));

		//2. 보드에 표시
		board.renderBoard(v.getMoves());

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(v);
		frame.getGlassPane().setVisible(false);
	}
}