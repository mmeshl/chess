package chess.piece.strategies;

import chess.piece.PieceName;

public final class BishopStrategy extends PieceStrategy implements IPieceStrategy {
    public BishopStrategy() {
        super(PieceName.BISHOP);
    }

    @Override
    public String toString() {
        return "\uDB82\uDC5C";
    }
}
