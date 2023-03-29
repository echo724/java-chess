package chess.command;

import chess.domain.game.ActionHandler;
import chess.domain.game.Game;
import chess.domain.game.Status;
import chess.history.History;

public interface Command {
    
    String COMMAND_ERROR_PREFIX = "[COMMAND ERROR] ";
    String INVALID_QUERY_ERROR_MESSAGE = " 명령어는 query를 지원하지 않습니다.";
    String INVALID_ARGUMENT_ERROR_MESSAGE = " 명령어는 인자를 입력할 수 없습니다.";
    String INVALID_EXECUTE_ERROR_MESSAGE = " 명령어는 update를 지원하지 않습니다.";
    String INVALID_ARGUMENT_COUNT_ERROR_MESSAGE = " 명령어는 인자를 2개만 가질 수 있습니다.";
    
    Status query(ActionHandler action);
    
    Game update(ActionHandler action);
    
    void addHistory(History history);
    
    CommandType getType();
    
}
