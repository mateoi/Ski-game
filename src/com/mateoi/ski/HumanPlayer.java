package com.mateoi.ski;

import javafx.scene.input.KeyCode;

/**
 * A Player that is controlled by keyboard input
 *
 * @author mateo
 *
 */
public class HumanPlayer implements Player {

    public HumanPlayer() {
        // Nothing here
    }

    /**
     * Moves the paddle according to the keys that are currently pressed. Does
     * not use the game state at all as this is a human input, so the state can
     * be anything.
     */
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
