package views;

import core.Board;
import core.GameManager;
import pieces.*;
import utils.Movement;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PromotionView extends JPanel {
	private ChessPiece target;

	public PromotionView(ChessPiece target) {
		this.target = target;
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
	public ChessPiece getTarget() {
		return target;
	}

}

class ListenerClass implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		PromotionView v = ((PromotionView) ((JButton)e.getSource()).getParent().getParent());
		ChessPiece target = v.getTarget();
		//1. 말을 퀸으로 바꾸고
		Board board = GameManager.runningGame.getBoard();
		ChessPiece newPiece;

		switch (e.getActionCommand()) {
			case "Queen":
				newPiece = new Queen(target.getPosition()[0], target.getPosition()[1], target.getColor());
				break;
			case "Rook":
				newPiece = new Rook(target.getPosition()[0], target.getPosition()[1], target.getColor());
				break;
			case "Bishop":
				newPiece = new Bishop(target.getPosition()[0], target.getPosition()[1], target.getColor());
				break;
			default:
				newPiece = new Knight(target.getPosition()[0], target.getPosition()[1], target.getColor());
				break;
		}

		List<Movement> list = new ArrayList<>();
		list.add(board.changePiece(board.getStatus(), target, newPiece));

		//2. 보드에 표시
		board.renderBoard(list);
		board.renderBoard(new ArrayList<>());

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(v);
		frame.getGlassPane().setVisible(false);
	}
}