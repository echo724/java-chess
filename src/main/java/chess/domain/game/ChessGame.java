package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.board.PieceProvider;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class ChessGame implements Game {
    
    public static final String GAME_HAS_NOT_STARTED = GAME_ERROR_PREFIX + "게임이 시작되지 않았습니다.";
    public static final String GAME_HAS_ALREADY_STARTED = GAME_ERROR_PREFIX + "게임이 이미 시작되었습니다.";
    
    private final Board board;
    private Color turn;
    private GameProgress progress;
    
    public ChessGame() {
        this.progress = GameProgress.READY;
        this.board = ChessBoard.create();
        this.turn = Color.WHITE;
    }
    
    @Override
    public void start() {
        if (this.isNotReady()) {
            throw new IllegalStateException(GAME_HAS_ALREADY_STARTED);
        }
        this.progress = GameProgress.RUN;
    }
    
    @Override
    public void move(final Position from, final Position to) {
        if (this.isNotRunning()) {
            throw new IllegalStateException(GAME_HAS_NOT_STARTED);
        }
        this.board.checkColor(from, to, this.turn);
        this.board.checkRoute(from, to);
        this.board.move(from, to);
        this.turn = Color.reverse(this.turn);
    }
    
    @Override
    public Status status() {
        if (this.isNotRunning()) {
            throw new IllegalStateException(GAME_HAS_NOT_STARTED);
        }
        return Status.from(this.board);
    }
    
    @Override
    public void end() {
        if (this.isNotRunning()) {
            throw new IllegalStateException(GAME_HAS_NOT_STARTED);
        }
        this.progress = GameProgress.END;
    }
    
    @Override
    public PieceProvider getBoard() {
        return this.board;
    }
    
    @Override
    public boolean isContinued() {
        return this.progress != GameProgress.END && !this.board.isKingDead();
    }
    
    @Override
    public boolean isEnd() {
        return this.progress == GameProgress.END;
    }
    
    @Override
    public boolean isOver() {
        return this.board.isKingDead();
    }
    
    public boolean isNotRunning() {
        return this.progress != GameProgress.RUN;
    }
    
    public boolean isNotReady() {
        return this.progress != GameProgress.READY;
    }
}

