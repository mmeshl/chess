package chess.piece.strategies;

import chess.piece.PieceName;

public final class KingStrategy extends PieceStrategy implements IPieceStrategy {
    public KingStrategy() {
        super(PieceName.KING);
    }

    @Override
    public String toString() {
        return "\uDB82\uDC57";
    }
}
