import core.Board;
import core.GameManager;
import core.Square;
import core.TwoPlayersBoard;
import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import pieces.ChessPiece;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SquareTest {

	private static boolean[][] piecePosition = new boolean[][] {
			{false, true, false, false, true, false, true, false},
			{true, true, true, false, true, false, true, false},
			{false, true, false, true, true, false, true, false},
			{true, false, true, false, true, false, true, false},
			{false, false, false, true, true, false, false, false},
			{false, false, true, false, false, true, false, false},
			{false, false, false, false, false, false, false, false},
			{false, true, true, true, true, true, true, false},
	};

	private static void toggleSquareColor(Square square) {
		Color newColor = (square.getColor() == Square.COLOR_BRIGHT)
				? Square.COLOR_DARK : Square.COLOR_BRIGHT;

		square.setBackground(newColor);
	}

	private static void putPiece(JPanel container, boolean[][] position, ChessPieceSprite.ChessPieceSpriteType type) {
		for(Component c : container.getComponents()) {
			if(!(c instanceof Square))
				continue;
			Square square = (Square) c;
			int[] squarePos = square.getPosition();


			if(position[squarePos[1]][squarePos[0]])
				square.setImage(ChessPieceSprite.getInstace().getChessPiece(type));

			System.out.println("Pos: " + Arrays.toString(squarePos) + ", " + position[squarePos[0]][squarePos[1]]);
		}

	}


	public static void main(String[] args) {
		final int ROW = 8;
		final int COL = 8;
		final int squareLen = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / ROW;

		// Frame
		JFrame frame = new JFrame("SquareTest");
		frame.setSize(new Dimension(1280, 720));
		frame.getContentPane().setBackground(Color.WHITE);

		// Container
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		container.setSize(new Dimension(squareLen * ROW, squareLen * COL));

		GridBagConstraints c = new GridBagConstraints();

		JButton button = new JButton("Toggle");

		for(int i=0; i<ROW; i++) {
			for(int j=0; j<COL; j++) {
				Color color = ((i + j) % 2 == 0) ? Square.COLOR_BRIGHT : Square.COLOR_DARK;
				Square square = new Square(i, j, squareLen, color);
				square.setOpaque(true);

				c.gridx = i;
				c.gridy = j;

				container.add(square, c);
			}
		}

		putPiece(container, piecePosition, ChessPieceSprite.ChessPieceSpriteType.WHITE_KING);

		button.addActionListener(e -> {
			RepaintManager.currentManager(container).markCompletelyDirty(container);

			for(Component component : container.getComponents()) {
				Square square = (Square) component;
				toggleSquareColor(square);
			}

			RepaintManager.currentManager(container).paintDirtyRegions();
		});



		frame.getContentPane().add(container);
		frame.getContentPane().add(button);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}