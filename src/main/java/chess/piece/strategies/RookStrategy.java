package chess.piece.strategies;

import chess.piece.PieceName;

public final class RookStrategy extends PieceStrategy implements IPieceStrategy {
    public RookStrategy() {
        super(PieceName.ROOK);
    }

    @Override
    public String toString() {
        return "\uDB82\uDC5B";
    }
}
