package chess.piece.strategies;

import chess.piece.PieceName;

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
}
