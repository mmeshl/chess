package chess.piece.strategies;

import chess.piece.PieceName;

public final class KnightStrategy extends PieceStrategy implements IPieceStrategy {
    public KnightStrategy() {
        super(PieceName.KNIGHT);
    }

    @Override
    public String toString() {
        return "\uDB82\uDC58";
    }
}
