package com.assodepicche.minesweeper;

import com.assodepicche.minesweeper.model.Board;
import com.assodepicche.minesweeper.view.BoardCLI;

public final class App {
    public static void main(String[] args) throws Exception {
        new BoardCLI(new Board(3, 3, 1));
    }
}
