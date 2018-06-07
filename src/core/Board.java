package core;

import pieces.*;
import utils.Movement;
import views.ResultView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;


/**
 *
 * @author 신원준
 *
 */
public abstract class Board extends JPanel {

	protected int N;
    protected Square[][] squares;
    protected ChessPiece[][] status;

	private ChessPiece selectedPiece;

    public Board() {
		setLayout(new GridBagLayout());
		setBackground(new Color(0xD59759));
		setBorder(BorderFactory.createLineBorder(new Color(0x904A00), 5));

		initStatus();
	}

	public abstract void initStatus();



	// 보드판 렌더링
    public void renderBoard(List<Movement> moves) {
		RepaintManager.currentManager(this).markCompletelyDirty(this);

		for(Movement move : moves) {
			if(move.getType() == Movement.MoveType.MOVE) {
				int[] from = move.getFromPosition();
				int[] to = move.getToPosition();

				squares[from[1]][from[0]].setImage(null);
				squares[to[1]][to[0]].setImage(move.getChessPiece().getImage());

				status[to[1]][to[0]] = move.getChessPiece();
				status[from[1]][from[0]] = null;

				move.getChessPiece().setPosition(to[0], to[1]);
				move.getChessPiece().addMoveCount();
			}
			else if(move.getType() == Movement.MoveType.CHANGED) {
				int[] to = move.getToPosition();

				if(move.getChangedPiece() != null)
					squares[to[1]][to[0]].setImage(move.getChangedPiece().getImage());
				else {
					if(move.getChessPiece() instanceof King) {
						GameManager runningGame = GameManager.runningGame;
						Player deadPlayer = runningGame.getPlayer(move.getChessPiece().getColor());

						runningGame.killPlayer(deadPlayer);

						// 한 명만 죽었으면, 나머지 살아있는 사람에게 말 양도
						if(runningGame.getAlly(deadPlayer) != null
								&& runningGame.getAlly(deadPlayer).isAlive()) {

							for(ChessPiece[] p_line : status) {
								for(ChessPiece p : p_line) {
									if(p == null) continue;

									if(p.getColor() == deadPlayer.getColor())
										p.setColor(runningGame.getAlly(deadPlayer).getColor());

								}
							}
						}
					}
				}

				status[to[1]][to[0]] = move.getChangedPiece();

				// 바뀐 색 반영
				for(int y=0; y<squares.length; y++) {
					for(int x=0; x<squares[y].length; x++) {
						if(status[y][x] != null)
							squares[y][x].setImage(status[y][x].getImage());
					}
				}


			}
			else {
				return ;
			}
		}


		RepaintManager.currentManager(this).paintDirtyRegions();

		Player prevPlayer = GameManager.runningGame.getCurrentTurn();
		GameManager.runningGame.changeTurn();
		Player nextPlayer = GameManager.runningGame.getCurrentTurn();

		// 한 명만 살았을 때
		if(nextPlayer == null || prevPlayer == GameManager.runningGame.getAlly(nextPlayer)) {
			// 게임 뷰
			Container gameView = getParent().getParent();

			Container resultView = (this instanceof TwoPlayersBoard)
					? new ResultView(prevPlayer) : new ResultView(prevPlayer, GameManager.runningGame.getAlly(prevPlayer));

			Container parent = gameView.getParent();

			parent.add(resultView);
			parent.remove(gameView);

			GameManager.runningGame = null;

			parent.validate();
		}
	}

	// 말 이동
	public Movement movePiece(ChessPiece[][] status, ChessPiece piece, int toX, int toY) {
		int[] from = piece.getPosition();

		// 상태 변경
		status[from[1]][from[0]] = null;
		status[toY][toX] = piece;

		return new Movement(piece, from[0], from[1], toX, toY);
    }

    // 말을 보드에서 제거
    public Movement killPiece(ChessPiece[][] status, ChessPiece piece) {
    	int[] piecePosition = piece.getPosition();
    	Square targetSquare = squares[piecePosition[1]][piecePosition[0]];

    	// 삭제
    	targetSquare.removeAll();
    	status[piecePosition[1]][piecePosition[0]] = null;

    	return new Movement(piece, null);
    }

    // 말의 종류를 변경
    public Movement changePiece(ChessPiece[][] status, ChessPiece oldPiece, ChessPiece newPiece) {
		int[] piecePosition = oldPiece.getPosition();

		// 상태 변경
		status[piecePosition[1]][piecePosition[0]] = newPiece;

		return new Movement(oldPiece, newPiece);
    }

    public void setTurnLabel(JLabel label) {
    	for(Square[] line : squares) {
    		for(Square s : line) {
    			if(s == null) continue;

    			s.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(GameManager.runningGame != null) {
							String turn_color = GameManager.runningGame.getCurrentTurn().getColor().toString();
							label.setText("<html><font color='"+turn_color+"'>"+turn_color+"</font> TURN</html>");
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
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}
				});
			}
		}
	}

	public ChessPiece[][] getStatus() {
		return status.clone();
	}

	public ChessPiece getSelectedPiece() {
		return selectedPiece;
	}

	void setSelectedPiece(ChessPiece selectedPiece) {
		this.selectedPiece = selectedPiece;
	}

}