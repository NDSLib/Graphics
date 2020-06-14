package com.ndsl.graphics.display.key;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.DisplayAttitude;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputHandler implements KeyListener {
    public Display display;
    public DisplayAttitude attitude;

    public KeyInputHandler(Display display){
        this.display=display;
        this.attitude=display.attitude;
        this.display.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
