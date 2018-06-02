package core;

import pieces.ChessPiece;

import java.awt.*;

public class FourPlayersBoard extends Board {

    public FourPlayersBoard(GameManager gameManager) {
        super(gameManager);
        this.width = 14;
		this.height = 14;

		setSize(new Dimension(Square.WIDTH * this.width, Square.HEIGHT * this.height));


		squares = new Square[this.width][this.height];
		status = new ChessPiece[this.height][this.width];

		GridBagConstraints c = new GridBagConstraints();

		for(int i=0; i<this.width; i++) {
			for(int j=0; j<this.height; j++) {
				// 사각형이 존재하지 않는 영역 skip
				if((i < 3 && (j < 3 || j >= this.width - 3))
						|| ((i >= this.height - 3 && (j < 3 || j >= this.width - 3)))) continue;

				// 사각형 생성
				Color squareColor = ((i + j) % 2 == 0) ? Square.COLOR_BRIGHT : Square.COLOR_DARK;
				squares[i][j] = new Square(i, j, squareColor);

				c.gridx = i;
				c.gridy = j;

				add(squares[i][j]);
			}
		}
    }

	@Override
	public void initStatus() {

	}

}