package core;

import kr.ac.cau.mecs.lenerd.chess.ImagePanel;
import pieces.ChessPiece;
import utils.Movement;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.ArrayList;

public class Square extends ImagePanel {

    public static final Color COLOR_DARK = new Color(59, 59, 59);
    public static final Color COLOR_BRIGHT = new Color(238, 238, 238);
    private static final Color COLOR_CLICKED = new Color(119, 136, 153);
    public static final Color COLOR_MOVABLE = new Color(255, 165, 0);
    public static final Color COLOR_CHECKED = new Color(220, 20, 60);


    private int pos_x;
    private int pos_y;
    private Color color;

    public Square(int x, int y, int len, Color color) {
        this.pos_x = x;
        this.pos_y = y;
        this.color = color;

        setPreferredSize(new Dimension(len, len));
        setBackground(color);

        addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

				GameManager gameManager = GameManager.runningGame;
				Board board = gameManager.getBoard();
				Square square = (Square)e.getSource();

				ChessPiece[][] status = board.getStatus();

				int[] squarePos = square.getPosition();

				ChessPiece piece = status[squarePos[1]][squarePos[0]];

				// 현재 차례의 말이면
				if(piece != null && piece.getColor() == gameManager.getCurrentTurn().getColor()) {
					// 원래 색으로 복구
					for(Square[] line : board.squares) {
						for(Square s : line) {
							if(s != null && s.getColor() != s.getBackground())
								s.setBackground(s.getColor());
						}
					}



					if(piece != board.getSelectedPiece()) {
						square.setBackground(Square.COLOR_CLICKED);
						board.setSelectedPiece(piece);

						boolean[][] movableArea = piece.showMovableArea(board.getStatus());


						for(int y=0; y<movableArea.length; y++) {
							for(int x=0; x<movableArea[y].length; x++)  {
								if(movableArea[y][x] && board.squares[y][x] != null) {
									board.squares[y][x].setBackground(Square.COLOR_MOVABLE);
								}
							}
						}
					} else {
						board.setSelectedPiece(null);
					}
				} else {
					// 선택된 말이 있는 채로 클릭했다면,
					if(board.getSelectedPiece() != null) {
						if(getBackground() == COLOR_MOVABLE) {

							List<Movement> moves = new ArrayList<>();

							if (piece != null)
								moves.add(board.killPiece(status, piece));

							moves.add(board.movePiece(status, board.getSelectedPiece(), square.pos_x, square.pos_y));

							board.renderBoard(moves);
						}
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


			// 마우스 올려져 있으면 반투명화
			@Override
			public void mouseEntered(MouseEvent e) {
				Color color = ((Square)e.getSource()).getBackground();
				if(color != COLOR_MOVABLE)
					setBackground(new Color(color.getRed(), color.getBlue(), color.getGreen(), 150));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Color color = ((Square)e.getSource()).getBackground();
				if(color != COLOR_MOVABLE)
					setBackground(new Color(color.getRed(), color.getBlue(), color.getGreen(), 255));
			}
		});
    }

	public int[] getPosition() {
        return new int[] {pos_x, pos_y};
    }

	public Color getColor() {
        return color;
    }

}