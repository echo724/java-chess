package chess;

import chess.controller.ChessController;
import chess.view.InputView;
import chess.view.OutputView;

public class Applictaion {
    
    public static void main(String[] args) {
        ChessController chessController = new ChessController(new InputView(), new OutputView());
        chessController.execute();
    }
    
}
