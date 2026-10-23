package chess.board;

import chess.Color;
import chess.piece.Piece;

import java.util.Optional;

public final class Square {
    private final Color color;
    private Piece piece = null;

    public Square(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        final String innerContent = piece != null ? piece.toString() : " ";
        return String.format("%s %s %s", color.background(), innerContent, color.reset());
    }

    public Optional<Piece> getPiece() {
        return Optional.ofNullable(piece);
    }

    public void placePiece(Piece piece) {
        this.piece = piece;
    }
}
