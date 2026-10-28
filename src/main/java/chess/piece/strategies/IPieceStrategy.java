package chess.piece.strategies;

import chess.Color;
import chess.Position;
import chess.board.Board;

import java.util.stream.Stream;

public interface IPieceStrategy {
    Stream<Position> getValidMoves(Position position, Color color, Board board);

    default void doMove() {}
}
