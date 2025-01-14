package core;

import kr.ac.cau.mecs.lenerd.chess.ImagePanel;
import pieces.ChessPiece;
import pieces.King;
import utils.ChessColor;
import utils.Movement;
import views.PromotionView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Square extends ImagePanel {

    public static final Color COLOR_DARK = new Color(59, 59, 59);
    public static final Color COLOR_BRIGHT = new Color(238, 238, 238);
    private static final Color COLOR_CLICKED = new Color(119, 136, 153);
    public static final Color COLOR_MOVABLE = new Color(255, 165, 0);
    public static final Color COLOR_CHECKED = new Color(220, 20, 60);
	public static final Color COLOR_CASTLING = new Color(120, 20, 60);


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

				GameManager gm = GameManager.runningGame;
				Board board = gm.getBoard();
				Square square = (Square)e.getSource();

				ChessPiece[][] status = board.getStatus();

				int[] squarePos = square.getPosition();

				ChessPiece piece = status[squarePos[1]][squarePos[0]];
				List<ChessColor> checkedColor = new ArrayList<>();

				List<int[][]> castlingArea = null;

				// 현재 차례의 말이면
				if(piece != null && piece.getColor() == gm.getCurrentTurn().getColor()) {

					for(Player player : gm.getPlayers()) {
						if(gm.getRule().IsCheck(player)) {
							checkedColor.add(player.getColor());
						}
					}

					// 원래 색으로 복구
					for(Square[] line : board.getSquares()) {
						for(Square s : line) {
							if(s != null) {
								ChessPiece p = status[s.getPosition()[1]][s.getPosition()[0]];

								if(p instanceof King) {
									if(checkedColor.contains(p.getColor()))
										s.setBackground(COLOR_CHECKED);
								}
								else {
									s.setBackground(s.getColor());
								}
							}
						}
					}



					if(piece != board.getSelectedPiece()) {
						square.setBackground(Square.COLOR_CLICKED);
						board.setSelectedPiece(piece);


						if(board.getSelectedPiece() instanceof King)
							castlingArea = gm.getRule().IsCastling((King) board.getSelectedPiece());

						boolean[][] movableArea = piece.showMovableArea(board.getStatus());


						for(int y=0; y<movableArea.length; y++) {
							for(int x=0; x<movableArea[y].length; x++)  {
								if(movableArea[y][x] && board.getSquares()[y][x] != null) {

									if(board.getStatus()[y][x] instanceof King) {
										if(checkedColor.contains(board.getStatus()[y][x].getColor()))
											board.getSquares()[y][x].setBackground(Square.COLOR_CHECKED);
									}
									else {
										board.getSquares()[y][x].setBackground(Square.COLOR_MOVABLE);
										if(castlingArea != null) {
											for (int[][] ca : castlingArea) {
												if (Arrays.equals(ca[0], board.getSquares()[y][x].getPosition()))
													board.getSquares()[y][x].setBackground(COLOR_CASTLING);
											}
										}
									}
								}
							}
						}
					} else {
						board.setSelectedPiece(null);
					}
				} else {
					// 선택된 말이 있는 채로 클릭했다면,
					if(board.getSelectedPiece() != null) {

						if(board.getSelectedPiece() instanceof King)
							castlingArea = gm.getRule().IsCastling((King) board.getSelectedPiece());

						if(getBackground() == COLOR_MOVABLE || getBackground() == COLOR_CHECKED) {

							List<Movement> moves = new ArrayList<>();

							if (piece != null)
								moves.add(board.killPiece(status, piece));

							Movement move = board.movePiece(status, board.getSelectedPiece(), square.pos_x, square.pos_y);
							moves.add(move);

							if(gm.getRule().IsPawnPromotion(move)) {
								JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(board);
								frame.setGlassPane(new PromotionView(move.getChessPiece(), status, moves));
								frame.getGlassPane().setVisible(true);
							} else {
								board.renderBoard(moves);
							}

						} else if(getBackground() == COLOR_CASTLING) {

							if(castlingArea != null) {
								for(int[][] ca : castlingArea) {
									if(Arrays.equals(ca[0], square.getPosition())) {
										board.getSquares()[y][x].setBackground(COLOR_CASTLING);

										List<Movement> moves = new ArrayList<>();

										Movement move = board.movePiece(status, board.getSelectedPiece(), square.pos_x, square.pos_y);
										moves.add(move);

										ChessPiece castling_rook = null;
										ChessPiece selectedPiece = board.getSelectedPiece();

										switch (board.getSelectedPiece().getColor()) {
											case BLACK:
												if(gm.getNumOfPlayers() == 2) {
													if(ca[0][0] < selectedPiece.getPosition()[0])
														castling_rook = status[0][0];
													else
														castling_rook = status[0][7];
												} else {
													if(ca[0][0] < selectedPiece.getPosition()[0])
														castling_rook = status[0][3];
													else
														castling_rook = status[0][10];
												}
												break;
											case RED:
												if(ca[0][1] < selectedPiece.getPosition()[1])
													castling_rook = status[3][0];
												else
													castling_rook = status[10][0];
												break;
											case WHITE:
												if(gm.getNumOfPlayers() == 2) {
													if(ca[0][0] < selectedPiece.getPosition()[0])
														castling_rook = status[7][0];
													else
														castling_rook = status[7][7];
												} else {
													if(ca[0][0] < selectedPiece.getPosition()[0])
														castling_rook = status[13][3];
													else
														castling_rook = status[13][10];
												}
												break;
											case GREEN:
												if(ca[0][1] < selectedPiece.getPosition()[1])
													castling_rook = status[3][13];
												else
													castling_rook = status[10][13];
												break;
										}

										move = board.movePiece(status, castling_rook, ca[1][0], ca[1][1]);
										moves.add(move);
										board.renderBoard(moves);
									}
								}
							}
						}
					}

					for(Player player : gm.getPlayers()) {
						if(gm.getRule().IsCheck(player)) {
							checkedColor.add(player.getColor());
						}
					}

					// 원래 색으로 복구
					for(Square[] line : board.getSquares()) {
						for(Square s : line) {
							if(s != null) {
								ChessPiece p = status[s.getPosition()[1]][s.getPosition()[0]];

								if(p instanceof King && checkedColor.contains(p.getColor()))
									s.setBackground(COLOR_CHECKED);
								else
									s.setBackground(s.getColor());
							}
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
				if(color != COLOR_MOVABLE && color != COLOR_CHECKED && color != COLOR_CASTLING)
					setBackground(new Color(color.getRed(), color.getBlue(), color.getGreen(), 150));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Color color = ((Square)e.getSource()).getBackground();
				if(color != COLOR_MOVABLE && color != COLOR_CHECKED && color != COLOR_CASTLING)
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