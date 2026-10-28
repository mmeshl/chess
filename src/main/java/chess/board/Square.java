package chess.board;

import chess.Color;
import chess.piece.Piece;

import java.util.Objects;
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

    public Color getColor() {
        return color;
    }

    public Optional<Piece> getPiece() {
        return Optional.ofNullable(piece);
    }

    public Optional<Piece> placePiece(Piece piece) {
        final Optional<Piece> previousPiece = Optional.ofNullable(this.piece);
        this.piece = piece;
        return previousPiece;
    }

    public Boolean hasPiece() {
        return getPiece().isPresent();
    }

    public Boolean hasPiece(Color color) {
        return Objects.equals(getPiece().map(Piece::getColor).orElse(null), color);
    }

    public Piece removePiece() {
        final Piece removedPiece = getPiece().orElseThrow();
        piece = null;
        return removedPiece;
    }
}
