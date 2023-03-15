package chess.domain.piece;

public class Pawn extends Piece {
    
    private Pawn(final Color color) {
        super(color, PieceType.PAWN);
    }
    
    public static Pawn create(Color color) {
        return new Pawn(color);
    }
}