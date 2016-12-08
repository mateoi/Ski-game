package com.mateoi.ski;

import javafx.scene.input.KeyCode;

public class HumanPlayer implements Player {

    public HumanPlayer() {
        // Nothing here
    }

    @Override
    public int move(SkiGame state) {
        int move = 0;
        if (SkiFXApp.keyPressed(KeyCode.RIGHT)) {
            move++;
        }
        if (SkiFXApp.keyPressed(KeyCode.LEFT)) {
            move--;
        }
        return move;
    }

}
