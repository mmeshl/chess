package chess.piece;

import chess.Color;
import chess.piece.strategies.IPieceStrategy;
import chess.piece.strategies.PieceStrategyFactory;

public final class PieceFactory {
    private final PieceStrategyFactory pieceStrategyFactory;

    public PieceFactory(PieceStrategyFactory pieceStrategyFactory) {
        this.pieceStrategyFactory = pieceStrategyFactory;
    }

    public Piece createPiece(PieceName pieceName, Color color) {
        return switch (pieceName) {
            case PAWN -> createPawn(color);
            case ROOK -> createRook(color);
            case KNIGHT -> createKnight(color);
            case BISHOP -> createBishop(color);
            case QUEEN -> createQueen(color);
            case KING -> createKing(color);
        };
    }

    public Piece createPawn(Color color) {
        return createPiece(color, pieceStrategyFactory.createPawnStrategy());
    }

    public Piece createRook(Color color) {
        return createPiece(color, pieceStrategyFactory.createRookStrategy());
    }

    public Piece createKnight(Color color) {
        return createPiece(color, pieceStrategyFactory.createKnightStrategy());
    }

    public Piece createBishop(Color color) {
        return createPiece(color, pieceStrategyFactory.createBishopStrategy());
    }

    public Piece createQueen(Color color) {
        return createPiece(color, pieceStrategyFactory.createQueenStrategy());
    }

    public Piece createKing(Color color) {
        return createPiece(color, pieceStrategyFactory.createKingStrategy());
    }

    private Piece createPiece(Color color, IPieceStrategy pieceStrategy) {
        return new Piece(color, pieceStrategy);
    }
}
