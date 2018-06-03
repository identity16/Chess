package core;

import kr.ac.cau.mecs.lenerd.chess.ImagePanel;
import pieces.ChessPiece;
import utils.Movement;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Square extends ImagePanel {
	public static final int WIDTH = 50;
	public static final int HEIGHT = 50;


    public static final Color COLOR_DARK = new Color(59, 59, 59);
    public static final Color COLOR_BRIGHT = new Color(238, 238, 238);
    public static final Color COLOR_CLICKED = new Color(119, 136, 153);
    public static final Color COLOR_AVAILABLE = new Color(255, 165, 0);
    public static final Color COLOR_CHECKED = new Color(220, 20, 60);


    private Board board;
    private int pos_x;
    private int pos_y;
    private Color color;

    public Square(Board board, int x, int y, Color color) {
    	this.board = board;
        this.pos_x = x;
        this.pos_y = y;
        this.color = color;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(color);

        addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Square square = (Square)e.getSource();
				Board board = square.getBoard();
				GameManager gameManager = board.getGameManager();

				ChessPiece[][] status = board.getStatus();

				int[] squarePos = square.getPosition();

				ChessPiece piece = status[squarePos[1]][squarePos[0]];

				System.out.println(status[squarePos[1]][squarePos[0]]);
				System.out.println(board.getSelectedPiece());

				// 현재 차례의 말이면
				if(piece != null && piece.getColor() == gameManager.getCurrentTurn().getColor()) {
					// 원래 색으로 복구
					for(Square[] line : board.squares) {
						for(Square s : line) {
							if(s != null && s.getColor() != s.getBackground())
								s.setBackground(s.getColor());
						}
					}

					square.setBackground(Square.COLOR_CLICKED);
					board.setSelectedPiece(piece);
				} else {
					// 선택된 말이 있는 채로 클릭했다면,
					if(board.getSelectedPiece() != null) {
						List<Movement> moves = new ArrayList<>();


						if(piece != null)
							moves.add(board.killPiece(status, piece));

						moves.add(board.movePiece(status, board.getSelectedPiece(), square.pos_x, square.pos_y));

						board.renderBoard(moves);
					}

					// 원래 색으로 복구
					for(Square[] line : board.squares) {
						for(Square s : line) {
							if(s != null)
								s.setBackground(s.getColor());
						}
					}

					board.setSelectedPiece(null);
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Color color = ((Square)e.getSource()).getBackground();
				setBackground(new Color(color.getRed(), color.getBlue(), color.getGreen(), 150));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Color color = ((Square)e.getSource()).getBackground();
				setBackground(new Color(color.getRed(), color.getBlue(), color.getGreen(), 255));
			}
		});
    }

	public Board getBoard() {
		return board;
	}

	public int[] getPosition() {
        return new int[] {pos_x, pos_y};
    }

    public Color getColor() {
        return color;
    }

}