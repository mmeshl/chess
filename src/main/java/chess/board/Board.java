package chess.board;

import chess.Position;
import chess.piece.Piece;

import java.util.List;

public final class Board {
    private final List<List<Square>> squares;

    public Board(List<List<Square>> squares) {
        this.squares = squares;
    }

    @Override
    public String toString() {
        return String.join("\n",
                squares.reversed().stream()
                        .map(row -> String.join("", row.stream().map(Square::toString).toList()))
                        .toList());
    }

    public void placePiece(Position position, Piece piece) {
        squares.get(position.row()).get(position.col()).placePiece(piece);
    }
}
