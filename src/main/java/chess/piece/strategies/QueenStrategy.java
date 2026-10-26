package chess.piece.strategies;

import chess.piece.PieceName;

public final class QueenStrategy extends PieceStrategy implements IPieceStrategy {
    public QueenStrategy() {
        super(PieceName.QUEEN);
    }

    @Override
    public String toString() {
        return "\uDB82\uDC5A";
    }
}
