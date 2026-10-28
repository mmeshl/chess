package chess.piece.strategies;

import chess.Color;
import chess.Position;
import chess.board.Board;
import chess.piece.PieceName;

import java.util.stream.Stream;

public abstract class PieceStrategy {
    private final PieceName pieceName;

    protected PieceStrategy(PieceName pieceName) {
        this.pieceName = pieceName;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PieceStrategy other)) {
            return false;
        }

        return this.pieceName.equals(other.pieceName);
    }

    public Stream<Position> getValidMoves(Position position, Color color, Board board) {
        return Stream.empty();
    }
}
