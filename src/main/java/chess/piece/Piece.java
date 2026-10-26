package chess.piece;

import chess.Color;
import chess.piece.strategies.IPieceStrategy;

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
}
