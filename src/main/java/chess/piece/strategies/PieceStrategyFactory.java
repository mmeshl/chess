package chess.piece.strategies;

public final class PieceStrategyFactory {
    public IPieceStrategy createPawnStrategy() {
        return new PawnStrategy();
    }

    public IPieceStrategy createRookStrategy() {
        return new RookStrategy();
    }

    public IPieceStrategy createKnightStrategy() {
        return new KnightStrategy();
    }

    public IPieceStrategy createBishopStrategy() {
        return new BishopStrategy();
    }

    public IPieceStrategy createQueenStrategy() {
        return new QueenStrategy();
    }

    public IPieceStrategy createKingStrategy() {
        return new KingStrategy();
    }
}
