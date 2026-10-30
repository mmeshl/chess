package chess.piece;

import chess.Color;
import chess.Position;
import chess.board.Board;
import chess.piece.strategies.IPieceStrategy;

import java.util.List;
import java.util.Optional;

public final class Piece {
    private final Color color;
    private IPieceStrategy strategy;

    public Piece(Color color, IPieceStrategy strategy) {
        this.color = color;
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return String.format("%s%s", color.foreground(), strategy);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Piece other)) {
            return false;
        }

        return this.color.equals(other.color) && this.strategy.equals(other.strategy);
    }

    public Color getColor() {
        return color;
    }

    public List<Position> getValidMoves(Position position, Board board) {
        return strategy.getValidMoves(position, color, board).toList();
    }

    public Optional<Piece> move(Position currentPosition, Position nextPosition, Board board) {
        assert getValidMoves(currentPosition, board).contains(nextPosition);

        strategy.doMove();
        return board.movePiece(currentPosition, nextPosition);
    }
}
