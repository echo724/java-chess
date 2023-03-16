package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BoardTest {
    
    @ParameterizedTest
    @ValueSource(strings = {"a1", "d2", "h8"})
    @DisplayName("빈 보드 생성 테스트")
    void create(String position) {
        Board board = Board.create();
        assertThat(board).extracting("board").asInstanceOf(InstanceOfAssertFactories.MAP)
                .containsEntry(Position.from(position), Empty.create());
    }
    
    @Test
    @DisplayName("보드 피스 생성 테스트")
    void initialize() {
        Board board = Board.create();
        board.initialize();
        List<String> positions = List.of("a1", "b1", "g7", "h8");
        List<Piece> pieces = List.of(Rook.create(Color.WHITE), Knight.create(Color.WHITE), Pawn.create(Color.BLACK),
                Rook.create(Color.BLACK));
        for (int i = 0; i < 4; i++) {
            assertThat(board).extracting("board").asInstanceOf(InstanceOfAssertFactories.MAP)
                    .containsEntry(Position.from(positions.get(i)), pieces.get(i));
        }
    }
    
    @Test
    @DisplayName("특정 위치의 같은 색깔 피스 가져오는 테스트")
    void get_piece() {
        Board board = Board.create();
        board.initialize();
        Position source = Position.from("e1");
        Color white = Color.WHITE;
        Piece piece = board.getPiece(source, white);
        assertThat(piece.getType()).isEqualTo(PieceType.KING);
        assertThat(piece.isSameColor(white)).isTrue();
    }
    
    @Test
    @DisplayName("특정 위치에 피스가 없을 경우")
    void no_piece() {
        Board board = Board.create();
        board.initialize();
        Position source = Position.from("e4");
        Color white = Color.WHITE;
        assertThatThrownBy(() -> board.getPiece(source, white)).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("특정 위치의 피스가 색깔이 다른 경우")
    void not_same_color_piece() {
        Board board = Board.create();
        board.initialize();
        Position source = Position.from("e1");
        Color black = Color.BLACK;
        assertThatThrownBy(() -> board.getPiece(source, black)).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("이동 경로에 다른 피스가 존재하지 않는 경우 에러가 발생하지 않는다")
    void check_route() {
        Board board = Board.create();
        board.initialize();
        assertDoesNotThrow(() -> board.checkRoute(Position.from("a2"), Position.from("a3")));
    }
    
    @Test
    @DisplayName("이동 경로에 다른 피스가 존재하는 경우 에러가 발생한다")
    void other_piece_in_route_error() {
        Board board = Board.create();
        board.initialize();
        assertThatThrownBy(() -> board.checkRoute(Position.from("a1"), Position.from("a2")))
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("목적지에 같은 색깔의 피스가 있습니다.")
    void same_color_piece_in_destination() {
        Board board = Board.create();
        board.initialize();
        Assertions.assertThatThrownBy(() -> board.checkColor(Position.from("a2"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }
    
}