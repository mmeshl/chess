package chess.board;

import chess.Color;
import chess.Position;
import chess.piece.Piece;

import java.util.List;
import java.util.Optional;

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

    public Optional<Piece> placePiece(Position position, Piece piece) {
        return getSquare(position).placePiece(piece);
    }

    public Boolean hasPiece(Position position) {
        return getSquare(position).hasPiece();
    }

    public Boolean hasPiece(Position position, Color color) {
        return getSquare(position).hasPiece(color);
    }

    public Boolean inBounds(Position position) {
        return inRowBounds(position) && inColumnBounds(position);
    }

    public List<List<Square>> getSquares() {
        return List.copyOf(squares);
    }

    public Optional<Piece> getPiece(Position position) {
        return getSquare(position).getPiece();
    }

    public Optional<Piece> movePiece(Position currentPosition, Position nextPosition) {
        return placePiece(nextPosition, removePiece(currentPosition));
    }

    private Square getSquare(Position position) {
        assert inBounds(position);
        return getRow(position).get(position.col());
    }

    private List<Square> getRow(Position position) {
        assert inRowBounds(position);
        return squares.get(position.row());
    }

    private Boolean inRowBounds(Position position) {
        return position.row() >= 0 && position.row() < squares.size();
    }

    private Boolean inColumnBounds(Position position) {
        return position.col() >= 0 && position.col() < getRow(position).size();
    }

    private Piece removePiece(Position position) {
        return getSquare(position).removePiece();
    }
}
