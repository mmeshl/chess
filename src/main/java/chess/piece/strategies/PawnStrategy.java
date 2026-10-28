package chess.piece.strategies;

import chess.Color;
import chess.Position;
import chess.board.Board;
import chess.piece.PieceName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class PawnStrategy extends PieceStrategy implements IPieceStrategy {
    private Boolean firstMove = true;

    public PawnStrategy() {
        super(PieceName.PAWN);
    }

    @Override
    public String toString() {
        return "\uDB82\uDC59";
    }

    @Override
    public Stream<Position> getValidMoves(Position position, Color color, Board board) {
        return Stream.concat(getNonCapturingMoves(position, color, board), getCapturingMoves(position, color, board))
                .filter(move -> !board.piecesBetween(position, move));
    }

    @Override
    public void doMove() {
        firstMove = false;
    }

    private Stream<Position> getNonCapturingMoves(Position position, Color color, Board board) {
        final Integer direction = getMoveDirection(color);
        final List<Position> moves =  new ArrayList<>();

        moves.add(position.offset(0, direction));
        if (firstMove) {
            moves.add(position.offset(0, direction * 2));
        }

        return moves.stream().filter(move -> board.inBounds(move) && !board.hasPiece(move));
    }

    private Stream<Position> getCapturingMoves(Position position, Color color, Board board) {
        final Integer direction = getMoveDirection(color);

        return Stream.of(position.offset(-1, direction), position.offset(+1, direction))
                .filter(move -> board.inBounds(move) && board.hasPiece(move, color.getOpposite()));
    }

    private Integer getMoveDirection(Color color) {
        return switch (color) {
            case WHITE -> +1;
            case BLACK -> -1;
        };
    }
}
