package chess.board;

import chess.Color;

public final class SquareFactory {
    public Square createWhite() {
        return new Square(Color.WHITE);
    }

    public Square createBlack() {
        return new Square(Color.BLACK);
    }
}
